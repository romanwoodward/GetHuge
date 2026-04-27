package com.example

class User {

    String username
    String email
    String password
    String displayName

    Boolean enabled = true
    Boolean accountLocked = false
    Boolean passwordExpired = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [workoutSessions: WorkoutSession]

    static constraints = {
        username blank: false, unique: true, size: 3..50
        email blank: false, unique: true, email: true, maxSize: 255
        password blank: false, size: 8..255
        displayName blank: false, maxSize: 100
        enabled nullable: false
        accountLocked nullable: false
        passwordExpired nullable: false
    }

    static mapping = {
        password column: '`password`'
        sort dateCreated: 'desc'
    }

    String toString() {
        displayName ?: username
    }
}
