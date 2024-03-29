<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            09/23/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.1
   - Module Name:     userHome.jsp
   - Module Version:  1.0
   - Summary:         This is the user's home page for my website. In the future, there will be a little dashboard
   -				  for each service in this page if the user has created a profile for said service. These dashboards
   -				  are not currently dynamic, but they will be in the future.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="team-boxed">
	<div class="container">
		<div class="intro">
			<h2 class="text-center">
				Welcome Home
				<c:out value="${user.username}" />
			</h2>
		</div>
		<div class="row people">
			<div class="col-md-6 col-lg-4 item" style="color: #0fb800;">
				<div class="box">
					<c:choose>
						<c:when test="${sessionScope.hasSocial != true}">
							<h3 class="name">Apoco Social</h3>
							<p class="description">You have not set up a social account!</p>
							<a class="btn btn-light action-button" role="button"
								href="../social/social"
								style="background-color: #0fb800; color: #ffffff; font-size: 14px;">Create
								Profile</a>
						</c:when>
						<c:otherwise>
							<h3 class="name">Apoco Social</h3>
							<p class="description">Social mini dashboard coming soon!</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-md-6 col-lg-4 item" style="color: rgb(0, 7, 169);">
				<div class="box">
					<h3 class="name">Apoco Business</h3>
					<p class="description">You have not set up a business account!</p>
					<a class="btn btn-light action-button" role="button" href="#"
						style="background-color: rgb(0, 7, 169); color: #ffffff; font-size: 14px;">Create
						Profile</a>
				</div>
			</div>
			<div class="col-md-6 col-lg-4 item" style="color: #dd00c8;">
				<div class="box">
					<h3 class="name">Apoco Dating</h3>
					<p class="description">You have not set up a dating account!</p>
					<a class="btn btn-light action-button" role="button" href="#"
						style="background-color: #dd00c8; color: #ffffff; font-size: 14px;">Create
						Profile</a>
				</div>
			</div>
		</div>
	</div>
</div>