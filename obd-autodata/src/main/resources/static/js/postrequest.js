$(document).ready(function () {

    // SUBMIT FORM
    $("#loginForm").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost() {
        alert("rak");
        // PREPARE FORM DATA
        var formData = {
            username: $("#inputEmail").val(),
            password: $("#inputPassword").val(),
            grant_type: "password"
        }

        // DO POST
        $.ajax({
            type: "POST",
            //contentType: "application/json",
            url: "http://localhost:8080/oauth/token",
            headers: {
                'Authorization': 'Basic Y2xpZW50OnNlY3JldA=='
                , 'content-type': 'application/x-www-form-urlencoded'
            },
            data: {
                    'username':$("#inputEmail").val()
                ,   'password':$("#inputPassword").val()
                ,   'grant_type':'password'
            },
            dataType: 'x-www-form-urlencoded',
            //dataType: 'json',
            success: function (result) {
                if (result.status == "Done") {
                    $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
                        "Post Successfully! <br>" +
                        "---> Customer's Info: FirstName = " +
                        result.data.firstname + " ,LastName = " + result.data.lastname + "</p>");
                    /*jQuery.each(data, function (index, item) {
                        alert(item);
                    });*/
                } else {
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
                console.log(result);
                alert(result.data);
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData() {
        $("#inputEmail").val("");
        $("#inputPassword").val("");
    }
})