<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="connection.Connect"%>
<%@page import="java.util.*"%>

<%
	if(request.equals(null))
		response.sendRedirect("index.jsp");
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8"/>
		<Title>Historique</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<link href="css/historique.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" media="screen and (max-width: 1024px) and (orientation: portrait)" href="css/mobileHistorique.css" />
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
		<div class="featured">
					<div id="up"><h1>Historique</h1></div>

					<%
					try{
						ArrayList<Timestamp> dates = (ArrayList<Timestamp>)request.getAttribute("dates");
						ArrayList<String> urls = (ArrayList<String>)request.getAttribute("urls");
						ArrayList<String> contents = (ArrayList<String>)request.getAttribute("contents");
						for(int i=0; i<urls.size(); i++){
					%>
					<div id="notification1">
					<div id="indication">
						<h3> <%=dates.get(i)%> -</h3>
						<a href="lemonde.fr"><%=urls.get(i)%></a>
					</div>
					<p><%=contents.get(i) %></p>
					</div>
					
					<%
						}
					} catch (Exception e){
						response.sendRedirect("index.jsp");
					}
					%>
					
			</div>


		</div>
		<footer>
			<p>© - 2018 Notification</p>
		</footer>
		<script>

		</script>
	</body>
</html>
