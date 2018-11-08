<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            09/23/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.0
   - Module Name:     footer.jsp
   - Module Version:  1.0
   - Summary:         This is the footer used for the index, login, registration, userHome,
   - 				  and editProfile views. Other views made for Dating, Social, and Business
   -                  will have a different header and footer.
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${sessionScope.theme == null}">
		<div class="footer-basic" style="background-color: #a70000;">
			<footer>
				<p class="copyright" style="color: #ffffff;">Apoco Inc ©
					2018-2019</p>
			</footer>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('dating')}">
		<div class="footer-basic" style="background-color: #dd00c8;">
			<footer>
				<p class="copyright" style="color: #ffffff;">Apoco Inc ©
					2018-2019</p>
			</footer>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('social')}">
		<div class="footer-basic" style="background-color: #0fb800;">
			<footer>
				<p class="copyright" style="color: #ffffff;">Apoco Inc ©
					2018-2019</p>
			</footer>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('business')}">
		<div class="footer-basic" style="background-color: #0007a9;">
			<footer>
				<p class="copyright" style="color: #ffffff;">Apoco Inc ©
					2018-2019</p>
			</footer>
		</div>
	</c:when>
</c:choose>