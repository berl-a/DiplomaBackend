

var quizId;
var game;
var gameId;
var teacherName;

$(document).ready(function(){

    game = JSON.parse(localStorage.getItem("game"));
    quizId = localStorage.getItem("quizId");
    teacherName = localStorage.getItem("name");
    $('.logged-in-user-username').text(teacherName);

	getFromBackend();
   

    $('.back').on('click', onBackClicked);
	$('.start').click(onStartClicked);
});

var playersJoinedIds = [];
var playersJoinedNames = [];

function sendLongPoll() {
    $.ajax({
            type: "GET",
            url: "http://127.0.0.1:5000/games/waitForPlayerJoin?" + 
            "gameId=" + game.id + 
            "&teacherId=" + game.actualQuiz.teacher + 
            "&playersAlreadyJoined=" + playersJoinedIds,
            dataType: 'json',
            contentType: 'application/json',
            success: function (stringData) {
                console.log("receive");
                console.log(stringData);
                if(typeof stringData.result.longpoll != "undefined") {
                    sendLongPoll();
                } else {
                    playersJoined = stringData.result.objects;
                    onPlayerJoined(playersJoined);
                    sendLongPoll();
                }
            }
        });
}

function startRefreshingPlayersList() {
    sendLongPoll();
}

function onPlayerJoined(playersJoined) {
    playersJoinedIds = [];
    playersJoinedNames = [];
    var playersJoinedCombined = "";
    playersJoined.forEach(function(e){
        playersJoinedIds.push(e.id);
        playersJoinedNames.push(e.name);
        playersJoinedCombined += e.name + "\n";
    });
    $('.players-joined').val(playersJoinedCombined);
    console.log(playersJoinedIds);
}


function onStartClicked() {
    var name = $('.current-quiz-name').val();
    var time = $('.current-quiz-time').val();
    if(name == '' || time == '') {
        alert("Enter name and time");
    } else {
        localStorage.setItem("actualGameName", name);
        localStorage.setItem("gameTimeInMinutes", time);
        $.ajax({
            type: "post",
            url: "http://127.0.0.1:5000/games/startGame?" + 
            "gameId=" + game.id + 
            "&teacherId=" + game.actualQuiz.teacher + 
            "&gameName=" + name + 
            "&time=" + time,
            success: function (stringData) {
                console.log(stringData.result);
                window.location = "teacher-running-quiz.html";
            }
        });   
    }
}

function onBackClicked() {
    window.location = "teacher-quizzes.html";
}

function getFromBackend() {
	$.ajax({
        type: "post",
        url: "http://127.0.0.1:5000/games/addFromQuiz",
        dataType: 'json',
        contentType: 'application/json',
        data: quizId,
        success: function (stringData) {
        	console.log(stringData.result);
        	game = stringData.result.object;
            localStorage.setItem("game", JSON.stringify(game));
        	$('.quiz-name').text(game.actualQuiz.name);
        	$('.quiz-join-code').text(game.code);

            startRefreshingPlayersList();
        }
    });
}