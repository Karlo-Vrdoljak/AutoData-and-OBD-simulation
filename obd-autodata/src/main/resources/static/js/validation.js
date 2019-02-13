var validUsername = false;
var validFirstName = false;
var validLastName = false;
var validEmail = false;
var validPassword = false;
var validOldPassword = false;

$('#submitname').hide();
$('#submitlastName').hide();
$('#submitemail').hide();
$('#submitpassword').hide();

function validateUsername() {

    var uname = document.getElementById("inputUsername").value;
    if (uname == "") {
        document.getElementById('inputUsername').style.borderColor = "red";
        $('#inputUsername').siblings("span").text('You must provide a username!').css('color', 'red');
        return;
    }
    if (uname.length < 4) {
        document.getElementById('inputUsername').style.borderColor = "red";
        $('#inputUsername').siblings("span").text('Please lengthen to 4 characters or more').css('color', 'red');
        return;
    }
    else {
        const Url = 'http://localhost:8080/check';
        const Data = {
            username: uname
        };
        $.ajax({
            url: Url,
            type: "POST",
            data: Data,
            success: function (result) {
                if (result == 'true') {
                    document.getElementById('inputUsername').style.borderColor = "red";
                    $('#inputUsername').siblings("span").text('Username already taken').css('color', 'red');
                }
                else if (result == 'false') {
                    document.getElementById('inputUsername').style.borderColor = "green";
                    $('#inputUsername').siblings("span").html('Username is unique &#10004').css('color', 'green');
                    validUsername = true;
                }
            },
            error: function (error) {
                console.log(error);

            }
        })
    }
}
function validateFirstName() {

    var name = document.getElementById("inputFirstname").value;
    if (name == "") {
        validFirstName = false;
        document.getElementById('inputFirstname').style.borderColor = "red";
        $('#inputFirstname').siblings("span").text('You must provide your name!').css('color', 'red');
    }
    else {
        document.getElementById('inputFirstname').style.borderColor = "green";
        $('#inputFirstname').siblings("span").html('&nbsp');
        validFirstName = true;
    }
}
function validateLastName() {

    var lastName = document.getElementById("inputLastName").value;
    if (lastName == "") {
        validLastName = false;
        document.getElementById('inputLastName').style.borderColor = "red";
        $('#inputLastName').siblings("span").text('You must provide your name!').css('color', 'red');
    }
    else {
        document.getElementById('inputLastName').style.borderColor = "green";
        $('#inputLastName').siblings("span").html('&nbsp');
        validLastName = true;
    }
}

function validateEmail() {

    var email = document.getElementById("inputEmail").value;
    if (email == "") {
        validEmail = false;
        document.getElementById('inputEmail').style.borderColor = "red";
        $('#inputEmail').siblings("span").text('You must provide an email!').css('color', 'red');
    }
    else {
        var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        var result = regex.test(String(email).toLocaleLowerCase());
        if (result == false) {
            validEmail = false;
            document.getElementById('inputEmail').style.borderColor = "red";
            $('#inputEmail').siblings("span").text('Please, provide a valid email').css('color', 'red');
        }
        else if (result == true) {
            document.getElementById('inputEmail').style.borderColor = "green";
            //$('#inputEmail').siblings("span").html('Email is valid').css('color', 'green');
            $('#inputEmail').siblings("span").html('');
            $('#inputEmail').siblings("small").html('As soon as we verify your account, you can begin using it!');
            validEmail = true;
        }
    }
}
function validatePassword() {

    var passOne = document.getElementById("inputPassOne").value;
    var passTwo = document.getElementById("inputPassTwo").value;
    if (passOne == "") {
        validPassword = false;
        document.getElementById('inputPassOne').style.borderColor = "red";
        $('#inputPassOne').siblings("span").text('You must provide your password!').css('color', 'red');
        return;
    }
    else {
        if (passOne.length < 8) {
            validPassword = false;
            document.getElementById('inputPassOne').style.borderColor = "red";
            $('#inputPassOne').siblings("span").text('Your password must have atleast 8 characters!').css('color', 'red');
            return;
        }
        else {
            validPassword = false;
            document.getElementById('inputPassOne').style.borderColor = "green";
            $('#inputPassOne').siblings("span").html('&nbsp');
        }
    }
    if (passTwo == "") {
        validPassword = false;
        document.getElementById('inputPassTwo').style.borderColor = "red";
        $('#inputPassTwo').siblings("span").text('You must provide your confirmation password!').css('color', 'red');
        return;
    }
    if ((passOne == passTwo) && (passOne != "" && passTwo != "") && (passOne.length >= 8 && passTwo.length >= 8)) {
        document.getElementById('inputPassOne').style.borderColor = "green";
        document.getElementById('inputPassTwo').style.borderColor = "green";
        $('#inputPassOne').siblings("span").html('&nbsp');
        $('#inputPassTwo').siblings("span").html('Passwords match &#10004').css('color', 'green');
        validPassword = true;
    }
    else {
        if (passOne != "") {
            document.getElementById('inputPassTwo').style.borderColor = "red";
            $('#inputPassTwo').siblings("span").text('Passwords don\'t match!').css('color', 'red');
            validPassword = false;

        }
    }
}

function checkOldPassword() {
    var oldPassword = document.getElementById("inputPassOld").value;
    if (oldPassword == "") {
        document.getElementById('inputPassOld').style.borderColor = "red";
        $('#inputPassOld').siblings("span").text('You must provide your old password!').css('color', 'red');
        validOldPassword = false;
        return;
    }
    else {
        const Url = 'http://localhost:8080/checkpw';
        const Data = {
            password: oldPassword
        };
        $.ajax({
            url: Url,
            type: "POST",
            data: Data,
            success: function (result) {
                if (result == 'false') {
                    document.getElementById('inputPassOld').style.borderColor = "red";
                    $('#inputPassOld').siblings("span").text('Passwords don\'t match').css('color', 'red');
                    validOldPassword = false;
                    console.log(result);
                }
                else if (result == 'true') {
                    document.getElementById('inputPassOld').style.borderColor = "green";
                    $('#inputPassOld').siblings("span").html('Old password is verified &#10004').css('color', 'green');
                    validOldPassword = true;
                    console.log(result);

                }
            },
            error: function (error) {
                console.log(error);

            }
        })
    }
}


function enableEditName() {
    $('#inputFirstname').prop('disabled', false);
    document.getElementById("inputFirstname").focus();
    $('#editname').hide();
    $('#submitname').show();
}
function enableEditlastName() {
    $('#inputLastName').prop('disabled', false);
    document.getElementById("inputLastName").focus();
    $('#editlastName').hide();
    $('#submitlastName').show();
}
function enableEditEmail() {
    $('#inputEmail').prop('disabled', false);
    document.getElementById("inputEmail").focus();
    $('#editemail').hide();
    $('#submitemail').show();
}
function enableUpdatePassword() {
    $('#inputPassOld').prop('disabled', false);
    document.getElementById("inputPassOld").focus();
    $('#inputPassOne').prop('disabled', false);
    $('#inputPassTwo').prop('disabled', false);
    //  document.getElementById("inputPassOne").focus();
    $('#editpassword').hide();
    $('#submitpassword').show();
}
function password_validate() {
    if (validPassword == true && validOldPassword == true) {
        return true;
    }
    return false;
}
function email_validate() {
    if (validEmail == true) {
        return true;
    }
    return false;
}
function name_validate() {
    if (validFirstName == true) {
        return true;
    }
    return false;
}
function lastName_validate() {
    if (validLastName == true) {
        return true;
    }
    return false;
}

function validate() {
    if (validEmail == true && validFirstName == true && validLastName == true &&
        validPassword == true && validUsername == true) {
        return true;
    }
    else {
        return false;
    }
}
