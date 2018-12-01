const BUTTONS = "<button class=\"btn bg-success edit\"><i class=\"fa fa-pencil-alt fa-1x\"></i></button>\n" +
                "<button class=\"btn bg-info view\"><i class=\"fa fa-eye fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button>";

const DEFAULT_QUESTION_TYPE = "SINGLE_CHOICE";

var selectedQuestionType = DEFAULT_QUESTION_TYPE;

var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId = "2082f56d-e7b3-471d-abb4-73948cfa92bc";

$(document).ready(function() {
    initTable("questions");


    $('button.edit').on('click', onEditButtonClicked);

    $('button.remove').on('click', onRemoveButtonClicked);

    $('.submit').on('click', onSubmitButtonClicked);
	
    $('.clear').on('click', clearFields);

    $('.back').on('click', onBackClicked);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    // teacherId = localStorage.getItem("id");//todo add proper checking
    // if(typeof teacherId === 'undefined') teacherId = "2082f56d-e7b3-471d-abb4-73948cfa92bc";

    $('.logout-button').on('click', logOut);

    getFromBackend();
});

function getFromBackend() {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/questions/getAllWithCatNames?teacher=" + teacherId,
        success: function (stringData) {
        	var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
            	var item = data[i];
                console.log(data[i]);
                var newRow = table[0].row.add([
                    item["questionTitle"] != undefined ? item["questionTitle"] : "",
                    item["questionText"] != undefined ? item["questionText"] : "",
                    "<img src=" + item["questionImage"] + ">",
                    item["questionType"] != undefined ? item["questionType"] : "",
                    item["categoryName"] != undefined ? item["categoryName"] : "",
                    item["subcategoryName"] != undefined ? item["subcategoryName"] : "",
                    item["subsubcategoryName"] != undefined ? item["subsubcategoryName"] : "",
                    BUTTONS
                ]).draw().nodes().to$();
                newRow.attr("data-id", data[i]["id"]);

                newRow.find("td:nth-of-type(2)").addClass("text-center");

                newRow.find("td:nth-of-type(4)").attr("data-id", item["category"]);
                newRow.find("td:nth-of-type(5)").attr("data-id", item["subcategory"]);
                newRow.find("td:nth-of-type(6)").attr("data-id", item["subsubcategory"]);

                newRow.find("button.edit").on('click', onEditButtonClicked);
                newRow.find("button.remove").on('click', onRemoveButtonClicked);
                newRow.find("button.view").on('click', onViewButtonClicked);
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/questionGroups/getAll?teacher=" + teacherId,
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
    var questionTitle = $('.title').val();
    var question = $('input.question').val();
    var questionImage = $('input.question-image').val();

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
    
    var answers = [];
    var answerImages = [];
    var answersCorrect = [];


    if(selectedQuestionType === "SINGLE_CHOICE" || selectedQuestionType === "MULTIPLE_CHOICE") {
    	$('input.answer-text').each(function(){
    		// console.log($(this).val());
    		answers.push($(this).val());
    	})
    	$('input.answer-image').each(function() {
    		// console.log($(this).val());
    		answerImages.push($(this).val());
    	})
    	$('input.answer-correct').each(function() {
    		// console.log($(this).prop('checked'));
    		answersCorrect.push($(this).prop('checked'));
    	})
    }

    if(formAdding) {

        if(question === "" && questionImage === "") {
            alert("Enter question");
        } else {

            var newRow = table[0].row.add([
                questionTitle,
                question,
                "<img src=" + questionImage + ">",
                selectedQuestionType,
                cat != "" ? cat : "",
                subcat != "" ? subcat : "",
                subsubcat != "" ? subsubcat : "",
                BUTTONS
            ]).draw().nodes().to$();

            var newId = addToDatabase(
                questionTitle,
                question, 
                questionImage, 
                selectedQuestionType, 
                catId, 
                subcatId, 
                subsubcatId,
                answers,
                answerImages,
                answersCorrect
            );

            // newRow.attr("data-id", "TO_ADD");
            newRow.attr("data-id", newId);
            newRow.find("button.edit").on('click', onEditButtonClicked);
            newRow.find("button.remove").on('click', onRemoveButtonClicked);
            newRow.find("button.view").on('click', onViewButtonClicked);

            $('.current-input-form-purpose').text("Add new question");
            clearFields();
        }
    } else {

        if(question === "" && questionImage === "") {
            alert("Enter question");
        } else {
            if(question === "" && questionImage === "") {
                alert("Enter question");
            } else {

                var rowBeforeUpdating = table[0].row(selectedRowInTable);
                var id = $(selectedRowInTable).attr("data-id");
                // console.log(id);

                var row = table[0].row(selectedRowInTable).data([
                    questionTitle,
                    question,
                    "<img src=" + questionImage + ">",
                    selectedQuestionType,
                    cat != "" ? cat : "",
                    subcat != "" ? subcat : "",
                    subsubcat != "" ? subsubcat : "",
                    BUTTONS
                ]).draw().nodes().to$();


                editInDatabase(
                    id,
                    questionTitle,
                    question, 
                    questionImage, 
                    selectedQuestionType, 
                    catId, 
                    subcatId, 
                    subsubcatId,
                    answers,
                    answerImages,
                    answersCorrect
                );

                //CHANGED FROM SELECTED ROW TO ROW
                row.find("button.edit").on('click', onEditButtonClicked);
                row.find("button.remove").on('click', onRemoveButtonClicked);
                row.find("button.view").on('click', onViewButtonClicked);
                selectedRowInTable = undefined;

                $('.current-input-form-purpose').text("Add new question");

                formAdding = true;
                $('.clear').text("Clear");

                clearFields();
            }
        }
    }
}

function addToDatabase(qtitle, q, qi, qt, cid, scid, sscid, a, ai, ac) {
    console.log("adding to db");
    console.log(a);
    console.log(ai);
    console.log(ac);

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/questions/add",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "questionTitle": qtitle,
            "questionType": qt,
            "questionText": q,
            "questionImage": qi,
            "answerTexts": a,
            "answerImages": ai,
            "correctAnswers": ac,
            "category": cid,
            "subcategory": scid,
            "subsubcategory": sscid,
            "teacher": teacherId
        }),
        success: function (stringData) {
            var data = stringData.result;
            return data.newId;
        }
    });
    return "";
}
function editInDatabase(id, qtitle, q, qi, qt, cid, scid, sscid, a, ai, ac) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/questions/edit",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": id,
            "questionTitle": qtitle,
            "questionType": qt,
            "questionText": q,
            "questionImage": qi,
            "answerTexts": a,
            "answerImages": ai,
            "correctAnswers": ac,
            "category": cid,
            "subcategory": scid,
            "subsubcategory": sscid,
            "teacher": teacherId
        }),
        success: function (stringData) {
            var data = stringData.result;
            
        }
    });
}
function removeFromDatabase(id) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/questions/remove",
        data: id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}

function clearFields() {

    // $('input.username').prop('disabled', false);

    $('.current-input-form-purpose').text("Add new question");

    $('input.title').val("");
    $('input.question').val("");
    $('input.question-image').val("");

    $('select.quest-group.cat').val("");
    $('select.quest-group.subcat').val("");
    $('select.quest-group.subsubcat').val("");

    $('.quest-type').val(DEFAULT_QUESTION_TYPE);

    if(selectedQuestionType === "SINGLE_CHOICE" || selectedQuestionType === "MULTIPLE_CHOICE") {
        $('input.answer-text').each(function(){
            $(this).val("");
        })
        $('input.answer-image').each(function() {
            $(this).val("");
        })
        $('input.answer-correct').each(function() {
            $(this).prop("checked", false);
        })
    }

    $('.clear').text("Clear");

    formAdding = true;
}



function onEditButtonClicked() {

    $('input.username').prop('disabled', true);

    formAdding = false;
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;


    var id = $(row).attr("data-id");

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/questions/get?id=" + id,
        contentType: "application/json",
        success: function (stringData) {
            // console.log(stringData.result.result);
            var item = stringData.result.result;

            var qtitle = item.questionTitle;
            var q = item.questionText;
            var qi = item.questionImage;
            var cat = item.category;
            var subcat = item.subcategory;
            var subsubcat = item.subsubcategory;
            var a = item.answerTexts;
            var ai = item.answerImages;
            var ac = item.correctAnswers;
            var qtype = item.questionType;


            // updateQuestGroupLists(cat, subcat, subsubcat);
            $('select.quest-group.cat').val(cat);
            setTimeout(function() {
                $('select.quest-group.subcat').val(subcat);
                setTimeout(function() {
                    $('select.quest-group.subsubcat').val(subsubcat);
                }, 100);
            }, 100);

            $('input.questionTitle').val(qtitle);
            $('input.question').val(q);
            $('input.question-image').val(qi);
            $('.quest-type').val(qtype)

            $('select.quest-group').trigger('change');
            $('select.quest-type').trigger('change');

            // console.log(a);
            for(var i = 0; i < 4; i ++) {
                console.log(ac[i]);
                $('div.answer:nth-of-type(' + (i + 1) + ') input.answer-text').val(a[i]);
                $('div.answer:nth-of-type(' + (i + 1) + ') input.answer-image').val(ai[i]);
                $('div.answer:nth-of-type(' + (i + 1) + ') input.answer-correct').attr('checked', ac[i]);
            }
            

        }
    }); 

    $('.current-input-form-purpose').text("Edit question");
    
    $('.clear').text("Revert editing");
}

function onRemoveButtonClicked() {
    if(confirm("Really remove?")) {
        $this = $(this);
        var row = $(this).closest('tr');
        table[0].row(row).remove().draw();
        removeFromDatabase($(row).attr("data-id"));
    }
}

function onViewButtonClicked() {
    var $this = $(this);
    var questionId = $(this).closest('tr').attr("data-id");
    localStorage.setItem("questionId", questionId);
    getQuestionFromBackend(questionId);
}


function getQuestionFromBackend(questionId) {
    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/questions/get?" + 
        "id=" + questionId,
        success: function (stringData) {
            console.log(stringData.result);
            var question = stringData.result.result;

            var viewHtml = "";

            viewHtml = appendQuestionToHtml(question, viewHtml);
           
            $.tinyModal({
                title: 'Question content',
                html: $('<div>' + viewHtml + '</div>'), // jQuery selectior for modal content
                clickOutside: true,
                buttons: ['Close']
            });
        }
    });
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
	$('select.quest-type').change(questionTypeChangeListener);
    $('select.quest-group').change(questGroupChangeListener);
});



function questionTypeChangeListener() {
    console.log("type")
	var selectedText = "";
	$('select.quest-type option:selected').each(function() {
		selectedText = $(this).attr('value');
	})
	updateQuestionConstructor(selectedText);
}

function updateQuestionConstructor(questionType) {
	if(
		(
			(selectedQuestionType === "SINGLE_CHOICE" || selectedQuestionType === "MULTIPLE_CHOICE") && questionType === "FREE_TEXT") || 
			(selectedQuestionType === "FREE_TEXT" && (questionType === "SINGLE_CHOICE" || questionType === "MULTIPLE_CHOICE")
		)
	) {
		
		$('div.question-constructor').html(questionTypeHtmls[questionType]);
	} else {
		if(questionType === "SINGLE_CHOICE") {
			$('.answer-correct-label').each(function() {
				$(this).html('Correct:<br><input class="answer-correct" type="radio" name="correct">');
			})
		} else if(questionType === "MULTIPLE_CHOICE") {
			$('.answer-correct-label').each(function() {
				$(this).html('Correct:<br><input class="answer-correct" type="checkbox" name="correct">');
			})
		}
	}
	selectedQuestionType = questionType;
	
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


var questionTypeHtmls = {};
questionTypeHtmls["SINGLE_CHOICE"] = '<div class="row text-center"> <div class="col-5 answer nice-border m-4 text-center"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div>  <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> <div class="col-5 answer nice-border mt-0 m-4 mt-0"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> </div>';
questionTypeHtmls["MULTIPLE_CHOICE"] = '<div class="row text-center"> <div class="col-5 answer nice-border m-4 text-center"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div>  <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> <div class="col-5 answer nice-border mt-0 m-4 mt-0"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> </div>';
questionTypeHtmls["FREE_TEXT"] = '<div class="row text-center mt-2 mb-2"> <div class="col-12"> <p class="text-center text-info">You don\'t have to provide answers for this question</p> </div> </div>';





