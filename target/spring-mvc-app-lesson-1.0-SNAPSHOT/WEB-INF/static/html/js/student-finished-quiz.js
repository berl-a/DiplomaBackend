
var endTime, questions, gameId, playerId, game, gameName;
var actualGameName;


$(document).ready(function() {
    questions = JSON.parse(localStorage.getItem("questions"));
    game = JSON.parse(localStorage.getItem("game"));
    gameName = localStorage.getItem("gameName");
    playerId = localStorage.getItem("playerId");
    gameId = localStorage.getItem("gameId");
    actualGameName = localStorage.getItem("actualGameName")
    
    // console.log(game);
    // console.log(gameName);

    $('.quiz-name').text(game.actualQuiz.name);
    $('.current-quiz-name').text(actualGameName);

    $('.back').on('click', onBackClicked);
});

function onBackClicked() {
    window.location = "index.html";
}