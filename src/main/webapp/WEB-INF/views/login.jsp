<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
	</head>
	<body>
		<div class="main">
			<h1>Login</h1>
			<h3 th:text="${message}"></h3>
			<form method="post" action="/login">
				<table>
					<tr>
						<td>
							Name:
						</td>
						<td>
							<input type="text" name="name">
						</td>
					</tr>
					<tr>
						<td>
							Passwort:
						</td>
						<td>
							<input type="password" name="password">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Login">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>