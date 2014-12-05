	// create the module and name it scotchApp
    // also include ngRoute for all our routing needs
	var scotchApp = angular.module('scotchApp', ['ngRoute','pascalprecht.translate','angular-growl','ngAnimate']);

	// -------------- SERVICE POUR COMMUNIQUER ENTRE LES VUES ---------------- //
	
	scotchApp.factory('MySharedService', ['$rootScope', function($rootScope) {
		var sharedService = {};
		
		// Donnees communes aux differentes vues
		var user;

		var number=0;
		var total=0;
		 
		sharedService.getNumber = function () {
	    	return number;
	    };
	        
	    sharedService.getTotal = function () {
	    	return total;
	    };
	        
		sharedService.addItem = function (prix) {
    	  	number=number+1;
    	  	total=total+prix;
        };
	        
	    return sharedService;
	}]);
	
	// ----------------------------- CONFIGURATIONS -------------------------------- //
	
	// -------------- POPS UP ----------------//
	scotchApp.config(['growlProvider', function(growlProvider) {
		  growlProvider.globalTimeToLive(2000);
		  growlProvider.globalDisableCountDown(true);
//		  growlProvider.globalPosition('bottom-right');
	}]);
	
	// ---------------- URLS ---------------- //

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
				controller : 'pageProduitController'
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

	
	// ------------------------------- CONTROLEURS --------------------------------- //
	
	scotchApp.controller('mainController', ['WS_Service', 'MySharedService', function(WS_Service,MySharedService){
		// Creation automatique de la Websocket lors du passage de 'MyService' en parametre
		
	}]);
	
	scotchApp.controller('aboutController', function($scope) {
		$scope.message = 'Look! I am an about page.';
	});
	
	scotchApp.controller('categController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	
	scotchApp.controller('panierController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	