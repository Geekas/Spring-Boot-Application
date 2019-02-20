<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content">
	<div class="row">
		<div class="col">
			<form method="POST">
				<h3>Welcome</h3>
				<fieldset class="form-group">
					<label>Email: </label> <input type="text" id="email" name="email"
						placeholder="email" class="form-control" />
					<errors cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<label>Password: </label> <input type="password"
						placeholder="Password" id="password" name="password"
						class="form-control" />
					<errors cssClass="text-warning" />
				</fieldset>
				<button class="btn btn-lg btn-primary btn-block" name="Submit"
					value="Login" type="Submit">Login</button>
			</form>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>