<#import "templates_watch.ftl" as t>
<@t.page_watch>
    <script src="../js/cloud/download.js"></script>
    <script src="../js/cloud/watch.js"></script>
    <div class="container-fluid bg-white">
        <div class="d-flex">
            <div class="flex-fill mb-1">
                <button type="button" class="mt-1 btn btn-outline-dark" onclick="downloadImagesWithLinks()">
                    СКАЧАТЬ ВСЕ ФОТОГРАФИИ ПО ОДНОЙ
                </button>
                <button type="button" class="mt-1 btn btn-outline-dark"
                        onclick="window.location.href = '/cloud/download/zip/?dir='+ encodeURIComponent('${dir}')">
                    СКАЧАТЬ АРХИВОМ(.ZIP)
                </button>
            </div>
        </div>
        <div id="downloader" style="display: none">
            <#list names as name>
                <a href="/cloud/download?dir=${dir}&name=${name}" download="${name}">${name}</a>
            </#list>
        </div>
        <#assign x = 0>
        <#list names as name>
            <div class="d-flex">
                <div class="flex-fill">

                    <div id="${name}" class="img-responsive-watch" data-dir="${dir}"
                         data-load="${(x < 2)?then('true', 'false')}"
                         style="background: url('data:image/jpeg;base64,${(x < 2)?then(images[x], '')}');${(x < 2)?then('', 'opacity:0;')}"></div>
                    <#assign x++>
                </div>
            </div>
        </#list>
<#--        <div class="d-flex">-->
<#--            <div class="flex-fill align-items-center text-center">-->
<#--                <button id="showMore" type="button" class="mt-1 btn btn-outline-dark">-->
<#--                    ПОКАЗАТЬ ЕЩЕ-->
<#--                </button>-->
<#--            </div>-->
<#--        </div>-->
    </div>
</@t.page_watch>