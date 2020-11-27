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

	<h2>Mon profil</h2>
	<form id="signUpForm" method="POST" action="#">
		<label for="userNameInput">Pseudo : </label>
		<input type="text" id="userNameInput" name="userNameInput" /> <br />
		
		<label for="lastNameInput">Nom : </label>
		<input type="text" id="lastNameInput" name="lastNameInput" /> <br />
		
		<label for="firstNameInput">Prénom : </label>
		<input type="text" id="firstNameInput" name="firstNameInput" /> <br />
		
		<label for="emailInput">Email : </label>
		<input type="email" id="emailInput" name="emailInput" /> <br />
		
		<label for="phoneInput">Téléphone : </label>
		<input type="tel" id="phoneInput" name="phoneInput" pattern="[0-9]{10}" placeholder="0123456789"/> <br />
		
		<label for="streetInput">Rue : </label>
		<input type="text" id="streetInput" name="streetInput" /> <br />
		
		<label for="zipCodeInput">Code postal : </label>
		<input type="text" id="zipCodeInput" name="zipCodeInput" pattern="[0-9]{5}"/> <br />
		
		<label for="townInput">Ville : </label>
		<input type="text" id="lastNameInput" name="lastNameInput" /> <br />
		
		<label for="passwordInput">Mot de passe : </label>
		<input type="password" id="passwordInput" name="passwordInput" /> <br />
		
		<label for="confirmPasswordInput">Confirmation : </label>
		<input type="password" id="confirmPasswordInput" name="confirmPasswordInput" /> <br />
		
		<input type="submit" value="Créer" />
		<a href="<%=request.getContextPath()%>/"><button>Annuler</button></a>
	</form>

</body>
</html>