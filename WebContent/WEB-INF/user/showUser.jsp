<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<h1>Mon profil : ${user.userName}</h1>
				<% } else { %>
					<h1>Profil de ${user.userName} </h1>
				<% } %>
				
				<div id="tableShowUser">
					<table>
						<tr>
							<td>Nom :</td>
							<td>${user.lastName}</td>
						</tr>
						<tr>
							<td>Prénom :</td>
							<td>${user.firstName}</td>
						</tr>
						<tr>
							<td>Email :</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<td>Numéro de téléphone :</td>
							<td>${user.phone}</td>
						</tr>
						<tr>
							<td>Adresse :</td>
							<td>${user.street}</td>
						</tr>
						<tr>
							<td></td>
							<td>${user.zipCode}</td>
						</tr>
						<tr>
							<td></td>
							<td>${user.town}</td>
						</tr>
					</table>
				</div>
				<% if(currentUser != null && currentUser.getUserId() == u.getUserId()) { %>
					<div class="userOwnerButton">
						<a class="formButton submitButton button" href="${contextPath}/edit/user">Modifier mes informations</a>
					</div>
					<div class="userOwnerButton">
						<a class="formButton dangerButton button" href="${contextPath}/delete/user?val=ok"  onclick="if (! confirm('Are you sure ?')) { return false; }">Supprimer mon compte</a>
					</div>
				<% } %>
			<% } %>
			<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>
		</div>
	</body>
</html>