scotchApp.controller('searchController', function($scope){
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
		console.log(typed);
	};
});