<!DOCTYPE html>
<!-- define angular app -->
<html ng-app="scotchApp">

<head>
	  <meta charset="UTF-8" />
	  
	  <!-- SCROLLS -->
	  <link rel="stylesheet" href="css/bootstrap.min.css" />
	  <link rel="stylesheet" href="css/font-awesome.min.css">
	  <link rel="stylesheet" href="css/animate.css">
	    
	  <link rel="stylesheet" href="css/header.css" />
	  <link rel="stylesheet" href="css/login.css" />
	  <link rel="stylesheet" href="css/menus.css" />
	  <link rel="stylesheet" href="css/panier.css" />
	  <link rel="stylesheet" href="css/pageProduit.css" />
	  
	  <!-- SPELLS -->
	  <script src="js/angular.min.js"></script>
	  <script src="js/angular-route.js"></script>
	  <script src="js/script.js"></script>
	  
	  <title>RenTech</title>
</head>

<!-- HEADER AND NAVBAR -->
<body ng-controller="mainController">

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
									<li><a href="#panier"><i class="fa fa-shopping-cart"></i> Panier</a></li>
									<li><a href="#login" class="active"><i class="fa fa-lock"></i> Connexion</a></li>
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
								<li><a href="#">Home</a></li>
								<li class="dropdown"><a href="#">Particuliers<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#pageProduit">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li> 
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#">Groupe d'utilisateurs<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#categ">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li>
                                    </ul>
                                </li> 
								<li class="dropdown"><a href="#">Entreprises<i class="fa fa-angle-down"></i></a>
                                    <ul role="menu" class="sub-menu">
                                        <li><a href="#categ">Section 1</a></li>
										<li><a href="#categ">Section 2</a></li> 
										<li><a href="#categ">Section 3</a></li>
                                    </ul>
                                </li> 
								<li><a href="#about">About</a></li>
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