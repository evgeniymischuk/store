<#import "templates.ftl" as t>

<@t.page>

    <#assign x = 0>
    <#list itemList as item >
        <#if x <= 0>
            <div class="d-flex mt-card-custom">
        </#if>
        <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
            <div class="hovereffect">
                <div style="background-image: url('img/product_0.jpg')"
                     class="img-responsive"
                     alt="Кольцо лисенок">
                    <div class="overlay">
                        <div class="title ">${item.price}</div>
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
<#--    <div class="container mt-4">-->
<#--        <div class="row">-->
<#--            <#list itemList as item>-->
<#--                <div class="col-md-4 col-sm-4 col-4 mb-1 pl-2 pr-2">-->
<#--                    <div class="hovereffect">-->
<#--                        <div style="background-image: url('img/product_0.jpg')"-->
<#--                             class="img-responsive"-->
<#--                             alt="Кольцо лисенок">-->
<#--                            <div class="overlay">-->
<#--                                <div class="mt-4 title ">${item.item}</div>-->
<#--                                <a class="mt-4 btn btn-outline-dark info" href="#">Подробнее</a>-->
<#--                                <a class="mt-4 btn btn-outline-dark info" href="#">Купить</a>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </#list>-->
<#--        </div>-->
<#--    </div>-->
</@t.page>