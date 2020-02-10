$(document).ready(function () {
    $(document).on('click', '.btn-submit', function () {
        var val = $('#dir').val();
        if (val) {
            $("#loaderForm").submit();
        } else {
            alert("Имя альбома?!")
        }
    });
    $('form').submit(function (e) {
        e.preventDefault();
        var form_data = new FormData(this);
        $.ajax({
            xhr: function () {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener("progress", function (evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        var parsePercent = parseInt(percentComplete * 100);
                        if (parsePercent === 100) {
                            $("#result").html('Идет обработка файлов...')
                        }
                        parsePercent = parsePercent > 0 ? parsePercent : 0;
                        $('.progress-bar').attr('style', 'width:' + parsePercent + '%;');
                        $('.progress-bar').html(parsePercent + '%');
                    }
                }, false);
                xhr.addEventListener("progress", function (evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                        $('.progress-bar').attr('style', 'width:' + 100 + '%;');
                        $('.progress-bar').html(100 + '%');
                    }
                }, false);
                return xhr;
            },
            type: 'POST',
            cache: false,
            contentType: false,
            processData: false,
            url: $('#loaderForm').attr('action'),
            data: form_data,
            success: function (data) {
                $("#resultCopy").val(data)
                var copyText = document.getElementById("resultCopy");
                copyText.select();
                copyText.setSelectionRange(0, 99999);
                document.execCommand("copy");
                $("#result").html('Ссылка <a href="' + data + '">' + data + '</a> <br>')
            }
        });
        return false;
    });
});


