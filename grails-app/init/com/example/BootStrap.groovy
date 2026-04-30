package com.example

import grails.plugin.springsecurity.oauth2.SpringSecurityOauth2BaseService
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.google.GoogleOAuth2Service
import grails.plugin.springsecurity.SpringSecurityService
import grails.util.Environment
import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    SpringSecurityService springSecurityService
    SpringSecurityOauth2BaseService springSecurityOauth2BaseService
    GoogleOAuth2Service googleOAuth2Service

    def init = {
        registerOAuth2Providers()
        if (Environment.current in [Environment.DEVELOPMENT, Environment.TEST]) {
            seedSecurityData()
        }
    }

    def destroy = {
    }

    private void registerOAuth2Providers() {
        try {
            springSecurityOauth2BaseService.registerProvider(googleOAuth2Service)
        } catch (OAuth2Exception exception) {
            log.error('Unable to register Google OAuth2 provider', exception)
        }
    }

    private void seedSecurityData() {
        Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        User admin = User.findByUsername('admin')
        if (!admin) {
            admin = new User(
                    username: 'admin',
                    password: springSecurityService.encodePassword('ChangeMe123!'),
                    email: 'admin@gethugeapp.com',
                    displayName: 'Admin',
                    enabled: true
            ).save(failOnError: true)
        }

        if (!UserRole.exists(admin.id, userRole.id)) {
            UserRole.create(admin, userRole)
        }
        if (!UserRole.exists(admin.id, adminRole.id)) {
            UserRole.create(admin, adminRole)
        }
    }
}
