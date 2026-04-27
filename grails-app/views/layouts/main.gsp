<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><g:layoutTitle default="Grails"/></title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-expand-lg bg-body border-bottom">
    <div class="container-lg">
        <a class="navbar-brand fw-semibold" href="${createLink(controller: 'dashboard')}">GetHuge</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <g:link controller="dashboard" class="nav-link">Dashboard</g:link>
                </li>
                <li class="nav-item">
                    <g:link controller="exercise" class="nav-link">Exercises</g:link>
                </li>
                <li class="nav-item">
                    <g:link controller="workoutSession" class="nav-link">Workouts</g:link>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="bg-body-tertiary min-vh-100">
    <div class="container-lg py-4">
        <g:layoutBody/>
    </div>
</main>

<div id="spinner" class="position-absolute top-0 end-0 p-1" style="display:none;">
    <div class="spinner-border spinner-border-sm" role="status">
        <span class="visually-hidden">Loading...</span>
    </div>
</div>
<asset:javascript src="application.js"/>
</body>
</html>
