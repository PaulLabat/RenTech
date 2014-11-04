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
		  A_PROPOS : 'About'
	  });
	  
	  
	  $translateProvider.translations('fr', {
		  HOME : 'Accueil',
		  PANIER : 'Panier',
		  CONNEXION : 'Connexion',
		  PARTICULIERS : 'Particuliers',
		  G_UTILISATEURS : 'Groupe d\'utilisateurs',
		  ENTREPRISES : 'Entreprises',
		  A_PROPOS : '� propos'
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
				controller  : 'mainController'
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
		
			.when('/pageProduit', {
				templateUrl : 'views/pageProduit.jsp',
			});
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
	