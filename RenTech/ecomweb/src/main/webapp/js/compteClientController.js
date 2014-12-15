scotchApp.controller('compteClientController', function(WS_Service,$scope,$location,MySharedService,$ocModal){
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
    
    $scope.deleteAccount = function(){

    	var emailUser = MySharedService.user["email"];
    	var deleteCommand = {fonct : "deleteAccount", email : emailUser};
    	
    	WS_Service.send(deleteCommand);
    	
    	
    	// Deconnexion
		document.getElementById("a_login").style.display = 'block';      
		document.getElementById("a_logout").style.display = 'none';    
		
		MySharedService.connected = false;
		
		// changement de vue
		$scope.changeView('/');
    }
    
    $scope.changeView = function(view){
        $location.url(view); // path not hash
    	console.log("View changed");            
    }   
    
    $scope.email = MySharedService.user["email"];
    $scope.pwd = MySharedService.user["password"];
});
