scotchApp.controller('pageProduitController', function(MySharedService,$scope,growl) {
	
	$scope.addItem = function (name,prix) {
		MySharedService.addItem(name,prix);
		growl.success("PRODUIT_AJOUTE");
    };
});