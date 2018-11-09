<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            10/14/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.1
   - Module Name:     editAccount.jsp
   - Module Version:  1.0
   - Summary:         This is where the user can edit their account. Will be updated
   - 				  next milestone to include acocunt deletion and vaulting.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row" align="center">
		<div class="col-md-12">
			<h1 class="text-center">Edit Account</h1>
			<c:choose>
				<c:when test="${sessionScope.message != null || sessionScope.message2 != null}">
					<p style="color: #a70000;">
						<c:out value="${sessionScope.message}" />
						<c:out value="${sessionScope.message2}" />
					</p>
					<c:remove var="message" />
					<c:remove var="message2" />
				</c:when>
				<c:when test="${sessionScope.message3 != null}">
					<p style="color: #000000;">
						<c:out value="${sessionScope.message3}" />
					</p>
					<c:remove var="message3" />
				</c:when>
			</c:choose>
			<br> <br> <br> <br>
		</div>
	</div>
	<div class="row">

		<div class="col-md-6">
			<h4>
				<form method="POST" action="updateFirst">
					First Name: <input type="text" style="margin-left: 20px;"
						name="firstName" value="${user.firstName}" minlength="2" maxlength="30" />
					<button class="btn action-button" type="submit"
						style="background-color: #000000; color: #ffffff; float: right; margin-right: 30px;">Update</button>
				</form>
				<br> <br>
				<form method="POST" action="updateLast">
					Last Name: <input type="text" style="margin-left: 23px;"
						name="lastName" value="${user.lastName}" minlength="2" maxlength="30" />
					<button class="btn action-button" type="submit"
						style="background-color: #000000; color: #ffffff; float: right; margin-right: 30px;">Update</button>
				</form>
				<br> <br>
				<form method="POST" action="updateUser">
					Username: <input type="text" style="margin-left: 27px;"
						name="username" value="${user.username}" minlength="4" maxlength="30" />
					<button class="btn action-button" type="submit"
						style="background-color: #000000; color: #ffffff; float: right; margin-right: 30px;">Update</button>
				</form>
			</h4>
		</div>

		<div class="col-md-6">
			<h4>
				<form method="POST" action="updateEmail">
					Email: <input type="email" style="margin-left: 100px;" name="email"
						value="${user.email}" minlength="4" maxlength="40" />
					<button class="btn action-button" type="submit"
						style="background-color: #000000; color: #ffffff; float: right; margin-right: 10px;">Update</button>
				</form>
				<br> <br>
				<form method="POST" action="updatePass">
					Old Password: <input type="password" style="margin-left: 9px;"
						name="oldPass" placeholder="Old Password" minlength="8" maxlength="100" required/> <br> <br> New
					Password: <input type="password" name="pass"
						placeholder="New Password" minlength="8" maxlength="100" required/><br><br>
					Re Password: <input type="password" style="margin-left: 22px;"
						name="rePass" placeholder="Repeat New Password" minlength="8" maxlength="100" required/>
					<button class="btn action-button" type="submit"
						style="background-color: #000000; color: #ffffff; float: right; margin-right: 10px;">Update</button>
				</form>
			</h4>
		</div>
	</div>
	<!-- This is being put off temporarily due to time constraints.
	<div align="center">
		<br> <br> <br> <a class="btn action-button"
			role="button" href=""
			style="background-color: #000000; color: #ffffff;">Vault Account</a>&nbsp&nbsp
		<a class="btn action-button" role="button" href=""
			style="background-color: #a70000; color: #ffffff;">Delete Account</a>
		<p style="font-size: xx-small;">*Vaulting your account will make
			it so none of your profiles (dating, business, or social) are visible
			to anyone. Nobody will be able to search for you, message your, or
			otherwise interact with you. We will keep your data and you will be
			able to reactivate your account by logging in again. This is
			recommended over deleting in case you change your mind, but we
			understand either way</p>
	</div>-->
</div>
