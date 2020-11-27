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
	
	<div>${exception}</div>
	
	<form id="signUpForm" method="POST" action="<%=request.getContextPath()%>/ServletSignUp">
		<label for="userNameInput">Pseudo : </label>
		<input type="text" id="userNameInput" name="userNameInput" value="${userName}" required/> <br />
		
		<label for="lastNameInput">Nom : </label>
		<input type="text" id="lastNameInput" name="lastNameInput" value="${lastName}" required/> <br />
		
		<label for="firstNameInput">Prénom : </label>
		<input type="text" id="firstNameInput" name="firstNameInput" value="${firstName}" required/> <br />
		
		<label for="emailInput">Email : </label>
		<input type="email" id="emailInput" name="emailInput" value="${email}" required/> <br />
		
		<label for="phoneInput">Téléphone : </label>
		<input type="tel" id="phoneInput" name="phoneInput" pattern="[0-9]{10}" placeholder="0123456789" value="${phone}"/> <br />
		
		<label for="streetInput">Rue : </label>
		<input type="text" id="streetInput" name="streetInput" value="${street}" required/> <br />
		
		<label for="zipCodeInput">Code postal : </label>
		<input type="text" id="zipCodeInput" name="zipCodeInput" pattern="[0-9]{5}" value="${zipCode}" required/> <br />
		
		<label for="townInput">Ville : </label>
		<input type="text" id="townInput" name="townInput" value="${town}" required/> <br />
		
		<label for="passwordInput">Mot de passe : </label>
		<input type="password" id="passwordInput" name="passwordInput" required/> <br />
		
		<label for="confirmPasswordInput">Confirmation : </label>
		<input type="password" id="confirmPasswordInput" name="confirmPasswordInput" required/> <br />
		
		<input type="submit" value="Créer" />
		<a href=".">Annuler</a>
	</form>

</body>
</html>