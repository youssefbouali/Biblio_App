<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ include file="check_session.jsp" %>
<%@ include file="include_functions.jsp" %>
<%@page import="entity.Livre"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="windows-1256">
		<link rel="stylesheet" href="style.css">
		<title>Biblio App | Livre</title>
	</head>
	<body>
		
		<h1>Biblio App : Livre</h1>
		
		<%
		Livre livre = (Livre) request.getAttribute("livre");
		if (livre != null){ %>
				<h3>Titre : <%= myFunctions.escapeXml(livre.getTitre()) %></h3><br/>
				<h3>Auteur : <%= myFunctions.escapeXml(livre.getAuteur()) %></h3><br/>
				<h3>Isbn : <%= myFunctions.escapeXml(livre.getIsbn()) %></h3><br/>
				<form action="Emprunts" method="POST">
					<input type="hidden" name="isbn" value="<%= myFunctions.escapeXml(livre.getIsbn()) %>" >
					<input type="submit" name="submit" value="Emprunter"></input>
				</form>
				<form action="Livres" method="POST">
					<input type="hidden" name="isbn" value="<%= myFunctions.escapeXml(livre.getIsbn()) %>" >
					<input type="submit" name="submit" value="Modification"></input>
				</form>
				<form action="Livres" method="POST">
					<input type="hidden" name="isbn" value="<%= myFunctions.escapeXml(livre.getIsbn()) %>" >
					<input type="submit" name="submit" value="Supprimer"></input>
				</form>
			<%
		
		}
		
		%>
		<br/>
		<a href="Livres">List des livres</a>
	
	</body>
</html>