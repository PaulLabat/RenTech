scotchApp.controller('changeProfileController', function($scope,$ocModal){
	console.log("changeProfileController");
	
    $scope.closeDialog = function(){
        $ocModal.close();
    }

});
