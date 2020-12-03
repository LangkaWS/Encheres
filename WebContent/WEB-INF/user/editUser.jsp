<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<% 
			User currentUser = null;
			if (session.getAttribute("currentUser") != null) {
				currentUser = (User) session.getAttribute("currentUser");
			} 
		%>
		<header>
			<h1>ENI-Enchères</h1>
		</header>
		<div class="userPage"> 
			<h2>Editer mon profil</h2>
			
			<p>Laissez vides les champs que vous ne souhaitez pas modifier. Renseignez votre mot de passe actuel pour confirmer les changements.</p>
			
			<div class="banniere error">${exception}</div>
			
			<form id="editForm" method="POST" action="<%=request.getContextPath()%>/ServletEditUser">
			<table class="signUpInFormTable">
				<tbody>
					<tr>
						<td class="tableLabel">
							<label for="userNameInput">Pseudo : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="userNameInput" name="userNameInput" value="<%= currentUser.getUserName() %>" />
						</td>
						<td class="tableLabel">
							<label for="lastNameInput">Nom : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="lastNameInput" name="lastNameInput" value="<%= currentUser.getLastName() %>" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="firstNameInput">Prénom : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="firstNameInput" name="firstNameInput" value="<%= currentUser.getFirstName() %>" />
						</td>
						<td class="tableLabel">
							<label for="emailInput">Email : </label>
						</td>
						<td class="tableInput">
							<input type="email" id="emailInput" name="emailInput" value="<%= currentUser.getEmail() %>" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="phoneInput">Téléphone : </label>
						</td>
						<td class="tableInput">
							<input type="tel" id="phoneInput" name="phoneInput" pattern="[0-9]{10}" value="<%= currentUser.getPhone() %>"/>
						</td>
						<td class="tableLabel">
							<label for="streetInput">Rue : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="streetInput" name="streetInput" value="<%= currentUser.getStreet() %>" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="zipCodeInput">Code postal : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="zipCodeInput" name="zipCodeInput" pattern="[0-9]{5}" value="<%= currentUser.getZipCode() %>" />
						</td>
						<td class="tableLabel">
							<label for="townInput">Ville : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="townInput" name="townInput" value="<%= currentUser.getTown() %>" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="newPasswordInput">Nouveau mot de passe : </label>
						</td>
						<td class="tableInput">
							<input type="password" id="newPasswordInput" name="newPasswordInput" />
						</td>
						<td class="tableLabel">
							<label for="confirmPasswordInput">Confirmation : </label>
						</td>
						<td class="tableInput">
							<input type="password" id="confirmPasswordInput" name="confirmPasswordInput" />
						</td>
					</tr>

					<tr>
						<td class="tableLabel">
							<label for="currentPasswordInput">Mot de passe actuel: </label>
						</td>
						<td class="tableInput">
							<input type="password" id="currentPasswordInput" name="currentPasswordInput" required/>
						</td>
					</tr>
				</tbody>
			</table>
				<div class="submitBox">
					<input type="submit" value="Modifier" class="formButton submitButton" />
				</div>
				<div class="submitBox">
					<button class="formButton cancelButton" onclick="location.href='/Encheres/ServletShowUser?userId=<%= currentUser.getUserId() %>'">Annuler</button>
				</div>
			</form>
		</div>
	</body>
</html>