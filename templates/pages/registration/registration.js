$(document).ready(function () {
    var registration = (function () {
        var signUp = function () {
            var formData = {
                firstname: $("#inputFirstname").val(),
                lastname: $("#inputLastname").val(),
                email: $("#inputEmail").val(),
                password: $("#inputPassword").val(),
                type: $("#typeSelect").val()
            }
            console.log(formData)
        };

        return {
            signUp: signUp
        };
    })();

    $(".form-signup").submit(function (event) {
        event.preventDefault();

        registration.signUp();
    });
});