var type;

$(document).ready(function() {
    $('.logged-in-user-username').text(localStorage.getItem("name"));
    $('.logout-button').on('click', logOut);
    type = localStorage.getItem("type");
    // updateWorkspaceAccordingToUserType();
    // todo uncomment on production
});

function updateWorkspaceAccordingToUserType() {
	if(type === "TEACHER") {
		$('.page-function').text("Teacher workspace")
	} else if (type === "ADMINISTRATOR") {
		$('.page-function').text("Administrator workspace");
	}
	$('.workspace-content').html(content[type]);
}

function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}

var content = {};
content["TEACHER"] = '<div class="col-1"></div> <div class="col-10"> <div class="row"> <div class="col-6 text-right"> <a class="questions btn btn-primary pl-4 pr-4" href="./teacher-questions.html">Questions</a> </div> <div class="col-6 text-left"> <a class="quizzes btn btn-primary" href="./teacher-quizzes.html">Quizzes</a> </div> </div> <div class="row mt-1"> <div class="col-6 text-right"> <a class="quest_categ btn btn-primary" href="./teacher-edit-question-categories.html">Question categories</a> </div> <div class="col-6 text-left"> <a class="quiz_categ btn btn-primary" href="./teacher-edit-quiz-categories.html">Quiz categories</a> </div> </div> <div class="row mt-1"> <div class="col-12 text-center"> <a class="results btn btn-success btn-primary ml-2" href="./teacher-results.html">Results</a> </div> </div> </div>';
content["ADMINISTRATOR"] = '<div class="col-1"></div> <div class="col-10"> <div class="row mt-1"> <div class="col-12 text-center"> <a class="results btn btn-primary btn-warning" href="./teacher-teachers.html">Teachers</a> </div> </div> </div>';