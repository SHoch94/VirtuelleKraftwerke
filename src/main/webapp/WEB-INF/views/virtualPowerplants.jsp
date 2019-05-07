<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Virtuals</title>
	</head>
	<body>
		<div class="main">
			<h2>Virtuelle Kraftwerke</h2>
			<h3 th:text="${message}"></h3>
			<div>
				<form action="/powerplants">
					<input type="submit" value="Zur&#252;ck">
				</form>
			</div>
			<div id="addButton">
				<input type="button" value="Hinzuf&#252;gen" onclick="toggleAddMenu()">
			</div>
			<div id="addMenu">
				<form action="/virtuals/add">
					<input type="text" name="name"><br>
					<input type="button" value="Abbrechen" onclick="toggleAddMenu()"><input type="submit" value="Speichern">
				</form>
			</div>
			<table class="mainContents">
				<tr>
					<th/>
					<th>ID</th>
					<th>Name</th>
					<th>Gesamtleistung</th>
				</tr>
				<tr th:if="${virtualPowerplants.isEmpty()}">
					<td colspan="4">Keine virtuellen Kraftwerke definiert!
					</td>
				</tr>
				<tr th:each="virtualPowerplant : ${virtualPowerplants}">
					<td>
						<form action="/virtuals/delete">
							<span>
								<input type="text" name="id" th:value="${virtualPowerplant.getId()}" style="display:none;"><input type="submit" value="L&#246;schen">
							</span>
						</form>
					</td>
					<td><span th:text="${virtualPowerplant.getId()}"> ID </span></td>
					<td><span th:text="${virtualPowerplant.getName()}"> Name </span></td>
					<td><span th:text="${overallPower.get(virtualPowerplant.getId())}"> Gesamtleistung </span></td>
				</tr>
			</table>
			<script type="text/javascript">
				(function(){
					document.getElementById('addMenu').style.display = 'none';
				})();
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