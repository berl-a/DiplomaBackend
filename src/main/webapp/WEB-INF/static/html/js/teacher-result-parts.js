const BUTTONS = "<button class=\"btn bg-warning edit-content\"><i class=\"fa fa-th-list fa-1x\"></i></button>";


var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;
var results;
var resultId;

$(document).ready(function() {
    initTable("result-parts");

    $('.back').on('click', onBackClicked);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    teacherId = localStorage.getItem("id");
    // teacherId = "-"//todo only for now
    results = JSON.parse(localStorage.getItem("results"));
    resultId = localStorage.getItem("resultId");

    $('.logout-button').on('click', logOut);

    getFromLocalStorage();

});

function getFromLocalStorage() {

    // console.log(results[0]);

    var item = results.filter(function(result) {
        return result.id === resultId;
    })[0];
    console.log(item);

    for(var i = 0; i < item.players.length; i ++) {
        // console.log(data[i]);

        // var numberOfUncheckedQuestions = item.playersPoints[i].points.filter(function(el) {return el === null}).length;

        var answers = item.playersAnswers[i].answers;
        var keys = [];
        for(var k in answers) keys.push(k);
        console.log(keys.length);

        var numberOfUncheckedQuestions = item.playersPoints[i].points.filter(function(el){return el == -0.12345}).length;

        var buttonsIfAreUncheckedQuestions = BUTTONS;
        if(numberOfUncheckedQuestions == 0)
            buttonsIfAreUncheckedQuestions = "";

        var newRow = table[0].row.add([
            i + 1,
            item.realPlayers[i].name,
            item.playersPointSums[i] != undefined ? item.playersPointSums[i] : "",
            numberOfUncheckedQuestions,
            buttonsIfAreUncheckedQuestions
        ]).draw().nodes().to$();
        newRow.attr("data-index", i);


        newRow.find("button.edit-content").on('click', onEditContentButtonClicked);
    }

}

function onBackClicked() {
    window.location = "teacher-results.html";
}


function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}


function onEditContentButtonClicked() {
    var $this = $(this);
    var studentIndex = $(this).closest('tr').attr("data-index");
    console.log("result index is " + studentIndex);
    localStorage.setItem("studentIndex", studentIndex);
    window.location = "teacher-result-part-parts.html";
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


