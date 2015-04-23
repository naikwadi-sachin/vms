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

		<h2 class="text-muted">My Upcoming events</h2>
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
						<th align="center" colspan="2"></th>
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
							<td align="left"><a
								href="${pageContext.request.contextPath}/comments/${event.eventId }"><img
									alt="Comments" title="Show Comments on this event"
									src="${pageContext.request.contextPath}/resources/images/comment.png"
									height="24" width="24"></a></td>
							<td align="left"><a
								href="${pageContext.request.contextPath}/donate/${event.eventId }"><img
									alt="Comments" title="Donate for this event"
									src="${pageContext.request.contextPath}/resources/images/donate.png"
									height="24" width="24"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>