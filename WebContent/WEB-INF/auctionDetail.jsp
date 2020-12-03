<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.encheres.bll.bo.User"%>
<%@page import="fr.eni.encheres.bll.bo.Article"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	<h2>Détail vente</h2>
	
	<%
		if(session.getAttribute("currentUser") == null) {
	%>
		<p>Vous devez être connecté pour visualiser le détail de cette enchère.</p>
		<a href="signIn">Se connecter</a>
	<%
		} else {
	%>
	
	<ul>
	
	<li>${art.name}</li>
	<li>${art.description}</li>
	<li>Catégorie : ${category}</li>
	<li>Meilleure offre : 
		<%if(request.getAttribute("buyer").equals("noBuyer")) {%>
			Aucune offre pour le moment
		<%} else {%>
			${art.sellingPrice} pts par ${buyer}
		<%}%>
	</li>
	<li>Mise à prix : ${art.startPrice} points</li>
	<li>Fin de l'enchère : 
		<fmt:parseDate value="${art.auctionEndDate}" var="parsedDate" pattern="y-M-dd'T'H:m" />
		<fmt:formatDate value="${parsedDate}" var="formattedEndDate" type="both" pattern="dd/MM/yyyy HH:mm" />
		${formattedEndDate}
	</li>
	<li>Retrait : ${pu.street} ${pu.zipCode} ${pu.town}</li>
	<li>Vendeur : ${seller}</li>
	
	<%if(((Article)request.getAttribute("art")).getState().equals("in progress")) {
	%>
		<li>Ma proposition : <form method="POST" action="<%=request.getContextPath()%>/ServletOutbid"><input type="number" name="newBidAmount" min="${art.sellingPrice +1}" value="${art.sellingPrice +1}" /> <button type="submit" name="submitButton" value="${art.articleId}" class="formButton submitButton">Enchérir</button></form></li>
		
	<%} else if(((Article)request.getAttribute("art")).getState().equals("created") && ((Article)request.getAttribute("art")).getSellerId() == ((User) session.getAttribute("currentUser")).getUserId()) {%>
		<button type="submit" name="editButton" value="${art.articleId}" class="formButton submitButton" onclick="location.href='<%=request.getContextPath()%>/edit/auctionDetail?id=${art.articleId}'">Modifier</button>
	<%} else if(((Article)request.getAttribute("art")).getState().equals("created")) {%>
		<li>Cette vente n'a pas encore commencé</li>
	<%} else if(((Article)request.getAttribute("art")).getState().equals("ended") && ((Article)request.getAttribute("art")).getBuyerId() == ((User) session.getAttribute("currentUser")).getUserId()) { %>
		<li>Vous avez remporté la vente</li>
	<%} else { %>
		<li>La vente est terminée. ${buyer} a remporté la vente.</li>
	<%} %>
	
		
	</ul>
	
	<%	}
	%>
	
	<button class="formButton cancelButton" onclick="location.href='.'">Retour</button>

</body>
</html>