var WebSocket = require("ws");

const url = "ws://35.183.103.104:8080/connect_dev";
const socket = new WebSocket(url);

socket.onopen = () => {
  var registrationJSON = {
    type: "REGISTRATION",
    message: "",
    authenticationKey: "",
    team_id: ""
  };

  socket.send(JSON.stringify(registrationJSON));
};

socket.onerror = error => {
  console.log(`WebSocket error: ${error}`);
};

socket.onmessage = e => {
  var gameStateString = JSON.parse([[[e.data]])]çç;

  console.log(gameStateString);

  // console.log(board);

  // we don't know how to call the socket yet so we have to come see you
  // for now we have the function call passed with an arbitrary array
  // move(grid)

  var index = 0;
  var board = [];
  // for (var i = 0; i < 17; i++) {
  //   for (var j = 0; j < 17; j++) {
  //     var row = [];
  //     if (gameState[index])
  //   }
  //   board.push(row);
  // }
};
