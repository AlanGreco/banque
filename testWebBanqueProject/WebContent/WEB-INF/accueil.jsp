<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link href="inc/accueil.css" rel="stylesheet" type="text/css" />

<title>Accueil Banque</title>
</head>
<body>
	<%@include file="menu.jsp"%>

	</br>

	<form action="nouveaucompte" method="get" class="formAjouterCompte">
		<button type="submit" value="simple" name="type" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter un compte Simple
		</button>

		<button type="submit" value="premium" name="type" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter un compte Premium
		</button>
		<button type="submit" value="epargne" name="type" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter un compte Epargne
		</button>
	</form>
	<form id="detailsCompte" action="comptes" method="get">
		<input type="hidden"  value="1" name="choix" />
	</form>
	<form id="suppressionCompte" action="comptes" method="get">
		<input type="hidden" value="2" name="choix" />
		<c:forEach var="compte" items="${listeCompte}">
			<div class="compteBlock">
				<button form="detailsCompte" type="submit" name="selectedCompte" value="${compte.id}" class="compte">
					<c:set var="classe" value=" ${ compte.class.name}" />
					<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
					<c:out value="Solde :     ${compte.solde}"></c:out>
				</button>
				<button type="submit" value="${compte.id}" name="compteID" class="btn btn-default btn-lg">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</div>
		</c:forEach>
	</form>
</body>
</html>