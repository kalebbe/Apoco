<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            10/14/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.1
   - Module Name:     socialFeed.jsp
   - Module Version:  1.0
   - Summary:         This is a view that allows the user to upload posts to their feed and view others
   -				  that they have posted. In the future, they will be able to see their friend's posts
   -				  and upload pictures.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<h1>Your feed</h1>
	<p style="font-size: xx-small;">Soon you'll be able to post pictures here for your friends to
		see</p><hr>
	<form:form method="POST" modelAttribute="feed" action="createFeed">
		<h5>Youtube Link</h5>
		<form:input path="link" />
		<p style="font-size: xx-small;">*Not required. Only for youtube videos</p>
		<form:textarea style="white-space: pre-wrap" id="word_count"
			path="feed" rows="5" cols="100" />
		<p style="font-size: xx-small;">
			Word count: <span id="display_count">0</span> of 200 (Max)
		</p>
		<form:errors style="color: #a70000;" path="feed" />
		<h5>
			<form:radiobutton path="privacy" value="public" label=" Public" checked="checked" />
			<form:radiobutton path="privacy" value="friends" label=" Friends" />
		</h5>
		<button class="btn" type="submit"
			style="background-color: #000000; color: #ffffff;">Post to
			feed</button>
	</form:form>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
				<p style="color: #000000; font-size: small;">
					<c:out value="${sessionScope.message}" />
				</p>
			<c:remove var="message" />
		</c:when>
	</c:choose>
	<hr />
	<table>
		<c:forEach var="feed" items="${feedList}">
			<tr>
				<td><p style="opacity: 0.5;">
						<c:out value="${feed.name}" />
						said:
					</p></td>
			</tr>
			<tr>
				<td><p style="white-space: pre-wrap"><c:out value="${feed.feed}" /></p></td>
			</tr>
			<c:choose>
				<c:when test="${feed.link != null}">
					<tr>
						<td>
							<iframe width="400" height="300" src="https://www.youtube.com/embed/${feed.link}"></iframe>
						</td>
					</tr>
				</c:when>
			</c:choose>
			<tr
				style="border-bottom: 1px solid rgba(0, 0, 0, .1); margin-bottom: 50px;">
				<td align="center"><form:form method="POST"
						modelAttribute="feed" action="deleteFeed">
						<p style="font-size: small; font-weight: bold;">
							<c:out
								value="${feed.privacy.equals('public') ? 'Everyone' : 'Friends'}" />
							can see your post
							<button value="${feed.id}" name="id"
								style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Remove</button>
						</p>
					</form:form></td>
			</tr>
		</c:forEach>
	</table>

</div>