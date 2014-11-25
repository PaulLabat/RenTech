	// create the module and name it scotchApp
    // also include ngRoute for all our routing needs
	var scotchApp = angular.module('scotchApp', ['ngRoute','pascalprecht.translate']);

	// Traductions
	scotchApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
		  HOME : 'Home',
		  PANIER : 'Card',
		  CONNEXION : 'Login',
		  PARTICULIERS : 'Private individuals',
		  G_UTILISATEURS : 'Group of users',
		  ENTREPRISES : 'Companies',
		  CONNECTED : 'Connected as',
		  DECONNEXION : 'Logout',
		  A_PROPOS : 'About',
		  A_PARTIR_DE : 'Starting from',
		  TAXES : 'excl. VAT/month',
		  SIGNUP : 'Signup',
		  REMEMBER_ME : 'Remember me',
		  NOT_A_MEMBER : 'Not a member ?',
		  SAVE : "Save",
		  PASSWORD : "Password",
		  OLD : "Old",
		  NEW : "New",
		  CHECK : "Check",
		  ADDRESS : "Address",
		  COMPLETED_ORDERS : "Completed orders",
		  CURRENT_ORDERS : "Current orders",
		  FIRST_NAME : "First name",
		  LAST_NAME : "Last name",
		  CITY : "City",
		  POSTAL_CODE : "Postal code",
		  PRODUCT : "Product",
		  PRICE : "Price",
		  QUANTITY : "Quantity",
		  VOIR_PLUS: "See more >"
	  });
	  
	  
	  $translateProvider.translations('fr', {
		  HOME : 'Accueil',
		  PANIER : 'Panier',
		  CONNEXION : 'Connexion',
		  PARTICULIERS : 'Particuliers',
		  G_UTILISATEURS : 'Groupe d\'utilisateurs',
		  ENTREPRISES : 'Entreprises',
		  CONNECTED : 'Connect&eacute; en tant que',
		  DECONNEXION : 'Deconnexion',
		  A_PROPOS : '&Agrave; propos',
		  A_PARTIR_DE : '&Agrave; partir de',
		  TAXES : 'HT/mois',
		  SIGNUP : 'S\'inscrire',
		  REMEMBER_ME : 'Se souvenir de moi',
		  NOT_A_MEMBER : 'Pas encore membre ?',
		  SAVE : "Sauvegarde",
		  PASSWORD : "Mot de passe",
		  OLD : "Vieux",
		  NEW : "Nouveau",
		  CHECK : "V&eacute;rifier",
		  ADDRESS : "Adresse",
		  COMPLETED_ORDERS : "Commandes termin&eacute;es",
		  CURRENT_ORDERS : "Commandes en cours",
		  FIRST_NAME : "Pr&eacute;nom",
		  LAST_NAME : "Nom",
		  CITY : "Ville",
		  POSTAL_CODE : "Code postal",
		  PRODUCT : "Produit",
		  PRICE : "Prix",
		  QUANTITY : "Quantit&eacute;",
		  VOIR_PLUS: "En voir plus >"
	  });
	  
	  $translateProvider.preferredLanguage('fr');
	});
	
	scotchApp.controller('Ctrl', function ($scope, $translate) {
		  $scope.changeLanguage = function (key) {
		    $translate.use(key);
		  };
	});

	
	// configure our routes
	scotchApp.config(function($routeProvider) {
		$routeProvider

			.when('/', {
				templateUrl : 'views/home.jsp',
			})

			.when('/about', {
				templateUrl : 'views/about.jsp',
				controller  : 'aboutController'
			})

			.when('/categ', {
				templateUrl : 'views/categ.jsp',
				controller  : 'categController'
			})
		
			.when('/login', {
				templateUrl : 'views/login.jsp',
			})
		
			.when('/panier', {
				templateUrl : 'views/panier.jsp',
			})
		
			.when('/git', {
				templateUrl : 'views/pageProduit_git.jsp',
			})
			.when('/web', {
				templateUrl : 'views/pageProduit_web.jsp',
			})
			.when('/mail', {
				templateUrl : 'views/pageProduit_mail.jsp',
			})
			.when('/compte',{
				templateUrl : 'views/compteClient.jsp',
			})
			.when('/compte/password', {
				templateUrl : 'views/compteClientPassword.jsp',
			})
			.when('/compte/adresse', {
				templateUrl : 'views/compteClientAdresse.jsp',
			})
			.when('/compte/commandes', {
				templateUrl : 'views/compteClientCommandes.jsp',
			})
			.when('/compte/historique', {
				templateUrl : 'views/compteClientHistorique.jsp',
			})
			.otherwise({
				templateUrl : 'views/Error404.jsp'
			})
			;
	});

	// create the controller and inject Angular's $scope
	scotchApp.controller('mainController', function($scope) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';
	});

	scotchApp.controller('aboutController', function($scope) {
		$scope.message = 'Look! I am an about page.';
	});

	scotchApp.controller('categController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	
	scotchApp.controller('panierController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	
	scotchApp.controller('pageProduitController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	