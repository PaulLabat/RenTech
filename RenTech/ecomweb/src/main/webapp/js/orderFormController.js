scotchApp.controller('orderFormController', function(WS_Service,MySharedService,$scope,$location,growl) {
	
	$scope.total = MySharedService.getTotal().toFixed(2);	// 2 decimales
	
    $scope.pay = function() {
    	if (MySharedService.connected)
    	{
	    	var t = MySharedService.getTotal().toFixed(2);
	    	var emailUser = MySharedService.user["email"];
	    	var listeGit = MySharedService.getListGit();
	 
	    	var command = {fonct : "pushCommande", email : emailUser, adresse: "avenue du general de Gaulle", prix : t, git : listeGit};
	    	
	    	console.log(command);
	    	
	    	WS_Service.send(command);	// Envoi de la commande au serveur
    	}
    	else
    		growl.error("ERROR_MUST_BE_CONNECTED");
    }   
    
    $scope.changeView = function(view){
        $location.url(view); // path not hash
    	console.log("View changed");            
    } 
    
});