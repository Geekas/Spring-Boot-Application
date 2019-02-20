<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container-fluid main_content">
	<div class="row">
		<div class="col-12">
			<h1 class="text-center">Welcome to my Spring Boot project</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6 col-sm-12">
			<p>This is my first Spring Boot project. It took me a while to
				learn and to apply knowledge. I understand there is still a long way
				to go as junior but I am ready to learn from more experienced
				programmers also from books and documentation..</p>
			<p>This project included technologies as Spring Boot, Hibernate,
				Hibernate Validation, Spring Security, MySQL, BootStrap, some
				JavaScript, CSS, JSP, a bit of Rest API. I believe there are things
				that could be better in this project, for example, planning to add
				tests for hibernate, but for now, I tested manually and I am looking
				for best practises how to write Spring Boot test. I know Mocking and
				etc. so it should be done pretty soon :)</p>
		</div>
		<div class="col-lg-6 col-sm-12">
			<p>
				Some documentation about this web:<br> You can log in as Admin:
				romarijo007@yahoo.com pass:ramunas<br> or as manager:
				test@test.lt pass:rokas<br> or as an employee:
				employee@magicalmind.com pass:tomas <br> You could notice that
				there are menu items that can see only an admin and some that can
				see only the manager. When creating new employee there is validation
				for most of the fields only not for a phone I believe. Also, there
				is a date picker. The password is encrypted with salt and hash.
			</p>
			<p>Some Rest endpoints for admin and manager:
				<br>http://localhost:8080/employee/rest/employee
				<br>http://localhost:8080/employee/rest/employee/29
				<br>http://localhost:8080/projects/rest/projects
				<br>http://localhost:8080/projects/rest/project/1</p>
		</div>
	</div>
</div>
<%@ include file="common/footer.jspf"%>