<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<table id="emplTable" class="table table-striped">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Second Name</th>
				<sec:authorize url="/employee-list">
					<th>Salary</th>
				</sec:authorize>
				<th>Position</th>
				<th>Started work</th>
				<sec:authorize url="/employee">
					<th class="no_name"></th>
					<th class="no_name"></th>
					<th class="no_name"></th>
					<th class="no_name"></th>
					<th class="no_name"></th>
					<th class="no_name"></th>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${Employeess}" var="employee">
				<tr>
					<td>${employee.firstName}</td>
					<td>${employee.secondName}</td>
					<sec:authorize url="/employee-list">
						<td>${employee.salary}</td>
					</sec:authorize>
					<td>${employee.position}</td>
					<td><fmt:formatDate value="${employee.workStart}"
							pattern="yyyy/MM/dd" /></td>
					</td>
					<sec:authorize url="/employee">
						<td><a type="button" class="btn btn-success"
							href="/employee/add-project-employee?id=${employee.id}">ADD
								PROJECT</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/employee/delete-project-employee?id=${employee.id}">DELETE
								PROJECT</a></td>
						<td><a type="button" class="btn btn-success"
							href="/employee/add-role-employee?id=${employee.id}">ADD ROLE</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/employee/delete-role-employee?id=${employee.id}">DELETE
								ROLE</a></td>
						<td><a type="button" class="btn btn-success"
							href="/employee/update-employee?id=${employee.id}&contId=${employee.contacts.id}">UPDATE</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/employee/delete-employee?id=${employee.id}"
							onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">DELETE</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a type="button" style="margin-bottom: 50px;" class="btn btn-success"
		href="/employee/add-employee">Add Employee</a>
	</td>
</div>
<%@ include file="common/footer.jspf"%>