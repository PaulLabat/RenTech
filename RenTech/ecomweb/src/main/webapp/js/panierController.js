scotchApp.controller('panierController', function(WS_Service,MySharedService,$scope,$location,growl) {
	
	$scope.products = MySharedService.getList();
	$scope.total = MySharedService.getTotal().toFixed(2);	// 2 decimales

    $scope.emptyCard = function() {
    	console.log("emptyCard() : " + $scope.total==0);
        return ($scope.total==0);  
    }   
    
    $scope.pay = function() {
    	if (MySharedService.connected) 	
	    	$scope.changeView('/orderForm');
    	
    	else
    		growl.error("ERROR_MUST_BE_CONNECTED");
    }   
    
    $scope.changeView = function(view){
        $location.url(view); // path not hash
    	console.log("View changed");            
    } 
    
});