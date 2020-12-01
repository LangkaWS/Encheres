<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<title>Profil utilisateur</title>
	</head>
	<body>
		<header>
			<h1>ENI-Enchères</h1>
		</header>
		<div class="showPage">
			<% User u = (User) request.getAttribute("user"); %>
			<% 
				User currentUser = null;
				if (session.getAttribute("currentUser") != null) {
					currentUser = (User) session.getAttribute("currentUser");
					} 
			%>
			<% if(currentUser != null) { %>
				<% if(currentUser.getUserId() == u.getUserId()) { %>
					<h1>Mon profil : <%= u.getUserName() %></h1>
				<% } %>
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
			<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>
		</div>
	</body>
</html>