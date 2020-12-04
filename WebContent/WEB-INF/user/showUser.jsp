<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Profil utilisateur</title>
	</head>
	<body>
	
		<%@include file="/WEB-INF/fragments/navbar.jspf" %>

		<div class="userPage">
			<div class="banner warning">${warning}</div>
			<div class="banner secondary">${info}</div>
			<% User u = (User) request.getAttribute("user"); %>

			<% if(u == null) { %>
				<h3>Oops... Wrong URL</h3>
			<% } else { %>

				<% if(currentUser != null && currentUser.getUserId() == u.getUserId()) { %>
					<h1>Mon profil : <%= u.getUserName() %></h1>
				<% } else { %>
					<h1>Profil de <%= u.getUserName() %> </h1>
				<% } %>
				
				<div id="tableShowUser">
					<table>
						<tr>
							<td>Nom :</td>
							<td><%= u.getLastName() %></td>
						</tr>
						<tr>
							<td>Prénom :</td>
							<td><%= u.getFirstName() %></td>
						</tr>
						<tr>
							<td>Email :</td>
							<td><%= u.getEmail() %></td>
						</tr>
						<tr>
							<td>Numéro de téléphone :</td>
							<td><%= u.getPhone() %></td>
						</tr>
						<tr>
							<td>Adresse :</td>
							<td><%= u.getStreet() %></td>
						</tr>
						<tr>
							<td></td>
							<td><%= u.getZipCode() %></td>
						</tr>
						<tr>
							<td></td>
							<td><%= u.getTown() %></td>
						</tr>
					</table>
				</div>
				<% if(currentUser != null && currentUser.getUserId() == u.getUserId()) { %>
					<div class="userOwnerButton">
						<a class="formButton submitButton button" href="<%=request.getContextPath()%>/ServletEditUser">Modifier mes informations</a>
					</div>
					<div class="userOwnerButton">
						<a class="formButton dangerButton button" href="<%=request.getContextPath()%>/ServletDeleteUser"  onclick="if (! confirm('Are you sure ?')) { return false; }">Supprimer mon compte</a>
					</div>
				<% } %>
			<% } %>
			<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>
		</div>
	</body>
</html>