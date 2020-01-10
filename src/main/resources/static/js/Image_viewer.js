$("body").append("<div id=\"image_viewer\" onclick='function hide() {" +
    "          $(\"#image_viewer\").hide();" +
    "        }'>" +
    "            <div id=\"background\"></div>" +
    "            <img alt='ImageViewer' src=\"\">" +
    "        </div>");
$("#image_viewer").on("click", function () {
    $("#image_viewer").fadeOut()
});

$("#image_viewer img").on('mousewheel DOMMouseScroll', onMouseScroll);
function onMouseScroll(e){
    let img = $("#image_viewer img");
    e.preventDefault();
    let wheel = e.originalEvent.wheelDelta || -e.originalEvent.detail;
    let delta = Math.max(-1, Math.min(1, wheel) );
    let height = img.height();
    img.css("max-height", "none");
    img.css("max-width", "none");
    img.css("transition", "0.2s");

    if(delta<0){//向下滚动
        height *= 0.8;
        img.height(height);
    }else{//向上滚动
        height *= 1.25;
        img.height(height);
    }
    $("#background").height(Math.max(img.height(), $("#image_viewer").height()));
}

function view_image(img) {
    $("#image_viewer img").attr("src", img.getAttribute("src"))
        .css("height", "90%")
        .css("max-height", "90%")
        .css("max-width", "90%")
        .css("transition", "none");
    $("#background").height("100%");
    $("#image_viewer").fadeIn();
}

