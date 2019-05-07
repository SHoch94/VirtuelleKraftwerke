<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit User</title>
	</head>
	<body>
		<div class="main">
			<h2>Benutzer bearbeiten</h2>
			<h3 th:text="${message}"></h3>
			<div>
				<form action="/users">
					<input type="submit" value="Zur&#252;ck">
				</form>
			</div>
			<form action="/users/save">
				<table>
					<tr>
						<td>
							ID
						</td>
						<td>
							<input type="text" name="id" th:value="${user.getId()}" style="display:none;">
							<span th:text="${user.getId() }"></span>
						</td>
					</tr>
					<tr>
						<td>
							Name
						</td>	
						<td>
							<input type="text" name="name" th:value="${user.getName()}">
						</td>
					</tr>
					<tr>
						<td>
							Passwort
						</td>
						<td>
							<input type="password" name="password" th:value="${user.getPassword() }">
						</td>
					</tr>
					<tr>
						<td/>	
						<td>
							<input type="submit" value="Speichern">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>