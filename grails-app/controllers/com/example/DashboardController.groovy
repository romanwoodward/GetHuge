package com.example

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class DashboardController extends AuthenticatedController {

    def index() {
        User me = currentUser()
        [
                currentUser         : me,
                exerciseCount       : Exercise.countByOwner(me),
                workoutSessionCount  : WorkoutSession.countByUser(me),
                recentExercises      : Exercise.where { owner == me }.list(max: 5, sort: 'lastUpdated', order: 'desc'),
                recentWorkoutSessions: WorkoutSession.where { user == me }.list(max: 5, sort: 'performedOn', order: 'desc')
        ]
    }
}
