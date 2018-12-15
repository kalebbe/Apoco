<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" align="center">
	<c:choose>
		<c:when test="${sessionScope.page.equals('search')}">
			<h1>Search Results</h1>
		</c:when>
		<c:otherwise>
			<h1>Friend List</h1>
		</c:otherwise>
	</c:choose>
	<br>
	<c:forEach var="user" items="${users}">
		<div class="row">

			<div class="col-md-6">

				<h3>
					<c:out value="${user.firstName} ${user.lastName}" />
				</h3>
				<img src="<c:url value="/assets/img/Placeholder.png" /> ">
				<br>
				<h5>
					<c:out value="${user.social.gender}, ${user.social.age}" />
				</h5>
				<br>

			</div>
			<div class="col-md-6">
				<br><br>
				<p>
					<c:out value="${user.social.bio}" />
				</p>
			</div>
		</div>
		<form method="POST" action="../friends/view">
			<button class="btn" value="${user.id}" name="id" style="background-color: #000000; color: #ffffff;">View Profile</button>
		</form>
		<hr>
	</c:forEach>
</div>