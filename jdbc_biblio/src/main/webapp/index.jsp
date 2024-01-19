<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
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
		<title>Biblio App</title>
	</head>
	<body>
		<h1>Biblio App</h1>
		<a href="Inscription">Inscription</a>
		<a href="Connexion">Connexion</a>
	
	</body>
</html>