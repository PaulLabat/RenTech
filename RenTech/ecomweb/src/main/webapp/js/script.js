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
	
	// ----------------------------- WEBSOCKETS ------------------------------ //
	scotchApp.factory('WS_Service', ['$rootScope', function($rootScope) {
		console.log("Debut"); 
	    // We return this object to anything injecting our service
	    var Service = {};
	    // Keep all pending requests here until they get responses
	    var callbacks = {};
	    // Create a unique callback ID to map requests to responses
	    var currentCallbackId = 0;
	    // Create our websocket object with the address to the websocket
	    var ws = new WebSocket("ws://"+location.host+"/ecom/Services");
	    
	    ws.onopen = function(){  
	        console.log("Socket has been opened!");  
	    };
	    
	    ws.onmessage = function(message) {
	    	console.log(JSON.parse(message.data));
	    	var msg_received = JSON.parse(message.data);
	    	    	
	    	console.log("Message received from server : " + JSON.stringify(msg_received));
	    	if (msg_received["fonct"]=="connectUser")
	    	{
	    		onConnectUser(msg_received);
	    	}	    	
	    };	    

	    function onConnectUser(data) {
	    	if (data["status"]=="OK")
	    	{
	    		$rootScope.$broadcast('connectionSucceed',data["email"]);
	    	}
	    	else if (data["status"]=="FAIL")
    		{
	    		$rootScope.$broadcast('connectionFailed',data["email"]);
    		}
	    }
	    
	    Service.send = function(data) {
	    	console.log("sending...");
	    	ws.send(JSON.stringify(data));
	    }
	    
	    return Service;
	}]);
	
	
	// ---------------------- TRADUCTIONS -------------------------- //

	scotchApp.config(function ($translateProvider) {
	  $translateProvider.translations('en', {
		  HOME : 'Home',
		  PANIER : 'Cart',
		  PRODUIT : 'Products',
		  CONNEXION : 'Login',
		  CONNECTE : 'Connected as ',
		  CONNEXION_FAILED : 'Incorrect email or password',
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
		  HISTORIQUE : "History rentals",
		  PARAMETRES : "Account parameters",
		  PRODUCT : "Product",
		  PRICE : "Price",
		  QUANTITY : "Quantity",
		  VOIR_PLUS: "See more >",
		  PAYEMENT : "Checkout",
		  PRODUIT_AJOUTE : "Product added!"
	  });
	  
	  
	  $translateProvider.translations('fr', {
		  HOME : 'Accueil',
		  PRODUIT : 'Produits',
		  PANIER : 'Panier',
		  CONNEXION : 'Connexion',
		  CONNECTE : 'Connect&eacute; en tant que ',
		  CONNEXION_FAILED : 'Email ou mot de passe invalide',
		  PARTICULIERS : 'Particuliers',
		  G_UTILISATEURS : 'Groupe d\'utilisateurs',
		  ENTREPRISES : 'Entreprises',
		  CONNECTED : 'Connect&eacute; en tant que',
		  DECONNEXION : 'D&eacuteconnexion',
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
		  HISTORIQUE : "Historique des commandes",
		  PARAMETRES : "Param&egrave;tres compte",
		  PRODUCT : "Produit",
		  PRICE : "Prix",
		  QUANTITY : "Quantit&eacute;",
		  VOIR_PLUS: "En voir plus >",
		  PAYEMENT: "Payement",
	      PRODUIT_AJOUTE : "Produit ajout&eacute;!"
	  });
	  
	  $translateProvider.preferredLanguage('fr');
	});
	
	scotchApp.controller('Ctrl', function ($scope, $translate) {
		  $scope.changeLanguage = function (key) {
		    $translate.use(key);
		  };
	});
	
	// ------------------------------- POPS UP --------------------------------//
	scotchApp.config(['growlProvider', function(growlProvider) {
		  growlProvider.globalTimeToLive(2000);
		  growlProvider.globalDisableCountDown(true);
//		  growlProvider.globalPosition('bottom-right');
	}]);
	
	// --------------------------------- URLS -------------------------------- //

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
				controller : 'loginController'
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

	scotchApp.controller('headerController', function($scope,MySharedService){
		// Creation automatique de la Websocket lors du passage de 'MyService' en parametre
		
		$scope.number=MySharedService.getNumber();
		
		$scope.$watch(MySharedService.getNumber,function(n){
			  $scope.number=n;
			  $scope.total=MySharedService.getTotal().toFixed(2);	// 2 decimales
		});

	});
	
	scotchApp.controller('aboutController', function($scope) {
		$scope.message = 'Look! I am an about page.';
	});

	
	scotchApp.controller('loginController', function(WS_Service,MySharedService,$scope) {
		
		$scope.connectUser = function() {
            var emailUser = document.getElementById("emailConnect").value;
            var passwordUser = document.getElementById("passwordConnect").value;
            var utilisateur = {fonct : "connectUser", email : emailUser, password : passwordUser};
            
            console.log("Email : " + emailUser + " Password : " + passwordUser);
      
            MySharedService.user = utilisateur;	// Inscription de la donnee dans un service pour qu'elle soit visible a une autre vue
            WS_Service.send(utilisateur);
		}
		
		$scope.$on('connectionFailed', function(event, data) { 
			document.getElementById("s_connectionFailed").style.display = 'block';
			console.log("connectionFailedCallback");
        });    
	});
	
	scotchApp.controller('connectedController', function(WS_Service,MySharedService,$scope,$location) {

		$scope.$on('connectionSucceed', function(event, data) {	
			document.getElementById("a_login").style.display = 'none';      
			document.getElementById("s_connectionFailed").style.display = 'none';
			document.getElementById("a_logout").style.display = 'block';       
			
			$scope.$apply($scope.username=data);	
			$scope.$apply($scope.changeView('/'));
			
			console.log("connectionSucceedCallback");
        });	
		
        $scope.changeView = function(view){
            $location.url(view); // path not hash
	    	console.log("View changed");            
        }   
        
		/*$scope.$on('msgReceived', function(event, data) {
			$scope.$apply($scope.msg=data);		
			document.getElementById("a_login").style.display = 'none';        
			document.getElementById("a_logout").style.display = 'block';         
			console.log("msgReceivedCallback");
        });*/
	});
	
	scotchApp.controller('categController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	
	scotchApp.controller('panierController', function($scope) {
		$scope.message = 'Hey! This is a section!';
	});
	
	scotchApp.controller('pageProduitController', function(MySharedService,$scope,growl) {
		
		$scope.addItem = function (prix) {
			MySharedService.addItem(prix);
			growl.success("PRODUIT_AJOUTE");
        };
	});
	