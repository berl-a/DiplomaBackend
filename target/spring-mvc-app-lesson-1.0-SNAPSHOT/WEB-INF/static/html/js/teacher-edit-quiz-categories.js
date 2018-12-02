var teacherId;

$(document).ready(function() {
	$('.col').sortable();
	$('.col').disableSelection();

	$('.item').click(onItemClick);
	$('.item').dblclick(onItemDblClick);
	$('.item .remove').click(onRemoveClick);
	
	
	$('.add.cat').click(function(e) {addItemToCol('cat');})
	$('.add.subcat').click(function(e) {addItemToCol('subcat');})
	$('.add.subsubcat').click(function(e) {addItemToCol('subsubcat');})

	$('.logged-in-user-username').text(localStorage.getItem("name"));

	
    // teacherId = localStorage.getItem("id");
    teacherId = "2082f56d-e7b3-471d-abb4-73948cfa92bc"//todo only for now

	getFromBackend();
})

function getFromBackend() {

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:80/quizGroups/getAll?teacher=" + teacherId,
        success: function (stringData) {
            var data = stringData.result.objects;

            for(var i = 0; i < data.length; i ++) {
                var item = data[i];
                // console.log(item);
                var type = item.type.toLowerCase();
                if(type == "cat") cat.push(item);
                else if(type == "subcat") subcat.push(item);
                else if(type == "subsubcat") subsubcat.push(item);

                $('div.cat-col.' + item.type.toLowerCase()).append(
                    "<div class='item inactive' data-id=" + item.id + 
                    (item.father != undefined ? " data-father=" + item.father : "") + 
                    (item.father != undefined ? " data-grandfather=" + item.grandfather : "") + 
                    "><button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button><p>" + item.name + 
                    "</p></div>"
                );
                addItemListeners($('.cat-col.' + item.type.toLowerCase() + ' .item:last-of-type'));
            
            }
            $('.cat-col .item').addClass("hidden");
            
            $('.cat-col.cat .item').removeClass("hidden");
        }
    });
}

var cat = [], subcat = [], subsubcat = [];
var currCat, currSubcat, curSubsubcat;

function questGroupChangeListener() {
	var changedListClasses = $(this).closest(".cat-col").attr("class");
    
    if(changedListClasses.indexOf("subsubcat") !== -1) {}
    else if(changedListClasses.indexOf("subcat") !== -1) {
    	$('.cat-col.subsubcat .item').each(function() {
    		$(this).removeClass('active');
    	})
    } else if(changedListClasses.indexOf("cat") !== -1) {
        $('.cat-col.subsubcat .item').each(function() {
    		$(this).removeClass('active');
    	})
    	$('.cat-col.subcat .item').each(function() {
    		$(this).removeClass('active');
    	})
    }

    var cat = $('.cat-col.cat .item.active').attr("data-id");
    var subcat = $('.cat-col.subcat .item.active').attr("data-id");
    var subsubcat = $('.cat-col.subsubcat .item.active').attr("data-id");

    currCat = cat;
    currSubcat = subcat;
    curSubsubcat = subsubcat;

    updateQuestGroupLists(cat, subcat, subsubcat);
}

function updateQuestGroupLists(cat, subcat, subsubcat) {
    // $('select')

    $('.cat-col .item').addClass("hidden");
    $('.cat-col.cat .item').removeClass("hidden");
    if(cat != "") {
        $('.cat-col.subcat .item').each(function() {
            var currItem = $(this);
            if((currItem).attr("data-father") === cat) {
                currItem.removeClass("hidden");
            }
        });

        if(subcat != "") {
            $('.cat-col.subsubcat .item').each(function() {
            var currItem = $(this);
            if((currItem).attr("data-father") === subcat) {
                currItem.removeClass("hidden");
            }
        });
        }
    }
}


function addItemToCol(colName) {

	var newName = $('input.' + colName).val();

	if(colName == "cat") {
		addItemToColInternal(colName, newName, null, null);
	} else if(colName == "subcat") {
		addItemToColInternal(colName, newName, currCat, null);
	} else if(colName == "subsubcat") {
		if(currCat != undefined && currSubcat != undefined)
			addItemToColInternal(colName, newName, currSubcat, currCat);
	}

}

function addItemToColInternal(colName, name, father, grandfather) {

	$('input.' + colName).val('');
	$('.col.' + colName).append("<div class='item inactive' " + 
                    (father != undefined ? " data-father=" + father : "") + 
                    (father != undefined ? " data-grandfather=" + grandfather : "") + 
                    "><button class=\"btn bg-danger remove\"><i class=\"fa fa-times fa-1x\"></i></button><p>" + name + 
                    "</p></div>");

	var addedItem = $('.col.' + colName + ' .item:last-of-type');

	addItemListeners('.col.' + colName + ' div.item:last-of-type');
	addToDatabase(colName.toUpperCase(), name, father, grandfather, addedItem);
}

function addToDatabase(type, name, father, grandfather, element) {
	
	$.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizGroups/add",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "type": type,
            "name": name,
            "father": father,
            "grandfather": grandfather,
            "teacher": teacherId
        }),
        success: function (stringData) {
        	console.log(stringData.result);
            var newId = stringData.result.newId;
            element.attr("data-id", newId);
        }
    });
}

function removeFromDatabase(id) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizGroups/remove",
        data: id,
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}
function renameInDatabase(id, newName) {
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:80/quizGroups/rename?id=" + id + "&newName=" + newName,
        
        success: function (stringData) {
            var data = stringData.result;
        }
    });
}


function refreshItemNameInputEnterListener() {
	$('.item-name-input').off('keypress');
	$('.item-name-input').keypress(function(e) {
		if(e.which == 13) {
			var enteredText = $(e.target).val()
			var item = $(e.target).parent();
			item.find("input").remove();
			item.append("<p>" + enteredText + "</p>")

			var itemId = $(item).attr("data-id");
			var newName = enteredText;
			renameInDatabase(itemId, newName);
		}
	}
)};


function addItemListeners(item) {
	$(item).click(onItemClick);
	$(item).dblclick(onItemDblClick);
	$(item).find(".remove").click(onRemoveClick);
	$(item).click(questGroupChangeListener);

}

function onItemClick(e) {
	var item = $(e.target);
		
	var name;
	if(item.is("p")) {
		name = $(item).text();
		item = $(item).parent();
	} else if (item.is("div")) {
		name = $(item).find('p').text();
	}
	
	item.parent().find('.item').each(function() {
		$(this).removeClass('active');
		$(this).addClass('inactive');
	}); 
	item.removeClass('inactive');
	item.addClass('active');
}

function onItemDblClick(e) {
	var item = $(e.target);
	
	var currentName;
	if(item.is("p")) {
		currentName = $(item).text();
		item = $(item).parent();
	} else if (item.is("div")) {
		currentName = $(item).find('p').text();
	}
	
	item.find('p').remove();
	item.append("<input class='item-name-input btn-block'" + " value='" + currentName + "'>");
	refreshItemNameInputEnterListener();
	
}

var machineRemoval = false;
function onRemoveClick(e) {
	console.log("sdfsdf");
	var item = $(e.target).closest('.item');
	var id = item.attr("data-id");

	var numberOfChildrenAndGrandchildren = 
		$('.cat-col .item[data-father='+ id + ']').size() + 
		$('.cat-col .item[data-grandfather='+ id + ']').size();

	console.log(numberOfChildrenAndGrandchildren)

	var conf = true;
	if(!machineRemoval && numberOfChildrenAndGrandchildren > 0) {
		var conf = confirm("Do you want to remove this category?");
	}
	if(conf) {
		removeFromDatabase(id);
		item.remove();
		machineRemoval = true;
		$('.cat-col .item[data-father='+ id + '] button.remove').click();
		$('.cat-col .item[data-grandfather='+ id + '] button.remove').click();
		machineRemoval = false;
	}

	
}

