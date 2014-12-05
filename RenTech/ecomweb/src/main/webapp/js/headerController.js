scotchApp.controller('headerController', function($scope,MySharedService) {
	// Creation automatique de la Websocket lors du passage de 'MyService' en parametre
	
	$scope.number=MySharedService.getNumber();
	
	$scope.$watch(MySharedService.getNumber,function(n){
		  $scope.number=n;
		  $scope.total=MySharedService.getTotal().toFixed(2);	// 2 decimales
	});

});