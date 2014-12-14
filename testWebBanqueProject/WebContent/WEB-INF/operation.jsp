<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opération</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<link href="inc/compte.css" rel="stylesheet" type="text/css" />
<%@include file="menu.jsp"%>
</head>
<body>

	<div style="text-align: center; width: 80%;">
		<form action="operation" method="post">
			<input type="hidden" name="type" value="operation">
			<input type="hidden" name="choix" value="1">
			
			 <select name="selectedCompte">
				<c:forEach var="compte" items="${listeCompte}">
					<option value="${compte.id}">
						<c:set var="classe" value=" ${ compte.class.name}" />
						<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
					</option>
				</c:forEach>
			</select> 
			</br> </br> 
			<input type="number" step="0.01" value="0" name="montant"> <input type="submit" value="Valider l'opération">
		</form>


		<button id="boutonVirement" type="submit" value="simple" name="type" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-plus"></span> Effectuer un virement
		</button>

		<div id="virementPanel" style="display: none;">
			<form action="operation" method="post">
				<input type="hidden" name="choix" value="1">
				<input type="hidden" name="type" value="virement"> 
				<select name="selectedCompte">
					<c:forEach var="compte" items="${listeCompte}">
						<option value="${compte.id}">
							<c:set var="classe" value=" ${ compte.class.name}" />
							<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
						</option>
					</c:forEach>
				</select> 
				<select name="selectedCompte2">
					<c:forEach var="compte" items="${listeCompte}">
						<option value="${compte.id}">
							<c:set var="classe" value=" ${ compte.class.name}" />
							<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
						</option>
					</c:forEach>
				</select> 
				</br> </br> 
				<input type="number" step="0.01" min="0" value="0" name="montant"> 
				<input type="submit" value="Valider l'opération">
			</form>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("button").click(function() {
			$("#virementPanel").toggle();
		});
	});
</script>
</html>