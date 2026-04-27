package com.example

class Exercise {

    User owner
    String name
    String primaryMuscleGroup
    String equipment
    String description

    Date dateCreated
    Date lastUpdated

    static hasMany = [exerciseSets: ExerciseSet]
    static belongsTo = [owner: User]

    static constraints = {
        owner nullable: false
        name blank: false, unique: 'owner', maxSize: 120
        primaryMuscleGroup blank: false, maxSize: 50
        equipment nullable: true, maxSize: 50
        description nullable: true, maxSize: 1000
    }

    static mapping = {
        description type: 'text'
        sort name: 'asc'
    }

    String toString() {
        name
    }
}
