<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            10/14/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.1
   - Module Name:     playGames.jsp
   - Module Version:  1.0
   - Summary:         This view is used to load the minesweeper game with the desired difficulty
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div align="center">
	<h1>Minesweeper</h1>
	<hr />
	<form method="POST" action="../mines/start">
		<div class="row">
			<div class="col-md-4">
				<button class="btn btn-primary btn-large" style="float: right;" name="diff" value="1">Play
					an easy game</button>
			</div>
			<div class="col-md-4">
				<button class="btn btn-warning btn-large" name="diff" value="2">Play
					a medium game</button>
			</div>
			<div class="col-md-4">
				<button class="btn btn-danger btn-large" style="float: left;" name="diff" value="3">Play
					a hard game</button>
			</div>
		</div>
	</form>
	<hr />
	<h1>More coming soon!</h1>
</div>