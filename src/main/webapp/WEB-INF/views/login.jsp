<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel='stylesheet'
	href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<script type="text/javascript"
		src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>

	<div class="page-header">
		<h1 align="center">VMS Web App</h1>
	</div>
	<div class="container">

		<div class="row">
			<div class="col-md-4 col-md-offset-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class="">Login</strong>

					</div>
					<div class="panel-body">
						<form:form class="form-horizontal" role="form"
							modelAttribute="login" action="validateUser" method="POST">
							<form:errors path="*" cssClass="text-danger" />
							<div class="form-group">
								<label for="emailField" class="col-sm-3 control-label">Email</label>
								<div class="col-sm-9">

									<form:input path="email" cssClass="form-control"
										id="emailField" placeholder="Enter email"></form:input>
								</div>
							</div>
							<div class="form-group">
								<label for="passwordField" class="col-sm-3 control-label">Password</label>
								<div class="col-sm-9">
									<form:password path="password" cssClass="form-control"
										id="passwordField" placeholder="Enter password" />

								</div>
							</div>

							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-primary btn-sm">Sign
										in</button>
									<button type="reset" class="btn btn-default btn-sm">Reset</button>
								</div>
							</div>
						</form:form>
					</div>
					<div class="panel-footer">
						Not Registered? <a href="registerUser" class="">Register here</a>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>