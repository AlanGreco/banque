<%@page import="entities.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouveau Compte Banque</title>
<link href="inc/contactform.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="cfg-contactform" id="cfg-contactform-8">

		<div class="cfg-contactform-content">

			<div class="cfg-element-container">

				<div class="cfg-element-set" id="cfg-element-8-9-set">
					<div class="cfg-element-content">
						<span class="cfg-title " name="cfg-element-8-9" id="cfg-element-8-9">Créer votre compte</span>
					</div>
				</div>
			</div>

			<form action="NouveauClient" method="post">
				<div class="cfg-element-container">

					<label class="cfg-label" id="cfg-element-8-6-label"> <span class="cfg-label-value">Nom</span> <span class="cfg-required">*</span>
					</label>

					<div class="cfg-element-set" id="cfg-element-8-6-set">
						<div class="cfg-element-content">
							<input type="text" class="cfg-type-text cfg-form-value " name="nomClient" id="cfg-element-8-6" />
						</div>
					</div>

					<div class="cfg-clear"></div>
				</div>


				<div class="cfg-element-container">

					<label class="cfg-label" id="cfg-element-8-7-label"><span class="cfg-label-value">Prenom</span><span class="cfg-required">*</span></label>

					<div class="cfg-element-set" id="cfg-element-8-7-set">
						<div class="cfg-element-content">
							<input type="text" class="cfg-type-text cfg-form-value " name="prenomClient" id="cfg-element-8-7" />
						</div>
					</div>

					<div class="cfg-clear"></div>
				</div>

				<div class="cfg-element-container">

					<label class="cfg-label" id="cfg-element-8-8-label"> <span class="cfg-label-value">Mot de passe</span> <span class="cfg-required">*</span>
					</label>

					<div class="cfg-element-set" id="cfg-element-8-8-set">
						<div class="cfg-element-content">
							<input type="text" class="cfg-type-text cfg-form-value " name="motDePasseClient" id="cfg-element-8-8" />
						</div>
					</div>
					<div class="cfg-clear"></div>
				</div>

				<div class="cfg-element-container">
					<div class="cfg-element-set" id="cfg-element-8-5-set">
						<div class="cfg-element-content">
							<input type="submit" class="cfg-submit " name="submitNewClient" id="cfg-element-8-5" value="Valider" />
						</div>
					</div>
				</div>
				
				<div class="cfg-loading">&nbsp;</div>
			</form>
		</div>
	</div>

</body>
</html>