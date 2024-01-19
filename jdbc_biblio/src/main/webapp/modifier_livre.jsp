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
		<title>Biblio App | Modifier le Livre</title>
	</head>
	<body>
	
		<h1>Biblio App : Modifier le Livre</h1>
		
		<%
		Livre livre = (Livre) request.getAttribute("livre");
		if (livre != null){ %>
		<form action="Livres" method="POST">
		
			<input type="text" name="titre" placeholder="Titre" value="<%= myFunctions.escapeXml(livre.getTitre()) %>" ><br/>
			<input type="text" name="auteur" placeholder="Auteur" value="<%= myFunctions.escapeXml(livre.getAuteur()) %>" ><br/>
			<input type="hidden" name="isbn" placeholder="Isbn" value="<%= myFunctions.escapeXml(livre.getIsbn()) %>" ><br/>
			<input type="submit" name="submit" value="Modifier"></input>
		
		</form>
		<ul class="errors">
		<% } %>
		<%
		ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
		if (errors != null){
			for (String error : errors){ %>
				<li><%= error %></li>
			<% }
		
		}
		
		%>
		</ul>
		<br/>
		<a href="Livres">List des livres</a>
	
	</body>
</html>