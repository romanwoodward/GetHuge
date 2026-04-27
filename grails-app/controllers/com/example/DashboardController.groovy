package com.example

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class DashboardController {

    def index() {
        render 'Authenticated workout dashboard'
    }
}
