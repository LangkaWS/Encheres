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
	
	<div class="pageTitle">
		<h1 class="mainTitle">Se connecter</h1>
	</div>
	
	<div class="error">${exception}</div>

	<form id="signInForm" method="POST" action="<%=request.getContextPath()%>/ServletSignIn">
		<table class="signUpInFormTable">
			<tbody>
				<tr>
					<td class="tableLabel">
						<label for="emailInput">Email : </label>
					</td>
					<td class="tableLabel">
						<input type="text" id="emailInput" name="emailInput" value="${email}"/> <br />
					</td>
				</tr>
				<tr>
					<td class="tableLabel">
						<label for="passwordInput">Mot de passe : </label>
					</td>
					<td class="tableLabel">
						<input type="password" id="passwordInput" name="passwordInput" value="${password}" /> <br />
					</td>
				</tr>
			</tbody>
		</table>
		
		<div id="forgotPassword">
			<input type="checkbox" id="rememberMe" name="rememberMe" /><label for="rememberMe">Se souvenir de moi</label>
		</div>
		<div class="submitBox">
			<input type="submit" value="Se connecter" id="submitButton" class="formButton" />
		</div>
	</form>
	<div class="submitBox">
		<a href="#">Mot de passe oublié ?</a>			
	</div>
	<div class="submitBox">
		<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>
		<button class="formButton cancelButton" onclick="location.href='signUp'">Créer un compte</button>
	</div>
</body>
</html>