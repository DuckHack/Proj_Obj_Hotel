$(document).ready(function () {

    // GET REQUEST
    $("#getAllCustomerId").click(function (event) {
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/rooms/getAll",
            success: function (result) {
                console.log(result)
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }
})