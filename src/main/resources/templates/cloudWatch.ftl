<#import "templates_watch.ftl" as t>
<@t.page_watch>
    <script src="../js/cloud/watch.js"></script>
    <div class="container-fluid">
        <button type="button" class="mt-1 btn btn-outline-dark" onclick="downloadImagesWithLinks()">
            Скачать все фотографии
        </button>
        <#assign x = 0>
        <div class="flex-fill">
            <div id="downloader" style="display: none">
                <#list names as name>
                    <a onclick="window.location.href = '/cloud/download?dir=${dir}&name=${name}'" href="#"></a>
                </#list>
            </div>
            <#list names as name>
                <div id="${name}" class="img-responsive-watch" data-dir="${dir}"
                     data-load="${(x < 5)?then('true', 'false')}"
                     style="background: url('${(x < 5)?then(images[x], '')}');"></div>
                <#assign x++>
            </#list>
        </div>
    </div>
</@t.page_watch>