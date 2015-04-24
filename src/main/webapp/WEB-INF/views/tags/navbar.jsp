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
				<li><a href="<c:url value="/showPosts" />">Search Event</a></li>

				<c:choose>
					<c:when test="${sessionScope.user.userType == 'a'}">
						<li><a href="<c:url value="/createPost" />">Post Event</a></li>
						<li><a href="<c:url value="/showDonations" />">View
								Donations</a></li>
					</c:when>
					<c:when test="${sessionScope.user.userType == 'u'}">
						<li><a href="<c:url value="/createPost" />">Post Event</a></li>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

			</ul>

			<ul class="nav navbar-nav navbar-right">

				<c:choose>
					<c:when test="${sessionScope.user.userType == 'a'}">
						<li class="active"><a href="#">You are logged in as an Administrator</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Welcome
								<%=((UserEntity) session.getAttribute("user"))
							.getEmail()%><span class="caret"></span>
						</a>
						<li><a href="<c:url value="/logoutUser" />">Logout</a></li>

					</c:when>
					<c:when test="${sessionScope.user.userType == 'u'}">
						<li class="active"><a href="#">You are logged in as Registered user</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Welcome
								<%=((UserEntity) session.getAttribute("user"))
							.getEmail()%><span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<c:url value="/showMyEvents" />">My Events</a></li>
							</ul></li>
						<li><a href="<c:url value="/logoutUser" />">Logout</a></li>

					</c:when>
					<c:otherwise>
						<li class="active"><a href="#">Welcome Guest</a></li>
						<li><a href="<c:url value="/loginUser" />">Login</a></li>
						<li><a href="<c:url value="/registerUser" />">Register</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</div>
</div>