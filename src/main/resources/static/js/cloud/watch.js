$(document).ready(function () {
    // $('#showMore').on('click', function () {
    //     var els = $('.img-responsive-watch');
    //     timeoutX = 0;
    //     count = 0;
    //     $.each(els, function (i, el) {
    //         loadImage($(el));
    //     });
    // });

    var els = $('.img-responsive-watch');
    timeoutX = 0;
    count = 0;
    $.each(els, function (i, el) {
        loadImage($(el));
    });
});
var count;
var timeoutX;

function loadImage(el) {
    if (el.data('load') !== true) {
        timeoutX++;
        // $('#cloudCarousel').carousel('pause');
        if (el.data('dir') !== undefined && el.attr('id') !== undefined) {
            setTimeout(function () {
                sendRequest(el);
            }, 2000 * timeoutX)

        }
    }
}

function sendRequest(el) {
    // if (count < 5) {
        count++;
        $.ajax({
            url: "/cloud/load/image?dir=" + encodeURIComponent(el.data('dir')) + "&name=" + encodeURIComponent(el.attr('id')),
            cache: true,
            success: function (response) {
                console.log('loadpic');
                el.attr('style', 'opacity: 0;display:block;');
                el.attr('style', 'background: url(' + 'data:image/jpeg;base64,' + response + '); opacity: 1;');
                el.data('load', true);
            }
        });
    // }
}
