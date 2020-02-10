$(document).ready(function () {
    var els = $('.img-responsive-watch');
    count = 0;
    $.each(els, function (i, el) {
        loadImage($(el));
    });
    setTimeout(function () {
        sendRequest($(arrRequest[0]))
    }, 500);
});
var arrRequest = [];
var count;

function loadImage(el) {
    if (el.data('load') !== true) {
        if (el.data('dir') !== undefined && el.attr('id') !== undefined) {
            arrRequest.push(el);
        }
    }
}

function sendRequest(el) {
    count++;
    $.ajax({
        url: "/cloud/load/image?dir=" + encodeURIComponent(el.data('dir')) + "&name=" + encodeURIComponent(el.attr('id')),
        cache: true,
        success: function (response) {
            console.log('loadpic');
            el.attr('style', 'opacity: 0;display:block;');
            el.attr('style', 'background: url(' + 'data:image/jpeg;base64,' + response + '); opacity: 1;');
            el.data('load', true);
            if (count < arrRequest.length) {
                sendRequest(arrRequest[count])
            }
        }
    });
}
