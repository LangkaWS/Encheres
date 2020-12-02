<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<header>
			<h1>ENI-Enchères</h1>
		</header>
		<div class="userPage"> 
			<% 
				User currentUser = null;
				if (session.getAttribute("currentUser") != null) {
					currentUser = (User) session.getAttribute("currentUser");
				} 
			%>
		
			<h2>Editer mon profil</h2>
			
			<p>Laissez vides les champs que vous ne souhaitez pas modifier. Renseignez votre mot de passe actuel pour confirmer les changements.</p>
			
			<div class="error">${exception}</div>
			
			<form id="editForm" method="POST" action="<%=request.getContextPath()%>/ServletEditUser">
			<table class="signUpInFormTable">
				<tbody>
					<tr>
						<td class="tableLabel">
							<label for="userNameInput">Pseudo : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="userNameInput" name="userNameInput" placeholder="<%= currentUser.getUserName() %>" value="${userName}" />
						</td>
						<td class="tableLabel">
							<label for="lastNameInput">Nom : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="lastNameInput" name="lastNameInput" placeholder="<%= currentUser.getLastName() %>" value="${lastName}" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="firstNameInput">Prénom : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="firstNameInput" name="firstNameInput" placeholder="<%= currentUser.getFirstName() %>" value="${firstName}" />
						</td>
						<td class="tableLabel">
							<label for="emailInput">Email : </label>
						</td>
						<td class="tableInput">
							<input type="email" id="emailInput" name="emailInput" placeholder="<%= currentUser.getEmail() %>" value="${email}" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="phoneInput">Téléphone : </label>
						</td>
						<td class="tableInput">
							<input type="tel" id="phoneInput" name="phoneInput" pattern="[0-9]{10}" placeholder="<%= currentUser.getPhone() %>" value="${phone}"/>
						</td>
						<td class="tableLabel">
							<label for="streetInput">Rue : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="streetInput" name="streetInput" placeholder="<%= currentUser.getStreet() %>" value="${street}" />
						</td>
					</tr>
					
					<tr>
						<td class="tableLabel">
							<label for="zipCodeInput">Code postal : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="zipCodeInput" name="zipCodeInput" placeholder="<%= currentUser.getZipCode() %>" pattern="[0-9]{5}" value="${zipCode}" />
						</td>
						<td class="tableLabel">
							<label for="townInput">Ville : </label>
						</td>
						<td class="tableInput">
							<input type="text" id="townInput" name="townInput" placeholder="<%= currentUser.getTown() %>" value="${town}" />
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