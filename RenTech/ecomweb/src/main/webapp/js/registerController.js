scotchApp.controller('registerController', function(WS_Service,MySharedService,$scope) {
	
	$scope.createUser = function() {
		var nameUser = document.getElementById("nameRegister").value;
        var emailUser = document.getElementById("emailRegister").value;
        var passwordUser = document.getElementById("passwordRegister").value;
        var utilisateur = {fonct : "createUser", name: nameUser, email : emailUser, password : passwordUser};
        
        console.log("createUser() : Name : " + nameUser + "Email : " + emailUser + " Password : " + passwordUser);
  
//        MySharedService.user = utilisateur;	// Inscription de la donnee dans un service pour qu'elle soit visible a une autre vue
        WS_Service.send(utilisateur);
	}  
});