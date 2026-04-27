<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${exercise.name}</title>
</head>
<body>
<div class="d-flex justify-content-between align-items-center mb-3">
    <div>
        <h1 class="h3 mb-1">${exercise.name}</h1>
        <p class="text-body-secondary mb-0">${exercise.primaryMuscleGroup}</p>
    </div>
    <div class="btn-group">
        <g:link action="edit" id="${exercise.id}" class="btn btn-outline-primary">Edit</g:link>
        <g:form action="delete" id="${exercise.id}" method="POST" style="display:inline">
            <button type="submit" class="btn btn-outline-danger">Delete</button>
        </g:form>
    </div>
</div>

<div class="card shadow-sm mb-3">
    <div class="card-body">
        <dl class="row mb-0">
            <dt class="col-sm-3">Equipment</dt>
            <dd class="col-sm-9">${exercise.equipment ?: 'Bodyweight'}</dd>
            <dt class="col-sm-3">Description</dt>
            <dd class="col-sm-9">${exercise.description ?: 'No description yet.'}</dd>
        </dl>
    </div>
</div>
</body>
</html>
