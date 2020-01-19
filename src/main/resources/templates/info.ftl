<#import "templates.ftl" as t>

<@t.page>
    <div class="container mt-4">
        <div class="row">
            <#list itemList as item>
                <div class="col-md-4 col-sm-4 col-4 mb-1 pl-2 pr-2">
                    <div class="hovereffect">
                        <div style="background-image: url('../static/img/product_1.jpg')"
                             class="img-responsive"
                             alt="Кольцо лисенок">
                            <div class="overlay">
                                <div class="mt-4 title ">${item.item}</div>
                                <a class="mt-4 btn btn-outline-dark info" href="#">Подробнее</a>
                                <a class="mt-4 btn btn-outline-dark info" href="#">Купить</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@t.page>