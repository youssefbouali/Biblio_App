<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ include file="check_session.jsp" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="windows-1256">
		<link rel="stylesheet" href="style.css">
		<title>Biblio App | Ajouter un Livre</title>
	</head>
	<body>
		
		<h1>Biblio App : Ajouter un Livre</h1>
		
		<form action="Livres" method="POST">
		
			<input type="text" name="titre" placeholder="Titre" ><br/>
			<input type="text" name="auteur" placeholder="Auteur" ><br/>
			<input type="text" name="isbn" placeholder="Isbn" ><br/>
			<input type="submit" name="submit" value="Ajouter"></input>
		
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
		<a href="Livres">List des livres</a>
	
	</body>
</html>