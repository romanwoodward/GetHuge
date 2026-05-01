<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Complete your profile</title>
</head>
<body>
<div class="row justify-content-center">
    <div class="col-12 col-lg-8 col-xl-7">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4 p-md-5">
                <div class="mb-4">
                    <p class="text-uppercase text-body-secondary small mb-1">Almost there</p>
                    <h1 class="h3 fw-bold mb-2">Complete your profile</h1>
                    <p class="text-body-secondary mb-0">
                        We need a few details to personalize your dashboard and track your weekly training goal.
                    </p>
                </div>

                <g:hasErrors bean="${profileCompletionCommand}">
                    <div class="alert alert-danger">
                        <g:renderErrors bean="${profileCompletionCommand}" as="list"/>
                    </div>
                </g:hasErrors>

                <g:form controller="profile" action="save" method="POST">
                    <div class="row g-3">
                        <div class="col-12 col-md-6">
                            <label class="form-label" for="firstName">First name</label>
                            <g:textField name="firstName" value="${profileCompletionCommand?.firstName}" class="form-control"
                                         placeholder="Jane"/>
                        </div>
                        <div class="col-12 col-md-6">
                            <label class="form-label" for="lastName">Last name</label>
                            <g:textField name="lastName" value="${profileCompletionCommand?.lastName}" class="form-control"
                                         placeholder="Doe"/>
                        </div>
                        <div class="col-12">
                            <label class="form-label" for="weeklyWorkoutGoal">Workouts per week</label>
                            <g:field type="number" name="weeklyWorkoutGoal"
                                     value="${profileCompletionCommand?.weeklyWorkoutGoal}"
                                     class="form-control" min="1" max="21" placeholder="3"/>
                            <div class="form-text">
                                We'll use this to show your weekly progress on the dashboard.
                            </div>
                        </div>
                    </div>

                    <div class="d-flex flex-column flex-sm-row gap-2 mt-4">
                        <button type="submit" class="btn btn-primary">Save profile</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
