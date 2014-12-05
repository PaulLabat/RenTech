scotchApp.controller('pageProduitController', function(MySharedService,$scope,growl) {
	
	$scope.addItem = function (prix) {
		MySharedService.addItem(prix);
		growl.success("PRODUIT_AJOUTE");
    };
});