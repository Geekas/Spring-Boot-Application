<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<table id="projTable" class="table table-striped">
		<thead>
			<tr>
				<th>Project Name</th>
				<th>Budget</th>
				<th class="no_name"></th>
				<th class="no_name"></th>
				<th class="no_name"></th>
				<th class="no_name"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${projects}" var="project">
				<tr>
					<td>${project.name}</td>
					<td>${project.budget}</td>
					<td><a type="button" class="btn btn-success"
						href="/projects/project-add-employee?id=${project.id}">Add Employee</a></td>
					<td><a type="button" class="btn btn-success"
						href="/projects/project-remove-employee?id=${project.id}">Remove
							Employee</a></td>
					<td><a type="button" class="btn btn-success"
						href="/projects/project-update?id=${project.id}">Update</a></td>
					<td><a type="button" class="btn btn-warning"
						href="/projects/project-delete?id=${project.id}"
						onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a type="button" style="margin-bottom: 50px;" class="btn btn-success"
		href="/projects/new-project">Add Project</a>
</div>
<%@ include file="common/footer.jspf"%>