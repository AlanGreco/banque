<%@page import="entities.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Client"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Détail des comptes</title>
</head>
<body>
<h1>Veuillez sélectionner un compte</h1>

<% ArrayList<Compte> listeCompte = (ArrayList<Compte>) request.getAttribute("listeCompte");
				for (int i=0; i<listeCompte.size(); i++)
				{out.println (listeCompte.get(i).getClass());				
				}
				%>
</body>
</html>