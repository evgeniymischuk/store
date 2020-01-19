<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#list itemList as item >
            <#if x <= 0>
                <div class="d-flex mt-card-custom">
            </#if>
            <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
                <div class="hovereffect">
                    <div style="background-image: url('img/product_${item.id}.jpg')"
                         class="img-responsive"
                         alt="Кольцо лисенок">
                        <div class="overlay">
                            <div class="title ">${item.name}</div>
                            <a class="btn btn-outline-dark info" href="#">Подробнее</a>
                            <a class="btn btn-outline-dark info" href="#">Купить</a>
                        </div>
                    </div>
                </div>
            </div>
            <#assign x++>
            <#if x == 3>
                </div>
                <#assign x = 0>
            </#if>
        </#list>
    </div>
</@t.page>