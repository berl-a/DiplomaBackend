

$(document).ready(function() {

	$('a.go-button').click(onGoClicked);
	$('a.results-button').click(onResultsClicked);

});

function onGoClicked() {
    var code = $('input.code').val();
    var name = $('input.name').val();

    if(code === "" || name === "") {
        alert("Enter code and name");
    } else {
        $.ajax({
            type: "post",
            url:
            "http://127.0.0.1:80/games/join?" +
            "code=" + code +
            "&name=" + name,
            success: function (stringData) {
                var data = stringData.result;
                if(typeof data.error !== "undefined") {
                    if(data.error === "not found") {
                        alert("Quiz not found, enter the correct join code");
                    }
                } else {
                    var player = data.player;
                    var game = data.game;

                    var playerId = player.id;
                    var playerName = player.name
                    var gameId = game.id;

                    if(playerName !== name) {
                        alert("Your player name is " + playerName);
                    }

                    localStorage.setItem("playerId", playerId);
                    localStorage.setItem("gameId", gameId);
                    localStorage.setItem("game", JSON.stringify(game));

                    window.location = "student-starting-quiz.html";
                }
            }
        });
    }

}

function onResultsClicked() {
    var code = $('input.code').val();
    var name = $('input.name').val();

    if(code === "" || name === "") {
        alert("Enter code and name");
    } else {
        $.ajax({
            type: "get",
            url:
            "http://127.0.0.1:80/results/get?" +
            "code=" + code +
            "&name=" + name,
            success: function (stringData) {
                var data = stringData.result;
                if(typeof data.error !== "undefined") {
                    if(data.error === "not found") {
                        alert("Your result was not found");
                    }
                } else {
                    var grade = data.object * 100;
                    if(grade !== -100.0) {
                        alert("Your result is " + grade + "%")
                    } else {
                        alert("Your result was not found");
                    }
                }
            }
        });
    }

}