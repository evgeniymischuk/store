<#import "templates_up.ftl" as t>
<@t.page_up>
    <div class="container-fluid">
        <#assign x = 0>
        <div id="cloudCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <#list names as name >
                    <li data-target="#cloudCarousel" data-slide-to="${x}" class="${(x == 0)?then('active', '')}"></li>
                    <#assign x++>
                </#list>
            </ol>
            <#assign x = 0>
            <div class="carousel-inner">
                <#list names as name >
                    <div class="carousel-item ${(x == 0)?then('active', '')}">
                        <div class="d-flex mt-card-custom">
                            <div class="flex-fill">
                                <div id="${name}" class="img-responsive-watch"
                                     style="background: url('${(x == 0)?then(item, '')}');"></div>
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
    <script>
        $('#cloudCarousel').on('slide.bs.carousel', function (e) {
            var el = $(e.relatedTarget).find('.img-responsive-watch');
            if (el.data('load') !== 'true'){
                // $('#cloudCarousel').carousel('pause');
                $.ajax({
                    url: "/cloud/load/image?dir=${dir}&name="+encodeURIComponent(el.attr('id')),
                    cache: true,
                    success: function (response) {
                        el.attr('style', 'background: url('+ response+')');
                        el.data('load', 'true');
                        // $('#cloudCarousel').carousel(e.direction === 'left' ? 'prev' :'next')
                    }
                });
            }
        })
    </script>
</@t.page_up>