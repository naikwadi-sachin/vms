<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<title>Login</title>

</head>
<body>
	<%@ include file="/include/header.jsp"%>


	<div class="login_bg_div">
		<div class="login_div">
			<span class="head_font">Log In</span>
			<div class="line"></div>

			<form:errors />
			<%-- <jsp:include page="/customError.jsp" /> --%>

			<form:form action="/vms-web/validateUser" method="POST">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="height20"></td>
					</tr>
					<tr>
						<td><form:input path="email" styleClass="input_text"></form:input>
						</td>
					</tr>
					<tr>
						<td class="height20"></td>
					</tr>
					<tr>
						<td><form:password path="password" styleClass="input_text"></form:password></td>
					</tr>
					<tr>
						<td class="height20"></td>
					</tr>
					<tr>
						<td><input type="submit" value="SUBMIT" styleClass="btn" />
							<form:button property="cancel" value="CANCEL" styleClass="btn"></form:button></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

	<%@ include file="/include/footer.jsp"%>


</body>
</html>
