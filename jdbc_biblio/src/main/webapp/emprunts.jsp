<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ include file="check_session.jsp" %>
<%@ include file="include_functions.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="entity.Livre"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="windows-1256">
		<link rel="stylesheet" href="style.css">
		<title>Biblio App | livres empruntés</title>
	</head>
	<body>
		
		<h1>Biblio App : Liste des livres empruntés</h1>
		
		<%
		ArrayList<Livre> livres = (ArrayList<Livre>) request.getAttribute("emprunts");
		if (livres != null){
			for (Livre livre : livres){ %>
				<a href="Emprunts?isbn=<%= myFunctions.escapeXml(livre.getIsbn()) %>"><%= myFunctions.escapeXml(livre.getTitre()) %></a><br/>
			<% }
		
		}
		
		%>
		
		<br/>
		<a href="accueil.jsp">Accueil</a>
	
	</body>
</html>