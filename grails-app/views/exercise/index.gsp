<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Exercises</title>
</head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h1 class="h3 mb-1">Exercises</h1>
        <p class="text-body-secondary mb-0">Exercises you own.</p>
    </div>
    <g:link action="create" class="btn btn-primary">
        <i class="bi bi-plus-lg me-1"></i>New exercise
    </g:link>
</div>

<div class="card shadow-sm">
    <div class="card-body p-0">
        <table class="table mb-0 align-middle">
            <thead>
            <tr>
                <th>Name</th>
                <th>Muscle group</th>
                <th>Equipment</th>
                <th class="text-end">Actions</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${exerciseList}">
                <g:each in="${exerciseList}" var="exercise">
                    <tr>
                        <td>${exercise.name}</td>
                        <td>${exercise.primaryMuscleGroup}</td>
                        <td>${exercise.equipment ?: 'Bodyweight'}</td>
                        <td class="text-end">
                            <div class="btn-group btn-group-sm" role="group">
                                <g:link action="show" id="${exercise.id}" class="btn btn-outline-secondary">View</g:link>
                                <g:link action="edit" id="${exercise.id}" class="btn btn-outline-primary">Edit</g:link>
                            </div>
                        </td>
                    </tr>
                </g:each>
            </g:if>
            <g:else>
                <tr>
                    <td colspan="4" class="text-body-secondary p-4">No exercises yet.</td>
                </tr>
            </g:else>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
