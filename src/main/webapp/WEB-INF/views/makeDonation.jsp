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
						<strong class="">Make Monetary Donation</strong>

					</div>
					<div class="panel-body">
						<form:form id="form" method="post" modelAttribute="donation"
							action="${pageContext.request.contextPath}/donate"
							class="form-horizontal" role="form">

							<form:errors path="*" cssClass="text-danger" />

							<fieldset>

								<form:hidden path="eventId" value="${donation.eventId }" />

								<div class="form-group">
									<form:label class="col-lg-2 control-label" path="amount"> 
						Amount 
					</form:label>
									<div class="col-xs-3">
										<div class="input-group">
											<div class="input-group-addon">$</div>
											<form:input path="amount" class="form-control"
												placeholder="Enter Amount" maxlength="30" size="20" />
											<div class="input-group-addon">.00</div>
										</div>
									</div>

									<form:label class="col-lg-2 control-label"
										path="cardHolderName">            
		  				Card Holder Name
					</form:label>
									<div class="col-xs-3">
										<form:input class="form-control" path="cardHolderName"
											size="16" placeholder="Enter card holder name" maxlength="25" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-2 control-label"
										path="billingAddress">
		  				Billing Address 
					</form:label>
									<div class="col-xs-3">
										<form:input path="billingAddress"
											class="form-control input-medium"
											placeholder="Enter billing address" />
									</div>
									<form:label class="col-lg-2 control-label" path="cardType">
		  				Card Type 
					</form:label>
									<div class="col-xs-3">
										<form:input path="cardType" class="form-control input-medium"
											placeholder="Enter card type" />
									</div>
								</div>

								<div class="form-group">
									<form:label class="col-lg-2 control-label" path="cardNumber">
		  				Card Number 
					</form:label>
									<div class="col-xs-3">
										<form:input path="cardNumber"
											class="form-control input-medium"
											placeholder="Enter card number" />
									</div>
									<form:label class="col-lg-2 control-label" path="expiryMonth">
		  				Expiry Month 
					</form:label>
									<div class="col-xs-3">
										<form:input path="expiryMonth"
											class="form-control input-medium"
											placeholder="Enter expiry month" />
									</div>
								</div>

								<div class="form-group">
									<form:label class="col-lg-2 control-label" path="expiryYear">
		  				Expiry Year 
					</form:label>
									<div class="col-xs-3">
										<form:input path="expiryYear"
											class="form-control input-medium"
											placeholder="Enter expiry year" />
									</div>
									<form:label class="col-lg-2 control-label" path="securityCode">
		  				Security Code 
					</form:label>
									<div class="col-xs-3">
										<form:input path="securityCode"
											class="form-control input-medium"
											placeholder="Enter security code" />
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