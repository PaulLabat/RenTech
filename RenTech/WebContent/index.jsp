<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="fr">
  <head>
    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <link rel="icon" href="favicon.ico">

	    <title>RenTech</title>

	    <!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">


  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
          
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        <a class="navbar-brand" href="#"><img src="img/top-logo.png"/></a>
        </div>
        
        

        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li><a href="#web">Web</a></li>
            <li><a href="#dedie">Dédié</a></li>
			<li><a href="#service">Services</a></li>
            <li>
				<form class="navbar-form" role="search" method="get" id="search-form" name="search-form">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search" id="query" name="query" value="">
							<div class="input-group-btn">
						    	<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-search"></span></button>
						    </div>
					</div>
				</form>
			</li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Votre compte <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Panier</a></li>
                  <li><a href="#">Suivi commande</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Paramètres</a></li>
                  <li><a href="#">Déconnexion</a></li>
                </ul>
              </li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
	
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

  </body>
</html>

