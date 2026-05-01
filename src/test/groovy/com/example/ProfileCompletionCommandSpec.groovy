package com.example

import spock.lang.Specification

class ProfileCompletionCommandSpec extends Specification {

    void "profile completion requires a first name last name and weekly goal"() {
        when:
        def command = new ProfileCompletionCommand(
                firstName: '',
                lastName: null,
                weeklyWorkoutGoal: 0
        )

        then:
        !command.validate()
        command.errors.getFieldError('firstName')
        command.errors.getFieldError('lastName')
        command.errors.getFieldError('weeklyWorkoutGoal')
    }

    void "profile completion accepts valid input"() {
        expect:
        new ProfileCompletionCommand(
                firstName: 'Jordan',
                lastName: 'Miles',
                weeklyWorkoutGoal: 4
        ).validate()
    }
}
