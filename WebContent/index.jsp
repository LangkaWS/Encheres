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

	<% 
	User currentUser = null;
	if (session.getAttribute("currentUser") != null) {
		currentUser = (User) session.getAttribute("currentUser");
	} %>
	
	<header>
		<h1>ENI-Enchères</h1>
		
		<% if (session.getAttribute("currentUser") != null) { %>
			
			<form id="signOutForm" method="POST" action="<%=request.getContextPath()%>/ServletSignOut">
				<a class="logLink" id="logOutLink" onclick="this.closest('form').submit();return false;">Se déconnecter</a>
			</form>
		
		<% } else { %>
		
			<a href="signIn" class="logLink">S'inscrire - Se connecter</a>
		
		<% }%>
		
	</header>
	
	<% if (session.getAttribute("currentUser") != null) { %>
	
		<h2>Bienvenue, <%= currentUser.getUserName() %> !</h2>
	
	<% } else { %>
	
		<h3>Vous n'êtes pas encore authentifié... Inscrivez-vous ou connectez-vous !</h3>
	
	<% }%>
	
	<h2>Liste des enchères</h2>
	<form id="searchDiv" method="POST" action="<%=request.getContextPath()%>/filter">
		<div id="searchParams">
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
		<div id="searchButtonDiv">
			<input type="submit" value="Rechercher" id="searchButton">
		</div>
	</form>
	
	<div id="articlesDisplayWelcome">
		<c:forEach var="a" items="${artList}">
			<div class="articleTile">
				<p class="articleName">${a.name}</p>
				<p class="articleDescription">${a.description}</p>
				<p class="articlePrice">${a.startPrice}</p>
			</div>
		</c:forEach>
	</div>
	
</body>
</html>