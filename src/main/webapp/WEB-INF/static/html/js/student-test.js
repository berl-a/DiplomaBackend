const SHOW_CORRECT_ANSWER_TO_STUDENT = false;

var endTime, questions, gameId, playerId, game, gameName;

var timeLeft;

$(document).ready(function() {
	questions = JSON.parse(localStorage.getItem("questions"));
	game = JSON.parse(localStorage.getItem("game"));
	gameName = localStorage.getItem("gameName");
	playerId = localStorage.getItem("playerId");
    gameId = localStorage.getItem("gameId");
    timeLeft = localStorage.getItem("timeLeft");
    console.log("Time left is " + timeLeft);
    if(timeLeft < 0) {
    	onQuizEnd()
    } else {
    	startTest();
    }
});


var currQuestionIndex = -1;
var currQuestionType;
var gameEndTimeout;

function refreshEndGameTimeout() {
	clearTimeout(gameEndTimeout);
	console.log("time left is " + timeLeft)
	gameEndTimeout = setTimeout(function() {console.log("2"); onQuizEnd()}, timeLeft);
}

function startTest() {
	nextQuestion()
	refreshEndGameTimeout();
}

function nextQuestion() {
	currQuestionIndex ++;
	if(currQuestionIndex != 0 && questions.length != 1) {
		console.log(currQuestionIndex);
		console.log(questions.length);
		if(currQuestionIndex >= questions.length) {
			console.log("1")
			onQuizEnd();
		}
	}
	console.log("current question is " + currQuestionIndex);
	
	populatePageWithQuestion(currQuestionIndex);


}


function populatePageWithQuestion(index) {
	var question = questions[index];
	// console.log(question);
	console.log("question is");
	console.log(question);
	if(typeof question === "undefined") {
		return;
	}

	$('.question-number').text((currQuestionIndex + 1) + "/" + questions.length);
	$('.question-text').text(question.questionText);
	$('.questionImage').attr('src', question.questionImage);

	currQuestionType = question.questionType;
	$('.container.answers').html(answerButtons[currQuestionType]);

	if(currQuestionType === "SINGLE_CHOICE") {
		for(var ans = 0; ans < 4; ans ++) {

			var imageCode = "";
			if(typeof question.answerImages[ans] != "undefined")
				imageCode = "<br><img class='answer-image answer-" + ans + " img-responsive' src=" + question.answerImages[ans] + ">";


			$('.answer-col.answer-' + ans).html(
				"<button class='btn btn-primary answer answer-" + ans + "'>" + 
				question.answerTexts[ans] +
				imageCode + 
				"</button>"
			);
			// console.log("adding listeners")
			$('button.answer-' + ans).on('click', onAnswerButtonClick);
		}

	} else if(currQuestionType === "MULTIPLE_CHOICE") {
		for(var ans = 0; ans < 4; ans ++) {

			var imageCode = "";
			if(typeof question.answerImages[ans] != "undefined")
				imageCode = "<br><img class='answer-image answer-" + ans + " img-responsive' src=" + question.answerImages[ans] + ">";


			$('.answer-col.answer-' + ans).html(
				"<button class='btn btn-primary answer answer-" + ans + " answer-multiple'>" + 
				question.answerTexts[ans] +
				imageCode + 
				"</button>"
			);
			// console.log("adding listeners")
			$('button.answer-' + ans).on('click', onAnswerButtonClick);
			$('.submit-answer').on('click', onSubmitButtonClick);
		}

	} else if (currQuestionType === "FREE_TEXT") {
		$('.submit-answer').on('click', onSubmitButtonClick);
	}
	answeredThisQuestion = false;
}

var answeredThisQuestion = false;
function onAnswerButtonClick() {
	var $this = $(this);
	if(currQuestionType === "SINGLE_CHOICE") {
		var answer = $this.attr("class")[$this.attr("class").indexOf("answer-") + 7];

		if(!answeredThisQuestion) {
			$.ajax({
	            type: "POST",
	            url: "http://127.0.0.1:80/games/answerQuestion?" + 
	            "gameId=" + gameId + 
	            "&playerId=" + playerId + 
	            "&questionId=" + questions[currQuestionIndex].id + 
	            "&questionType=" + currQuestionType+
	            "&answerAsString=" + answer,
	            dataType: 'json',
	            contentType: 'application/json',
	            success: function (stringData) {
	                // console.log(stringData);
	                timeLeft = stringData.result.time_left;
	                refreshEndGameTimeout();

	                var answerCorrect = stringData.result.result;
	                if(SHOW_CORRECT_ANSWER_TO_STUDENT) {
		                if(answerCorrect) {
		                	$this.addClass("correct");
		                } else {
		                	$this.addClass("incorrect");
		                }
		                answeredThisQuestion = true;
		            	setTimeout(nextQuestion, 500);
		            } else {
		            	answeredThisQuestion = true;
		            	nextQuestion();
		            }
	            }
		    });
		}
	} else if(currQuestionType === "MULTIPLE_CHOICE") {
		var answer = $this.attr("class")[$this.attr("class").indexOf("answer-") + 7];
		// console.log("answer #" + answer);
		$this.toggleClass("selected");
	}
}

function onSubmitButtonClick() {
	var $this = $(this);
	var answerAsString;
	if (currQuestionType === "FREE_TEXT") {
		var answer = $('textarea.answer-text').val();
		console.log("answer is " + answer);
		answerAsString = answer;
		
	} else if(currQuestionType === "MULTIPLE_CHOICE") {
		var answerIndexes = [];
		$('.answer').each(function(el) {
			if($(this).attr("class").indexOf("selected") !== -1) {
				answerIndexes.push($(this).attr("class")[$(this).attr("class").indexOf("answer-") + 7]);
			}
		})
		console.log("answer indexes are " + answerIndexes);
		console.log("and joined: " + answerIndexes.join());
		answerAsString = answerIndexes.join();
	}
	console.log("answer as string is " + answerAsString);
	if(!answeredThisQuestion) {
		$.ajax({
            type: "POST",
            url: "http://127.0.0.1:80/games/answerQuestion?" + 
            "gameId=" + gameId + 
            "&playerId=" + playerId + 
            "&questionId=" + questions[currQuestionIndex].id + 
            "&questionType=" + currQuestionType+
            "&answerAsString=" + answerAsString,
            dataType: 'json',
            contentType: 'application/json',
            success: function (stringData) {
                // console.log(stringData);
                timeLeft = stringData.result.time_left;
                refreshEndGameTimeout();

                answeredThisQuestion = true;
            	setTimeout(nextQuestion, 500);
            }
	    });
	}
}

function onQuizEnd() {
	// alert("Quiz ended");
	clearTimeout(gameEndTimeout);
	console.log("end");
	// $('.quiz-end-frame').removeClass("d-none");
	// $('.quiz-end-frame').dialog();
	window.location = "student-finished-quiz.html";
}

var answerButtons = {
	"SINGLE_CHOICE": '<div class="row p-2"> <div class="col-1 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-0"> <button class="btn btn-primary answer answer-0"> <br> <img class="answer-image answer-0 img-responsive"> </button> </div> <div class="col-2 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-1"> <button class="btn btn-primary answer answer-1"> <br> <img class="answer-image answer-1"> </button> </div> <div class="col-1"></div> </div> <div class="row p-2"> <div class="col-1"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-2"> <button class="btn btn-primary answer answer-2 "> <br> <img class="answer-image answer-2 img-responsive"> </button> </div> <div class="col-2"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-3"> <button class="btn btn-primary answer answer-3 "> <br> <img class="answer-image answer-3 img-responsive"> </button> </div> <div class="col-1"></div> </div>',
	"MULTIPLE_CHOICE": '<div class="row p-2"> <div class="col-1 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-0"> <button class="btn btn-primary answer answer-0 answer-multiple"> <br> <img class="answer-image answer-0 img-responsive"> </button> </div> <div class="col-2 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-1"> <button class="btn btn-primary answer answer-1 answer-multiple"> <br> <img class="answer-image answer-1"> </button> </div> <div class="col-1"></div> </div> <div class="row p-2"> <div class="col-1"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-2"> <button class="btn btn-primary answer answer-2 answer-multiple"> <br> <img class="answer-image answer-2 img-responsive"> </button> </div> <div class="col-2"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-3"> <button class="btn btn-primary answer answer-3 answer-multiple"> <br> <img class="answer-image answer-3 img-responsive"> </button> </div> <div class="col-1"></div> </div> <div class="row"> <div class="col-12 text-center"> <button class="btn btn-primary submit-answer multiple-choice">Submit</button> </div> </div>',
	"FREE_TEXT": '<div class="row"> <div class="col-12"> <textarea class="answer-text" rows=6></textarea> </div> </div> <div class="row"> <div class="col-12 text-center"> <button class="btn btn-primary submit-answer">Submit</button> </div> </div>'
};