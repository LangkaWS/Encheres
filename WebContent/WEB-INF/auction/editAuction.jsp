<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>ENI-Enchères</title>
</head>
<body>

	<%@include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<h2>Modifier une vente</h2>
	
	<form method="POST" action="<%=request.getContextPath()%>/edit/auctionDetail?id=${art.articleId}">
	
		<label for="articleName">Article : </label>
		<input type="text" id="articleName" name="articleName" value="${art.name}" required />
		<br />
		
		<label for="articleDescription">Description : </label>
		<textarea id="articleDescription" name="articleDescription" rows="3" required>${art.description}</textarea>
		<br />
		
		<label for="category">Catégorie : </label>
		<select id="category" name="category" required>
			<c:forEach var="c" items="${applicationScope.catList}">
				<option value="${c.categoryId}" ${c.categoryId == art.categoryId ? "selected" : ""}>${c.name}</option>
			</c:forEach>
		</select>
		<br />
		
		<label for="picture">Photo : </label>
		<br />
		
		<label for="articleStartPrice">Mise à prix : </label>
		<input type="number" id="articleStartPrice" name="articleStartPrice" value="${art.startPrice}" required />
		<br />
		
		<!-- Pour Chrome, Edge et Opera -->
		
		<label for="startDate">Début de l'enchère : </label>
		<input type="datetime-local" id="startDate" name="startDate" min="1899-01-01T00:00" value="${art.auctionStartDate}" required />
		<br />
		
		<label for="endDate">Fin de l'enchère : </label>
		<input type="datetime-local" id="endDate" name="endDate" min="1899-01-01T00:00" value="${art.auctionEndDate}" required />
		<br />
	
		<fieldset>
		
		<legend>Retrait</legend>
			<label for="street">Rue : </label>
			<input type="text" id="street" name="street" value="${empty pickUp.street ? sessionScope.currentUser.street : pickUp.street}" required/>
			<br />
			
			<label for="zipCode">Code postal : </label>
			<input type="text" id="zipCode" name="zipCode" pattern="[0-9]{5}" value="${empty pickUp.zipCode ? sessionScope.currentUser.zipCode : pickUp.zipCode}" required/>
			<br />
			
			<label for="town">Ville : </label>
			<input type="text" id="town" name="town" value="${empty pickUp.town ? sessionScope.currentUser.town : pickUp.town}" required/>
			<br />
		</fieldset>
		
		<input type="submit" value="Enregistrer" class="formButton submitButton" />
		<button class="formButton cancelButton" onclick="location.href='<%=request.getContextPath()%>/auctionDetail?id=${art.articleId}'">Annuler</button>	
	</form>
	

	<script>
		var today = new Date();
		console.log(today.toString());
		console.log(today.getDate());
		var dd = today.getDate().toString.length == 1 ? "0" + today.getDate().toString() : today.getDate();
		var MM = (today.getMonth()).toString().length == 1 ? "0" + (today.getMonth()+1).toString() : today.getMonth()+1;
		var yyyy = today.getFullYear();
		var HH = today.getHours().toString().length == 1 ? "0" + today.getHours().toString() : today.getHours();
		var mm = today.getMinutes().toString().length == 1 ? "0" + today.getMinutes().toString() : today.getMinutes();
		
		var formattedDate = yyyy + "-" + MM + "-" + dd + "T" + HH + ":" + mm;
		document.getElementById("startDate").setAttribute("min", formattedDate);
		document.getElementById("endDate").setAttribute("min", formattedDate);
	</script>

</body>
</html>