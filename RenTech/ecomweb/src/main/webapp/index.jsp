<!DOCTYPE html>
<!-- define angular app -->
<html ng-app="scotchApp">
<head>
	  <meta charset="UTF-8" />
	  
	  <!-- SCROLLS -->
	  <link rel="stylesheet" href="css/bootstrap.min.css" />
	  <link rel="stylesheet" href="css/font-awesome.min.css">
	  <link rel="stylesheet" href="css/animate.css">
	  <link rel="stylesheet" href="css/responsive.css">
	    
	  <link rel="stylesheet" href="css/header.css" />
	  <link rel="stylesheet" href="css/login.css" />
	  <link rel="stylesheet" href="css/menus.css" />
	  <link rel="stylesheet" href="css/panier.css" />
	  <link rel="stylesheet" href="css/pageProduit.css" />
	  <link rel="stylesheet" href="css/responsive.css" />
	  <link rel="stylesheet" href="css/main.css" />
	  <link rel="stylesheet" href="css/footer.css" />
	  
	  <!-- SPELLS -->
	  <script src="js/angular.min.js"></script>
	  <script src="js/angular-route.js"></script>
	  <script src="js/angular-translate.js"></script>
	  <script src="js/script.js"></script>
	  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	  <script>$(document).ready(function(){
			$('#buttonregister').click(function(){

							$('#somediv').text("salut");
							$.get('ConnexionServlet',function(responseText){

													$('#somediv').text(responseText);

																	})
									})
			}); </script>
	  
	  <title>RenTech</title>
</head>
<!-- HEADER AND NAVBAR -->
<body ng-controller="mainController">
<%@page import="beans.Utilisateur" %>

	<header id="header"><!--header-->
		<div class="header-middle"><!--header-middle-->
				<div class="container">
					<div class="row">
						<div class="col-sm-4">
							<div class="logo pull-left">
								<a href="#"><img src="img/logo-small.png" alt="" /></a>
							</div>
						</div>
						
						<div class="col-sm-8">
							<div class="shop-menu pull-right">

								<ul class="nav navbar-nav">
									<li>
										<div class="languages" ng-controller="Ctrl">
											<button ng-click="changeLanguage('en')" class="ng-scope"><img src="img/United-Kingdom.png" alt="" /></button>
											<button ng-click="changeLanguage('fr')" class="ng-scope"><img src="img/France.png" alt="" /></button>
										</div>
									</li>
									<li><a href="#panier"><i class="fa fa-shopping-cart"></i> <span translate="PANIER">Panier</span></a></li>
									
									<%if (request.getSession().getAttribute("user")==null){ %>
									<li>
									<a href="#login"><i class="fa fa-lock"></i> <span translate="CONNEXION">Connexion</span></a></li>
									</li>
									<%} else {
									Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("user");
									
									%>
									<li><a href="#compte"><i class="fa fa-lock"></i> <span translate="CONNECTED">Connect� en tant que</span> <%=utilisateur.getMail() %></a>
									</li>
									<li><a href="<%=request.getContextPath()+"/LogoutServlet"%>"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> <span translate="DECONNEXION">Deconnexion</span></a>
									</li>
									<%} %>
									
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div><!--/header-middle-->
		
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="#"><span translate="HOME">Accueil</span></a></li>
								<li class="dropdown"><a href="#"><span translate="PARTICULIERS">Particuliers</span></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#pageProduit">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li> 
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#"><span translate="G_UTILISATEURS">Groupe d'utilisateurs</span></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#categ">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li>
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#"><span translate="ENTREPRISES">Entreprises</span></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#categ">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li>
                                    </ul>
                                </li> 
								<li><a href="#about"><span translate="A_PROPOS">About</span></a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
	
  <!-- MAIN CONTENT AND INJECTED VIEWS -->
  <div id="main">
  
    <!-- angular templating -->
		<!-- this is where content will be injected -->
    <div ng-view></div>
   
  </div>
</body>

</html>