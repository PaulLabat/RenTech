<!DOCTYPE html>
<!-- define angular app -->
<html ng-app="scotchApp">
<head>
	  <meta charset="UTF-8" />
	  
	<!-- SCROLLS -->
	<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="bower_components/components-font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="bower_components/angular-growl-v2/build/angular-growl.css">
	<link rel="stylesheet" href="bower_components/ngDialog/css/ngDialog.css">
	
<!-- 	<link rel="stylesheet" href="css/animate.css"> -->
<!-- 	<link rel="stylesheet" href="css/responsive.css"> -->
	
	<link rel="stylesheet" href="css/header.css" />
	<link rel="stylesheet" href="css/login.css" />
	<link rel="stylesheet" href="css/home.css" />
	<link rel="stylesheet" href="css/menus.css" />
	<link rel="stylesheet" href="css/panier.css" />
	<link rel="stylesheet" href="css/pageProduit.css" />
	<link rel="stylesheet" href="css/footer.css" >
	<link rel="stylesheet" href="css/responsive.css" />
	
	<!-- SPELLS -->
	<script src="bower_components/angular/angular.min.js"></script>
	<script src="bower_components/angular-animate/angular-animate.js"></script>
	<script src="bower_components/angular-route/angular-route.js"></script>
	<script src="bower_components/angular-translate/angular-translate.js"></script>
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="bower_components/angular-growl-v2/build/angular-growl.js"></script>
    <script src="bower_components/ngDialog/js/ngDialog.js"></script>
    
	<script src="js/script.js"></script>
	<script src="js/gestionPanier.js"></script>
	<title>RenTech</title>

</head>
<!-- HEADER AND NAVBAR -->

<body ng-controller="mainController" >
<%@page import="ejb.entity.Utilisateur" %>
	<header id="header">
		<!--header-->
		<div class="header-middle">
			<!--header-middle-->
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
										<button ng-click="changeLanguage('en')" class="ng-scope">
											<img src="img/United-Kingdom.png" alt="" />
										</button>
										<button ng-click="changeLanguage('fr')" class="ng-scope">
											<img src="img/France.png" alt="" />
										</button>
									</div>
								</li>

								<li ng-controller="headerController"><a href="#panier"><i class="fa fa-shopping-cart"></i>
									<span>{{number}} </span>
									<span translate="PRODUIT"></span> </a>
									<div>{{total}}&#128; HT/mois</div>
								</li>
								
								<li><a id="a_login" href="#login"><i class="glyphicon glyphicon-user"></i> 
									<span translate="CONNEXION">Connexion</span></a></li>
								
								<li><a id="a_logout" href="#logout" style="display:none"><i class="glyphicon glyphicon-off"></i> 
									<span translate="DECONNEXION">Déconnexion</span></a></li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-middle-->

		<div class="header-bottom">
			<!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-9">
						<div class="mainmenu pull-left">
							<ul class="nav navbar-nav collapse navbar-collapse">
								<li><a href="#"><span translate="HOME">Accueil</span></a></li>
								<li class="dropdown"><a href="#"><span
										translate="PARTICULIERS">Particuliers</span></a>
									<ul role="menu" class="sub-menu">
										<li><a href="#git">Serveur Git</a></li>
										<li><a href="#web">Hï¿½bergement web</a></li>
										<li><a href="#mail">Serveur Mail</a></li>
									</ul></li>
								<li class="dropdown"><a href="#"><span
										translate="G_UTILISATEURS">Groupe d'utilisateurs</span></a>
									<ul role="menu" class="sub-menu">
										<li><a href="#mumble">Mumble</a></li>
										<li><a href="#serveurDedie">Serveur dï¿½diï¿½</a></li>
										<li><a href="#partageFichier">Serveur de partage</a></li>
									</ul></li>
								<li class="dropdown"><a href="#"><span
										translate="ENTREPRISES">Entreprises</span></a>
									<ul role="menu" class="sub-menu">
										<li><a href="#cloudDedie">Cloud dï¿½diï¿½</a></li>
										<li><a href="#cloudPublic">Cloud public</a></li>
										<li><a href="#stockage">Stockage</a></li>
									</ul></li>
								<li><a href="#about"><span translate="A_PROPOS">About</span></a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search" />
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
	
	<footer class="footer">
	      <div class="container">
	        <p class="text-muted">Copyright RenTech 2014 ï¿½ - Augustin HUSSON - Paul LABAT - Paul MARIAGE - Patrick PEREA</p>
	      </div>
    </footer>
    
    <!-- Div necessaire pour les notifications (pops-up)s -->
    <div growl></div>
</body>

</html>
