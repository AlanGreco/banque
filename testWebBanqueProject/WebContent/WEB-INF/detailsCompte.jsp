<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="inc/compte.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détails de votre compte</title>
</head>

<body>
	<%@include file="menu.jsp"%>
	<div class="container">
		<div class="panel panel-default" style="width: 50%;">
			<div class="panel-heading">Détails du compte</div>
			<table class="table" style="text-align: center;">
				<tr>
					<td>Solde :</td>
					<td><c:out value="${compteSelectionne.solde}" /></td>
				</tr>
				<c:if test="${compteSelectionne.class.name == 'entities.CompteStandard' 
					|| compteSelectionne.class.name == 'entities.ComptePlatine'}">
					<tr style="height: 20px;">
						<td>Penalité :</td>
						<td><c:out value="${compteSelectionne.penalite}" /></td>
					</tr>
				</c:if>
				<c:if test="${compteSelectionne.class.name == 'entities.ComptePlatine'}">
					<tr style="height: 20px;">
						<td>Découvert autorisé :</td>
						<td><c:out value="${compteSelectionne.decouvertAutorise}" /></td>
					</tr>
				</c:if>
				<c:if test="${compteSelectionne.class.name == 'entities.CompteEpargne'}">
					<tr style="height: 20px;">
						<td>Taux d'intérêt :</td>
						<td><c:out value="${compteSelectionne.tauxInteret}" /></td>
					</tr>
				</c:if>
			</table>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">Historique des mouvements</div>
			<table class="table">
				<tr>
					<td>Motif</td>
					<td>Date</td>
					<td>Montant</td>
				</tr>
				<c:forEach var="mouvement" items="${compteSelectionne.historiqueMouvements}">
					<tr>
						<td><c:out value="${mouvement.commentaire}"></c:out></td>
						<td><c:set var="dateMouvement" target="java.util.Date" value="${mouvement.date}" /> <fmt:formatDate value="${dateMouvement}" type="both" dateStyle="short" /></td>
						<td><c:out value="${mouvement.montant}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>