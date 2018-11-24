$(document).ready(function () {
    $('.input-daterange').datepicker({});

    $("#range_03").ionRangeSlider({
        type: "double",
        grid: true,
        min: 0,
        max: 1000,
        from: 0,
        to: 1000,
        postfix: "zł"
    });


})