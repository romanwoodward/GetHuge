package com.example

class ExerciseSet {

    WorkoutSession workoutSession
    Exercise exercise
    Integer setNumber
    Integer reps
    BigDecimal weight
    String unit = 'lb'
    Boolean completed = true
    String notes

    Date dateCreated
    Date lastUpdated

    static belongsTo = [workoutSession: WorkoutSession]

    static constraints = {
        exercise nullable: false
        setNumber nullable: false, min: 1
        reps nullable: false, min: 1
        weight nullable: false, min: 0.0G, scale: 2
        unit blank: false, inList: ['lb', 'kg']
        completed nullable: false
        notes nullable: true, maxSize: 500
    }

    static mapping = {
        notes type: 'text'
        weight scale: 2
        sort setNumber: 'asc'
    }

    String toString() {
        "${exercise?.name} set ${setNumber}: ${reps} x ${weight}${unit}"
    }
}
