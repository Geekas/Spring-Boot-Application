<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="col">
			<form method="post">
				<form:hidden path="project.id" />
				<fieldset class="form-group">
					<form:label path="project.name">Project Name</form:label>
					<form:input path="project.name" name="name" type="text"
						class="form-control" />
					<form:errors path="project.name" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="project.description">Description</form:label>
					<form:input path="project.description" name="description"
						type="text" class="form-control" />
					<form:errors path="project.description" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="project.budget">Budget</form:label>
					<form:input path="project.budget" name="budget" type="number"
						class="form-control" />
					<form:errors path="project.budget" cssClass="text-warning" />
				</fieldset>

				<fieldset>
					<button type="submit" class="btn btn-success">${action}
						Project</button>
					<a type="button" class="btn btn-info" href="/projects">Cancel</a>
				</fieldset>

			</form>

		</div>

	</div>

</div>
<%@ include file="common/footer.jspf"%>