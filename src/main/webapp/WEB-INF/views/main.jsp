<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Main</title>
	</head>
	<body>
		<div class="main">
			<h2>Hauptmenu</h2>
			<h3 th:text="${message}"></h3>
			<form action="powerplants">
				<input type="submit" value="Kraftwerke">
			</form>
			<form action="users">
				<input type="submit" value="Benutzer">
			</form>
			<form action="logout">
				<input type="submit" value="Logout">
			</form>
		</div>
	</body>
</html>