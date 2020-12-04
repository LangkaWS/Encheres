<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="../css/style.css">
<title>ENI-Enchères</title>
</head>
<body>

	<%@include file="WEB-INF/fragments/navbar.jspf" %>
	
	<div class="pageTitle">
		<h1 class="mainTitle">Se connecter</h1>
	</div>
	
	<div class="banniere error">${exception}</div>

	<form id="signInForm" method="POST" action="<%=request.getContextPath()%>/ServletSignIn">
		<table class="signUpInFormTable">
			<tbody>
				<tr>
					<td class="tableLabel">
						<label for="emailInput">Email ou pseudo : </label>
					</td>
					<td class="tableLabel">
						<input type="text" id="emailInput" name="loginInput" value="${loginInput}"/> <br />
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
			<input type="submit" value="Se connecter" class="formButton submitButton" />
		</div>
	</form>
	<div class="submitBox">
		<a href="#">Mot de passe oublié ?</a>			
	</div>
	<div class="submitBox">
		<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>
		<button class="formButton infoButton" onclick="location.href='signUp'">Créer un compte</button>
	</div>
</body>
</html>