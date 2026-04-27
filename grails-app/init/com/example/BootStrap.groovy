package com.example

import grails.plugin.springsecurity.SpringSecurityService
import grails.util.Environment

class BootStrap {

    SpringSecurityService springSecurityService

    def init = {
        if (Environment.current in [Environment.DEVELOPMENT, Environment.TEST]) {
            seedSecurityData()
        }
    }

    def destroy = {
    }

    private void seedSecurityData() {
        Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        User admin = User.findByUsername('admin')
        if (!admin) {
            admin = new User(
                    username: 'admin',
                    password: springSecurityService.encodePassword('ChangeMe123!'),
                    email: 'admin@gethuge.local',
                    displayName: 'Admin',
                    enabled: true
            ).save(failOnError: true)
        }

        if (!UserRole.exists(admin.id, userRole.id)) {
            UserRole.create(admin, userRole, true)
        }
        if (!UserRole.exists(admin.id, adminRole.id)) {
            UserRole.create(admin, adminRole, true)
        }
    }
}
