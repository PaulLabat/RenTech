scotchApp.controller('connectedController', function(WS_Service,MySharedService,$scope,$location) {

	$scope.$on('connectionSucceed', function(event, data) {	
		document.getElementById("a_login").style.display = 'none';      
		document.getElementById("s_connectionFailed").style.display = 'none';
		document.getElementById("a_logout").style.display = 'block';       
		
		$scope.$apply($scope.username=data);	
		$scope.$apply($scope.changeView('/'));
		
		console.log("connectionSucceedCallback");
    });	
	
    $scope.changeView = function(view){
        $location.url(view); // path not hash
    	console.log("View changed");            
    }   
    
	/*$scope.$on('msgReceived', function(event, data) {
		$scope.$apply($scope.msg=data);		
		document.getElementById("a_login").style.display = 'none';        
		document.getElementById("a_logout").style.display = 'block';         
		console.log("msgReceivedCallback");
    });*/
});