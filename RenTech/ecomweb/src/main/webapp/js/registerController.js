scotchApp.controller('registerController', function(WS_Service,MySharedService,$scope,$ocModal, growl) {

	$scope.createUser = function() {
		var nameUser = document.getElementById("nameRegister").value;
		var emailUser = document.getElementById("emailRegister").value;
		var passwordUser = document.getElementById("passwordRegister").value;
		var utilisateur = {fonct : "createUser", name: nameUser, email : emailUser, password : passwordUser};

		console.log("createUser() : Name : " + nameUser + "Email : " + emailUser + " Password : " + passwordUser);

		WS_Service.send(utilisateur);
	}  

	$scope.openDialogCGU = function(){
		$ocModal.open({
			url :'views/dialog/CGU.jsp',
			controller : 'CGUController',
			cls: 'slide-down'
		});
	}

	$scope.$on('createUserSucceed', function(event, data) {	
		console.log("createUserSucceed");
		
		var messageSucceed = "A confirmation mail has been sent to " + data["email"];
		growl.success(messageSucceed, {ttl: -1});
    });	
	
	$scope.$on('createUserFailExist', function(event, data) {	
		console.log("createUserFailExist");

		var messageFail = "You are already registered !";
		growl.error(messageFail);
	});	

});
