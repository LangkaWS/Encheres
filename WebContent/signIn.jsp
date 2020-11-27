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
	</header>

	<form id="signInForm" method="POST" action="#">
		<label for="idInput">Identifiant : </label>
		<input type="text" id="idInput" name="idInput" /> <br />
		
		<label for="passwordInput">Mot de passe : </label>
		<input type="password" id="passwordInput" name="passwordInput" /> <br />
		
		<input type="submit" value="Connexion" />
		
		<input type="checkbox" id="rememberMe" name="rememberMe" /> <label for="rememberMe">Se souvenir de moi</label>
		<a href="#">Mot de passe oublié</a>
	</form>

	<a href="signUp"><button>Créer un compte</button></a>

</body>
</html>