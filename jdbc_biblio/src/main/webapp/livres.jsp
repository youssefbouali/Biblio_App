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
		<title>Biblio App | Liste des livres</title>
	</head>
	<body>
		
		<h1>Biblio App : Liste des livres</h1>
		
		<form action="Livres" method="POST">
			<input type="text" name="q" placeholder="Rechercher par titre..">
			<input type="submit" name="submit" value="Recherche"></input>
		</form>
		<% String tri = (String) request.getAttribute("tri"); %>
		<form action="Livres" method="POST">
			<label><input type="radio" name="tri" <% if(tri != null && tri.equals("titre")){ %> checked  <% } %> value="titre">Titre</label>
			<label><input type="radio" name="tri" <% if(tri != null && tri.equals("auteur")){ %> checked <% } %> value="auteur">Auteur</label>
			<label><input type="radio" name="tri" <% if(tri != null && tri.equals("isbn")){ %> checked <% } %> value="isbn">ISBN</label>
			<input type="submit" name="submit" value="Tri"></input>
		</form>
		
		<%
		ArrayList<Livre> livres = (ArrayList<Livre>) request.getAttribute("livres");
		if (livres != null){
			for (Livre livre : livres){ %>
				<a href="Livres?isbn=<%= myFunctions.escapeXml(livre.getIsbn()) %>"><%= myFunctions.escapeXml(livre.getTitre()) %></a><br/>
			<% }
		
		}
		
		%>
		
		<br/>
		<a href="ajout_livre.jsp">Ajouter un Livre</a>
		<br/>
		<a href="accueil.jsp">Accueil</a>
	
	</body>
</html>