<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            10/14/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.3
   - Module Name:     socialProfile.jsp
   - Module Version:  1.0
   - Summary:         This is the view used for creation of the social profile. Will be updated
   -  			   	  next milestone with a picture uploader.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<h1 class="text-center">Social Profile</h1>
	<br> <br> <br>
	<form:form method="POST" modelAttribute="social" action="submitSocial">
		<div class="row">
			<div class="col-md-6">
				<h5>
					Occupation:
					<form:select path="career">
						<form:options items="${jobList}" />
					</form:select>
					<br> <br>
				</h5>
				<form:errors style="color: #a70000;" path="job" />
				<h5>
					Job:
					<form:input type="text" path="job" style="margin-left:72px;" minlength="2" maxlength="100" />
					<br> <br> Education:
					<form:select path="education" style="margin-left:15px;">
						<form:options items="${edList}" />
					</form:select>
					<br> <br>
				</h5>
				<form:errors style="color: #a70000;" path="school" />
				<h5>
					School:
					<form:input type="text" path="school" style="margin-left: 45px;" minlength="2" maxlength="100" />
				</h5>
			</div>
			<div class="col-md-6">
				<h5>
					<!--
					<errors style="color: #a70000;" path="picture"/>
					Profile picture:
					<input type="file" name="picture" accept="image/*" />
					<br> <br> -->
					Birthday:
					<form:select path="birthDay">
						<form:options items="${dayList}" />
					</form:select>
					<form:select path="birthMonth">
						<form:options items="${monthList}" />
					</form:select>
					<form:select path="birthYear">
						<form:options items="${yearList}" />
					</form:select>
					<br> <br>
				</h5>
				<form:errors style="color: #a70000;" path="city" />
				<h5>
					City:
					<form:input type="text" path="city" minlength="2" maxlength="40" />
					State:
					<form:select path="state">
						<form:options items="${stateList}" />
					</form:select>
					<br> <br> Relationship Status:
					<form:select path="status">
						<form:options items="${statusList}" />
					</form:select>
					<br> <br> Gender:
					<form:radiobutton path="gender" value="Male" label="Male" />
					&nbsp
					<form:radiobutton path="gender" value="Female" label="Female" />
				</h5>
			</div>
		</div>
		<div align="center">
			<br> <br>
			<form:errors style="color: #a70000;" path="bio" />
			<h5>Biography:</h5>
			<br>
			<p style="font-size: xx-small;">
				Word count: <span id="display_count">0</span> of 200 (Max)<br>
			</p>
			<form:textarea style="white-space: pre-wrap;" id="word_count"
				path="bio" rows="10" cols="40" minlength="50" maxlength="5000" />
			<h5>
				<br> Privacy Mode:
				<form:radiobutton path="privacy" name="privacy" value="true"
					label="On" />
				<form:radiobutton path="privacy" name="privacy" value="false"
					label="off" />
			</h5>
			<p style="font-size: xx-small;">
				Privacy mode will make it so other users will only be able to look
				you up by your email. Users will not be able to view any details<br>
				about you or anything you have posted on your profile. Other users
				will not be able to message you without having you as a friend.
			</p>
			<br>
			<button class="btn" type="submit"
				style="background-color: #000000; color: #ffffff;">Create
				Profile</button>
			<br>
		</div>
	</form:form>
</div>