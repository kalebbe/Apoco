<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
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
		<h4><c:out value="${user.firstName} ${user.lastName}"/></h4>
		<p><c:out value="${user.social.bio}"/></p>
		<br>
	</c:forEach>
</div>