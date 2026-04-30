package com.example

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = ['provider', 'socialId'])
@ToString(includes = ['provider', 'socialId'], includeNames = true, includePackage = false)
class OAuthID implements Serializable {

    private static final long serialVersionUID = 1

    String provider
    String accessToken
    String refreshToken
    String socialId
    String screenName

    User user

    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User]

    static constraints = {
        provider blank: false, maxSize: 50
        accessToken nullable: true, maxSize: 4000
        refreshToken nullable: true, maxSize: 4000
        socialId blank: false, maxSize: 255
        screenName nullable: true, maxSize: 255
        user nullable: false
    }

    static mapping = {
        table 'oauth_id'
        version false
    }
}
