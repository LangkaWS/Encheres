<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>ENI-Enchères</title>
</head>
<body>

	<%@include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<div class="banner secondary">${info}</div>
	<div class="banner warning">${warning}</div>
	
	<c:if test="${currentUser != null }">

	
		<h2>Bienvenue, ${currentUser.userName} !</h2>
	
	</c:if>
	<c:if test="${currentUser == null }">
		<h3>Vous n'êtes pas encore authentifié... Inscrivez-vous ou connectez-vous !</h3>
	
	</c:if>
	
	<h2>Liste des enchères</h2>
	<form id="searchDiv" method="GET" action="<%=request.getContextPath()%>/filter">
		<div id="searchParams">
			<div id="searchParamsPublic">
				<label for="filterSearch">Filtres :</label><br />
				<input type="text" name="filterSearch" id="filterSearch" placeholder="Le nom de l'article contient..." /><br />
				
				<label for="selectCategory">Catégorie : </label>
				<select name="selectCategory" id="selectCategory">
					<option selected value="all">Toutes</option>
					<c:forEach var="c" items="${catList}">
					<option value="${c.categoryId}">${c.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<div id="searchParamsConnected">
			
				<div id="buyFilterParams"class="connectedParams" >
					<input type="radio" id="listBuy" name="filterBuyOrSell" value="buy" onclick="toggleBuySell('buy')" />
					<label for="listBuy" onclick="toggleBuyButtons()">Achats</label>
					<div id="buyFilterCheckbox"class="connectedParams" >
						<input type="checkbox" id="buyInProgressAuctions" class="buyBox" name="buyInProgressAuctions" value="c" />
						<label for="buyInProgressAuctions" class="buyFilterLabel">enchères ouvertes</label>
						<input type="checkbox" id="buyParticipatingInProgressAuctions" class="buyBox" name="buyParticipatingInProgressAuctions" value="c" />
						<label for="buyParticipatingInProgressAuctions" class="buyFilterLabel">mes enchères en cours</label>
						<input type="checkbox" id="buyParticipatingEnded" class="buyBox" name="buyParticipatingEnded" value="c" />
						<label for="buyParticipatingEnded" class="buyFilterLabel">mes enchères remportées</label>
					</div>
				</div>
				
				<div id="sellFilterParams"class="connectedParams" >
					<input type="radio" id="listSell" name="filterBuyOrSell" value="sell" onclick="toggleBuySell('sell')" />
					<label for="listSell" onclick="toggleSellButtons()">Mes ventes</label>
					<div id="sellFilterCheckbox"class="connectedParams" >
						<input type="checkbox" id="sellInProgress" class="sellBox" name="sellInProgress" value="c" />
						<label for="sellInProgress" class="sellFilterLabel">mes ventes en cours</label>
						<input type="checkbox" id="sellCreated" class="sellBox" name="sellCreated" value="c" />
						<label for="sellCreated" class="sellFilterLabel">ventes non débutées</label>
						<input type="checkbox" id="sellEnded" class="sellBox" name="sellEnded" value="c" />
						<label for="sellEnded" class="sellFilterLabel">ventes terminées</label>
					</div>
				</div>
			</div>
			
		</div>
		<div id="searchButtonDiv">
			<input type="submit" value="Rechercher" id="searchButton">
		</div>
	</form>
	
	<div id="articlesDisplayWelcome">
		<c:forEach var="a" items="${artList}">
			<div class="articleTile" onclick="location.href='<%=request.getContextPath()%>/auction?id=${a.articleId}'">
				<p class="articleName">${a.name}</p>
				<p class="articleDescription">${a.description}</p>
				<p class="articlePrice">${a.startPrice}</p>
			</div>
		</c:forEach>
	</div>
	
	<script>
		var user = "${currentUser.getUserName()}";
		var buySell = "${buySell}";
		var f1 = "${f1}";
		var f2 = "${f2}";
		var f3 = "${f3}";
	</script>
	<script src="js/main.js"></script>
	
	<!--  
	<script type="text/javascript">
	window.onload = toggleVisibility();
	
	function toggleBuyButtons() {
		enableLabel("buyFilterLabel");
		enableCheckbox("buyInProgressAuctions");
		enableCheckbox("buyParticipatingInProgressAuctions");
		enableCheckbox("buyParticipatingEnded");
		
		disableLabel("sellFilterLabel");
		disableCheckbox("sellInProgress");
		disableCheckbox("sellCreated");
		disableCheckbox("sellEnded");
	}
	
	function toggleSellButtons() {
		enableLabel("sellFilterLabel");
		enableCheckbox("sellInProgress");
		enableCheckbox("sellCreated");
		enableCheckbox("sellEnded");
		
		disableLabel("buyFilterLabel");
		disableCheckbox("buyInProgressAuctions");
		disableCheckbox("buyParticipatingInProgressAuctions");
		disableCheckbox("buyParticipatingEnded");
	}
	
	function disableCheckbox(elementId) {
		document.getElementById(elementId).checked = false;
		document.getElementById(elementId).disabled = true;
	}
	
	function enableCheckbox(elementId) {
		document.getElementById(elementId).disabled = false;
	}
	
	function enableLabel(className) {
		var list = document.getElementsByClassName(className);
		for(var i = 0; i < list.length; i++) {
			list[i].style.color = "black";
		}
	}
	
	function disableLabel(className) {
		var list = document.getElementsByClassName(className);
		for(var i = 0; i < list.length; i++) {
			list[i].style.color = "gray";
		}
	}
	
		function toggleVisibility() {
			var user = "${currentUser.getUserName()}";
			if(user !== ""){
				var c = document.getElementsByClassName("connectedParams");
				for(i = 0; i < c.length; i++) {
					c[i].style.display = "block";
				}
			}
		}
	
	</script>
	-->
	
</body>
</html>