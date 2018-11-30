

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
    console.log(game);
    $(".quiz-name").text(game.actualQuiz.name);
    $('.current-quiz-name').text(actualGameName);
    $('.quiz-join-code').text(game.code);
    // $('.quiz-time-passed').text();

    updateTimePassedInterval = setInterval(updateTimePassed, 500);

    $('.quiz-time-full').keypress(function(e) {
        if(e.which == 13) {
            var enteredText = $(e.target).val()
            var minutes = enteredText.split(":")[0];
            var seconds = enteredText.split(":")[1];
            if(minutes < 100 && seconds < 100) {
                var fullTimeInMs = (minutes * 60 + 1 * seconds) * 1000;
                sendNewFullTimeToServer(fullTimeInMs);
            }
        }
    })

    $('.end').click(onEndClicked);
    $('.back').on('click', onBackClicked);

    getStartTimeFromServer();
});

function sendNewFullTimeToServer(newFullTime) {
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:5000/games/setFullTime?" + 
        "gameId=" + game.id + 
        "&fullTime=" + newFullTime,
        dataType: 'json',
        contentType: 'application/json',
        success: function (stringData) {
            console.log("current-time-left is " + stringData.result.time_left);
            timeLeft = stringData.result.time_left ;
        }
    });
}

function getStartTimeFromServer() {
    console.log(game);
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/games/getGameInfo?" + 
        "gameId=" + game.id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (stringData) {
            console.log("receive start time from server");
            console.log(stringData.result);
            var r = stringData.result;
            // var serverTime = r.server_time;
            // var serverStartTime = r.start_time;
            timeLeft = r.time_left;
            fullTime = r.full_time;

            updateTimePassed();
            updateFullTime();
            // console.log(serverTime +  " " + serverStartTime + " " + timeLeft + " " + fullTime);

        }
    });
}

function updateTimePassed() {
    var leftM, leftS, fullM, fullS;
    leftS = Math.floor(timeLeft / 1000 % 60);
    leftM = Math.floor((timeLeft / 1000 - leftS) / 60); 
    if(leftS < 10) leftS = "0" + leftS;
    if(leftM < 10) leftM = "0" + leftM;
    $('.quiz-time-passed').text(leftM + ":" + leftS);
    timeLeft -= 500;
    if(timeLeft <= 0) {
        endGame();
    }
}

function updateFullTime() {
    var leftM, leftS;
    fullS = Math.floor(fullTime / 1000 % 60);
    fullM = Math.floor((fullTime / 1000 - fullS) / 60);
    if(fullS < 10) fullS = "0" + fullS;
    if(fullM < 10) fullM = "0" + fullM;
    $('.quiz-time-full').val(fullM + ":" + fullS);
}


function onBackClicked() {
    window.location = "teacher-quizzes.html";
}

function onEndClicked() {
    // console.log("Ending game");
    endGame();
}

function endGame() {
    // alert("Game ended");
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:5000/games/endGame?" + 
        "gameId=" + game.id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (stringData) {
            console.log("receive start time from server");
            console.log(stringData.result);
            
            clearInterval(updateTimePassedInterval);
            window.location = "teacher-finished-quiz.html";
        }
    });
    
}
