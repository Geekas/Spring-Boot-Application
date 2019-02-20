<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<c:set var="getAction" value="${action}" />
	<c:set var="conv_action" value="${fn:toLowerCase(getAction)}" />
	<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="col">
			<div class="row">
				<div class="col-6">
					<form:hidden path="project.id" />
					<fieldset class="form-group">
						<form:label path="project.name">Project Name</form:label>
						<form:input path="project.name" name="name" type="text"
							required="required" class="form-control" readonly="true" />
						<form:errors path="project.name" cssClass="text-warning" />
					</fieldset>
					<fieldset class="form-group">
						<form:label path="project.description">Description</form:label>
						<form:input path="project.description" name="description"
							type="text" required="required" class="form-control"
							readonly="true" />
						<form:errors path="project.description" cssClass="text-warning" />
					</fieldset>
					<fieldset class="form-group">
						<form:label path="project.budget">Budget</form:label>
						<form:input path="project.budget" name="budget" type="number"
							required="required" class="form-control" readonly="true" />
						<form:errors path="project.budget" cssClass="text-warning" />
					</fieldset>

				</div>
			</div>
			<div class="row">
				<div class="col">
					<table id="mngEmplTable" class="table table-striped">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Position</th>
								<th>Started work</th>
								<th class="no_name"></th>
							</tr>
						</thead>
						<c:forEach items="${employeess}" var="employee">
							<tr>
								<td>${employee.firstName}</td>
								<td>${employee.secondName}</td>
								<td>${employee.position}</td>
								<td><fmt:formatDate value="${employee.workStart}"
										pattern="yyyy/MM/dd" /></td>
								</td>
								<td><a type="button" class="btn btn-info"
									href="/projects/project-${conv_action}-employee-many?projId=${project.id}&emplId=${employee.id}">${action}</a></td>
							</tr>
						</c:forEach>

					</table>



					<fieldset style="margin-top: 35px">
						<a type="button" class="btn btn-success" href="/projects/projects">Done</a>
						<a type="button" class="btn btn-info" href="/projects/projects">Cancel</a>
					</fieldset>
				</div>
			</div>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>