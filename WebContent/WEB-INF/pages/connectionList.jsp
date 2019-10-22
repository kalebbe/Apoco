<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<div class="container" align="center">
	<c:choose>
		<c:when test="${sessionScope.page.equals('requests')}">
			<h1>New Connection Requests</h1>
		</c:when>
		<c:otherwise>
			<h1>Connection List</h1>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p style="color: #a70000;">
				<c:out value="${sessionScope.message}" />
			</p>
			<c:remove var="message" />
		</c:when>
		<c:when test="${sessionScope.message2 != null}">
			<p style="color: #000000;">
				<c:out value="${sessionScope.message2}" />
			</p>
			<c:remove var="message2" />
		</c:when>
	</c:choose>
	<br>
	<c:forEach var="user" items="${users}">
		<div class="row">

			<div class="col-md-6">

				<h3>
					<c:out value="${user.firstName} ${user.lastName}" />
				</h3>
				<img src="<c:url value="/assets/img/Placeholder.png" /> " style="width:50px;height:50px;">
				<br>
				<h5>
					<c:out value="${user.business.gender}, ${user.business.age}" />
				</h5>
				<br>

			</div>
			<div class="col-md-6">
				<br><br>
				<p>
					<strong>Profession: </strong><c:out value="${user.business.profession}" />
					<br>
					<strong>Education level: </strong><c:out value="${user.business.education}" />
				</p>
			</div>
		</div>
		<c:choose>
			<c:when test="${sessionScope.page.equals('requests')}">
				<input type="hidden" value="${user.id}" id="userId">
				<button class="btn" style="background-color: #0fb800; color: #ffffff;" value="${user.messageId}"
					id="accept" onclick="ajaxFeed('connections/addConnection', 'id', '#accept', 'connId', '#userId')">Accept Request</button>
				<button class="btn" style="background-color: #a70000; color: #ffffff;" value="${user.messageId}"
					id="deny" onclick="ajaxFeed('connections/denyRequest', 'id', '#deny')">Deny Request</button>
			</c:when>
			<c:otherwise>
				<form method="POST" action="../connections/view">
					<button class="btn" value="${user.id}" name="id" style="background-color: #000000; color: #ffffff;">View Profile</button>
				</form>
			</c:otherwise>
		</c:choose>
		<hr>
	</c:forEach>
</div>