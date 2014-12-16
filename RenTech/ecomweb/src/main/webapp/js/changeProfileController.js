scotchApp.controller('changeProfileController', function(WS_Service,MySharedService,$scope,$ocModal){
	console.log("changeProfileController");
	
    $scope.closeDialog = function(){
        $ocModal.close();
    }

    $scope.apply = function(){
    	var oldEmail_user = MySharedService.user["email"];
        var new_firstName = document.getElementById("changeProfile_firstName").value;
        var new_name = document.getElementById("changeProfile_name").value;
        var new_email = document.getElementById("changeProfile_email").value;

        var command = {fonct : "changeInfos", oldEmail : oldEmail_user, newEmail : new_email, newName : new_name, newFirstName : new_firstName};
        
        console.log("changeInfos() : oldEmail : " + oldEmail_user + " newEmail : " + new_email + " newName : " + new_name + " newFirstName : " + new_firstName);
  
        WS_Service.send(command);
    }
    
});
