scotchApp.controller('loginController', function(WS_Service,MySharedService,$scope) {
	
	$scope.connectUser = function() {
        var emailUser = document.getElementById("emailConnect").value;
        var passwordUser = document.getElementById("passwordConnect").value;
        var utilisateur = {fonct : "connectUser", email : emailUser, password : passwordUser};
        
        console.log("connectUser() : Email : " + emailUser + " Password : " + passwordUser);
  
        MySharedService.user = utilisateur;	// Inscription de la donnee dans un service pour qu'elle soit visible a une autre vue
        WS_Service.send(utilisateur);
	}
	
	$scope.$on('connectionFailed', function(event, data) { 
		document.getElementById("s_connectionFailed").style.display = 'block';
		console.log("connectionFailedCallback");
    });    
});