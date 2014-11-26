<%@page import="entities.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%@include file="menu.jsp" %>

</br>
	Bienvenue dans votre espace personel :
	<%=request.getAttribute("login")%>

		<form action="nouveaucompte" method="get" class="formAjouterCompte">
	<button type="submit" value="simple" name="type" class="btn btn-default btn-lg">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		Ajouter un compte Simple
	</button>

	<button type="submit" value="premium"  name="type" class="btn btn-default btn-lg">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		Ajouter un compte Premium
	</button>
	</button>
	<button type="submit" value="epargne" name="type" class="btn btn-default btn-lg">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		Ajouter un compte Epargne
	</button>
	</form>
	
<% ArrayList<Compte> listeCompte = (ArrayList<Compte>) request.getAttribute("listeCompte");
				for (int i=0; i<listeCompte.size(); i++)
				{
					out.println("<div class=\"compte\">"+listeCompte.get(i).getClass().getName().substring(9));
					out.println (  "     Solde : "+listeCompte.get(i).getSolde()+"</div>");				
				}
				%>

</body>
</html>