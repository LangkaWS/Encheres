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

	<header>
		<h1>ENI-Enchères</h1>
		<a href="signIn" id="signInLink">S'inscrire - Se connecter</a>
	</header>

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
				<p class="articleName">${a.name}</h5p>
				<p class="articleDescription">${a.description}</p>
				<p class="articlePrice">${a.startPrice}</p>
			</div>
		</c:forEach>
	</div>
	
</body>
</html>