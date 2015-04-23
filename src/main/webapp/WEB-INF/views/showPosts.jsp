<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
		<form:form id="form" method="post" modelAttribute="event"
			action="showPosts" class="form-horizontal" role="form">
			<c:if test="${not empty message}">
				<div id="message" class="success">${message}</div>
			</c:if>

			<c:if test="${not empty loginSuccess}">
				<div class="alert alert-success" role="alert">${loginSuccess}</div>
			</c:if>

			<c:if test="${not empty logoutSuccess}">
				<div class="alert alert-success" role="alert">${logoutSuccess}</div>
			</c:if>

			<c:if test="${not empty searchResult}">
				<div class="alert alert-warning" role="alert">${searchResult}</div>
			</c:if>

			<%-- <s:bind path="*">
				<c:if test="${status.error}">
					<div id="message" class="error">Form has errors</div>
				</c:if>
			</s:bind> --%>

			<fieldset>
				<h4 class="text-muted">Select following parameters to filter
					the events</h4>
				<br />

				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="eventName"> 
						Event Name <form:errors path="eventName" />
					</form:label>
					<div class="col-xs-2">
						<form:input path="eventName" class="form-control"
							placeholder="event name" maxlength="30" size="20" />
					</div>

					<form:label class="col-lg-2 control-label" path="eventDate">            
		  				Event Date (yyyy-mm-dd)<form:errors path="eventDate" />
					</form:label>
					<div class="col-xs-2">
						<form:input class="form-control" path="eventDate" size="16"
							placeholder="yyyy-mm-dd" maxlength="25" />
					</div>
				</div>
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="state">
		  				State <form:errors path="state" />
					</form:label>
					<div class="col-xs-2">
						<form:input path="state" class="form-control input-medium"
							placeholder="enter state" />
					</div>
					<form:label class="col-lg-2 control-label" path="city">
		  				City <form:errors path="city" />
					</form:label>
					<div class="col-xs-2">
						<form:input path="city" class="form-control input-medium"
							placeholder="enter city" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 control-label"> </label>
					<div class="col-lg-6">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>

			</fieldset>

		</form:form>



		<c:if test="${not empty joinSuccess}">
			<div class="alert alert-success" role="alert">${joinSuccess}</div>
		</c:if>

		<c:if test="${not empty joinError}">
			<div class="alert alert-danger" role="alert">${joinError}</div>
		</c:if>

		<c:if test="${not empty deleteSuccess}">
			<div class="alert alert-success" role="alert">${deleteSuccess}</div>
		</c:if>

		<c:if test="${not empty deleteError}">
			<div class="alert alert-danger" role="alert">${deleteError}</div>
		</c:if>
		
		<c:if test="${not empty donationSuccess}">
			<div class="alert alert-success" role="alert">${donationSuccess}</div>
		</c:if>
		
		<c:if test="${not empty donationError}">
			<div class="alert alert-danger" role="alert">${donationError}</div>
		</c:if>

		<h2 class="text-muted">Upcoming events</h2>
		<hr />
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Event Name</th>
						<th>Event Description</th>
						<th>Event Date</th>
						<th>State</th>
						<th>City</th>
						<th align="center" colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="event" items="${events}">
						<tr>
							<td><c:out value="${event.eventName}" /></td>
							<td><c:out value="${event.eventDescription}" /></td>
							<td><c:out value="${event.eventDate}" /></td>
							<td><c:out value="${event.state}" /></td>
							<td><c:out value="${event.city}" /></td>


							<c:if test="${sessionScope.user.userType =='u'}">


								<td align="left"><a
									href="${pageContext.request.contextPath}/join/${event.eventId }"><img
										alt="Comments" title="Join this event"
										src="${pageContext.request.contextPath}/resources/images/event.png"
										height="24" width="24"></a></td>
								<td align="left"><a
									href="${pageContext.request.contextPath}/donate/${event.eventId }"><img
										alt="Comments" title="Donate for this event"
										src="${pageContext.request.contextPath}/resources/images/donate.png"
										height="24" width="24"></a></td>
								<td align="left"><a
									href="${pageContext.request.contextPath}/comments/${event.eventId }"><img
										alt="Comments" title="Show Comments on this event"
										src="${pageContext.request.contextPath}/resources/images/comment.png"
										height="24" width="24"></a></td>
							</c:if>

							<c:if test="${sessionScope.user.userType == 'a'}">
								<td align="left"><a
									href="${pageContext.request.contextPath}/deleteEvent/${event.eventId }"><img
										alt="Comments" title="Delete this event"
										src="${pageContext.request.contextPath}/resources/images/delete.png"
										height="24" width="24"></a></td>
								<td align="left"><a
									href="${pageContext.request.contextPath}/comments/${event.eventId }"><img
										alt="Comments" title="Show Comments on this event"
										src="${pageContext.request.contextPath}/resources/images/comment.png"
										height="24" width="24"></a></td>
							</c:if>
							
							<td align="left"><a
									href="${pageContext.request.contextPath}/editEvent/${event.eventId }"><img
										alt="Comments" title="Edit this event"
										src="${pageContext.request.contextPath}/resources/images/edit.png"
										height="24" width="24"></a></td>
										
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>