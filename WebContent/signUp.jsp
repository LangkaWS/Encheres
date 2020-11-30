<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	</header>

	<h2>Mon profil</h2>
	
	<div class="error">${exception}</div>
	
	<form id="signUpForm" method="POST" action="<%=request.getContextPath()%>/ServletSignUp">
	<table id="signUpFormTable">
		<tbody>
			<tr>
				<td class="tableLabel">
					<label for="userNameInput">Pseudo : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="userNameInput" name="userNameInput" value="${userName}" required/>
				</td>
				<td class="tableLabel">
					<label for="lastNameInput">Nom : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="lastNameInput" name="lastNameInput" value="${lastName}" required/>
				</td>
			</tr>
			
			<tr>
				<td class="tableLabel">
					<label for="firstNameInput">Prénom : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="firstNameInput" name="firstNameInput" value="${firstName}" required/>
				</td>
				<td class="tableLabel">
					<label for="emailInput">Email : </label>
				</td>
				<td class="tableInput">
					<input type="email" id="emailInput" name="emailInput" value="${email}" required/>
				</td>
			</tr>
			
			<tr>
				<td class="tableLabel">
					<label for="phoneInput">Téléphone : </label>
				</td>
				<td class="tableInput">
					<input type="tel" id="phoneInput" name="phoneInput" pattern="[0-9]{10}" placeholder="0123456789" value="${phone}"/>
				</td>
				<td class="tableLabel">
					<label for="streetInput">Rue : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="streetInput" name="streetInput" value="${street}" required/>
				</td>
			</tr>
			
			<tr>
				<td class="tableLabel">
					<label for="zipCodeInput">Code postal : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="zipCodeInput" name="zipCodeInput" pattern="[0-9]{5}" value="${zipCode}" required/>
				</td>
				<td class="tableLabel">
					<label for="townInput">Ville : </label>
				</td>
				<td class="tableInput">
					<input type="text" id="townInput" name="townInput" value="${town}" required/>
				</td>
			</tr>
			
			<tr>
				<td class="tableLabel">
					<label for="passwordInput">Mot de passe : </label>
				</td>
				<td class="tableInput">
					<input type="password" id="passwordInput" name="passwordInput" required/>
				</td>
				<td class="tableLabel">
					<label for="confirmPasswordInput">Confirmation : </label>
				</td>
				<td class="tableInput">
					<input type="password" id="confirmPasswordInput" name="confirmPasswordInput" required/>
				</td>
			</tr>
		</tbody>
	</table>
		<div id="submitBox">
			<input type="submit" value="Créer" id="submitButton" class="formButton" />
			<button id="cancelButton" class="formButton" onclick="location.href='.'">Annuler</button>
		</div>
	</form>

</body>
</html>