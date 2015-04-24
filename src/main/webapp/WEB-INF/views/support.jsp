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
						<strong class="">Make Non-monetary Donation</strong>

					</div>
					<div class="panel-body">
						<form:form id="form" method="post" modelAttribute="item"
							action="${pageContext.request.contextPath}/support"
							class="form-horizontal" role="form">

							<form:errors path="*" cssClass="text-danger" />
							<fieldset>

								<form:hidden path="eventId" />

								<div class="form-group">
									<form:label class="col-lg-2 control-label" path="itemCategory"> 
						Category
					</form:label>
									<div class="col-xs-4">
										<form:select path="itemCategory" class="form-control">
											<form:option value="">Select category</form:option>
											<form:option value="Food">Food</form:option>
											<form:option value="Cloths">Cloths</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-2 control-label"
										path="itemDescription">            
		  				Description
					</form:label>
									<div class="col-xs-7">
										<form:textarea class="form-control" path="itemDescription"
											placeholder="Enter description" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-lg-2 control-label"> </label>
									<div class="col-lg-6">
										<button type="submit" class="btn btn-primary">Donate</button>
									</div>
								</div>

							</fieldset>

						</form:form>


					</div>
				</div>
			</div>
		</div>
</body>
</html>