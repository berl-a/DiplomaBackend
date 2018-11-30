const BUTTONS = "<button class=\"btn bg-info edit-content\"><i class=\"fa fa-th-list fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button>";


var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;

$(document).ready(function() {
    initTable("results");

    $('button.remove').on('click', onRemoveButtonClicked);

    $('.back').on('click', onBackClicked);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    teacherId = localStorage.getItem("id");
    // teacherId = "-"//todo only for now

    console.log(teacherId);

    $('.logout-button').on('click', logOut);

    getFromBackend();

});

function getFromBackend() {

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/results/getAll?teacherId=" + teacherId,
        success: function (stringData) {
        	var data = stringData.result.objects;
            
            localStorage.setItem("results", JSON.stringify(data));

            console.log(data[0]);

            for(var i = 0; i < data.length; i ++) {
            	var item = data[i];
                // console.log(data[i]);
                var newRow = table[0].row.add([
                    item.realGame.startTime,
                    item.realQuiz.name != undefined ? item.realQuiz.name : "",
                    item.realGame.name != undefined ? item.realGame.name : "",
                    BUTTONS
                ]).draw().nodes().to$();
                newRow.attr("data-id", data[i]["id"]);


                newRow.find("button.remove").on('click', onRemoveButtonClicked);
                newRow.find("button.edit-content").on('click', onEditContentButtonClicked);
            }
        }
    });

}

function onBackClicked() {
    window.location = "teacher-workspace.html";
}


function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}



function removeFromDatabase(id) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:5000/results/remove?id=" + id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}

function onRemoveButtonClicked() {
    if(confirm("Really remove?")) {
        $this = $(this);
        var row = $(this).closest('tr');
        table[0].row(row).remove().draw();
        removeFromDatabase($(row).attr("data-id"));
    }
}

function onEditContentButtonClicked() {
    var $this = $(this);
    var resultId = $(this).closest('tr').attr("data-id");
    console.log("id is " + resultId);
    localStorage.setItem("resultId", resultId);
    window.location = "teacher-result-parts.html";
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


