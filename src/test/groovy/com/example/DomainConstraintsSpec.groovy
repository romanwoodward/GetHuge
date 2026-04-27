package com.example

import grails.testing.gorm.DataTest
import spock.lang.Specification

class DomainConstraintsSpec extends Specification implements DataTest {

    @Override
    Class<?>[] getDomainClassesToMock() {
        [User, WorkoutSession, Exercise, ExerciseSet] as Class<?>[]
    }

    void "user requires core identity fields and basic auth flags default correctly"() {
        when:
        def user = new User(
                username: 'lifter1',
                email: 'lifter1@example.com',
                password: 'strongpass',
                displayName: 'Lifter One'
        )

        def invalidUser = new User(
                username: '',
                email: 'not-an-email',
                password: 'short',
                displayName: ''
        )

        then:
        user.validate()
        user.enabled
        !user.accountLocked
        !user.passwordExpired

        !invalidUser.validate()
        invalidUser.errors.getFieldError('username')
        invalidUser.errors.getFieldError('email')
        invalidUser.errors.getFieldError('password')
        invalidUser.errors.getFieldError('displayName')
    }

    void "workout session allows optional notes and duration"() {
        given:
        def user = new User(
                username: 'coach',
                email: 'coach@example.com',
                password: 'strongpass',
                displayName: 'Coach'
        )

        when:
        def session = new WorkoutSession(
                user: user,
                title: 'Push Day',
                performedOn: new Date()
        )

        then:
        session.validate()
    }

    void "exercise set enforces positive set metrics and supported units"() {
        given:
        def exercise = new Exercise(name: 'Bench Press', primaryMuscleGroup: 'Chest')
        def session = new WorkoutSession(
                user: new User(
                        username: 'sam',
                        email: 'sam@example.com',
                        password: 'strongpass',
                        displayName: 'Sam'
                ),
                title: 'Upper Body',
                performedOn: new Date()
        )

        when:
        def invalidSet = new ExerciseSet(
                workoutSession: session,
                exercise: exercise,
                setNumber: 0,
                reps: 0,
                weight: -10.0G,
                unit: 'stone'
        )

        then:
        !invalidSet.validate()
        invalidSet.errors.getFieldError('setNumber')
        invalidSet.errors.getFieldError('reps')
        invalidSet.errors.getFieldError('weight')
        invalidSet.errors.getFieldError('unit')
    }
}
