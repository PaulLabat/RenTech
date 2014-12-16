scotchApp.controller('registerController', function(WS_Service,MySharedService,$scope,$ocModal) {
	
	$scope.createUser = function() {
		var nameUser = document.getElementById("nameRegister").value;
        var emailUser = document.getElementById("emailRegister").value;
        var passwordUser = document.getElementById("passwordRegister").value;
        var utilisateur = {fonct : "createUser", name: nameUser, email : emailUser, password : passwordUser};
        
        console.log("createUser() : Name : " + nameUser + "Email : " + emailUser + " Password : " + passwordUser);
  
        WS_Service.send(utilisateur);
	}  
	
	$scope.$on('createUserSucceed', function(event, data) {	
			console.log("createUserSucceed");
			
	    	$ocModal.open({
	    		url :'views/dialog/createUserSucceedDialog.jsp',
	    		controller : 'createUserSucceedController',
	    		cls: 'slide-down',
    		    init: {
    		        param1: data["email"]
    		    }
	    	});
    });	
});