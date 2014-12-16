scotchApp.controller('changePwdController', function(WS_Service,MySharedService,$scope,$ocModal){
	console.log("changePwdController");
	
    $scope.closeDialog = function(){
        $ocModal.close();
    }
    
    $scope.apply() = function()
    {
    	var emailUser = MySharedService.user["email"];
        var old_pwd = document.getElementById("changePwd_old").value;
        var new_pwd = document.getElementById("changePwd_new").value;
        var command = {fonct : "changePassword", Email : emailUser, oldPassword : old_pwd, newPassword : new_pwd};
        
        console.log("changePassword : Email : " + emailUser + " Old password : "+ old_pwd + " New password : " + new_pwd);
  
        WS_Service.send(command);
    }

});
