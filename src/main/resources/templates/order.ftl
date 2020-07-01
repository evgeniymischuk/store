<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <ul class="list-group mt-3 list-none">
            <li class="list-group-item active text-center">Номер заказ : ${order.number}</li>
            <li class="list-group-item">Статус : ${order.status}</li>
<#--            <li class="list-group-item">Адрес или почтовый индекс : <br>${order.postal}</li>-->
<#--            <#if order.track != "">-->
<#--                <li class="list-group-item">Трек номер ${order.track}</li>-->
<#--            </#if>-->
            <li>
                <ul class="list-group text-center list-none">
                    <li class="list-group-item active">
                        Заказанные товары:
                    </li>
                    <#list order.purchasesDtoList as purchase>
                        <li class="list-group-item">
                            <a href="${(purchase.hide == 'true')?then('/archive?id='+purchase.id,'/#'+purchase.id)}"
                               class="text-primary">${purchase.title}
                            </a>
                        </li>
                        <li class="list-group-item ">
                            <div style='background-image: url("/download?id=${purchase.id}.jpg")'
                                 class='img-responsive-order m-auto'>
                            </div>
                        </li>
                        <li class="list-group-item text-dark">${purchase.price} &#8381;</li>
                    </#list>
                </ul>
            </li>
            <#if order.track != "">
                <li class="list-group-item">Информация : ${order.info}</li>
            </#if>
            <li class="list-group-item">Имя : ${order.name}</li>
            <li class="list-group-item">Электронная почта : ${order.email} </li>
            <li class="list-group-item">Дата заказа : ${order.dateString}</li>
            <li class="list-group-item text-danger text-center">Общая стоимость : ${order.priceTotal} &#8381;</li>
        </ul>
    </div>
</@t.page>