<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Détail des comptes</title>
<link href="inc/compte.css" rel="stylesheet">
</head>

<body>
	<%@include file="menu.jsp"%>
	<div class="container">
		<h1 style="color: ##26ADE4;">Veuillez sélectionner un compte</h1>


	<form action="comptes" method="get">
	<input type="hidden" value="1" name="choix" />
	<c:forEach var="compte" items="${listeCompte}">
		<div class="compteBlock">
		<button type="submit"  name="selectedCompte" value="${compte.id}" class="compte">
			<c:set var="classe" value=" ${ compte.class.name}" />
			<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
		</button>
		</div>
	</c:forEach>
	</form>
	
	</div>
</body>
</html>