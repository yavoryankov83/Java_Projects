`use strict`;

$(document).ready(function () {
    $("#giveStatus").on({
        click: function () {
            if ($("#status1").val() === "I want it!") {
                $("#addInfo").append("<b>YOU HAVE TO TASTE IT!!!</b>.");

            } else {
                $("#addInfo").append("<b>CHEERS, BUDDY!!!</b>.");
            }
        }
    });
});

$(document).ready(function () {
    let prevScrollpos = window.pageYOffset;
    window.onscroll = function () {
        let currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = "-50px";
        }
        prevScrollpos = currentScrollPos;
    }
});

