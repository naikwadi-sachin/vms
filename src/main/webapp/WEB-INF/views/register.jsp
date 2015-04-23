<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link rel='stylesheet'
	href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>

<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript"
		src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>

	<div class="page-header">
		<h1 align="center">VMS Web App</h1>
	</div>

	<div class="container">

		<div class="panel-heading">
			<h3 align="left">
				<strong class="">Register User</strong>
			</h3>

		</div>
		<div class="row">
			<form:form role="form" class="form-horizontal" modelAttribute="user"
				action="registerUser" method="POST">
				<div class="col-lg-6">
					<div class="well well-sm">
						<strong><span class="glyphicon glyphicon-asterisk"></span>Required
							Field</strong>
					</div>
					<form:errors path="*" cssClass="text-danger" />
					<div class="form-group">
						<label for="firstNameField">First Name</label>
						<div class="input-group">

							<form:input path="firstName" cssClass="form-control"
								id="firstNameField" placeholder="Enter first name"></form:input>

							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="lastNameField">Last Name</label>
						<div class="input-group">
							<form:input path="lastName" cssClass="form-control"
								id="lastNameField" placeholder="Enter last name"></form:input>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="emailField">Enter Email</label>
						<div class="input-group">
							<form:input path="email" cssClass="form-control" id="emailField"
								placeholder="Enter email"></form:input>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="passwordField">Enter password</label>
						<div class="input-group">


							<form:password path="password" cssClass="form-control"
								id="passwordField" placeholder="Enter password" />

							<span class="input-group-addon"><span
								class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-sm">Register
						</button>
						<button type="reset" class="btn btn-default btn-sm">Reset</button>
					</div>


				</div>
			</form:form>

		</div>
	</div>





</body>
</html>