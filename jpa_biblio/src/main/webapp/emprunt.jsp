<%@page import="entity.Livre"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ include file="check_session.jsp" %>
<%@ include file="include_functions.jsp" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="windows-1256">
		<link rel="stylesheet" href="style.css">
		<title>Biblio App | livre emprunté</title>
	</head>
	<body>
		
		<h1>Biblio App : Livre emprunté</h1>
		
		<%
		Livre livre = (Livre) request.getAttribute("emprunt");
		if (livre != null){ %>
				<a href="Livres?isbn=<%= myFunctions.escapeXml(livre.getIsbn()) %>"><%= myFunctions.escapeXml(livre.getTitre()) %></a><br/>
			<%
		
		}
		
		%>
				<form action="Emprunts" method="POST">
					<input type="hidden" name="isbn" value="<%= myFunctions.escapeXml(livre.getIsbn()) %>" >
					<input type="submit" name="submit" value="Supprimer"></input>
				</form>
		<ul class="errors">
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
		<a href="Emprunts">List Emprunts</a>
		<br/>
		<a href="accueil.jsp">Accueil</a>
	
	</body>
</html>