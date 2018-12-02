const BUTTONS = "<button class=\"btn bg-success edit\"><i class=\"fa fa-pencil-alt fa-1x\"></i></button>\n" +
                "<button class=\"btn bg-warning copy\"><i class=\"fa fa-copy fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button>";


var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;
var quizId;

$(document).ready(function() {
    initTable("quiz-parts");

    $('button.edit').on('click', onEditButtonClicked);

    $('button.remove').on('click', onRemoveButtonClicked);

    $('.submit').on('click', onSubmitButtonClicked);
	
    $('.clear').on('click', clearFields);

    $('.back').on('click', onBackClicked);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    // teacherId = localStorage.getItem("id");
    teacherId = "2082f56d-e7b3-471d-abb4-73948cfa92bc"//todo only for now

    quizId = localStorage.getItem("quizId");
    
    // quizId = "f4665ff5-670d-4b40-b2c9-d8c3247eda5e";

    $('.logout-button').on('click', logOut);

    getFromBackend();

});

function getFromBackend() {

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quiz-parts/getAllWithCatNames?quiz=" + quizId,
        success: function (stringData) {
        	var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
            	var item = data[i];
                // console.log(data[i]);
                var newRow = table[0].row.add([
                    item["name"] != undefined ? item["name"] : "",
                    item["number"] != undefined ? item["number"] : "",
                    item["categoryName"] != undefined ? item["categoryName"] : "",
                    item["subcategoryName"] != undefined ? item["subcategoryName"] : "",
                    item["subsubcategoryName"] != undefined ? item["subsubcategoryName"] : "",
                    BUTTONS
                ]).draw().nodes().to$();
                newRow.attr("data-id", data[i]["id"]);

                newRow.find("td:nth-of-type(3)").attr("data-id", item["category"]);
                newRow.find("td:nth-of-type(4)").attr("data-id", item["subcategory"]);
                newRow.find("td:nth-of-type(5)").attr("data-id", item["subsubcategory"]);

                newRow.find("button.edit").on('click', onEditButtonClicked);
                newRow.find("button.copy").on('click', onCopyButtonClicked);
                newRow.find("button.remove").on('click', onRemoveButtonClicked);
            }
        }
    });

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/questionGroups/getAllWithNumberOfQuestions?teacher=" + teacherId,
        success: function (stringData) {
            var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
                var item = data[i];
                // console.log(item);
                var type = item.type.toLowerCase();
                if(type == "cat") cat.push(item);
                else if(type == "subcat") subcat.push(item);
                else if(type == "subsubcat") subsubcat.push(item);

                $('select.quest-group.' + item.type.toLowerCase()).append(
                    "<option class='item' value=" + item.id + 
                    (item.father != undefined ? " data-father=" + item.father : "") + 
                    (item.father != undefined ? " data-grandfather=" + item.grandfather : "") + 
                    (item.numberOfQuestions != undefined ? " data-number-of-questions=" + item.numberOfQuestions : "") + 
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
var maxNumberOfQuestions;

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

    var catNumberOfQuestions = $('select.quest-group.cat option:selected').attr("data-number-of-questions");
    var subcatNumberOfQuestions = $('select.quest-group.subcat option:selected').attr("data-number-of-questions");
    var subsubcatNumberOfQuestions = $('select.quest-group.subsubcat option:selected').attr("data-number-of-questions");

    var numberToCheck;
    if(subsubcat == "") {
        if(subcat == "") {
            if(cat == "") {
                maxNumberOfQuestions = 0;
            } else {
                maxNumberOfQuestions = catNumberOfQuestions;
            }
        } else {
            maxNumberOfQuestions = subcatNumberOfQuestions;
        }
    } else {
        maxNumberOfQuestions = subsubcatNumberOfQuestions;
    }

    // console.log(maxNumberOfQuestions);

    updateMaxNumberOfQuestions(maxNumberOfQuestions)
    updateQuestGroupLists(cat, subcat, subsubcat);
}

function updateMaxNumberOfQuestions(number) {
    $('input.number').attr('max', number);
    $('p.maxNumberOfQuestions').html("/" + number);
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
    localStorage.removeItem("quizId");
    window.location = "teacher-quizzes.html";
}


function logOut() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
    localStorage.removeItem("quizId");
    window.location.href="teacher-logging.html";
}


function onSubmitButtonClicked() {

    // $('input.username').prop('disabled', true);

    var currentInputFormPurpose = $('.current-input-form-purpose').text();
    var name = $('input.name').val();
    var number = $('input.number').val();

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
    
    if(number === "") {
        alert("Enter number of questions")
    } else if (number > maxNumberOfQuestions) {
        alert("Number of questions entered is greater than max number of questions");
    } else {
        if(formAdding) {

            var newRow = table[0].row.add([
                name != "" ? name : "",
                number,
                cat != "" ? cat : "",
                subcat != "" ? subcat : "",
                subsubcat != "" ? subsubcat : "",
                BUTTONS
            ]).draw().nodes().to$();

            addToDatabase(
                name,
                number,
                catId, 
                subcatId, 
                subsubcatId,
                newRow
            );

            // newRow.attr("data-id", "TO_ADD");
            
            newRow.find("button.edit").on('click', onEditButtonClicked);
            newRow.find("button.copy").on('click', onCopyButtonClicked);
            newRow.find("button.remove").on('click', onRemoveButtonClicked);

            $('.current-input-form-purpose').text("Add new quiz");
            clearFields();
        } else {
    
            var rowBeforeUpdating = table[0].row(selectedRowInTable);
            var id = $(selectedRowInTable).attr("data-id");
            // console.log(id);

            var row = table[0].row(selectedRowInTable).data([
                name != "" ? name : "",
                number,
                cat != "" ? cat : "",
                subcat != "" ? subcat : "",
                subsubcat != "" ? subsubcat : "",
                BUTTONS
            ]).draw().nodes().to$();


            editInDatabase(
                id,
                name,
                number, 
                catId, 
                subcatId, 
                subsubcatId
            );

            //CHANGED FROM SELECTED ROW TO ROW
            row.find("button.edit").on('click', onEditButtonClicked);
            row.find("button.copy").on('click', onCopyButtonClicked);
            row.find("button.remove").on('click', onRemoveButtonClicked);
            selectedRowInTable = undefined;

            $('.current-input-form-purpose').text("Add new quiz part");

            formAdding = true;
            $('.clear').text("Clear");

            clearFields();
        }
        
    }
}

function addToDatabase(n, num, c, sc, ssc, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quiz-parts/add",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "name": n,
            "number": num,
            "category": c,
            "subcategory": sc,
            "subsubcategory": ssc,
            "quiz": quizId
        }),
        success: function (stringData) {
            var newId = stringData.result.newId;
            row.attr("data-id", newId);
        }
    });
}

function editInDatabase(id, n, num, c, sc, ssc) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quiz-parts/edit",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": id,
            "name": n,
            "number": num,
            "category": c,
            "subcategory": sc,
            "subsubcategory": ssc,
            "quiz": quizId
        }),
        success: function (stringData) {
            var data = stringData.result;
            
        }
    });
}
function copyToDatabase(id, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quiz-parts/copy",
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
        url: "http://127.0.0.1:80/quiz-parts/remove",
        data: id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}

function clearFields() {

    // $('input.username').prop('disabled', false);

    $('.current-input-form-purpose').text("Add new quiz part");

    $('input.number').val("");
    $('input.name').val("");

    $('select.quest-group.cat').val("");
    $('select.quest-group.subcat').val("");
    $('select.quest-group.subsubcat').val("");

    $('.maxNumberOfQuestions').html("/0");

    $('.clear').text("Clear");

    formAdding = true;
}



function onEditButtonClicked() {

    // $('input.username').prop('disabled', true);

    formAdding = false;
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;


    var id = $(row).attr("data-id");

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quiz-parts/get?id=" + id,
        contentType: "application/json",
        success: function (stringData) {
            // console.log(stringData.result.result);
            var item = stringData.result.result;

            var name = item.name;
            var number = item.number;
            var cat = item.category;
            var subcat = item.subcategory;
            var subsubcat = item.subsubcategory;

            // updateQuestGroupLists(cat, subcat, subsubcat);//////////
            $('select.quest-group.cat').val(cat);
            setTimeout(function() {
                $('select.quest-group.subcat').val(subcat);
                setTimeout(function() {
                    $('select.quest-group.subsubcat').val(subsubcat);
                }, 100);
            }, 100);


            $('input.name').val(name);
            $('input.number').val(number);

            $('select.quest-group').trigger('change');

        }
    }); 

    $('.current-input-form-purpose').text("Edit quiz part");
    
    $('.clear').text("Revert editing");
}

function onCopyButtonClicked() {
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;
    var id = $(row).attr("data-id");
    // console.log("old id is " + id);
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

}

function onRemoveButtonClicked() {
    if(confirm("Really remove?")) {
        $this = $(this);
        var row = $(this).closest('tr');
        table[0].row(row).remove().draw();
        removeFromDatabase($(row).attr("data-id"));
    }
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


// function questionTypeChangeListener() {
// 	var selectedText = "";
// 	$('select.quest-type option:selected').each(function() {
// 		selectedText = $(this).attr('value');
// 	})
// 	updateQuestionConstructor(selectedText);
// }

// function updateQuestionConstructor(questionType) {
// 	if(
// 		(
// 			(selectedQuestionType === "SINGLE_CHOICE" || selectedQuestionType === "MULTIPLE_CHOICE") && questionType === "FREE_TEXT") || 
// 			(selectedQuestionType === "FREE_TEXT" && (questionType === "SINGLE_CHOICE" || questionType === "MULTIPLE_CHOICE")
// 		)
// 	) {
		
// 		$('div.question-constructor').html(questionTypeHtmls[questionType]);
// 	} else {
// 		if(questionType === "SINGLE_CHOICE") {
// 			$('.answer-correct-label').each(function() {
// 				$(this).html('Correct:<br><input class="answer-correct" type="radio" name="correct">');
// 			})
// 		} else if(questionType === "MULTIPLE_CHOICE") {
// 			$('.answer-correct-label').each(function() {
// 				$(this).html('Correct:<br><input class="answer-correct" type="checkbox" name="correct">');
// 			})
// 		}
// 	}
// 	selectedQuestionType = questionType;
	
// }




// var questionTypeHtmls = {};
// questionTypeHtmls["SINGLE_CHOICE"] = '<div class="row text-center"> <div class="col-5 answer nice-border m-4 text-center"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div>  <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> <div class="col-5 answer nice-border mt-0 m-4 mt-0"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="radio" name="correct"> </label> </div> </div>';
// questionTypeHtmls["MULTIPLE_CHOICE"] = '<div class="row text-center"> <div class="col-5 answer nice-border m-4 text-center"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div>  <div class="col-5 answer nice-border m-4"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> <div class="col-5 answer nice-border mt-0 m-4 mt-0"> <label>Answer:<br> <input class="answer-text" size="16"> </label> <br> <label>Answer image:<br> <input class="answer-image" size="16"> </label> <br> <label class=answer-correct-label>Correct:<br> <input class="answer-correct" type="checkbox" name="correct"> </label> </div> </div>';
// questionTypeHtmls["FREE_TEXT"] = '<div class="row text-center mt-2 mb-2"> <div class="col-12"> <p class="text-center text-info">You don\'t have to provide answers for this question</p> </div> </div>';





