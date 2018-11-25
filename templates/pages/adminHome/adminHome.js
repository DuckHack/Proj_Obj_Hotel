$(document).ready(function () {
    var adminHome = (function () {
        var init = function () {
            getRooms();
            getVisitors();
        };

        var addRoom = function () {
            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/rooms/add",
                data: JSON.stringify(User),
                dataType: 'json',
                success: function (result) {
                    $.notifyDefaults({
                        type: 'success',
                        allow_dismiss: false
                    });
                    $.notify('Account added successfully!<br>Hello ' + User.firstname);
                    document.location.pathname = "/pages/login/login.html";
                },
                error: function (e) {
                    $.notifyDefaults({
                        type: 'danger',
                        allow_dismiss: false
                    });
                    $.notify('Something went wrong!');
                }
            });
        };

        var removeRoom = function () {
            $.notifyDefaults({
                type: 'success',
                allow_dismiss: false
            });
            $.notify('Room removed successfully!');
            getRooms();
        };

        var getRooms = function () {
            $.notifyDefaults({
                type: 'success',
                allow_dismiss: false
            });
            $.notify('Rooms list is up to date!');
        };

        var getVisitors = function () {
            $.notifyDefaults({
                type: 'success',
                allow_dismiss: false
            });
            $.notify('Visitors list is up to date!');
        };

        return {
            init: init,
            addRoom: addRoom,
            removeRoom: removeRoom,
            getRooms: getRooms,
            getVisitors: getVisitors
        };
    })();
    adminHome.init();


    $('#addRoom').click(function () {
        adminHome.addRoom();
    })

    $('.removeRoom').click(function () {
        adminHome.removeRoom();
    })

})