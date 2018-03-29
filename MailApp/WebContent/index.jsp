<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="connection.Connect"%>
<%@page import="java.sql.*"%>

<%
	if(request.getSession().getAttribute("id_user")!=null)
		response.sendRedirect("site.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<Title>Suivre des sites</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/connexion.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="divConnexion">
		<form id="formConnect" action="connexion" method="POST">

			<input type="email" name="email" placeholder="E-mail"
				class="necessaireConnect" /> <input type="password" name="password"
				placeholder="Mot de passe" class="necessaireConnect" /> <input
				type="submit" value="Connexion" id="buttonConnect" />

		</form>
	</div>
	<div id="divInscription">

		<h1>INSCRIVEZ-VOUS !</h1>
		<h2>Description à ajouter</h2>

		<form id="formInscription" action="inscription" method="POST">

			<div id="nomInscription">
				<input type="text" name="name" placeholder="Nom"
					class="necessaireInscript" /> <input type="text" name="surname"
					placeholder="Prénom" class="necessaireInscript" />
			</div>

			<input type="email" name="email" placeholder="E-mail"
				class="necessaireInscript" class="inscrInfos" /> <input
				type="password" name="password" placeholder="Mot de passe"
				class="necessaireInscript" class="inscrInfos" /> <input
				type="submit" value="Créer un compte" id="buttonCreate" />
		</form>

	</div>
</body>
</html>
