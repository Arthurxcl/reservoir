$("body").append("<div id=\"image_viewer\" onclick='function hide() {" +
    "          $(\"#image_viewer\").hide();" +
    "        }'>" +
    "            <div id=\"background\"></div>" +
    "            <img src=\"\">" +
    "        </div>");
$("#image_viewer").on("click", function () {
    $("#image_viewer").fadeOut()
});

$("#image_viewer img").on('mousewheel DOMMouseScroll', onMouseScroll);
function onMouseScroll(e){
    e.preventDefault();
    let wheel = e.originalEvent.wheelDelta || -e.originalEvent.detail;
    let delta = Math.max(-1, Math.min(1, wheel) );
    let height = $("#image_viewer img").height();
    $("#image_viewer img").css("max-height", "none");
    $("#image_viewer img").css("max-width", "none");
    $("#image_viewer img").css("transition", "0.2s");

    if(delta<0){//向下滚动
        height *= 0.8;
        $("#image_viewer img").height(height);
    }else{//向上滚动
        height *= 1.25;
        $("#image_viewer img").height(height);
    }
}

function view_image(img) {
    $("#image_viewer img").attr("src", img.getAttribute("src"))
        .css("height", "90%")
        .css("max-height", "90%")
        .css("max-width", "90%")
        .css("transition", "none");
    $("#image_viewer").fadeIn();
}

