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
		<form:form id="form" method="post" modelAttribute="" action=""
			class="form-horizontal" role="form">

			<c:if test="${not empty searchResult}">
				<div class="alert alert-warning" role="alert">${searchResult}</div>
			</c:if>
		</form:form>

		<c:if test="${not empty donationSuccess}">
			<div class="alert alert-success" role="alert">${donationSuccess}</div>
		</c:if>

		<c:if test="${not empty donationError}">
			<div class="alert alert-danger" role="alert">${donationError}</div>
		</c:if>

		<h2 class="text-muted">Donations</h2>
		<hr />
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>Event Name</th>
						<th>Donated by</th>
						<th>Email</th>
						<th>Donation Category</th>
						<th>Donation</th>
						<th>Date</th>
						<th align="center" colspan="3"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="donation" items="${donations}">
						<tr>
							<td><c:out value="${donation.eventName}" /></td>
							<td><c:out value="${donation.userName}" /></td>
							<td><c:out value="${donation.email}" /></td>
							<td><c:out value="${donation.donationCategory}" /></td>
							<td><c:out value="${donation.donation}" /></td>
							<td><c:out value="${donation.donationDate}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>