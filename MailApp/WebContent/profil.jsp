<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="java.sql.*"%>

<%
	if (request.getSession().getAttribute("id_user") == null)
		response.sendRedirect("index.jsp");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<Title>Profil</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/profil.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	media="screen and (max-width: 1024px) and (orientation: portrait)"
	href="css/mobileProfil.css" />
</head>

<body>
	<header>
		<nav class="menuSite">

			<ul class="onglets">
				<li id="interface"><a href="site.jsp">SUIVRE UN SITE</a></li>
				<li id="profil"><a href="profil.jsp">PROFIL</a></li>
				<li id="historique"><a href="historique">HISTORIQUE</a></li>
				<li id="deconnexion"><a href="deconnexion">DECONNEXION</a></li>
			</ul>


		</nav>

	</header>
	<div class="pageBody">
		<div id="websiteList">
			<form method="post">
				<ul>
					<li><a href="lemonde.fr">http://www.lemonde.fr/page/home</a> <label
						for="verif">Fréquence</label> <select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>

					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>
					<li><a href="lemonde.fr">lemonde.fr</a> <label for="verif">Fréquence</label>
						<select id="verif" size="1">
							<option value="1800000" selected="">Toutes les 30mn</option>
							<option value="3600000">Toutes les 1h</option>
							<option value="7200000">Toutes les 2h</option>
							<option value="86400000">Tous les jours</option>
							<option value="604800000">Toutes les semaines</option>
					</select>
						<button type="button" id="selectSection">Section →</button>
						<button type="button" class="deleteToWebsiteList"
							onclick="removeWebsite(this)">x</button></li>

				</ul>
				<input value="Modifier ce(s) site(s)" type="submit">
			</form>
		</div>
	</div>
	<footer>
		<p>© - 2018 Notification</p>
	</footer>
	<script>
		function removeWebsite(obj) {
			obj.parentElement.remove();
			checkList();
		}
	</script>
</body>
</html>
