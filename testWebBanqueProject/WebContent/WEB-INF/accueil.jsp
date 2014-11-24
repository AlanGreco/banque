
<%@page import="entities.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>Accueil Banque</title>
</head>
<body>
	Bienvenue dans votre espace personel :
	<%=request.getAttribute("login")%>


	<a href="accueil"></a>

	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Banque</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="accueil">Accueil <span
						class="sr-only">(current)</span></a></li>
				<li><a href="comptes">Consulter Mes comptes</a></li>
				<li class="dropdown">
				<li><a href="operations">Effectuer une opération</a></li>
			</ul>
			</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Link</a></li>
			</ul>
			</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<form action="nouveaucompte" method="get">
		<button type="submit" value="simple" name="type" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			Ajouter un compte Simple
		</button>
	</a>
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
				{out.println (listeCompte.get(i).getSolde());
				if (listeCompte.get(i).getClass().toString() == "CompteStandard")
				{
					out.println ("comptestandard <br>");
				}
				
				}
				%>

</body>
</html>