var table = [];

var BUTTONS_IN_CART_ROW = "<button class=\"btn bg-danger remove\">x</button>\n" +
    "<button class=\"btn bg-danger decrease\">-</button>\n" +
    "<button class=\"btn btn-sm bg-danger sm-decrease\">-</button>\n" +
    "<button class=\"btn btn-sm bg-success sm-increase\">+</button>\n" +
    "<button class=\"btn bg-success increase\">+</button>";

var BUTTONS_IN_CLIENT_PRODUCT_ROW = "<button class=\"btn bg-success add-product\">+</button>"
function round(num) {
    return Math.round(num * 100) / 100;
}



$(document).ready(function() {

    initTable("products");
    initTable("bought-products");

    $.ajax({
        type: "GET",
        url: "http://127.0.0.1:5000/products/get",
        success: function (stringData) {
            data = JSON.parse(stringData);
            for(var i = 0; i < data.length; i ++) {
                console.log(data[i]);
                var newRow = table[0].row.add([
                    data[i]["title"],
                    data[i]["volume"],
                    data[i]["price"],
                    BUTTONS_IN_CLIENT_PRODUCT_ROW
                ]).draw().nodes().to$();
            }

            //__________LISTEN_TO_THE_FIRST_TABLE
            $('button.add-product').on('click', onAddProductButtonClicked);
        }
    });




    //_____________lISTEN TO THE SECOND TABLE

    $('table#bought-products button').on('click', cartProductButtonListener);

    //_____________LISTEN TO THE BUTTONS UNDER THE CART
    $("button.clear-cart").on('click', function() {
        var cartRows = $("table#bought-products tbody tr").each(function() {
            $this = $(this);
            removeProductFromCart($this)
        });
    });

    $('button.buy').on("click", onBuyButtonClick);

    $('.logged-in-user-username').text(localStorage.getItem("name"));

    $('.logout-button').on('click', logOut);
} );

function onAddProductButtonClicked () {
    var row = $(this).closest('tr');
    var prodNameCell = row.find("td:first-of-type");
    var prodVolumeCell = row.find("td:nth-of-type(2)");
    var prodPriceCell = row.find("td:nth-of-type(3)");

    var prodName = prodNameCell.text();
    var prodVolume = round(parseFloat(prodVolumeCell.text()));
    var prodPrice = round(parseFloat(prodPriceCell.text()));

    console.log(prodName + " " + prodVolume + " " + prodPrice);
    if (prodVolume >= 1) {
        var rowAfterChanging = table[0].row(row).data([
            prodName,
            round(prodVolume - 1) + "",
            prodPrice + "",
            BUTTONS_IN_CLIENT_PRODUCT_ROW
        ]).draw().nodes().to$();
        rowAfterChanging.find("button.add-product").on('click', onAddProductButtonClicked);
        console.log("YOU CLICKED: " + (prodVolume - 1));
        addProductToCart(prodName, 1, prodPrice);
    } else if (prodVolume > 0) {
        console.log("Here, shit");
        var rowAfterChanging1 = table[0].row(row).data([
            prodName,
            0 + "",
            prodPrice + "",
            BUTTONS_IN_CLIENT_PRODUCT_ROW
        ]).draw().nodes().to$();

        rowAfterChanging1.find("button.add-product").on('click', onAddProductButtonClicked);

        addProductToCart(prodName, prodVolume, prodPrice);
    }
}

function sendProductsTableToServer() {
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:5000/products/set",
        data: {
            "newValues": JSON.stringify(table[0].rows().data())
        },
        success: function (stringData) {
            // window.location = "admin-workspace.html";
        },
        error: function(a, b, c) {
            alert("Please fix errors in the table before submitting");
            console.log("ERROR");
            console.log(a);
            console.log(b);
            console.log(c);
        }
    });
}


function sendOrderToServer() {
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:5000/orderItems/set",
        data: {
            "newValues": JSON.stringify(table[1].rows().data()),
            "clientUsername": localStorage.getItem("name")
        },
        success: function (stringData) {
            // window.location = "admin-workspace.html";
        },
        error: function(a, b, c) {
            alert("Please fix errors in the table before submitting");
            console.log("ERROR");
            console.log(a);
            console.log(b);
            console.log(c);
        }
    });
}


function onBuyButtonClick() {
    if(table[1].rows().data().length > 0) {

        sendProductsTableToServer();
        sendOrderToServer();

        console.log(table[1].rows().data())
        table[1].clear().draw();

        alert("Zakup dokonany pomyślnie");
    } else {
        alert("Dodaj chociaż jeden produkt do koszyka");
    }

}


function logOut() {
    localStorage.removeItem("name");
    window.location.href="client-logging.html";
}


function cartProductButtonListener() {
    $this = $(this);
    var btnClasses = $this.attr('class');


    var row = $this.closest('tr');
    var currentVolumeCell = row.find("td:nth-of-type(2)");
    var currentVol = round(parseFloat(currentVolumeCell.text().substring(0, currentVolumeCell.text().length - 0)));

    if(btnClasses.indexOf("remove") !== -1) {
        console.log("remove");
        removeProductFromCart(row);
    } else if(btnClasses.indexOf("sm-decrease") !== -1) {
        console.log("sm-decrease");
        modifyVolumeOfProductInTheProdTable(row, -.1);
    } else if(btnClasses.indexOf("decrease") !== -1) {
        console.log("decrease");
        modifyVolumeOfProductInTheProdTable(row, -1);
    } else if(btnClasses.indexOf("sm-increase") !== -1) {
        console.log("sm increase");
        modifyVolumeOfProductInTheProdTable(row, .1);
    } else if(btnClasses.indexOf("increase") !== -1) {
        console.log("increase");
        modifyVolumeOfProductInTheProdTable(row, 1);
    }

}

function addProductToCart(prodName, number, prodPrice) {
    var row = undefined;
    var cartRows = $("table#bought-products tr").each(function() {
        $this = $(this);
        if($this.find("td:first-of-type").text() === prodName) {
            row = $this;
        }
    });

    var cartRow = row;

    if(row === undefined) {
        console.log("Not found");
        var newRow = table[1].row.add([
            prodName,
            number + "",
            (parseFloat(prodPrice) * parseFloat(number)) + "",
            BUTTONS_IN_CART_ROW
        ]).draw().nodes().to$();
        newRow.find("button").on('click', cartProductButtonListener);
    } else {
        var prodNameCell = row.find("td:first-of-type");
        var prodVolumeCell = row.find("td:nth-of-type(2)");
        var prodPriceCell = row.find("td:nth-of-type(3)");

        var prodName = prodNameCell.text();
        var prodVolume = round(parseFloat(prodVolumeCell.text()));
        var prodPriceBig = round( parseFloat(prodPriceCell.text()));
        //console.log("new volume is " + (prodVolume + number) + "");
        // console.log("new product price is " + (prodVolume + number) * prodPrice + "")
        //console.log("Prod price is " + prodPrice);

        // prodVolumeCell.text(round((prodVolume + number)) + "");
        // prodPriceCell.text(round((prodVolume + number) * prodPrice) + "");

        // var rowAfterChanging = table[0].row(row).data([
        //     prodName,
        //     (prodVolume + number) + "",
        //     round(prodPrice) + "",
        //     BUTTONS_IN_CLIENT_PRODUCT_ROW
        // ]).draw().nodes().to$();
        //
        // rowAfterChanging.find("button.add-product").on('click', onAddProductButtonClicked);

        var cartRowAfterChanging = table[1].row(cartRow).data([
            prodName,
            round((prodVolume + number)) + "",
            round((prodVolume + number) * prodPrice) + "",
            BUTTONS_IN_CART_ROW
        ]).draw().nodes().to$();

        cartRowAfterChanging.find("button").on('click', cartProductButtonListener);


    }
}

function removeProductFromCart(cartRowToRemove) {
    console.log("REmoving product form cart")
    var prodNameCell = cartRowToRemove.find("td:first-of-type");
    var prodVolumeCell = cartRowToRemove.find("td:nth-of-type(2)");
    var prodBigPriceCell = cartRowToRemove.find("td:nth-of-type(3)");

    var prodName = prodNameCell.text();
    var prodVolume = round(parseFloat(prodVolumeCell.text().substring(0, prodVolumeCell.text().length - 0)));
    var prodPriceBig = round( parseFloat(prodBigPriceCell.text().substring(0, prodBigPriceCell.text().length - 0)));
    console.log(prodName, prodVolume, prodPriceBig);

    var productsTableRow = undefined;
    var cartRows = $("table#products tr").each(function() {
        $this = $(this);
        console.log($this.find("td:first-of-type").text());
        if($this.find("td:first-of-type").text() === prodName) {
            productsTableRow = $this;
        }
        // console.log($this);
    });

    if(productsTableRow === undefined) {
        console.log("Cannot return product to the products' table, ERROR");
    } else {
        var prodNameCellP = productsTableRow.find("td:first-of-type");
        var prodVolumeCellP = productsTableRow.find("td:nth-of-type(2)");
        var prodPriceCellP = productsTableRow.find("td:nth-of-type(3)");

        var prodNameP = prodNameCellP.text();
        var prodVolumeP = round(parseFloat(prodVolumeCellP.text()));
        var prodPriceP = round( parseFloat(prodPriceCellP.text()));

        var newProdBigPrice0 = round((prodVolume + prodVolumeP) * prodPriceP);

        var updatedProductsTableRow = table[0].row(productsTableRow).data([
            prodNameP,
            round(prodVolume + prodVolumeP) + "",
            // round(newProdBigPrice0) + "",
            round(prodPriceP) + "",
            BUTTONS_IN_CLIENT_PRODUCT_ROW
        ]).draw().nodes().to$();
        updatedProductsTableRow.find("button.add-product").on('click', onAddProductButtonClicked);

        //prodVolumeCellP.text(round(prodVolume + prodVolumeP) + "");

        table[1].row(cartRowToRemove).remove().draw();

    }
}

function modifyVolumeOfProductInTheProdTable(cartRow, cartProductChange) {
    console.log("MODIFYING NUMBER OF PROD IN PROD TAB");
    var prodNameCell = cartRow.find("td:first-of-type");
    var prodVolumeCell = cartRow.find("td:nth-of-type(2)");
    var prodBigPriceCell = cartRow.find("td:nth-of-type(3)");

    var prodName = prodNameCell.text();
    var prodVolume = round(parseFloat(prodVolumeCell.text()));
    var prodPriceBig = round( parseFloat(prodBigPriceCell.text()));
    console.log(prodName, prodVolume, prodPriceBig);

    var productsRow = undefined;
    var cartRows = $("table#products tr").each(function() {
        $this = $(this);
        console.log($this.find("td:first-of-type").text());
        if($this.find("td:first-of-type").text() === prodName) {
            productsRow = $this;
        }
        console.log($this);
    });

    if(productsRow === undefined) {
        console.log("Cannot modify number of this product int the products' table, ERROR");
    } else {
        var prodNameCellProducts = productsRow.find("td:first-of-type");
        var prodVolumeCellProducts = productsRow.find("td:nth-of-type(2)");
        var prodPriceCellProducts = productsRow.find("td:nth-of-type(3)");

        var prodNameP = prodNameCellProducts.text();
        var prodVolumeP = round(parseFloat(prodVolumeCellProducts.text().substring(0, prodVolumeCellProducts.text().length - 0)));
        var prodPriceP = round( parseFloat(prodPriceCellProducts.text().substring(0, prodPriceCellProducts.text().length - 0)));

        var newProdVolume = round(prodVolume + cartProductChange);
        var newProdVolumeP = round(prodVolumeP - cartProductChange);

        if(newProdVolume <= 0) {
            removeProductFromCart(cartRow);
        } else if(newProdVolumeP <= 0) {
            console.log("not enough products in the product table");
            if(prodVolumeP >= .1 && prodVolumeP < 1) {
                cartProductChange = prodVolumeP;
                newProdVolume = round(prodVolume + cartProductChange);
                newProdVolumeP = round(prodVolumeP - cartProductChange);

                var newProdBigPrice = round(newProdVolume * prodPriceP);

                var updatedCartRow = table[1].row(cartRow).data([
                    prodNameP,
                    newProdVolume + "",
                    newProdBigPrice + "",
                    BUTTONS_IN_CART_ROW
                ]).draw().nodes().to$();
                updatedCartRow.find("button").on('click', cartProductButtonListener);

                var updatedProductsRow = table[0].row(productsRow).data([
                    prodNameP,
                    0 + "",
                    prodPriceP + "",
                    BUTTONS_IN_CLIENT_PRODUCT_ROW
                ]).draw().nodes().to$();
                updatedProductsRow.find("button.add-product").on('click', onAddProductButtonClicked);



                // prodVolumeCell.text(newProdVolume + "");
                // prodVolumeCellProducts.text(0 + "");
                // prodBigPriceCell.text(newProdBigPrice + "");
            }
        } else {
            var newProdBigPrice1 = round(newProdVolume * prodPriceP);

            var updatedCartRow1 = table[1].row(cartRow).data([
                prodNameP,
                newProdVolume + "",
                newProdBigPrice1 + "",
                BUTTONS_IN_CART_ROW

            ]).draw().nodes().to$();
            updatedCartRow1.find("button").on('click', cartProductButtonListener);

            var updatedProductsRow1 = table[0].row(productsRow).data([
                prodNameP,
                newProdVolumeP + "",
                prodPriceP + "",
                BUTTONS_IN_CLIENT_PRODUCT_ROW
            ]).draw().nodes().to$();
            updatedProductsRow1.find("button.add-product").on('click', onAddProductButtonClicked);
            console.log("Figi: " + newProdVolume + " " + newProdVolumeP);

            // prodVolumeCell.text(newProdVolume + "");
            // prodVolumeCellProducts.text(newProdVolumeP + "");
            // prodBigPriceCell.text(newProdBigPrice + "");
        }


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
    console.log(table[i])

    // Apply the search
    table[i].columns().every( function () {
        var that = this;

        $( 'input', this.footer() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
    i ++;
}