$(document).ready(function () {
    const cardInGrid = $(".card-in-grid");
    const cardInRow = $(".card-in-row");
    const anchor = window.location.hash;
    if (!mobile) {
        if (anchor) {
            $(".card-in-row:not(" + anchor + ")").remove();
            cardInRow.removeClass("d-none");
            if (!cardInRow.hasClass("col-sm-6") || !cardInRow.hasClass("col-12")) {
                cardInRow.addClass("d-flex");
            }
            cardInGrid.remove();
        } else if (!archive) {
            cardInRow.remove();
        }
    } else {
        const hoverEffect = $(".hover-effect");
        hoverEffect.find(".add-to-cart").remove();
        hoverEffect.find(".price-in-grid").remove();
    }
    displayCart();
    if (anchor && mobile) {
        cardInRow.removeClass("d-none");
        if (!cardInRow.hasClass("col-sm-6") || !cardInRow.hasClass("col-12")) {
            cardInRow.addClass("d-flex");
        }
        window.location.href = "/" + anchor;
    } else {
        cardInGrid.removeClass("d-none");
        if (!cardInGrid.hasClass("col-sm-6") || !cardInGrid.hasClass("col-12")) {
            cardInGrid.addClass("d-flex");
        }
    }
    $(".hover-effect").on("click", function (e) {
        // if (mobile) {
        cardInGrid.addClass("d-none");
        cardInGrid.removeClass("d-flex");
        cardInRow.removeClass("d-none");
        if (!cardInRow.hasClass("col-sm-6") || !cardInRow.hasClass("col-12")) {
            cardInRow.addClass("d-flex");
        }
        const anchor = $(this).data('id');
        if (!mobile) {
            if (anchor) {
                $(".card-in-row:not(#" + anchor + ")").remove();
                cardInRow.removeClass("d-none");
                if (!cardInRow.hasClass("col-sm-6") || !cardInRow.hasClass("col-12")) {
                    cardInRow.addClass("d-flex");
                }
                cardInGrid.remove();
            } else if (!archive) {
                cardInRow.remove();
            }
        }
        window.location.href = "/#" + $(this).data('id');
        // }
    });
    const likeCountEls = $(".like-count");
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
    $(".btn-nav").on("click", function () {
        $('.not-additional-item').toggle();
        $('.btn-nav-arr').toggleClass('expand-arrow').toggleClass('collapse-arrow');
    })
    $(".btn-nav-add").on("click", function () {
        $('.additional-item').toggle();
        $('.btn-nav-add-arr').toggleClass('expand-arrow').toggleClass('collapse-arrow');
    })
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