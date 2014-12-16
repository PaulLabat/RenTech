scotchApp.controller('panierController', function(WS_Service,MySharedService,$scope,$location) {
	
	console.log($location.path());
	$scope.products = MySharedService.getList();
	$scope.total = MySharedService.getTotal().toFixed(2);	// 2 decimales

    $scope.emptyCard = function() {
    	console.log("emptyCard() : " + $scope.total==0);
        return ($scope.total==0);  
    }   
    
    $scope.pay = function() {
    	var 
    	WS_Service,
    }   
    
});