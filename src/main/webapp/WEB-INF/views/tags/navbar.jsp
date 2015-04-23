<%@page import="edu.uncc.vms.domain.UserEntity"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/showPosts" />">Home</a></li>


				<%
					if (session.getAttribute("user") == null) {
				%>
				<li><a href="<c:url value="/registerUser" />">Register</a></li>
				<%
					} else {
				%>
				<li><a href="<c:url value="/createPost" />">Post Event</a></li>
				<%
					}
				%>

				<li><a href="<c:url value="/showPosts" />">Search Event</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">


				<%
					if (session.getAttribute("user") == null) {
				%>
				<li><a href="<c:url value="/loginUser" />">Login</a></li>
				<%
					} else {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Welcome
						<%=((UserEntity) session.getAttribute("user"))
						.getEmail()%><span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value="/showMyEvents" />">My Events</a></li>
					</ul></li>
				<li><a href="<c:url value="/logoutUser" />">Logout</a></li>
				<%
					}
				%>





			</ul>
		</div>
	</div>
</div>