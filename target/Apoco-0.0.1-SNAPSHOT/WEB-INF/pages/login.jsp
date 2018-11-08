<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            09/23/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.1
   - Module Name:     login.jsp
   - Module Version:  1.0
   - Summary:         This is the user's login page for my website. User can log in with their
   - 				  email or username.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="login-clean">
	<form method="POST" action="login" style="background-color:#a70000;">
		<h2 class="sr-only">Login Form</h2>
		<div class="illustration">
			<i class="icon-fire" style="color: #000000;"></i>
		</div>
		<div class="form-group">
			<input class="form-control" name="email" placeholder="Email or Username"/>
		</div>
		<div class="form-group">
			<input class="form-control" type="password" name="password" placeholder="Password"/>
		</div>
		<div class="form-group">
			<button class="btn btn-primary btn-block" type="submit"
				style="background-color: #000000;">Log In</button>
			<c:choose>
			<c:when test = "${sessionScope.message != null}">
				<p style="color: #ffffff; font-size: small;"><c:out value="${sessionScope.message}"/></p>
				<c:remove var="message"/>
			</c:when>
		</c:choose>
		</div>
	</form>
</div>