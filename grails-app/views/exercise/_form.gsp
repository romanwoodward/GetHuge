<g:hasErrors bean="${exercise}">
    <div class="alert alert-danger">
        <g:renderErrors bean="${exercise}" as="list"/>
    </div>
</g:hasErrors>

<g:form controller="exercise" action="${exercise?.id ? 'update' : 'save'}" id="${exercise?.id}" method="POST" class="card shadow-sm">
    <div class="card-body">
        <div class="mb-3">
            <label class="form-label" for="name">Name</label>
            <g:textField name="name" value="${exercise?.name}" class="form-control"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="primaryMuscleGroup">Primary muscle group</label>
            <g:textField name="primaryMuscleGroup" value="${exercise?.primaryMuscleGroup}" class="form-control"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="equipment">Equipment</label>
            <g:textField name="equipment" value="${exercise?.equipment}" class="form-control"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="description">Description</label>
            <g:textArea name="description" value="${exercise?.description}" rows="4" class="form-control"/>
        </div>
        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary">Save</button>
            <g:link action="index" class="btn btn-outline-secondary">Cancel</g:link>
        </div>
    </div>
</g:form>
