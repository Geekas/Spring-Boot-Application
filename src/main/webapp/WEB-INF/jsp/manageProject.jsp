<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<c:set var="getAction" value="${action}" />
	<c:set var="conv_action" value="${fn:toLowerCase(getAction)}" />
	<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="col">
			<div class="row">
				<div class="col">
					<form:hidden path="employee.id" />
					<fieldset class="form-group">
						<form:label path="employee.firstName">First Name</form:label>
						<form:input path="employee.firstName" name="firstName" type="text"
							required="required" class="form-control" readonly="true" />
					</fieldset>
					<fieldset class="form-group">
						<form:label path="employee.secondName">First Name</form:label>
						<form:input path="employee.secondName" name="secondName"
							type="text" required="required" class="form-control"
							readonly="true" />
					</fieldset>
					<fieldset class="form-group">
						<form:label path="employee.position">Position</form:label>
						<form:input path="employee.position" name="positiont" type="text"
							required="required" class="form-control" readonly="true" />
					</fieldset>
					<fieldset class="form-group">
						<form:label path="employee.workStart">Started to work:</form:label>
						<form:input path="employee.workStart" name="workStart" type="text"
							required="required" class="form-control" readonly="true" />
					</fieldset>

				</div>
			</div>
			<div class="row">
				<div class="col">
					<table id="mngProjTable" class="table table-striped">
						<thead>
							<tr>
								<th>Project Name</th>
								<th>Budget</th>
								<th class="no_name"></th>
							</tr>
						</thead>
						<c:forEach items="${projects}" var="project">
							<tr>
								<td>${project.name}</td>
								<td>${project.budget}</td>
								<td><a type="button" class="btn btn-info"
									href="/employee/${conv_action}-project-employee-many?projId=${project.id}&emplId=${employee.id}">${action}</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<fieldset style="margin-top: 35px">
						<a type="button" class="btn btn-success" href="/employee/employeess">Done</a>
						<a type="button" class="btn btn-info" href="/employee/employeess">Cancel</a>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>