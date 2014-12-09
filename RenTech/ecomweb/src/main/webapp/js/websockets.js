	// ----------------------------- WEBSOCKETS ------------------------------ //
	scotchApp.factory('WS_Service', ['$rootScope', function($rootScope) {
		console.log("Debut"); 
	    // We return this object to anything injecting our service
	    var Service = {};
	    // Keep all pending requests here until they get responses
	    var callbacks = {};
	    // Create a unique callback ID to map requests to responses
	    var currentCallbackId = 0;
	    // Create our websocket object with the address to the websocket
	    var ws = new WebSocket("ws://"+location.host+"/ecom/Services");
	    
	    ws.onopen = function(){  
	        console.log("Socket has been opened!");  
	    };
	    
	    ws.onmessage = function(message) {
	    	console.log(JSON.parse(message.data));
	    	var msg_received = JSON.parse(message.data);
	    	    	
	    	console.log("Message received from server : " + JSON.stringify(msg_received));
	    	if (msg_received["fonct"]=="connectUser")
	    	{
	    		onConnectUser(msg_received);
	    	}	    	
	    };	    

	    function onConnectUser(data) {
	    	if (data["status"]=="OK")
	    	{
	    		$rootScope.$broadcast('connectionSucceed',data["email"]);
	    	}
	    	else if (data["status"]=="FAIL")
    		{
	    		$rootScope.$broadcast('connectionFailed',data["email"]);
    		}
	    }
	    
	    Service.send = function(data) {
	    	console.log("sending : " + JSON.stringify(data));
	    	ws.send(JSON.stringify(data));
	    }
	    
	    return Service;
	}]);