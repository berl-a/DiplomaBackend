
var playerId;
var gameId;
var game;

$(document).ready(function(){

	playerId = localStorage.getItem("playerId");
    gameId = localStorage.getItem("gameId");
    game = JSON.parse(localStorage.getItem("game"));

    // console.log(game);

    setPageValues();

    waitForGameStart();

});

var questionsForPlayer, gameName;

function sendLongPoll() {
    $.ajax({
            type: "GET",
            url: "http://127.0.0.1:80/games/waitForGameStart?" + 
            "gameId=" + gameId + 
            "&playerId=" + playerId,
            dataType: 'json',
            contentType: 'application/json',
            success: function (stringData) {
                console.log("receive");
                console.log(stringData);
                if(typeof stringData.result.longpoll != "undefined") {
                    sendLongPoll()
                } else {
                    gameStarting = true;
                    console.log(stringData.result);
                    questionsForPlayer = stringData.result.object;
                    gameName = stringData.result.name;
                    var timeLeft = stringData.result.time_left;
                    onGameStarting(questionsForPlayer, gameName, timeLeft);
                }
            }
        });
}

function waitForGameStart() {
    sendLongPoll();
}

function onGameStarting(questionsForPlayer, gameName, timeLeft) {
    console.log(questionsForPlayer);
    localStorage.setItem("questions", JSON.stringify(questionsForPlayer));
    localStorage.setItem("timeLeft", timeLeft);
    localStorage.setItem("gameName", gameName);
    window.location = "student-test.html";
}

function setPageValues() {
    $('.quiz-name').text(game.actualQuiz.name);
    $('.quiz-join-code').text(game.code);   
}