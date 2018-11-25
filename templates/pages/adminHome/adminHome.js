$(document).ready(function () {
    var adminHome = (function () {
        var init = function () {
            getRooms();
            getVisitors();
        };

        var addRoom = function () {
            $.notifyDefaults({
                type: 'success',
                allow_dismiss: false
            });
            $.notify('Room added successfully!');
            getRooms();
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