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
                        <div data-name="${item.name}" data-single="true" data-price="${item.price}"
                             class="add-to-cart buy-btn btn btn-outline-dark">
                            Купить
                        </div>
                        <span class="ml-4">${item.price}&#8381;</span>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@t.page>