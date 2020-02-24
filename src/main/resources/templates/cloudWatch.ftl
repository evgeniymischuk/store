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
        <#list names as name>
            <div class="d-flex">
                <div class="flex-fill">
                    <#if name?lower_case?contains(".jpg") || name?lower_case?contains(".jpeg")>
                        <img id="${name}DownloadButton" src="../svg/download.svg" width="25" height="25" class="mt-3 mr-3 float-right d-none" style="cursor: pointer"
                             onclick="document.getElementById('a${name}').click()" alt="Скачать">
                        <div id="${name}" class="img-responsive-watch downloaded-item animation-show" data-dir="${dir}"
                             data-load="false" style="opacity: 0"></div>
                    <#elseif name?lower_case?contains(".mov") || name?lower_case?contains(".mp4")>
<#--                        <img id="${name}DownloadButton" src="../svg/download.svg" width="25" height="25"-->
<#--                             class="mt-3 mr-3 float-right position-absolute d-none" style="right: 4em;z-index: 999;"-->
<#--                             onclick="document.getElementById('a${name}').click()" alt="Скачать">-->
                        <div id="${name}embed" class="embed-responsive embed-responsive-16by9 animation-show" style="opacity: 0">
                            <video id="${name}Video" class="embed-responsive-item" controls>
                                <source class="downloaded-item animation-show" id="${name}"
                                        data-dir="${dir}"
                                        data-load="false" type="video/mp4"/>
                            </video>
                        </div>
                    <#else>
                        <a id="${name}" class="downloaded-item page-link text-center d-none" data-dir="${dir}" data-load="false"></a>
                    </#if>
                </div>
            </div>
        </#list>
    </div>
</@t.page_watch>