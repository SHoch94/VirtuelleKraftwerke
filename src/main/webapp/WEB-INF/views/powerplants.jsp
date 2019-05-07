<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Powerplants</title>
	</head>
	<body>
		<div class="main">
			<h2>Kraftwerke</h2>
			<h3 th:text="${message}"></h3>
			<table>
				<tr>
					<td>
						<form action="/main">
							<input type="submit" value="Zur&#252;ck">
						</form>
					</td>
					<td>
						<form action="/types">
							<input type="submit" value="Typen">
						</form>
					</td>
					<td>
						<form action="/virtuals">
							<input type="submit" value="Virtuelle Kraftwerke">
						</form>
					</td>
				</tr>
			</table>
			<div id="addButton">
				<input type="button" value="Hinzuf&#252;gen" onclick="toggleAddMenu();">
			</div>
			<div id="addMenu" style="display:none;">
				<form action="/powerplants/add" method="post" enctype="multipart/form-data">
					<table class="addMenu">
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
								Typ
							</td>	
							<td>
								<select name="type">
									<option th:each="type : ${types}" th:value="${type.getId()}" th:text="${type.getName()}"></option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Leistung
							</td>	
							<td>
								<input type="number" name="powerConversion" value="0">
							</td>
						</tr>
						<tr>
							<td>
								Bild
							</td>	
							<td>
								<input type="file" name="image">
							</td>
						</tr>
						<tr>
							<td>
								Anschaffungsdatum
							</td>	
							<td>
								<input type="date" name="acquisition">
							</td>
						</tr>
						<tr>
							<td>
								Hersteller
							</td>	
							<td>
								<input type="text" name="manufacturer">
							</td>
						</tr>
						<tr>
							<td>
								Kaufpreis
							</td>	
							<td>
								<input type="number" name="price" value="0.00">
							</td>
						</tr>
						<tr>
							<td>
								Standort
							</td>	
							<td>
								<input type="text" name="location">
							</td>
						</tr>
						<tr>
							<td>
								Betriebsdauer in Stunden
							</td>	
							<td>
								<input type="number" name="runtimeInHours" value="0">
							</td>
						</tr>
						<tr>
							<td>
								Zuordnung
							</td>
							<td>
								<select name="virtual">
									<option value="0" th:text="keine"/>
									<option th:each="virtual : ${virtuals}" th:value="${virtual.getId()}" th:text="${virtual.getName()}"></option>
								</select>
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
					<th>Typ</th>
					<th>Leistung</th>
					<th>Bild</th>
					<th>Anschaffungsdatum</th>
					<th>Hersteller</th>
					<th>Kaufpreis</th>
					<th>Standort</th>
					<th>Betriebsdauer</th>
					<th>Zuordnung</th>
				</tr>
				<tr th:if="${powerplants.isEmpty()}">
					<td colspan="13">Keine Kraftwerke angelegt!
					</td>
				</tr>
				<tr th:each="powerplant : ${powerplants}">
					<td>
						<form action="/powerplants/delete">
							<span><input type="text" name="id" th:value="${powerplant.getId()}" style="display:none;"><input type="submit" value="L&#246;schen"></span>
						</form>
					</td>
					<td>
						<form action="/powerplants/edit">
							<span><input type="text" name="id" th:value="${powerplant.getId()}" style="display:none;"><input type="submit" value="Bearbeiten"></span>
						</form>
					</td>
					<td><span th:text="${powerplant.getId()}"> ID </span></td>
					<td><span th:text="${powerplant.getName()}"> Name </span></td>
					<td><span th:text="${typesMap.get(powerplant.getType().getId())}" > Typ </span></td>
					<td><span th:text="${powerplant.getPowerConversion()}"> Leistung </span></td>
					<td>
						<span th:if="${powerplant.getImage() != null}"><img th:src="@{'/powerplants/image/' + ${powerplant.getId()}}" style="width:50px;height:50px"></span>
						<span th:unless="${powerplant.getImage() != null }"> - </span>
					</td>
					<td><span th:text="${#dates.format(powerplant.getAcquisition(), 'dd.MM.yyyy')}"> Anschaffungsdatum </span></td>
					<td><span th:text="${powerplant.getManufacturer()}"> Hersteller </span></td>
					<td><span th:text="${powerplant.getPrice()}"> Kaufpreis </span></td>
					<td><span th:text="${powerplant.getLocation()}"> Standort </span></td>
					<td><span th:text="${powerplant.getRuntimeInHours()}"> Betriebsdauer </span></td>
					<td><span th:text="${powerplant.getVirtualPowerplant() == null ? 'keine' : powerplant.getVirtualPowerplant().getName()}"> Zuordnung </span></td>
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