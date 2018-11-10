var WebSocket = require('ws')

const url = 'ws://35.183.103.104:8080/connect_dev';
const socket = new WebSocket(url);

socket.onopen = () => {

  var registrationJSON = { 
    type: "REGISTRATION",
    message: "",
    authenticationKey: "",
    team_id: ""
  };

  socket.send(JSON.stringify(registrationJSON));
}

socket.onerror = (error) => {
  console.log(`WebSocket error: ${error}`)
}

socket.onmessage = (e) => {
  var gameState = [[e.data]];
  console.log(gameState[0][1]);
  
  
  var index = 0;
  var board = [];
  // for (var i = 0; i < 17; i++) {
  //   for (var j = 0; j < 17; j++) {
  //     var row = [];
  //     if (gameState[index])
  //   }
  //   board.push(row);
  // }
  
  
}