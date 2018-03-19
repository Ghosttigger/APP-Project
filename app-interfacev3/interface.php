<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8"/>
		<Title>Suivre des sites</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" media="screen and (max-width: 1024px)" href="css/mobile.css" />
	</head>
	
	<body>

		<header>
			<nav>
				
			</nav>
		</header>

		<div id="tabs">
			<div class="feature" onclick="change_tab(this, 'searchAddr')">Ajout URL</div>
			<div class="feature" onclick="change_tab(this, 'addWebsiteTheme')">Ajout par thèmes</div>
			<div class="feature" onclick="change_tab(this, 'addWebsiteVisited')">Ajout des plus visités</div>
		</div>
		
		<div class="subject">
			<div id="addWebsiteAddr" >
			
				<!-- Search address -->
				<div id="searchAddr">
					<label>Adresse :</label>
					<input type="url" placeholder="Insérez une URL"/>
				</div>
	
				<!-- Theme -->
				<div id="addWebsiteTheme">
					
					<p>Thèmes :</p>
					
					<div>
						<input type="checkbox">Reseau Social
						<input type="checkbox" checked="">News
						<input type="checkbox">Site de vente
						<input type="checkbox">Boite Mail
					</div>
					
					<ul>
						<li>
							<a href="http://lemonde.fr" target="blank_">Le Monde</a><input type="checkbox">
						</li>
						<li>
							<a href="http://nytimes.com" target="blank_">New York Times</a><input type="checkbox">
						</li>
					</ul>

				</div>

				<!-- More visited -->
				<div id = "addWebsiteVisited">
					<p>Sites les plus visités :</p>

					<ul>
						<li>
							<a href="http://google.fr" target="blank_">Google</a><input type="checkbox">
						</li>
						<li>
							<a href="http://facebook.com" target="blank_">Facebook</a><input type="checkbox">
						</li>
					</ul>

				</div>
				
			</div>
			
			<div id="lowerOptions">
			
				<!-- Verification notif -->
				<div id = "websiteVerification">

					<label for="verif">Fréquence</label>
					<select id="verif" size="1">
						<option value="1800000" selected>Toutes les 30mn</option>
						<option value="3600000">Toutes les 1h</option>
						<option value="7200000">Toutes les 2h</option>
						<option value="86400000">Tous les jours</option>
						<option value="604800000">Toutes les semaines</option>
					</select>
				</div>

				<!--Section--> 
				<div id = "websiteSection">
					<button type="button" >Section →</button>

				</div>
			</div>
			
			<button type="button" id="addWebButton" onclick="addWebsite()">+</button>
		</div>
		
		<div id="websiteList">
			<form action="#" method="post">
				<ul>
					
				</ul>
			
				<input type="submit" value="Suivre ce(s) site(s)"/>
			</form>
		</div>

		<script>
			
			window.onload = init();
			
			function init(){
				checkList();
				var add_button = document.getElementById("addWebButton");
				add_button.setAttribute("tab", "search");
				var tab = document.getElementById("addWebsiteTheme");
				tab.style.display = "none";
				tab = document.getElementById("addWebsiteVisited");
				tab.style.display = "none";
				
				tab = document.getElementById("tabs");
				//console.log(tab);
				tab.childNodes[1].style.backgroundColor = "#EA4335";
				tab.childNodes[1].style.color = "white";

			}

			function addWebsite(){
				var mode = document.getElementById("addWebButton").getAttribute("tab");
				var url;

				switch(mode){
					case 'search':
						url = document.getElementById("searchAddr").getElementsByTagName("input")[0].value;
					break;
					case 'theme':

					break;

					case 'visited':

					break;
				}


				var frequence = document.getElementById("websiteVerification").getElementsByTagName("select");
				frequence = frequence[0].options[frequence[0].selectedIndex].value;
				//ajouter la section


				var list = document.getElementById('websiteList');
				list = list.getElementsByTagName("ul");
				var site = document.createElement("li");
				var data_frequence = document.createElement("input");
				data_frequence.setAttribute("hidden","true");
				data_frequence.setAttribute("name","frequence");
				data_frequence.setAttribute("value",frequence.toString());
				
				var data_section = document.createElement("input");
				data_section.setAttribute("hidden","true");
				data_section.setAttribute("name","section");
				data_section.setAttribute("value","true");

				var a = document.createElement("a");
				a.setAttribute("href", url);
				a.innerHTML = url;
				var button = document.createElement("button");
				button.setAttribute("type", "button");
				button.setAttribute("class", "deleteToWebsiteList");
				button.setAttribute("onclick", "removeWebsite(this)");
				button.innerHTML = "x";

				site.appendChild(a);
				site.appendChild(button);
				site.appendChild(data_frequence);
				site.appendChild(data_section);
				list[0].appendChild(site);
				checkList();
			}

			function removeWebsite(obj){
				obj.parentElement.remove();
				checkList();
			}

			function checkList(){
				var list = document.getElementById('websiteList');
				list = list.getElementsByTagName("ul");
				if(list[0].children.length == 0){
					document.getElementById("websiteList").style.display = "none";
				}
				else{
					document.getElementById("websiteList").style.display = "block";
				}
			}
			
			function change_tab(obj, id){
				var add_button = document.getElementById("addWebButton");
				var tabs = document.getElementById("tabs");
				tabs = tabs.getElementsByTagName("div");
				for(var i=0;i<tabs.length;i++){

					tabs[i].style.backgroundColor = "#f5f5f5";
					tabs[i].style.color = "#747474";
				}

				var content = document.getElementById("addWebsiteAddr").children;
				for(i=0;i<content.length;i++){
					content[i].style.display = "none";
				}

				obj.style.color = "white";
				switch(id){
					case 'searchAddr':
						obj.style.backgroundColor = "#EA4335";
						content[0].style.display = "block";
						add_button.setAttribute("tab", "search");
					break;
					case 'addWebsiteTheme':
						obj.style.backgroundColor = "#FBBC05";
						content[1].style.display = "block";
						add_button.setAttribute("tab", "theme");
					break;
					case 'addWebsiteVisited':
						obj.style.backgroundColor = "#34A853";
						content[2].style.display = "block";
						add_button.setAttribute("tab", "visited");
					break;
				}				
			
			}
		</script>
	</body>
</html>
