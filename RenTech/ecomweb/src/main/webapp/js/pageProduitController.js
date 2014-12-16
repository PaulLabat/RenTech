scotchApp.controller('pageProduitController', function(MySharedService,$scope,growl) {
	
	$scope.addItemGit = function (name,prix,taille,ram,nbCoeurs) {
		MySharedService.addItemGit(name,prix,taille,ram,nbCoeurs);
		growl.success("PRODUIT_AJOUTE");
    };
});