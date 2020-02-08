<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#list itemList as item >
            <div id="${item.id}" class="d-flex trapHeader">
                <div class="flex-fill">
                    <div class="card-title">${item.name}</div>
                    <div style="background-image: url('img/product_${item.id}.jpg')" class="img-responsive-cards"></div>
                    <div class="card-description">${item.description}</div>
                    <div class="card-price border-btm mt-3">
                        <div class="buy-btn btn btn-outline-dark">Купить</div>
                        <span class="ml-4">${item.price}</span>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@t.page>