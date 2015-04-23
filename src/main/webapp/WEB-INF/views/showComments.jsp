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


		<div class="col-xs-12 col-sm-8">
			<h2 style="text-decoration: underline;">${event.eventName }</h2>
			<p>
				<strong>Date: </strong>${event.eventDate }
			</p>
			<p>
				<strong>Description: </strong>${event.eventDescription }
			</p>
			<p>
				<strong>Location: </strong>${event.city } ${event.state }
			</p>


		</div>

		<div class="row">
			<div class="col-md-10 col-md-offset-0">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class="">User Comments</strong>

					</div>
					<div class="panel-body">

						<form:form id="form" method="post" modelAttribute="comment"
							action="${pageContext.request.contextPath}/comments"
							class="form-horizontal" role="form">
							<c:if test="${not empty message}">
								<div id="message" class="success">${message}</div>
							</c:if>
							<s:bind path="*">
								<c:if test="${status.error}">
									<div id="message" class="error">Form has errors</div>
								</c:if>
							</s:bind>

							<form:hidden path="eventId" />
							<fieldset>
								<div class="form-group success">
									<div class="col-lg-6">
										<form:textarea class="form-control" path="comment" size="16"
											placeholder="write your comment here" />
									</div>
								</div>

								<div class="form-group success">
									<div class="col-lg-6">
										<button type="submit" class="btn btn-primary">Submit
											Comment</button>
									</div>
								</div>

							</fieldset>

						</form:form>

						<c:if test="${not empty commentSuccess}">
							<div class="alert alert-success" role="alert">${commentSuccess}</div>
						</c:if>

						<c:if test="${not empty commentError}">
							<div class="alert alert-danger" role="alert">${commentError}</div>
						</c:if>

						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th colspan="1">Comment Date</th>
										<th colspan="1">User</th>
										<th colspan="3">Comment</th>
										<th colspan=""></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="comment" items="${comments}">
										<tr>
											<td colspan="1"><c:out value="${comment.commentDate}" /></td>
											<td colspan="1"><c:out value="${comment.firstName}" />
												<c:out value="${comment.lastName}" /></td>
											<td colspan="3"><c:out value="${comment.comment}" /></td>

											<c:if test="${sessionScope.user.userType == 'a'}">
												<td align="left"><a
													href="${pageContext.request.contextPath}/deleteComment/${comment.eventId }/${comment.commentId }"><img
														alt="Comments" title="Delete this comment"
														src="${pageContext.request.contextPath}/resources/images/delete.png"
														height="24" width="24"></a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>