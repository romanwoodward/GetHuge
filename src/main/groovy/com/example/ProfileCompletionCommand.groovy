package com.example

import grails.validation.Validateable

class ProfileCompletionCommand implements Validateable {

    String firstName
    String lastName
    Integer weeklyWorkoutGoal

    static constraints = {
        firstName blank: false, maxSize: 50
        lastName blank: false, maxSize: 50
        weeklyWorkoutGoal nullable: false, min: 1, max: 21
    }
}
