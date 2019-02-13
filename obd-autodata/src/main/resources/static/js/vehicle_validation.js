var validManufacturername = false;
var validFirstName = false;
var validLastName = false;
var validEmail = false;
var validPassword = false;
var validOldPassword = false;


function validateFirstName() {

    var name = document.getElementById("inputManufacturer").value;
    if (name == "") {
        validManufacturername = false;
        document.getElementById('inputManufacturer').style.borderColor = "red";
        $('#inputManufacturer').siblings("span").text('You must provide your name!').css('color', 'red');
    }
    else {
        document.getElementById('inputManufacturer').style.borderColor = "green";
        $('#inputManufacturer').siblings("span").html('&nbsp');
        validManufacturername = true;
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


function validate() {
    if (validEmail == true && validFirstName == true && validLastName == true &&
        validPassword == true && validManufacturername == true) {
        return true;
    }
    else {
        return false;
    }
}
