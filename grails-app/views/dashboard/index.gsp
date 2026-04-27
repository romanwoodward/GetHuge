<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Dashboard</title>
</head>
<body>
<div class="row g-4 mb-4">
    <div class="col-12">
        <div class="d-flex flex-column flex-md-row align-items-md-end justify-content-between gap-3">
            <div>
                <p class="text-uppercase text-body-secondary small mb-1">Signed in</p>
                <h1 class="h3 mb-0">${currentUser}</h1>
            </div>
            <div class="d-flex gap-2">
                <g:link controller="exercise" action="create" class="btn btn-outline-primary">
                    <i class="bi bi-plus-lg me-1"></i>Exercise
                </g:link>
                <g:link controller="workoutSession" action="create" class="btn btn-primary">
                    <i class="bi bi-plus-lg me-1"></i>Workout
                </g:link>
            </div>
        </div>
    </div>
</div>

<div class="row g-3 mb-4">
    <div class="col-12 col-md-6">
        <div class="card shadow-sm">
            <div class="card-body">
                <div class="text-body-secondary small">Exercises</div>
                <div class="display-6 fw-semibold">${exerciseCount}</div>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-6">
        <div class="card shadow-sm">
            <div class="card-body">
                <div class="text-body-secondary small">Workout sessions</div>
                <div class="display-6 fw-semibold">${workoutSessionCount}</div>
            </div>
        </div>
    </div>
</div>

<div class="row g-4">
    <div class="col-12 col-lg-6">
        <div class="card shadow-sm h-100">
            <div class="card-body">
                <div class="d-flex align-items-center justify-content-between mb-3">
                    <h2 class="h5 mb-0">Recent exercises</h2>
                    <g:link controller="exercise">View all</g:link>
                </div>
                <g:if test="${recentExercises}">
                    <div class="list-group list-group-flush">
                        <g:each in="${recentExercises}" var="exercise">
                            <g:link controller="exercise" action="show" id="${exercise.id}" class="list-group-item list-group-item-action px-0">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <div class="fw-semibold">${exercise.name}</div>
                                        <div class="small text-body-secondary">${exercise.primaryMuscleGroup}</div>
                                    </div>
                                    <span class="small text-body-secondary">${exercise.equipment ?: 'Bodyweight'}</span>
                                </div>
                            </g:link>
                        </g:each>
                    </div>
                </g:if>
                <g:else>
                    <p class="text-body-secondary mb-0">No exercises yet.</p>
                </g:else>
            </div>
        </div>
    </div>

    <div class="col-12 col-lg-6">
        <div class="card shadow-sm h-100">
            <div class="card-body">
                <div class="d-flex align-items-center justify-content-between mb-3">
                    <h2 class="h5 mb-0">Recent workouts</h2>
                    <g:link controller="workoutSession">View all</g:link>
                </div>
                <g:if test="${recentWorkoutSessions}">
                    <div class="list-group list-group-flush">
                        <g:each in="${recentWorkoutSessions}" var="workoutSession">
                            <g:link controller="workoutSession" action="show" id="${workoutSession.id}" class="list-group-item list-group-item-action px-0">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <div class="fw-semibold">${workoutSession.title}</div>
                                        <div class="small text-body-secondary">
                                            ${workoutSession.performedOn?.format('MMM d, yyyy')}
                                        </div>
                                    </div>
                                    <span class="small text-body-secondary">
                                        ${workoutSession.durationMinutes ?: 0} min
                                    </span>
                                </div>
                            </g:link>
                        </g:each>
                    </div>
                </g:if>
                <g:else>
                    <p class="text-body-secondary mb-0">No workout sessions yet.</p>
                </g:else>
            </div>
        </div>
    </div>
</div>
</body>
</html>
