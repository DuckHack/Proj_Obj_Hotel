$(document).ready(function () {
    var login = (function () {
        var signIn = function () {
            var formData = {
                email: $("#inputEmail").val(),
                password: $("#inputPassword").val(),
                type: $("#typeSelect").val()
            }
            localStorage.setItem('currentUser', JSON.stringify(formData))
            var user = JSON.parse(localStorage.getItem('currentUser'));
            console.log(user)
            user.type = "User"
            if (user.type == "User") {
                document.location.pathname = "/pages/userHome/userHome.html";
            } else if (user.type == "Admin") {
                document.location.pathname = "/pages/adminHome/adminHome.html";
            }
        };
        var isLogged = function () {
            if (localStorage.getItem('currentUser')) {
                var user = JSON.parse(localStorage.getItem('currentUser'));
                console.warn('Witaj ' + user.email);
                if (user.type == "User") {
                    document.location.pathname = "/pages/userHome/userHome.html";
                } else if (user.type == "Admin") {
                    document.location.pathname = "/pages/adminHome/adminHome.html";
                }
            } else {}
        };
        return {
            signIn: signIn,
            isLogged: isLogged
        };
    })();

    login.isLogged();

    $(".form-signin").submit(function (event) {
        event.preventDefault();

        login.signIn();
    });
});