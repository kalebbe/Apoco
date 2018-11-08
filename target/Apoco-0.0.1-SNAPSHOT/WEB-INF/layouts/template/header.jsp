<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            09/23/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.0
   - Module Name:     header.jsp
   - Module Version:  1.0
   - Summary:         This is the header used for the default pages. As mentioned in the footer,
   -				  there will be new fragments in the future for bus, soc, and dating.
 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${sessionScope.theme == null}">
		<div style="background-color: #ff0000;">
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="background-color: #a70000;">
				<div class="container">
					<!-- Sends the user either to the index or userHome depending if they're logged in -->
					<c:choose>
						<c:when test="${sessionScope.id != null}">
							<a class="navbar-brand" href="../home/user"
								style="color: #000000; font-size: 30px;">Apoco</a>
						</c:when>
						<c:otherwise>
							<a class="navbar-brand" href="../"
								style="color: #000000; font-size: 30px;">Apoco</a>
						</c:otherwise>
					</c:choose>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav ml-auto">
							<c:choose>
								<c:when test="${sessionScope.id != null}">
									<li class="nav-item" role="presentation"><a
										class="nav-link" href="../social/social"
										style="color: #0fb800; font-size: 20px; font-family: 'Alegreya SC', serif;">Social</a></li>

									<li class="nav-item" role="presentation"><a
										class="nav-link" href="#"
										style="font-size: 20px; color: rgb(0, 7, 169); font-family: Aldrich, sans-serif;">Business</a></li>
									<li class="nav-item" role="presentation"><a
										class="nav-link" href="#"
										style="font-size: 20px; color: #dd00c8; font-family: 'Abril Fatface', cursive;">Dating</a></li>
								</c:when>
								<c:otherwise>
									<li class="nav-item" role="presentation"><a
										class="nav-link" href="../login/redirect"
										style="color: #0fb800; font-size: 20px; font-family: 'Alegreya SC', serif;">Social</a></li>
									<li class="nav-item" role="presentation"><a
										class="nav-link" href="../login/redirect"
										style="font-size: 20px; color: rgb(0, 7, 169); font-family: Aldrich, sans-serif;">Business</a></li>
									<li class="nav-item" role="presentation"><a
										class="nav-link" href="../login/redirect"
										style="font-size: 20px; color: #dd00c8; font-family: 'Abril Fatface', cursive;">Dating</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div>
			<nav
				class="navbar navbar-light navbar-expand-md navigation-clean-button"
				style="background-color: #6c6a6a; height: 50px;">
				<div class="container">
					<a class="navbar-brand" href="#" style="height: 0px;"></a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav mr-auto"></ul>
						<span class="navbar-text actions"> <!-- Changes the default navbar if the user is logged in -->
							<c:choose>
								<c:when test="${sessionScope.id != null}">
									<a href="../account/edit" class="login"
										style="color: #ffffff; font-size: 14px;">Edit Account</a>
									<a class="btn btn-light action-button" role="button"
										href="../logout/log"
										style="background-color: #000000; color: #ffffff; font-size: 14px;">Log
										Out</a>
								</c:when>
								<c:otherwise>
									<a href="../login/log" class="login"
										style="color: #ffffff; font-size: 14px;">Log In</a>
									<a class="btn btn-light action-button" role="button"
										href="../registration/reg"
										style="background-color: #000000; color: #ffffff; font-size: 14px;">Sign
										Up</a>
								</c:otherwise>
							</c:choose>
						</span>
					</div>
				</div>
			</nav>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('social')}">
		<div style="background-color: #ff0000;">
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="background-color: #0fb800;">
				<div class="container">
					<a class="navbar-brand" href="../home/user"
						style="color: #000000; font-size: 30px;">Apoco</a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="font-size: 20px; color: rgb(0, 7, 169); font-family: Aldrich, sans-serif;">Business</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="font-size: 20px; color: #dd00c8; font-family: 'Abril Fatface', cursive;">Dating</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div>
			<nav
				class="navbar navbar-light navbar-expand-md navigation-clean-button"
				style="background-color: #6c6a6a; height: 60px;">
				<div class="container">
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav mr-auto">
							<c:choose>
								<c:when test="${sessionScope.hasSocial != null}">
									<li class="nav-item" role="presentation"><a style="color: #ffffff;" class="nav-link" href="../social/social">Dashboard</a></li>
									<li class="nav-item" role="presentation"><a style="color: #ffffff;" class="nav-link" href="../feed/feed">View Feed</a></li>
									<li class="nav-item" role="presentation"><a style="color: #ffffff;" class="nav-link" href="../social/games">Play Games</a></li>
								</c:when>
							</c:choose>
						</ul>
						<span class="navbar-text actions"> <a href="../home/user"
							class="login" style="color: #ffffff; font-size: 14px;"><!-- View Profile --></a><a
							class="btn btn-light action-button" role="button"
							href="../logout/log"
							style="background-color: #000000; color: #ffffff; font-size: 14px;">Log
								Out</a></span>
					</div>
				</div>
			</nav>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('dating')}">
		<div style="background-color: #ff0000;">
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="background-color: #dd00c8;">
				<div class="container">
					<a class="navbar-brand" href="#"
						style="color: #000000; font-size: 30px;">Apoco</a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="color: #0fb800; font-size: 20px; font-family: 'Alegreya SC', serif;">Social</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="font-size: 20px; color: rgb(0, 7, 169); font-family: Aldrich, sans-serif;">Business</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div>
			<nav
				class="navbar navbar-light navbar-expand-md navigation-clean-button"
				style="background-color: #6c6a6a; height: 60px;">
				<div class="container">
					<a class="navbar-brand" href="#" style="height: 0px;"></a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav mr-auto"></ul>
						<span class="navbar-text actions"> <a href="../home/user"
							class="login" style="color: #ffffff; font-size: 14px;">Apoco Home</a><a
							class="btn btn-light action-button" role="button"
							href="../logout/log"
							style="background-color: #000000; color: #ffffff; font-size: 14px;">Log
								Out</a></span>
					</div>
				</div>
			</nav>
		</div>
	</c:when>
	<c:when test="${sessionScope.theme.equals('business')}">
		<div style="background-color: #ff0000;">
			<nav class="navbar navbar-light navbar-expand-md navigation-clean"
				style="background-color: #0007a9;">
				<div class="container">
					<a class="navbar-brand" href="#"
						style="color: #000000; font-size: 30px;">Apoco</a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav ml-auto">
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="color: #0fb800; font-size: 20px; font-family: 'Alegreya SC', serif;">Social</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								href="#"
								style="font-size: 20px; color: #dd00c8; font-family: 'Abril Fatface', cursive;">Dating</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div>
			<nav
				class="navbar navbar-light navbar-expand-md navigation-clean-button"
				style="background-color: #6c6a6a; height: 60px;">
				<div class="container">
					<a class="navbar-brand" href="#" style="height: 0px;"></a>
					<button class="navbar-toggler" data-toggle="collapse"
						data-target="#navcol-1">
						<span class="sr-only">Toggle navigation</span><span
							class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navcol-1">
						<ul class="nav navbar-nav mr-auto"></ul>
						<span class="navbar-text actions"> <a href="../home/user"
							class="login" style="color: #ffffff; font-size: 14px;">Apoco Home</a><a
							class="btn btn-light action-button" role="button"
							href="../logout/log"
							style="background-color: #000000; color: #ffffff; font-size: 14px;">Log
								Out</a></span>
					</div>
				</div>
			</nav>
		</div>
	</c:when>
</c:choose>