scotchApp.controller('changePwdController', function($scope,$ocModal){
	console.log("changePwdController");
	
    $scope.closeDialog = function(){
        $ocModal.close();
    }

});
