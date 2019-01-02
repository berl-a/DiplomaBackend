const BUTTONS = "<button class=\"btn bg-warning edit\"><i class=\"fa fa-pencil-alt fa-1x\"></i></button>\n" +
                "<button class=\"btn bg-warning copy\"><i class=\"fa fa-copy fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-info edit-content\"><i class=\"fa fa-th-list fa-1x\"></i></button>\n" +
                "<button class=\"btn bg-info view\"><i class=\"fa fa-eye fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-success start\"><i class=\"fa fa-play fa-1x\"></i></button>";


var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;

$(document).ready(function() {
    initTable("quizzes");

    $('button.edit').on('click', onEditButtonClicked);

    $('button.remove').on('click', onRemoveButtonClicked);

    $('.submit').on('click', onSubmitButtonClicked);
	
    $('.clear').on('click', clearFields);

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
        url: "http://127.0.0.1:80/quizzes/getAllWithCatNames?teacher=" + teacherId,
        success: function (stringData) {
        	var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
            	var item = data[i];
                // console.log(data[i]);
                var newRow = table[0].row.add([
                    item["name"],
                    item["categoryName"] !== undefined ? item["categoryName"] : "",
                    item["subcategoryName"] !== undefined ? item["subcategoryName"] : "",
                    item["subsubcategoryName"] !== undefined ? item["subsubcategoryName"] : "",
                    BUTTONS
                ]).draw().nodes().to$();
                newRow.attr("data-id", data[i]["id"]);

                newRow.find("td:nth-of-type(2)").attr("data-id", item["category"]);
                newRow.find("td:nth-of-type(3)").attr("data-id", item["subcategory"]);
                newRow.find("td:nth-of-type(4)").attr("data-id", item["subsubcategory"]);

                newRow.find("button.edit").on('click', onEditButtonClicked);
                newRow.find("button.copy").on('click', onCopyButtonClicked);
                newRow.find("button.remove").on('click', onRemoveButtonClicked);
                newRow.find("button.edit-content").on('click', onEditContentButtonClicked);
                newRow.find("button.start").on('click', onStartButtonClicked);
                newRow.find("button.view").on('click', onViewButtonClicked);
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quizGroups/getAll?teacher=" + teacherId,
        success: function (stringData) {
            var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
                var item = data[i];
                var type = item.type.toLowerCase();
                if(type == "cat") cat.push(item);
                else if(type == "subcat") subcat.push(item);
                else if(type == "subsubcat") subsubcat.push(item);

                $('select.quest-group.' + item.type.toLowerCase()).append(
                    "<option class='item' value=" + item.id + 
                    (item.father != undefined ? " data-father = " + item.father : "") + 
                    (item.father != undefined ? " data-grandfather = " + item.grandfather : "") + 
                    ">" + item.name + 
                    "</option>"
                );
                $('select.quest-group option').addClass("hidden");
            }

            $('select.quest-group.cat option').removeClass("hidden");
        }
    });
}

var cat = [], subcat = [], subsubcat = [];

function questGroupChangeListener() {
    var changedListClasses = $(this).attr("class");
    if(changedListClasses.indexOf("subsubcat") !== -1) {}
    else if(changedListClasses.indexOf("subcat") !== -1) {
        $('select.quest-group.subsubcat').val("");
    } else if(changedListClasses.indexOf("cat") !== -1) {
        $('select.quest-group.subcat').val("");
        $('select.quest-group.subsubcat').val("");
    }

    var cat = $('select.quest-group.cat option:selected').val();
    var subcat = $('select.quest-group.subcat option:selected').val();
    var subsubcat = $('select.quest-group.subsubcat option:selected').val();
    // console.log("SELECTED:");
    // console.log("'" + cat + "'\n'" + subcat + "'\n'" + subsubcat + "'");
    updateQuestGroupLists(cat, subcat, subsubcat);
}

function updateQuestGroupLists(cat, subcat, subsubcat) {
    // $('select')

    $('select.quest-group option.item').addClass("hidden");
    $('select.quest-group.cat option.item').removeClass("hidden");
    if(cat != "") {
        $('select.quest-group.subcat option.item').each(function() {
            var currItem = $(this);
            if((currItem).attr("data-father") === cat) {
                currItem.removeClass("hidden");
            }
        });

        if(subcat != "") {
            $('select.quest-group.subsubcat option.item').each(function() {
            var currItem = $(this);
            if((currItem).attr("data-father") === subcat) {
                currItem.removeClass("hidden");
            }
        });
        }
    }
}

function onBackClicked() {
    window.location = "teacher-workspace.html";
}


function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}


function onSubmitButtonClicked() {

    // $('input.username').prop('disabled', true);

    var currentInputFormPurpose = $('.current-input-form-purpose').text();
    var name = $('input.name').val();

    var cat, catId, subcat, subcatId, subsubcat, subsubcatId;

	$('select.quest-group.cat option:selected').each(function() {
		cat = $(this).text()
		catId = $(this).val()
	})
	$('select.quest-group.subcat option:selected').each(function() {
		subcat = $(this).text()
		subcatId = $(this).val()
	})
	$('select.quest-group.subsubcat option:selected').each(function() {
		subsubcat = $(this).text()
		subsubcatId = $(this).val()
	})
    

    if(formAdding) {

        if(name === "") {
            alert("Enter quiz name");
        } else {

            var newRow = table[0].row.add([
                name,
                cat !== "" ? cat : "",
                subcat !== "" ? subcat : "",
                subsubcat !== "" ? subsubcat : "",
                BUTTONS
            ]).draw().nodes().to$();

            addToDatabase(
                name,
                catId, 
                subcatId, 
                subsubcatId,
                newRow
            );

            // newRow.attr("data-id", "TO_ADD");
            
            newRow.find("button.edit").on('click', onEditButtonClicked);
            newRow.find("button.copy").on('click', onCopyButtonClicked);
            newRow.find("button.remove").on('click', onRemoveButtonClicked);
            newRow.find("button.edit-content").on('click', onEditContentButtonClicked);
            newRow.find("button.start").on('click', onStartButtonClicked);
            newRow.find("button.view").on('click', onViewButtonClicked);

            $('.current-input-form-purpose').text("Add new quiz");
            clearFields();
            console.log("cleared");
        }
    } else {

        if(name === "") {
            alert("Enter quiz name");
        } else {
            if(name === "") {
                alert("Enter quiz name");
            } else {

                var rowBeforeUpdating = table[0].row(selectedRowInTable);
                var id = $(selectedRowInTable).attr("data-id");
                // console.log(id);

                var row = table[0].row(selectedRowInTable).data([
                    name,
                    cat !== "" ? cat : "",
                    subcat !== "" ? subcat : "",
                    subsubcat !== "" ? subsubcat : "",
                    BUTTONS
                ]).draw().nodes().to$();


                editInDatabase(
                    id,
                    name, 
                    catId, 
                    subcatId, 
                    subsubcatId
                );

                //CHANGED FROM SELECTED ROW TO ROW
                row.find("button.edit").on('click', onEditButtonClicked);
                row.find("button.copy").on('click', onCopyButtonClicked);
                row.find("button.remove").on('click', onRemoveButtonClicked);
                row.find("button.edit-content").on('click', onEditContentButtonClicked);
                row.find("button.start").on('click', onStartButtonClicked);
                row.find("button.view").on('click', onViewButtonClicked);
                selectedRowInTable = undefined;

                $('.current-input-form-purpose').text("Add new quiz");

                formAdding = true;
                $('.clear').text("Clear");

                clearFields();
                console.log("cleared")
            }
        }
    }
}

function addToDatabase(n, c, sc, ssc, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizzes/add",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "name": n,
            "category": c,
            "subcategory": sc,
            "subsubcategory": ssc,
            "teacher": teacherId
        }),
        success: function (stringData) {
            var newId = stringData.result.newId;
            row.attr("data-id", newId);
        }
    });
}

function editInDatabase(id, n, c, sc, ssc) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizzes/edit",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": id,
            "name": n,
            "category": c,
            "subcategory": sc,
            "subsubcategory": ssc,
            "teacher": teacherId
        }),
        success: function (stringData) {
            var data = stringData.result;
            
        }
    });
}
function copyToDatabase(id, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizzes/copy",
        data: id,
        success: function (stringData) {
            var newId = stringData.result.newId;
            row.attr("data-id", newId);
        }
    });
}

function removeFromDatabase(id) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizzes/remove",
        data: id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}

function clearFields() {

    // $('input.username').prop('disabled', false);

    $('.current-input-form-purpose').text("Add new quiz");

    $('input.name').val("");

    $('select.quest-group.cat').val("");
    $('select.quest-group.subcat').val("");
    $('select.quest-group.subsubcat').val("");

    $('.clear').text("Clear");

    formAdding = true;
}


var subcat;
function onEditButtonClicked() {

    // $('input.username').prop('disabled', true);

    formAdding = false;
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;


    var id = $(row).attr("data-id");

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quizzes/get?id=" + id,
        contentType: "application/json",
        success: function (stringData) {
            // console.log(stringData.result.result);
            var item = stringData.result.result;

            var name = item.name;
            var cat = item.category;
            var subcat = item.subcategory;
            var subsubcat = item.subsubcategory;
            
            updateQuestGroupLists(cat, subcat, subsubcat);
            $('select.quest-group.cat').val(cat);

            setTimeout(function() {
                $('select.quest-group.subcat').val(subcat);
                setTimeout(function() {
                    $('select.quest-group.subsubcat').val(subsubcat);
                }, 100);
            }, 100);

            $('input.name').val(name);

            $('select.quest-group').trigger('change');
        }
    }); 

    $('.current-input-form-purpose').text("Edit quiz");
    
    $('.clear').text("Revert editing");
}

function onCopyButtonClicked() {
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;
    var id = $(row).attr("data-id");
    console.log("old id is " + id);
    var row_data = [];
    row.find('td').each(function() {
        row_data.push($(this).html());
    });
    var newRow = table[0].row.add(row_data).draw().nodes().to$();


    copyToDatabase(id, newRow);


    

    // var newRow = table[0].row.add([
    //     name,
    //     cat != "" ? cat : "",
    //     subcat != "" ? subcat : "",
    //     subsubcat != "" ? subsubcat : "",
    //     BUTTONS
    // ]).draw().nodes().to$();


    // newRow.attr("data-id", "TO_ADD");
    newRow.find("button.edit").on('click', onEditButtonClicked);
    newRow.find("button.copy").on('click', onCopyButtonClicked);
    newRow.find("button.remove").on('click', onRemoveButtonClicked);
    newRow.find("button.edit-content").on('click', onEditContentButtonClicked);
    newRow.find("button.start").on('click', onStartButtonClicked);
    newRow.find("button.view").on('click', onViewButtonClicked);

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
    var quizId = $(this).closest('tr').attr("data-id");
    console.log("id is " + quizId);
    localStorage.setItem("quizId", quizId);
    window.location = "teacher-quiz-parts.html";
}

function onStartButtonClicked() {
    var $this = $(this);
    var quizId = $(this).closest('tr').attr("data-id");
    console.log("id is " + quizId);
    localStorage.setItem("quizId", quizId);
    window.location = "teacher-starting-quiz.html";
}


function onViewButtonClicked() {
    var $this = $(this);
    var quizId = $(this).closest('tr').attr("data-id");
    localStorage.setItem("quizId", quizId);
    getQuizQuestionsFromBackend(quizId);
}


// function populatePageWithQuestion(index) {
//     var question = questions[index];
//     // console.log(question);
//     console.log("question is");
//     console.log(question);
//     if(typeof question == "undefined") {
//         return;
//     }

//     $('.question-number').text((currQuestionIndex + 1) + "/" + questions.length);
//     $('.question-text').text(question.questionText);
//     $('.questionImage').attr('src', question.questionImage);

//     currQuestionType = question.questionType;
//     $('.container.answers').html(answerButtons[currQuestionType]);

//     if(currQuestionType === "SINGLE_CHOICE") {
//         for(var ans = 0; ans < 4; ans ++) {

//             var imageCode = "";
//             if(typeof question.answerImages[ans] != "undefined")
//                 imageCode = "<br><img class='answer-image answer-" + ans + " img-responsive' src=" + question.answerImages[ans] + ">";


//             $('.answer-col.answer-' + ans).html(
//                 "<button class='btn btn-primary answer answer-" + ans + "'>" + 
//                 question.answerTexts[ans] +
//                 imageCode + 
//                 "</button>"
//             );
//             // console.log("adding listeners")
//             $('button.answer-' + ans).on('click', onAnswerButtonClick);
//         }
//     } else if (currQuestionType === "FREE_TEXT") {
//         $('.submit-answer').on('click', onAnswerButtonClick);
//     }
//     answeredThisQuestion = false;
// }

function getQuizQuestionsFromBackend(quizId) {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quizzes/getQuestionsFromQuiz?" + 
        "quizId=" + quizId,
        success: function (stringData) {
            var questions = stringData.result.objects;
            console.log(questions);
            var viewHtml = "";
            var i = 1;
            questions.forEach(function(el) {viewHtml = appendQuestionToHtml(el, viewHtml)});
           
           $.tinyModal({
                title: 'Quiz content',
                html: $('<div>' + viewHtml + '</div>'), // jQuery selectior for modal content
                clickOutside: true,
                buttons: ['Close']
            });

            i ++;
        }
    });
}

function appendQuestionToHtml(q, viewHtml) {
    viewHtml += "<h5 class='questionText'>" + q.questionText + "</h5>";
    if(typeof q.questionImage != "undefined")
    viewHtml += "<img class='questionImage' src='" + q.questionImage + "'>";

    viewHtml += '<div class="row p-2">';
    if(q.questionType == "SINGLE_CHOICE" || q.questionType == "MULTIPLE_CHOICE") {
        for(var a = 0; a < 2; a ++) {
            var imageCode, answerCode;
            if(typeof q.answerImages[a] != "undefined")
                imageCode = "<br><img class='answer-image answer-" + a + " img-responsive' src=" + q.answerImages[a] + ">";
            answerCode = '<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-center mt-1 answer-col answer-' + a + '"> <button class="btn btn-primary answer answer-' + a + '">' + q.answerTexts[a] + ' ' + imageCode + ' </button></div>'
            viewHtml += answerCode
        }
        viewHtml += "</div>";
        viewHtml += '<div class="row p-2">';
        for(var a = 2; a < 4; a ++) {
            var imageCode, answerCode;
            if(typeof q.answerImages[a] != "undefined")
                imageCode = "<br><img class='answer-image answer-" + a + " img-responsive' src=" + q.answerImages[a] + ">";
            answerCode = '<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-center mt-1 answer-col answer-' + a + '"> <button class="btn btn-primary answer answer-' + a + '">' + q.answerTexts[a] + ' ' + imageCode + ' </button></div>'
            viewHtml += answerCode
        }
    } else {
        viewHtml += '<textarea class="answer-text" rows=2 style="width: 100%"></textarea>'
    }
    viewHtml += "</div>";
    viewHtml += "<hr>"
    return viewHtml;
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








$(document).ready(function() {
	// $('select.quest-type').change(questionTypeChangeListener);
    $('select.quest-group').change(questGroupChangeListener);
});



var answerButtons = {
    "SINGLE_CHOICE": '<div class="row p-2"> <div class="col-1 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-0"> <button class="btn btn-primary answer answer-0"> <br> <img class="answer-image answer-0 img-responsive"> </button> </div> <div class="col-2 col-sm-0"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-1"> <button class="btn btn-primary answer answer-1"> <br> <img class="answer-image answer-1"> </button> </div> <div class="col-1"></div> </div> <div class="row p-2"> <div class="col-1"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-2"> <button class="btn btn-primary answer answer-2 "> <br> <img class="answer-image answer-2 img-responsive"> </button> </div> <div class="col-2"></div> <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-center mt-1 answer-col answer-3"> <button class="btn btn-primary answer answer-3 "> <br> <img class="answer-image answer-3 img-responsive"> </button> </div> <div class="col-1"></div> </div>',
    "MULTIPLE_CHOICE": '',
    "FREE_TEXT": '<div class="row"> <div class="col-12"> <textarea class="answer-text" rows=6></textarea> </div> </div> <div class="row"> <div class="col-12 text-center"> <button class="btn btn-primary submit-answer">Submit</button> </div> </div>'
};

