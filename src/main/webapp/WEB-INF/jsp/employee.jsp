<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<div class="row" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="col">
			<form method="post">
				<form:hidden path="employee.id" />
				<fieldset class="form-group">
					<form:label path="employee.firstName">First Name</form:label>
					<form:input path="employee.firstName" name="firstName" type="text"
						class="form-control" />
					<form:errors path="employee.firstName" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="employee.secondName">First Name</form:label>
					<form:input path="employee.secondName" name="secondName"
						type="text" class="form-control" />
					<form:errors path="employee.secondName" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="employee.salary">Salary</form:label>
					<form:input path="employee.salary" name="salary" type="number"
						class="form-control" />
					<form:errors path="employee.salary" cssClass="text-warning" />
				</fieldset>


				<fieldset class="form-group">
					<form:label path="employee.workStart">Started to work:</form:label>
					<form:input path="employee.workStart" name="workStart" type="text"
						class="form-control" autocomplete="off" />
					<form:errors path="employee.workStart" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="employee.email">Email</form:label>
					<form:input path="employee.email" name="email" class="form-control" />
					<form:errors path="employee.email" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="employee.position">Position</form:label>
					<form:input path="employee.position" name="positiont" type="text"
						class="form-control" />
					<form:errors path="employee.position" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="employee.password">Password</form:label>
					<form:input path="employee.password" name="password"
						type="password" class="form-control" />
					<form:errors path="employee.password" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="employee.active">Active</form:label>
					<form:input path="employee.active" name="active" type="number"
						class="form-control" />
					<form:errors path="employee.active" cssClass="text-warning" />
				</fieldset>

				<form:hidden path="contacts.id" />
				<fieldset class="form-group">
					<form:label path="contacts.streetAddress">Street</form:label>
					<form:input path="contacts.streetAddress" name="address"
						type="text" class="form-control" />
					<form:errors path="contacts.streetAddress" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="contacts.town">Town</form:label>
					<form:input path="contacts.town" name="town" type="text"
						class="form-control" />
					<form:errors path="contacts.town" cssClass="text-warning" />
				</fieldset>
				<fieldset class="form-group">
					<form:label path="contacts.phone">Phone</form:label>
					<form:input path="contacts.phone" name="phone" type="text"
						class="form-control" />
					<form:errors path="contacts.phone" cssClass="text-warning" />
				</fieldset>



				<fieldset>
					<button type="submit" class="btn btn-success">${action}
						Employee</button>
					<a type="button" class="btn btn-info" href="/employee/employeess">Cancel</a>
				</fieldset>

			</form>

		</div>

	</div>

</div>
<%@ include file="common/footer.jspf"%>