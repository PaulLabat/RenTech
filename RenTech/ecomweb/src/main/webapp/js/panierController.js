scotchApp.controller('panierController', function(WS_Service,MySharedService,$scope,$location) {
	
	console.log($location.path());
	$scope.products = MySharedService.getList();
	$scope.total = MySharedService.getTotal().toFixed(2);	// 2 decimales

    $scope.emptyCard = function() {
    	console.log("emptyCard() : " + $scope.total==0);
        return ($scope.total==0);  
    }   
    
    $scope.pay = function() {
    	var t = JSON.stringify(MySharedService.getTotal().toFixed(2));
    	console.log(t);
    	var listeGit = MySharedService.getListGit();
    	console.log(listeGit);
//    	var listeOffre = {prix : t; git : listeGit};
//    	console.log(listeOffre);
//    	var command = {fonct : "pushCommande", adresse: "avenue du general de Gaulle", offre : listeOffre};
    	
//    	console.log(command);
    }   
    
});