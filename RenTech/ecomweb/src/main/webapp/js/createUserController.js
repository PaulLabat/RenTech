scotchApp.controller('createUserController', function($init, $scope, $ocModal) {
	
	$scope.email = $init.param1;
	
    $scope.closeDialog = function(){
        $ocModal.close();
    }
});