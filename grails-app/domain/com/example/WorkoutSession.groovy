package com.example

class WorkoutSession {

    User user
    String title
    Date performedOn = new Date()
    Integer durationMinutes
    String notes

    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User]
    static hasMany = [exerciseSets: ExerciseSet]

    static constraints = {
        title blank: false, maxSize: 120
        performedOn nullable: false
        durationMinutes nullable: true, min: 1
        notes nullable: true, maxSize: 2000
    }

    static mapping = {
        notes type: 'text'
        exerciseSets cascade: 'all-delete-orphan'
        sort performedOn: 'desc'
    }

    String toString() {
        "${title} (${performedOn.format('yyyy-MM-dd')})"
    }
}
