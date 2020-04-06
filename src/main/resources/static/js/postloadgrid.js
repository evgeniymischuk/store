$(document).ready(function () {
    var anchor = window.location.hash;
    if (anchor) {
        var cardInRow = $(".card-in-row");
        cardInRow.removeClass("d-none");
        cardInRow.addClass("d-flex");
        window.location.href = "/" + anchor;
    } else {
        var cardInGrid = $(".card-in-grid");
        cardInGrid.removeClass("d-none");
        cardInGrid.addClass("d-flex");
    }
    $(".hover-effect").on("click", function (e) {
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
        if (url) {
            $.ajax({
                url: el.data('instagram'),
                success: function (html) {
                    var div = document.createElement("div");
                    div.setAttribute("style", "display:none");
                    div.innerHTML = html;
                    var int_likes = parseInt($(div).find("meta[name=description]").attr('content').substring(0, 5));
                    if (int_likes) {
                        if (el.hasClass("like-count-in-card")) {
                            el.html(int_likes + " " + get_name_records(int_likes));
                            el.parent().find('.like-count-svg').attr("style", "opacity:1;");
                            el.attr("style", "opacity:1;");
                        } else {
                            el.html(int_likes);
                            el.parent('.likeCountLi').attr("style", "opacity:1;");
                        }
                    }
                    div.remove();
                },
                error: function (e) {
                    // el.parent('.likeCountLi').attr("style", "opacity:0;");
                }
            });
        }
    });
});

function get_name_records(number) {
    var titles = ["like", "likes", "likes"];
    var cases = [2, 0, 1, 1, 1, 2];
    var index;
    if (number % 100 > 4 && number % 100 < 20) {
        index = 2;
    } else {
        if (number % 10 < 5) {
            index = cases[number % 10];
        } else {
            index = cases[5];
        }
    }
    return titles[index];
}