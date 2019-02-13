function showUserInfo() {
    var x = document.getElementById("showUserInformation");
    if (x.style.display === "none") {
        $('#toggleUserInfo').html('Hide my information');
        $('#showUserInformation').show();
    } else {
        $('#toggleUserInfo').html('Show my information');
        x.style.display = "none";
    }
}