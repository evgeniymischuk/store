<#import "templates_watch.ftl" as t>
<@t.page_watch>
    <script src="../js/cloud/download.js"></script>
    <script src="../js/cloud/watch.js"></script>
    <div class="container-fluid bg-white">
        <div id="downloader" style="display: none">
            <#list names as name>
                <a id="a${name}" href="/cloud/download?dir=${dir}&name=${name}" download="${name}">${name}</a>
            </#list>
        </div>
        <#assign x = 0>
        <#list names as name>
            <div class="d-flex">
                <div class="flex-fill">
                    <img src="../svg/download.svg" width="25" height="25" class="mt-3 mr-3 float-right"
                         onclick="document.getElementById('a${name}').click()" alt="Скачать">
                    <div id="${name}" class="img-responsive-watch" data-dir="${dir}"
                         data-load="${(x < 2)?then('true', 'false')}"
                         style="background: url('data:image/jpeg;base64,${(x < 2)?then(images[x], '')}');${(x < 2)?then('', 'opacity:0;')}"></div>
                    <#assign x++>
                </div>
            </div>
        </#list>
    </div>
</@t.page_watch>