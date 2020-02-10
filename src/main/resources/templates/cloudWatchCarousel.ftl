
<#import "templates_watch.ftl" as t>
<@t.page_watch>
    <script src="../js/cloud/download.js"></script>
    <script src="../js/cloud/watchCarousel.js"></script>
    <div class="container-fluid">
        <#assign x = 0>
        <div id="cloudCarousel" class="carousel slide" data-ride="carousel">
            <div id="downloader" style="display: none">
                <#list names as name >
                    <a onclick="window.location.href = this.href" href="/cloud/download?dir=${dir}&name=${name}"></a>
                </#list>
            </div>
            <ol class="carousel-indicators">
                <#list names as name >
                    <li data-target="#cloudCarousel" data-slide-to="${x}" class="${(x == 0)?then('active', '')}"></li>
                    <#assign x++>
                </#list>
            </ol>
            <#assign x = 0>
            <div class="carousel-inner">
                <#list names as name >
                    <div class="carousel-item carousel-item${x} ${(x == 0)?then('active', '')}">
                        <div class="d-flex mt-card-custom">
                            <div class="flex-fill">
                                <div id="${name}" class="img-responsive-watch" data-dir="${dir}"
                                     data-load="${(x < 4 || x == last)?then('true', 'false')}"
                                     style="background: url('${(x < 4 || x == last)?then(images[(x == last)?then(lastImageInArray, x)], '')}');"></div>
                            </div>
                        </div>
                    </div>
                    <#assign x++>
                </#list>
            </div>
            <a class="carousel-control-prev" href="#cloudCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Предыдущая</span>
            </a>
            <a class="carousel-control-next" href="#cloudCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Следующая</span>
            </a>
        </div>
    </div>
</@t.page_watch>