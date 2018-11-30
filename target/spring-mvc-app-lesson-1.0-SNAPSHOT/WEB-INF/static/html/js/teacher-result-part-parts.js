const BUTTONS = "<button class=\"btn bg-danger wrong\"><i class=\"fa fa-times fa-1x\"></i></button> " + 
                "<button class=\"btn bg-success right\"><i class=\"fa fa-check fa-1x\"></i></button>";
const FREE_TEXT_ANSWER_CORRECTNESS = -0.12345;

var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;
var results;
var resultId;
var studentIndex;

$(document).ready(function() {
    initTable("result-part-parts");
    teacherId = localStorage.getItem("id");
    reloadResultsToMemory();

});

function reloadResultsToMemory() {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/results/getAll?teacherId=" + teacherId,
        success: function (stringData) {
            var data = stringData.result.objects;
            localStorage.setItem("results", JSON.stringify(data));

            $('.back').on('click', onBackClicked);

            $('.logged-in-user-username').text(localStorage.getItem("name"));

            results = JSON.parse(localStorage.getItem("results"));
            resultId = localStorage.getItem("resultId");
            studentIndex = localStorage.getItem("studentIndex");

            $('.logout-button').on('click', logOut);

            goBackIfNoUncheckedQuestionsLeft();
            getFromLocalStorage();
        }
    });
}
function goBackIfNoUncheckedQuestionsLeft() {
    var item = results.filter(function(result) {
        return result.id === resultId;
    })[0];
    var numberOfUncheckedQuestions = item.playersPoints[studentIndex].points.filter(function(el){return el == -0.12345}).length;
    if(numberOfUncheckedQuestions == 0) {
        console.log("Go back");
        onBackClicked();
    }
}
function getFromLocalStorage() {

    var item = results.filter(function(result) {
        return result.id === resultId;
    })[0];

    console.log(item);

    var uncheckedQuestionsIndexes = [];

    var questionsForCurrentPlayer = item.questionsForPlayers[studentIndex].questions;
    for(var q = 0; q < questionsForCurrentPlayer.length; q ++) {
        if(item.playersPoints[studentIndex].points[q] == FREE_TEXT_ANSWER_CORRECTNESS) {
            uncheckedQuestionsIndexes.push(q);
        }
    }
    console.log(uncheckedQuestionsIndexes);

    for(var i = 0; i < uncheckedQuestionsIndexes.length; i ++) {
        // console.log(data[i]);
        
        var question = item.questionsForPlayers[studentIndex].questions[uncheckedQuestionsIndexes[i]];
        var questionText = question.questionText;
        var questionImage = question.questionImage;
        var questionId = question.id;

        var answer = item.playersAnswers[studentIndex].answers[questionId].answer;

        var newRow = table[0].row.add([
            uncheckedQuestionsIndexes[i] + 1,
            questionText != undefined ? questionText : "",
            questionImage != undefined ? "<img src=" + questionImage + ">" : "",
            answer != undefined ? answer : "",
            BUTTONS
        ]).draw().nodes().to$();
        newRow.attr("data-question-id", questionId);
        newRow.attr("data-index", uncheckedQuestionsIndexes[i]);

        newRow.find("button.wrong").on('click', onWrongButtonClicked);
        newRow.find("button.right").on('click', onRightButtonClicked);
    }

}

function onWrongButtonClicked() {
    $this = $(this);
    var row = $(this).closest('tr');
    var questionIndex = $(row).attr("data-index");
    var questionId = $(row).attr("data-question-id");

    console.log(questionIndex);
    gradeAnswer(resultId, studentIndex, questionId, 0.0, row);
}

function onRightButtonClicked() {
    $this = $(this);
    var row = $(this).closest('tr');
    var questionIndex = $(row).attr("data-index");
    var questionId = $(row).attr("data-question-id");

    console.log(questionIndex);
    gradeAnswer(resultId, studentIndex, questionId, 1.0, row);
}

function gradeAnswer(resultId, studentIndex, questionId, grade, row) {
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:5000/results/grade?resultId=" + resultId + 
        "&studentIndex=" + studentIndex + 
        "&questionId=" + questionId + 
        "&grade=" + grade,
        contentType: "application/json",
        success: function (stringData) {
            console.log(stringData.result.result);
            var tdWithButtons = $(row).find("td:nth-of-type(5)");
            $(tdWithButtons).html("<span class=grade>" + grade + "</span>");
        }
    }); 
    
}

function onBackClicked() {
    window.location = "teacher-result-parts.html";
}


function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}


function onEditContentButtonClicked() {
    // var $this = $(this);
    // var studentIndex = $(this).closest('tr').attr("data-index");
    // console.log("result index is " + studentIndex);
    // localStorage.setItem("studentIndex", studentIndex);
    // window.location = "teacher-result-part-parts.html";
}


var i = 0;
function initTable(tableId) {
    $('#'+tableId+' tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
    } );

    // DataTable
    table[i] = $('#' + tableId).DataTable();
    // console.log(table[i])

    // Apply the search
    table[i].columns().every( function () {
        var that = this;
        $( 'input', this.footer() ).on('keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
    i ++;
}


