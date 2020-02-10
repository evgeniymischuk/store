function downloadImagesWithLinks() {
    var elements = $('#downloader a');
    if ($("#downloadButton").html() === "ОСТАНОВИТЬ СКАЧИВАНИЕ") {
        var highestTimeoutId = setTimeout(";");
        for (var i = 0; i < highestTimeoutId; i++) {
            clearTimeout(i);
        }
        $("#downloadButton").html("СКАЧАТЬ ВСЕ ФОТОГРАФИИ ПО ОДНОЙ");
    } else {
        $("#downloadButton").html("ОСТАНОВИТЬ СКАЧИВАНИЕ");
        $.each(elements, function (i, e) {
            setTimeout(function () {
                clickA(e);
            }, 1500 * i)
        })
    }
}

function clickA(e) {
    e.click()
}