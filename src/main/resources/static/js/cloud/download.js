function downloadImagesWithLinks() {
    var elements = $('#downloader a');
    $.each(elements, function (i, e) {
        setTimeout(function () {
            clickA(e);
        }, 2000 * i)
    })
    // setTimeout(function () {
    //     sendRequestDownload(el);
    // }, 0)
}

function clickA(e) {
    e.click()
}