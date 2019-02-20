<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container main_content" style="padding-top: 50px;">
	<div class="container">
		<div class="row">
			<div class="col-4">
				<div class="profile-img">
					<img
						src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog"
						alt="" />
				</div>
			</div>
			<div class="col-8">
				<div class="profile-head">
					<h5>${employee.firstName} ${employee.secondName}</h5>
					<h6>${employee.position}</h6>
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" href="#home" role="tab" aria-controls="home"
							aria-selected="true">About</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" href="#profile" role="tab"
							aria-controls="profile" aria-selected="false">Details</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="tab-content profile-tab mt-4" id="myTabContent">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<div class="row">
							<div class="col-md-4">
								<label>Name</label>
							</div>
							<div class="col-md-4">
								<p>${employee.firstName} ${employee.secondName}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label>Phone</label>
							</div>
							<div class="col-md-4">
								<p>${employee.contacts.phone}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label>Started to work:</label>
							</div>
							<div class="col-md-4">
								<p>
									<fmt:formatDate value="${employee.workStart}"
										pattern="yyyy/MM/dd" />
								</p>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">
						<div class="row">
							<div class="col-md-4">
								<label>Email</label>
							</div>
							<div class="col-md-4">
								<p>${employee.email}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label>Position</label>
							</div>
							<div class="col-md-4">
								<p>${employee.position}</p>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<label>Salary</label>
							</div>
							<div class="col-md-4">
								<p>${employee.salary}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>