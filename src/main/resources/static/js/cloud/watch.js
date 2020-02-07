$(document).ready(function () {
    $('#cloudCarousel').on('slide.bs.carousel', function (e) {
        var left = e.direction === 'left';
        var elSource = $(e.relatedTarget);
        for (var i = 0; i < 5; i++) {
            if (i !== 0) {
                if (!left) {
                    elSource = $($(elSource).prev());
                } else {
                    elSource = $($(elSource).next());
                }
            }
            var el = elSource.find('.img-responsive-watch');
            loadImage(el, elSource, i === 0);
        }
    })
});
var count = 0;

function loadImage(el, first) {
    if (el.data('load') !== true) {
        // $('#cloudCarousel').carousel('pause');
        if (el.data('dir') !== undefined && el.attr('id') !== undefined) {
            count++;
            setTimeout(function () {
                sendRequest(el);
            }, first ? 1100 : 0)

        }
    }
}

function sendRequest(el) {
    if (count > 3) {
        $.ajax({
            url: "/cloud/load/image?dir=" + encodeURIComponent(el.data('dir')) + "&name=" + encodeURIComponent(el.attr('id')),
            cache: true,
            success: function (response) {
                console.log('loadpic');
                el.attr('style', 'background: url(' + response + ')');
                el.data('load', true);
                count = 0;
                // $('#cloudCarousel').carousel(e.direction === 'left' ? 'prev' :'next')
            }
        });
    } else {
        count = 0;
    }
}

function downloadImages() {
    var elements = $('.img-responsive-watch');
    $.each(elements, function (i, e) {
        sendRequestDownload($(e));
    })
    // setTimeout(function () {
    //     sendRequestDownload(el);
    // }, 0)
}

function downloadImagesWithLinks() {
    var elements = $('#downloader a');
    $.each(elements, function (i, e) {
        setTimeout(function () {
            clickA(e);
        }, 1000 * i)
    })
    // setTimeout(function () {
    //     sendRequestDownload(el);
    // }, 0)
}

function clickA(e) {
    e.click()
}

function sendRequestDownload(el) {
    var a = $("")
    window.open("/cloud/download?dir=" + encodeURIComponent(el.data('dir')) + "&name=" + encodeURIComponent(el.attr('id')))
    // $.ajax({
    //     url: ,
    //     cache: true,
    //     success: function (response) {
    //     }
    // });
}