scotchApp.controller('compteClientController', function($scope,MySharedService,$ocModal){
    $scope.openDialogProfile = function(){
    	$ocModal.open({
    		url :'views/changeProfile.jsp',
    		controller: 'changeProfileController',
    		cls: 'slide-down'
    	});
    }
    
    $scope.openDialogPwd = function(){
    	$ocModal.open({
    		url :'views/changePwd.jsp',
    		controller: 'changePwdController',
    		cls: 'slide-down'
    	});
    }
    
    $scope.email = MySharedService.user["email"];
    $scope.pwd = MySharedService.user["password"];
});
