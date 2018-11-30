

$(document).ready(function() {

	$('a.go-button').click(onGoClicked);

})

function onGoClicked() {
	var code = $('input.code').val();
	var name = $('input.name').val();

	if(code == "" || name == "") {
		alert("Enter code and name");
	} else {
		$.ajax({
	        type: "post",
	        url: 
	        "http://127.0.0.1:5000/games/join?" + 
	        "code=" + code + 
	        "&name=" + name,
	        success: function (stringData) {
	            var data = stringData.result;
	            if(typeof data.error != "undefined") {
	            	if(data.error == "not found") {
	            		alert("Quiz not found, enter the correct join code");
	            	}
	            } else {
	            	var player = data.player;
	            	var game = data.game;

	            	var playerId = player.id;
	            	var gameId = game.id;

	            	localStorage.setItem("playerId", playerId);
	            	localStorage.setItem("gameId", gameId);
	            	localStorage.setItem("game", JSON.stringify(game));

	            	window.location = "student-starting-quiz.html";
	            }
	        }
	    });
	} 
	
}