<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Enchères</title>
</head>
<body>

<header>
<h1>ENI-Enchères</h1>
<a href="signIn">S'inscrire - Se connecter</a>
</header>

<h2>Liste des enchères</h2>
<form id="searchDiv" method="GET" action="#">
<label for="filterSearch">Filtres :</label><br />
<input type="text" name="filterSearch" id="filterSearch" placeholder="Le nom de l'article contient..."/><br />
<label for="selectCategory">Catégorie : </label>
<select id="selectCategory">
<option>Toutes</option>
</select>
<input type="submit" value="Rechercher">
</form>

</body>
</html>