<html xmlns:th="http://www.thymeleaf.org">
	<link rel="stylesheet" type="text/css" media="all"  
    	  href="/css/styles.css" th:href="@{/css/styles.css}" />
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Powerplant</title>
	</head>
	<body>
		<div class="main">
			<h2>Kraftwerk bearbeiten</h2>
			<h3 th:text="${message}"></h3>
			<div>
				<form action="/powerplants">
					<input type="submit" value="Zur&#252;ck">
				</form>
			</div>
			<form action="/powerplants/save" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							ID
						</td>
						<td>
							<input type="text" name="id" th:value="${powerplant.getId()}" style="display:none;">
							<span th:text="${powerplant.getId() }"></span>
						</td>
					</tr>
					<tr>
						<td>
							Name
						</td>	
						<td>
							<input type="text" name="name" th:value="${powerplant.getName()}">
						</td>
					</tr>
					<tr>
						<td>
							Typ
						</td>	
						<td>
							<select name="type" th:value="${powerplant.getType().getId() }">
								<option th:each="type : ${types}" th:value="${type.getId()}" th:text="${type.getName()}"></option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Leistung
						</td>	
						<td>
							<input type="number" name="powerConversion" th:value="${powerplant.getPowerConversion()}">
						</td>
					</tr>
					<tr>
						<td>
							Bild
						</td>	
						<td>
							<span th:if="${powerplant.getImage() != null }">
								<img th:src="@{'/powerplants/image/' + ${powerplant.getId()}}" style="width:50px;height:50px">
							</span>
							<input type="file" name="image" onchange="setUpdate()">
							<input type="text" name="updateImage" id="updateImage" value="0" style="display:none;">
						</td>
					</tr>
					<tr>
						<td>
							Anschaffungsdatum
						</td>	
						<td>
							<input type="text" id="acquisitionDay" style="display:none;" th:value="${#dates.format(powerplant.getAcquisition(), 'dd')}">
							<input type="text" id="acquisitionMonth" style="display:none;" th:value="${#dates.format(powerplant.getAcquisition(), 'MM')}">
							<input type="text" id="acquisitionYear" style="display:none;" th:value="${#dates.format(powerplant.getAcquisition(), 'yyyy')}">
							<input type="date" name="acquisition" th:value="${powerplant.getAcquisition() }">
						</td>
					</tr>
					<tr>
						<td>
							Hersteller
						</td>
						<td>
							<input type="text" name="manufacturer" th:value="${powerplant.getManufacturer() }">
						</td>
					</tr>
					<tr>
						<td>
							Kaufpreis
						</td>	
						<td>
							<input type="number" name="price" value="0.00" th:value="${powerplant.getPrice() }">
						</td>
					</tr>
					<tr>
						<td>
							Standort
						</td>	
						<td>
							<input type="text" name="location" th:value="${powerplant.getLocation() }">
						</td>
					</tr>
					<tr>
						<td>
							Betriebsdauer in Stunden
						</td>	
						<td>
							<input type="number" name="runtimeInHours" value="0" th:value="${powerplant.getRuntimeInHours() }">
						</td>
					</tr>
					<tr>
						<td>
							Zuordnung
						</td>
						<td>
							<select name="virtual" th:value="${powerplant.getVirtualPowerplant() == null ? 0 : powerplant.getVirtualPowerplant().getId()}">
								<option value="0" th:text="keine"/>
								<option th:each="virtual : ${virtuals}" th:value="${virtual.getId()}" th:text="${virtual.getName()}"></option>
							</select>
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
		<script>
			(function(){
				var dateControl = document.querySelector('input[type="date"]');
				var day = document.getElementById('acquisitionDay').value;
				var month = document.getElementById('acquisitionMonth').value;
				var year = document.getElementById('acquisitionYear').value;
				dateControl.value = year + '-' + month + '-' + day;
			})();
			function setUpdate(){
				document.getElementById('updateImage').value = '1';
			}
		</script>
	</body>
</html>