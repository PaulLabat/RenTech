var webSocket;
            var messages = document.getElementById("messages");
           //console.log(document);
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                webSocket = new WebSocket("ws://localhost:8080/ecomweb-0.1.0/Services");
                console.log("WebSocket Cree");
                 
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined)
                        return;
 
                    writeResponse(event.data);
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            }
           
            /**
             * Sends the value of the text input to the server
             */
            function send(){
                var text = document.getElementById("messageinput").value;
                webSocket.send(text);
            }
            
            function createUser(){
            	console.log("Create User");
                var nameUser = document.getElementById("nameRegister").value;
                var emailUser = document.getElementById("emailRegister").value;
                var passwordUser = document.getElementById("passwordRegister").value;
                
                console.log("name : "+nameUser+" email : "+emailUser+" password : "+passwordUser);
                var utilisateur = {fonct : "createUser", name : nameUser, email : emailUser, password : passwordUser};
                console.log(utilisateur);
                
                webSocket.send(JSON.stringify(utilisateur));
            }
            
            function connectUser(){
            	console.log("Connect User");
            
                var emailUser = document.getElementById("emailConnect").value;
                var passwordUser = document.getElementById("passwordConnect").value;
                
                console.log("email : "+emailUser+" password : "+passwordUser);
                var utilisateur = {fonct : "connectUser", email : emailUser, password : passwordUser};
                console.log(utilisateur);
                
                webSocket.send(JSON.stringify(utilisateur));
            }
            
            function addCart(item){            
                
                console.log("element à ajouter : "+item);
                var aAjouter = {fonct : "addCart", idObjet : item};
                console.log(aAjouter);
                
                webSocket.send(JSON.stringify(aAjouter));
            }
           
            function closeSocket(){
                webSocket.close();
            }
 
            function writeResponse(text){
            	var aParser = text;
            	var montext = JSON.parse(aParser);
                console.log(montext);
            }