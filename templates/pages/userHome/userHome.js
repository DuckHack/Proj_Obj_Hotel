$(document).ready(function () {
    var userHome = (function () {
        var init = function () {
            $('.input-daterange').datepicker({
                format: 'dd-mm-yyyy'
            });
            $("#range_03").ionRangeSlider({
                type: "double",
                grid: true,
                min: 0,
                max: 1000,
                from: 0,
                to: 1000,
                postfix: "zł"
            });
            getRooms();
        };

        var getSearch = function () {
            var searchObj = {
                dateStart: $('#datepicker [name="start"]').val(),
                dateEnd: $('#datepicker [name="end"]').val(),
                priceStart: $('#range_03').val().split(';')[0],
                priceEnd: $('#range_03').val().split(';')[1],
                adult: $('#typeAdult').val(),
                children: $('#typeChild').val(),
                rating: $('#typeRating').val(),
                typpe: $('#typeRoom').val(),
                wifi: $("#wifi").is(':checked'),
                conditioning: $("#conditioning").is(':checked'),
                petFriendly: $("#petFriendly").is(':checked')
            }
            console.log(searchObj);
        };

        var getRooms = function () {
            console.log('Rooms updated')
        };

        return {
            init: init,
            getSearch: getSearch
        };
    })();
    userHome.init();


    $('#search').click(function () {
        userHome.getSearch();
    })




})