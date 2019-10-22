<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<div class="container">
	<div align="center">
	<c:choose>
		<c:when test="${sessionScope.profile.equals('user')}">
			<h1>Your Profile</h1>
			<br>
		</c:when>
		<c:otherwise>
			<br>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p style="color: #a70000;">
				<c:out value="${sessionScope.message}" />
			</p>
			<c:remove var="message" />
		</c:when>
		<c:when test="${sessionScope.message1 != null}">
			<p style="color: #000000;">
				<c:out value="${sessionScope.message1}" />
			</p>
			<c:remove var="message1" />
		</c:when>	
	</c:choose>
	</div>
	<div class="row">
		<div class="col-md-3">
			<h3>
				<c:out value="${user.firstName} ${user.lastName}" />
			</h3>
			<img src="<c:url value="/assets/img/Placeholder.png" /> "> <br>
			<p>
				<c:out
					value="${user.business.gender}, ${user.business.age}" />

				<br> <strong>Education:</strong>&nbsp
				<c:out value="${user.business.education}" />
				<br> <strong>Profession:</strong>&nbsp
				<c:out value="${user.business.profession}" />
				<br> <strong>Location:</strong>&nbsp
				<c:out value="${user.business.city}, ${user.business.state}" />
			</p>
		</div>
		<div class="col-md-9">

			<p>
				<br> <br> <strong>Ethnicity:</strong><br>
				<c:out value="${user.business.ethnicity}" />
				<br> <br> <strong>Highest level of education:</strong><br>
				<c:out value="${user.business.education}" />
			</p>
		</div>
	</div>
	<c:choose>
		<c:when test="${!sessionScope.profile.equals('user')}">
			<c:choose>
				<c:when test="${user.connection == true}">
					<div align="center">
						<button class="btn" id="sendConn" onclick="ajaxFeed('connections/sendRequest', 'id', '#sendConn')"
							style="background-color: #000000; color: #ffffff;"
							value="${user.id}">Connect with ${user.firstName}</button>
					</div>
				</c:when>
				<c:otherwise>
					<div align="center">
						<button class="btn" id="remConn" onclick="ajaxFeed('connections/removeConn', 'id', '#remConn')"
							style="background-color: #a70000; color: #ffffff;"
							value="${user.id}">Remove Connection</button>
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>
</div>