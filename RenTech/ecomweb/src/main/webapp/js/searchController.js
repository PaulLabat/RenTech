scotchApp.controller('searchController', function($scope,$location, $translate){

	$scope.changeView = function(view){
		$location.url(view);           
	};
	$scope.changeLanguageSearch = function (key) {
	    $translate.use(key);
	  };


	$scope.services = ["Serveur Git",
	                   "Hebergement Web",
	                   "Serveur Mail",
	                   "Serveur partage",
	                   "Serveur dedie",
	                   "Mumble",
	                   "Cloud dedie",
	                   "Cloud public",
	                   "Stockage",
	                   "Git server",
	                   "Web hosting",
	                   "Mailserver",
	                   "Dedicated server",
	                   "File hosting service",
	                   "Public cloud",
	                   "Dedicated cloud",
	                   "Data storage"];
	$scope.updateServices = function(typed){
		switch(typed){
		case "Serveur Git":
			$scope.$apply($scope.changeLanguageSearch('fr'));
			$scope.$apply($scope.changeView('/git'));
			break;
		case "Hebergement Web":
			$scope.$apply($scope.changeLanguageSearch('fr'));
			$scope.$apply($scope.changeView('/web'));
			break;
		case "Serveur Mail":
			$scope.$apply($scope.changeLanguageSearch('fr'));
			$scope.$apply($scope.changeView('/mail'));
			break;
		case "Serveur partage":
		case "Serveur dedie" :
		case "Mumble" :
		case "Cloud dedie":
		case "Cloud public" :
		case "Stockage":
			break;
		case "Git server":
			$scope.$apply($scope.changeLanguageSearch('en'));
			$scope.$apply($scope.changeView('/git'));
			break;
		case "Web hosting":
			$scope.$apply($scope.changeLanguageSearch('en'));
			$scope.$apply($scope.changeView('/web'));
		case "Mailserver":
			$scope.$apply($scope.changeLanguageSearch('en'));
			$scope.$apply($scope.changeView('/mail'));
			break;
		case "Dedicated server":
		case "File hosting service" :
		case "Public cloud" :
		case "Dedicated cloud":
		case "Data storage" :
			break;
		}
	};
});