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
		<h1 class="text-muted">Post new event</h1>
		<hr />
		<form:form action="createPost" method="POST" modelAttribute="event">

			<form:errors path="*" cssClass="text-danger" />
			<div class="form-group">
				<label for="eventNameField">Event Name</label>

				<form:input path="eventName" cssClass="form-control"
					id="eventNameField" placeholder="Enter event name"></form:input>
			</div>

			<div class="form-group">
				<label for="eventDescriptionField">Event description</label>
				<form:textarea path="eventDescription" cssClass="form-control"
					id="eventDescriptionField" placeholder="Enter event description"></form:textarea>
			</div>

			<div class="form-group">
				<label for="eventDateField">Event Date (yyyy-mm-dd)</label>
				<form:input path="eventDate" cssClass="form-control"
					id="eventDateField" placeholder="Select event date"></form:input>
			</div>
			<div class="form-group">
				<label for="stateField">State</label>
				<form:input path="state" cssClass="form-control" id="stateField"
					placeholder="Enter event state"></form:input>
			</div>
			<div class="form-group">
				<label for="cityField">City</label>
				<form:input path="city" cssClass="form-control" id="cityField"
					placeholder="Enter event city"></form:input>
			</div>

			<button type="submit" class="btn btn-primary">Post Event</button>
		</form:form>
	</div>



</body>
</html>