scotchApp.controller('connectedController', function(WS_Service,MySharedService,$scope,$location) {

	$scope.$on('connectionSucceed', function(event, data) {	
		document.getElementById("a_login").style.display = 'none';      
		document.getElementById("s_connectionFailed").style.display = 'none';
		document.getElementById("a_logout").style.display = 'block';       
		
		MySharedService.connected = true;
		
		$scope.$apply($scope.username=data.replace(/\"/g,''));	
		$scope.$apply($scope.changeView('/'));
		
		console.log("connectionSucceedCallback");
    });	
	
    $scope.changeView = function(view){
        $location.url(view); // path not hash
    	console.log("View changed");            
    }   
    
    $scope.disconnect = function(){
		document.getElementById("a_login").style.display = 'block';      
		document.getElementById("a_logout").style.display = 'none';    
		
		MySharedService.connected = false;
    }  
    
	/*$scope.$on('msgReceived', function(event, data) {
		$scope.$apply($scope.msg=data);		
		document.getElementById("a_login").style.display = 'none';        
		document.getElementById("a_logout").style.display = 'block';         
		console.log("msgReceivedCallback");
    });*/
});