<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>ENI-Enchères</title>
</head>
<body>

<p>Hello there !</p>
<p>It's a me, Mario !</p>
	<p>Hello there !</p>

	<p>Hello</p>

	<%@include file="/WEB-INF/fragments/navbar.jspf" %>
	
	<div class="banner secondary">${info}</div>
	<div class="banner warning">${warning}</div>
	
	<% if (session.getAttribute("currentUser") != null) { %>
	
		<h2>Bienvenue, <%= currentUser.getUserName() %> !</h2>
	
	<% } else { %>
	
		<h3>Vous n'êtes pas encore authentifié... Inscrivez-vous ou connectez-vous !</h3>
	
	<% }%>
	
	<h2>Liste des enchères</h2>
	<form id="searchDiv" method="POST" action="<%=request.getContextPath()%>/filter">
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
					<input type="radio" id="listBuy" name="filterBuyOrSell" value="buy" checked />
					<label for="listBuy">Achats</label>
					<div id="buyFilterCheckbox"class="connectedParams" >
						<input type="checkbox" id="buyInProgressAuctions" name="buyInProgressAuctions" value="c" checked />
						<label for="buyInProgressAuctions">enchères ouvertes</label>
						<input type="checkbox" id="buyParticipatingInProgressAuctions" name="buyParticipatingInProgressAuctions" value="c" />
						<label for="buyParticipatingInProgressAuctions">mes enchères en cours</label>
						<input type="checkbox" id="buyParticipatingEnded" name="buyParticipatingEnded" value="c" />
						<label for="buyParticipatingEnded">mes enchères remportées</label>
					</div>
				</div>
				<div id="sellFilterParams"class="connectedParams" >
					<input type="radio" id="listSell" name="filterBuyOrSell" value="sell" />
					<label for="listSell">Mes ventes</label>
					<div id="sellFilterCheckbox"class="connectedParams" >
						<input type="checkbox" id="sellInProgress" name="sellInProgress" value="c" />
						<label for="sellInProgress">mes ventes en cours</label>
						<input type="checkbox" id="sellCreated" name="sellCreated" value="c" />
						<label for="sellCreated">ventes non débutées</label>
						<input type="checkbox" id="sellEnded" name="sellEnded" value="c" />
						<label for="sellEnded">ventes terminées</label>
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
			<div class="articleTile" onclick="location.href='<%=request.getContextPath()%>/auctionDetail?id=${a.articleId}'">
				<p class="articleName">${a.name}</p>
				<p class="articleDescription">${a.description}</p>
				<p class="articlePrice">${a.startPrice}</p>
			</div>
		</c:forEach>
	</div>
	
	<script type="text/javascript">
	
	window.onload = toggleVisibility;
	
		function toggleVisibility() {
			var user = "${currentUser.getUserName()}";
			if(user !== ""){
				console.log("connected");
				var c = document.getElementsByClassName("connectedParams");
				for(i = 0; i < c.length; i++) {
					c[i].style.display = "block";
				}
			} else {
				console.log("disconnected");
			}
		}
	
	</script>
	
</body>
</html>