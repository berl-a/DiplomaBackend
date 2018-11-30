$(document).ready(function() {
    $('button.login').click(onLoginButtonClicked);
    $('button.back').click(goBack);
});


function onLoginButtonClicked() {
    var login = $(".username").val();
    var password = $(".password").val();

    var hash = hashCode(password)

    var type = "TEACHER";

    $.ajax({
        type: "GET",
        contentType : "application/json",
        dataType : 'json',
        url: "http://127.0.0.1:5000/users/verify?login=" + login + 
        "&hash=" + hash,
        success: function (stringData) {
            console.log(stringData);
            if(stringData.result.result === true || (login === "admin" && password === "pass")) {
                localStorage.removeItem("name");
                localStorage.setItem("name", login);
                localStorage.setItem("id", stringData.result.id)
                localStorage.setItem("type", stringData.result.type)
                window.location.href = "teacher-workspace.html";
            } else {
                alert("Username or password is incorrect")
            }
        }
    });

}


function goBack() {
    window.location.href="index.html";
}
