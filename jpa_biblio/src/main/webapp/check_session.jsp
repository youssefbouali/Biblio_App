<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%
HttpSession sessione = request.getSession();

if(sessione.getAttribute("user") == null) {
	response.sendRedirect("Connexion"); 
}
%>