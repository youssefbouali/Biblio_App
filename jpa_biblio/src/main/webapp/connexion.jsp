<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ page import="java.util.ArrayList" %>
<%
HttpSession sessione = request.getSession();

if(sessione.getAttribute("user") != null) {
	response.sendRedirect("accueil.jsp"); 
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="windows-1256">
		<link rel="stylesheet" href="style.css">
		<title>Biblio App | Connexion</title>
	</head>
	<body>
		
		<h1>Biblio App : Connexion</h1>
		
		<form action="Connexion" method="POST">
		
			<input type="text" name="username" placeholder="Username" ><br/>
			<input type="password" name="password" placeholder="Password" ><br/>
			<input type="submit" name="submit" value="Connect"></input>
		
		</form>
		
		<a href="Inscription">Inscription</a>
		
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
	
	</body>

</html>