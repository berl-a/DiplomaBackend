

var quizId;
var game;
var gameId;
var teacherName;
var teacherId;

var actualGameName, gameTimeInMinutes, gameId;

var updateTimePassedInterval;

var timeLeft, fullTime;

$(document).ready(function(){

    quizStartMoment = Date.now();

    quizId = localStorage.getItem("quizId");
    teacherName = localStorage.getItem("name");
    game = JSON.parse(localStorage.getItem("game"));
    gameId = localStorage.getItem("gameId");
    teacherId = game.actualQuiz.teacher;
    actualGameName = localStorage.getItem("actualGameName")
    gameTimeInMinutes = localStorage.getItem("gameTimeInMinutes");

    $('.logged-in-user-username').text(teacherName);
    // console.log(game);
    $(".quiz-name").text(game.actualQuiz.name);
    $('.current-quiz-name').text(actualGameName);

    $('.back').on('click', onBackClicked);
});


function onBackClicked() {
    window.location = "teacher-workspace.html";
}
