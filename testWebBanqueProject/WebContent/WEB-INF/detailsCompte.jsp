<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<table rules="all" frame="box" style="text-align:center; line-height:20px;"> 
   <tr> 
      <td>Solde : </td> 
      <td>	
      	<c:out value="${compteSelectionne.solde}" />
      </td> 
   </tr> 
   <c:out value="${compteSelectionne.class.name }"></c:out>
   <c:if test="${compteSelectionne.class.name == 'entities.CompteStandard' || compteSelectionne.class.name == 'entities.ComptePlatine'}">
   <tr style="height:20px;"> 
      <td>Penalité : </td>
      <td> 
         	<c:out value="${compteSelectionne.penalite}" />     	
      </td> 
   </tr> 
   </c:if>
 <c:if test="${compteSelectionne.class.name == 'entities.ComptePlatine'}">
   <tr style="height:20px;"> 
      <td>Penalité : </td>
      <td> 
         	<c:out value="${compteSelectionne.penalite}" />     	
      </td> 
   </tr> 
   </c:if>
     
<!--    decouvertAutorise -->
</table> 
	</div>
</body>
</html>