scotchApp.controller('panierController', function(MySharedService,$scope) {
	
	$scope.products = MySharedService.getList();
	$scope.total = MySharedService.getTotal().toFixed(2);	// 2 decimales

    $scope.emptyCard = function() {
    	console.log("emptyCard() : " + $scope.total==0);
        return ($scope.total==0);  
    }   
    
});