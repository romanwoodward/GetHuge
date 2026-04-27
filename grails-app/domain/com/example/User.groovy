package com.example

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    String displayName

    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [workoutSessions: WorkoutSession]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        username blank: false, unique: true, size: 3..50
        password blank: false, password: true
        email blank: false, unique: true, email: true, maxSize: 255
        displayName blank: false, maxSize: 100
    }

    static mapping = {
        password column: '`password`'
        sort dateCreated: 'desc'
    }
}
