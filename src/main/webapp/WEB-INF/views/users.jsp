<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Users</title>
	</head>
	<body>
		<div class="main">
			<h2>Benutzer</h2>
			<h3 th:text="${message}"></h3>
			<div>
				<form action="/main">
					<input type="submit" value="Zur&#252;ck">
				</form>
			</div>
			<div id="addButton">
				<input type="button" value="Hinzuf&#252;gen" onclick="toggleAddMenu()">
			</div>
			<div id="addMenu" style="display:none;">
				<form action="/users/add">
					<table>
						<tr>
							<td>
								Name
							</td>	
							<td>
								<input type="text" name="name">
							</td>
						</tr>
						<tr>
							<td>
								Passwort
							</td>
							<td>
								<input type="password" name="password">
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="Abbrechen" onclick="toggleAddMenu()">
							</td>	
							<td>
								<input type="submit" value="Speichern">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<table class="mainContents">
				<tr>
					<th/>
					<th/>
					<th>ID</th>
					<th>Name</th>
				</tr>
				<tr th:if="${users.isEmpty()}">
					<td colspan="4">Keine Benutzer definiert!
					</td>
				</tr>
				<tr th:each="user : ${users}">
					<td>
						<form action="/users/delete">
							<span th:unless="${user.getId() == session.user.getId()}">
								<input type="text" name="id" th:value="${user.getId()}" style="display:none;">
								<input type="submit" value="L&#246;schen">
							</span>
						</form>
					</td>
					<td>
						<form action="/users/edit">
							<span th:unless="${user.getId() == session.user.getId()}">
								<input type="text" name="id" th:value="${user.getId()}" style="display:none;">
								<input type="submit" value="Bearbeiten">
							</span>
						</form>
					</td>
					<td><span th:text="${user.getId()}"> ID </span></td>
					<td><span th:text="${user.getName()}"> Name </span></td>
				</tr>
			</table>
			<script type="text/javascript">
				function toggleAddMenu(){
					var addMenuDiv = document.getElementById('addMenu');
					var addButtonDiv = document.getElementById('addButton');
					if(addMenuDiv.style.display === 'none'){
						addMenuDiv.style.display = 'inline';
						addButtonDiv.style.display = 'none';
					}
					else{
						addMenuDiv.style.display = 'none';
						addButtonDiv.style.display = 'inline';
					}
				}
			</script>
		</div>
	</body>
</html>