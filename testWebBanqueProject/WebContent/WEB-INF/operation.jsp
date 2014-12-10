<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opération</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div style="text-align: center; width: 80%;">
		<form action="operation" method="post">
			<input type="hidden" name="choix" value="1"> 
			<select name="selectedCompte">
				<c:forEach var="compte" items="${listeCompte}">
					<option value="${compte.id}">
						<c:set var="classe" value=" ${ compte.class.name}" />
						<c:out value="${fn:substringAfter(classe, 'entities.'  )  }" />
					</option>
				</c:forEach>
			</select> </br>
			</br> <input type="number" step="0.1" name="montant"> <input type="submit" value="Valider l'opération">
		</form>
	</div>
</body>
</html>