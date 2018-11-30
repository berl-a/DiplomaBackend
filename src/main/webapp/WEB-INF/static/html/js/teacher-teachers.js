const BUTTONS = "<button class=\"btn bg-success edit\"><i class=\"fa fa-pencil-alt fa-1x\"></i></button>\n" + 
                "<button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button>";

const DEFAULT_USER_TYPE = "TEACHER";


var table = [];

var formAdding = true;

var selectedRowInTable = undefined;

var teacherId;

$(document).ready(function() {
    initTable("teachers");

    $('button.edit').on('click', onEditButtonClicked);

    $('button.remove').on('click', onRemoveButtonClicked);

    $('.submit').on('click', onSubmitButtonClicked);
	
    $('.clear').on('click', clearFields);

    $('.back').on('click', onBackClicked);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    teacherId = localStorage.getItem("id");

    $('.logout-button').on('click', logOut);

    getFromBackend();

});

function getFromBackend() {

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/users/getAll",
        success: function (stringData) {
        	var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
            	var item = data[i];
                // console.log(data[i]);
                var newRow = table[0].row.add([
                    item["login"],
                    item["hash"] != undefined ? item["hash"] : "",
                    item["type"] != undefined ? item["type"] : "",
                    BUTTONS
                ]).draw().nodes().to$();
                newRow.attr("data-id", data[i]["id"]);

                newRow.find("button.edit").on('click', onEditButtonClicked);
                newRow.find("button.remove").on('click', onRemoveButtonClicked);
            }
        }
    });

}

function onBackClicked() {
    window.location = "teacher-workspace.html";
}


function logOut() {
    localStorage.removeItem("login");
    localStorage.removeItem("id");
    window.location.href="teacher-logging.html";
}


function onSubmitButtonClicked() {

    // $('input.userlogin').prop('disabled', true);

    var currentInputFormPurpose = $('.current-input-form-purpose').text();
    var type = $('.type').val();
    var login = $('.login').val();
    var pass = $('.password').val();

    console.log("Hello");
    console.log(type + " " + login + " " + pass)

    var hash = hashCode(pass);

    if(pass == "***") {
        hash = $('.password').attr("data-hash");
    }

    if(formAdding) {

        if(login === "") {
            alert("Enter teacher login");
        } else {

            var newRow = table[0].row.add([
                login,
                hash,
                type,
                BUTTONS
            ]).draw().nodes().to$();

            addToDatabase(
                login,
                hash,
                type,
                newRow
            );

            // newRow.attr("data-id", "TO_ADD");
            
            newRow.find("button.edit").on('click', onEditButtonClicked);
            newRow.find("button.remove").on('click', onRemoveButtonClicked);

            $('.current-input-form-purpose').text("Add new teacher");
            clearFields();
        }
    } else {

        if(login === "") {
            alert("Enter teacher login");
        } else {
            if(login === "") {
                alert("Enter teacher login");
            } else {

                var rowBeforeUpdating = table[0].row(selectedRowInTable);
                var id = $(selectedRowInTable).attr("data-id");
                // console.log(id);

                var row = table[0].row(selectedRowInTable).data([
                    login,
                    hash,
                    type,
                    BUTTONS
                ]).draw().nodes().to$();


                editInDatabase(
                    id,
                    login,
                    hash,
                    type
                );

                //CHANGED FROM SELECTED ROW TO ROW
                row.find("button.edit").on('click', onEditButtonClicked);
                row.find("button.remove").on('click', onRemoveButtonClicked);
                selectedRowInTable = undefined;

                $('.current-input-form-purpose').text("Add new teacher");

                formAdding = true;
                $('.clear').text("Clear");

                clearFields();
            }
        }
    }
}

function addToDatabase(login, hash, type, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:5000/users/add",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "login": login,
            "hash": hash,
            "type": type
        }),
        success: function (stringData) {
            var newId = stringData.result.newId;
            row.attr("data-id", newId);
        }
    });
}

function editInDatabase(id, login, hash, type, ssc) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:5000/users/edit",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": id,
            "login": login,
            "hash": hash,
            "type": type
        }),
        success: function (stringData) {
            var data = stringData.result;
            
        }
    });
}
function copyToDatabase(id, row) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:5000/users/copy",
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
        url: "http://127.0.0.1:5000/users/remove",
        data: id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}

function clearFields() {

    // $('input.userlogin').prop('disabled', false);

    $('.current-input-form-purpose').text("Add new teacher");

    $('input.login').val("");
    $('input.password').val("");
    $('.type').val(DEFAULT_USER_TYPE);

    $('.clear').text("Clear");

    formAdding = true;
}



function onEditButtonClicked() {

    // $('input.userlogin').prop('disabled', true);

    formAdding = false;
    $this = $(this);
    var row = $(this).closest('tr');
    selectedRowInTable = row;

    var id = $(row).attr("data-id");

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/users/getById?id=" + id,
        contentType: "application/json",
        success: function (stringData) {
            console.log(stringData.result);
            var item = stringData.result.result;

            var type = item.type;
            var login = item.login;
            var hash = item.hash;

            console.log(type + " " + login + " " + hash);

            // updateQuestGroupLists(cat, subcat, subsubcat);
            $('input.login').val(login);
            $('select.type').val(type);
            $('input.password').val("***");
            $('input.password').attr("data-hash", hash);


            $('select.quest-group').trigger('change');
        }
    }); 

    $('.current-input-form-purpose').text("Edit teacher");
    
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


    newRow.find("button.edit").on('click', onEditButtonClicked);
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
