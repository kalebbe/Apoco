<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            10/14/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.3
   - Module Name:     socialFeed.jsp
   - Module Version:  1.0
   - Summary:         This is a view that allows the user to upload posts to their feed and view others
   -				  that they have posted. In the future, they will be able to see their friend's posts
   -				  and upload pictures.
   -
   -				  -----UPDATE MILESTONE 5-----
   -				  -Added jquery/script to autosize textareas on startup and keyup.
   -				  -Clicking (read: double clicking) textarea posts will now allow updating
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	//jQuery for autosizing textareas on page load
	$(function() {
		$("textarea").each(function() {
			this.style.height = (this.scrollHeight + 10) + 'px';
		});
	});
</script>
<c:set var="count" value="0" scope="page" />
<div align="center">
	<h1>Your feed</h1>
	<p style="font-size: xx-small;">Soon you'll be able to post
		pictures here for your friends to see</p>
	<hr>
	<form:form method="POST" modelAttribute="feed" action="createFeed">
		<h5>Youtube Link</h5>
		<form:input size="50" path="link" minlength="10" maxlength="100" />
		<p style="font-size: xx-small;">*Not required. Only for youtube
			videos</p>
			
		<!-- 
		   - Removes scroll bar + resize button from textarea. Form will auto resize as the
		   - user updates.
		 -->
		<form:textarea
			style="white-space: pre-wrap; resize:none; overflow:hidden;"
			id="word_count" path="feed" cols="65" minlength="20" maxlength="5000"
			oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"' />
		<p style="font-size: xx-small;">
			Word count: <span id="display_count">0</span> of 200 (Max)
		</p>
		<form:errors style="color: #a70000;" path="feed" />
		<h5>
			<form:radiobutton path="privacy" value="public" label=" Public"
				checked="checked" />
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
		<c:choose>
			<c:when test="${sessionScope.message2 != null}">
				<p style="color: #a70000;">
					<c:out value="${sessionScope.message2}" />
				</p>
				<c:remove var="message2"/>
			</c:when>
			<c:when test="${sessionScope.message3 != null}">
				<p style="color: #000000;">
					<c:out value="${sessionScope.message3}" />
				</p>
				<c:remove var="message3" />
			</c:when>
		</c:choose>
		<c:forEach var="feed" items="${feedList}">
			<tr>
				<td>
					<p style="opacity: 0.5;">
						<c:choose>
							<c:when test="${feed.userId == sessionScope.id}">
										You said:
									</c:when>

							<c:otherwise>
								<c:out value="${feed.name}" />
									said:
								</c:otherwise>
						</c:choose>
					</p>
				</td>
			</tr>
			<tr>
				<td><c:choose>
						<c:when test="${feed.userId == sessionScope.id}">
							<form method="POST" action="updateFeed">
								<div
									onclick="$('#feedTarget${count}').attr('disabled', false); $('#target${count}').css('display', 'inline-block');">

									<textarea id="feedTarget${count}" cols="65" disabled="disabled" name="feed" minlength="20" maxlength="5000"
										style="white-space: pre-wrap; border: none; outline: none; background-color: white; resize: none; overflow: hidden;"
										oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'><c:out
											value="${feed.feed}" /></textarea>
								</div>
								<div id="target${count}" style="display: none;">
									<button class="btn" value="${feed.id}" name="id"
										style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue;">
										Update</button>
									<br>
								</div>
							</form>
							<p style="font-size: xx-small;" align="center">Double click
								your post to edit it!</p>
						</c:when>
						<c:otherwise>
							<p style="white-space: pre-wrap"><c:out value="${feed.feed}" /></p>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<div>
				<c:choose>
					<c:when test="${feed.link != null}">
						<tr>
							<td><iframe id="video" width="500" height="300"
									src="https://www.youtube.com/embed/${feed.link}"></iframe></td>
						</tr>
					</c:when>
				</c:choose>
			</div>
			<c:choose>
				<c:when test="${feed.userId == sessionScope.id}">
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
				</c:when>
			</c:choose>
			<c:set var="count" value="${count + 1}" scope="page" />
		</c:forEach>
	</table>
</div>