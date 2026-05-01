package com.example

import grails.plugin.springsecurity.annotation.Secured

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

@Secured(['ROLE_USER'])
class DashboardController extends AuthenticatedController {

    def index() {
        User me = currentUser()
        if (requiresProfileCompletion(me)) {
            redirect controller: 'profile', action: 'complete'
            return
        }

        ZoneId zone = ZoneId.systemDefault()
        LocalDate startOfWeekDate = LocalDate.now(zone).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        Date startOfWeek = Date.from(startOfWeekDate.atStartOfDay(zone).toInstant())
        Date startOfNextWeek = Date.from(startOfWeekDate.plusWeeks(1).atStartOfDay(zone).toInstant())
        long weeklyWorkoutCount = (WorkoutSession.executeQuery(
                'select count(ws.id) from WorkoutSession ws where ws.user = :user and ws.performedOn >= :startOfWeek and ws.performedOn < :startOfNextWeek',
                [user: me, startOfWeek: startOfWeek, startOfNextWeek: startOfNextWeek]
        )[0] as Number).longValue()
        int weeklyWorkoutGoal = me.weeklyWorkoutGoal ?: 0
        int weeklyWorkoutProgressPercent = weeklyWorkoutGoal > 0 ? Math.min(100, Math.round((weeklyWorkoutCount * 100.0) / weeklyWorkoutGoal) as int) : 0

        [
                currentUser                  : me,
                exerciseCount                : Exercise.countByOwner(me),
                workoutSessionCount          : WorkoutSession.countByUser(me),
                weeklyWorkoutCount           : weeklyWorkoutCount,
                weeklyWorkoutGoal            : weeklyWorkoutGoal,
                weeklyWorkoutProgressPercent : weeklyWorkoutProgressPercent,
                recentExercises              : Exercise.where { owner == me }.list(max: 5, sort: 'lastUpdated', order: 'desc'),
                recentWorkoutSessions        : WorkoutSession.where { user == me }.list(max: 5, sort: 'performedOn', order: 'desc')
        ]
    }

    private boolean requiresProfileCompletion(User user) {
        OAuthID.findByUserAndProvider(user, 'google') && !user.profileComplete
    }
}
