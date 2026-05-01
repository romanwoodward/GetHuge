package com.example

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

import java.nio.charset.StandardCharsets

@Slf4j
@Secured('permitAll')
class GoogleOAuth2Controller extends AuthenticatedController {

    SpringSecurityService springSecurityService

    @Transactional
    def complete() {
        String sessionKey = 'OAuth2:access-t:google'
        def accessToken = session[sessionKey]

        if (!accessToken) {
            flash.error = 'Google sign-in expired. Please try again.'
            redirect(controller: 'login', action: 'auth')
            return
        }

        Map profile = fetchGoogleProfile(accessToken.accessToken as String)
        String email = profile.email as String
        String firstName = profile.given_name as String
        String lastName = profile.family_name as String
        String displayName = (profile.name ?: [firstName, lastName].findAll { it?.trim() }.join(' ') ?: email) as String

        if (!email) {
            flash.error = 'Google did not return an email address.'
            redirect(controller: 'login', action: 'auth')
            return
        }

        User user = User.findByEmail(email) ?: User.findByUsername(email)
        boolean newlyCreated = false
        String rawPassword = UUID.randomUUID().toString()

        if (!user) {
            user = new User(
                    username: buildUsername(email),
                    email: email,
                    displayName: displayName,
                    firstName: firstName,
                    lastName: lastName,
                    enabled: true,
                    password: rawPassword
            )
            newlyCreated = true
        } else {
            user.email = user.email ?: email
            user.displayName = user.displayName ?: displayName
            user.firstName = user.firstName ?: firstName
            user.lastName = user.lastName ?: lastName
        }

        if (!user.save(flush: true)) {
            log.warn('Unable to create or update local user for Google sign-in {}', email)
            flash.error = 'We could not finish creating your account.'
            redirect(controller: 'login', action: 'auth')
            return
        }

        Role userRole = Role.findOrSaveByAuthority('ROLE_USER')
        if (!UserRole.exists(user.id, userRole.id)) {
            UserRole.create(user, userRole, true)
        }

        OAuthID oauthId = OAuthID.findByProviderAndSocialId('google', email) ?: new OAuthID(
                provider: 'google',
                socialId: email,
                screenName: displayName,
                user: user
        )
        oauthId.accessToken = accessToken.accessToken as String
        oauthId.refreshToken = accessToken.refreshToken as String
        oauthId.user = user
        if (!oauthId.save(flush: true)) {
            log.warn('Unable to save Google OAuth identity for {}', email)
            flash.error = 'We could not finish creating your account.'
            redirect(controller: 'login', action: 'auth')
            return
        }

        if (newlyCreated) {
            log.info('Created local user {} from Google sign-in', user.username)
        }

        springSecurityService.reauthenticate(user.username)
        session.removeAttribute(sessionKey)
        if (user.profileComplete) {
            redirect(uri: '/dashboard')
        } else {
            redirect(controller: 'profile', action: 'complete')
        }
    }

    private Map fetchGoogleProfile(String bearerToken) {
        HttpURLConnection connection = new URL('https://www.googleapis.com/oauth2/v2/userinfo').openConnection() as HttpURLConnection
        connection.setRequestProperty('Authorization', "Bearer ${bearerToken}")
        connection.setRequestProperty('Accept', 'application/json')
        connection.connectTimeout = 10_000
        connection.readTimeout = 10_000

        int status = connection.responseCode
        InputStream responseStream = status >= 200 && status < 300 ? connection.inputStream : connection.errorStream
        String responseBody = responseStream?.getText(StandardCharsets.UTF_8.name()) ?: '{}'

        if (status < 200 || status >= 300) {
            log.warn('Google userinfo request failed with status {}: {}', status, responseBody)
            throw new IllegalStateException('Unable to read Google profile')
        }

        new JsonSlurper().parseText(responseBody) as Map
    }

    private String buildUsername(String email) {
        String base = (email ?: 'google-user').take(50)
        if (base.size() < 3) {
            base = "user-${Math.abs(email?.hashCode() ?: 0)}".take(50)
        }

        String candidate = base
        int suffix = 1
        while (User.findByUsername(candidate)) {
            String marker = "-${suffix++}"
            candidate = base.take(Math.max(0, 50 - marker.size())) + marker
        }
        candidate
    }
}
