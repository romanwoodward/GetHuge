package com.example

import grails.plugin.springsecurity.SpringSecurityService

abstract class AuthenticatedController {

    SpringSecurityService springSecurityService

    protected User currentUser() {
        springSecurityService.currentUser as User
    }
}
