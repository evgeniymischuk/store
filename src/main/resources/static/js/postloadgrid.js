$(document).ready(function () {
    var anchor = window.location.hash;
    if (anchor) {
        var cardInRow = $(".card-in-row");
        cardInRow.removeClass("d-none");
        cardInRow.addClass("d-flex");
        window.location.href = "/" + anchor;
    }else {
        var cardInGrid = $(".card-in-grid");
        cardInGrid.removeClass("d-none");
        cardInGrid.addClass("d-flex");
    }
    $(".hover-effect").on("click", function () {
        var cardInGrid = $(".card-in-grid");
        var cardInRow = $(".card-in-row");
        cardInGrid.addClass("d-none");
        cardInGrid.removeClass("d-flex");
        cardInRow.removeClass("d-none");
        cardInRow.addClass("d-flex");
        window.location.href = "/#" + $(this).data('id');
    });
    var likeCountEls = $(".like-count");
    likeCountEls.each(function (i, e) {
        var el = $(e);
        var url = el.data('instagram');
        if (url){
            $.ajax({
                url: el.data('instagram'),
                success: function (html) {
                    var div = document.createElement("div");
                    div.setAttribute("style", "display:none");
                    div.innerHTML = html;
                    el.html(parseInt($(div).find("meta[name=description]").attr('content').substring(0, 5)));
                    el.parent('.likeCountLi').attr("style", "opacity:1;");
                    div.remove();
                },
                error: function (e) {
                    console.log(e);
                    el.parent('.likeCountLi').attr("style", "opacity:0;");
                }
            });
        }
    });
});