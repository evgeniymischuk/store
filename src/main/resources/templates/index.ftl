<#import "templates.ftl" as t>
<@t.page>
    <script>
        let mobile = ${mobile};
        let archive = "false";
    </script>
    <div class="container-fluid">
        <div class="jumbotron jumbotron-fluid card-in-grid bg-white mb-0">
            <div class="text-center">
                <h1 class="display-4">«ГОТОВЫЕ КОНТЕНТ-ПЛАНЫ ДЛЯ INSTAGRAM»</h1>
                <p class="lead">Выберите подходящий вам план</p>
            </div>
        </div>
        <div class="row align-content-center">
            <div class="col-6 p-2 p-sm-3 m-0 card-in-grid d-none">
                <#list itemList as item>
                    <#if item.additionalItem != "1">
                        <div class="mt-card-custom card-in-grid d-none">
                            <div class="flex-fill">
                                <div class="hover-effect" data-id="${item.id}">
                                    <#if mobile == "true">
                                        <p class="mt-3 font-weight-100">${item.title}</p>
                                    </#if>
                                    <#if mobile == "false">
                                        <p class="mt-3"></p>
                                    </#if>
                                    <div style="background-image: url('download?id=${item.id}_small')"
                                         class="img-responsive">
                                        <div class="overlay">
                                            <ul>
                                                <li class="likeCountLi animation-show">
                                                    <#if mobile == "false">
                                                        <span class="ml-1 price-in-grid font-weight-light font-20">${item.title} ${item.price}&#8381;</span>
                                                        <div data-id="${item.id}" data-name="${item.title}"
                                                             data-single="true"
                                                             data-price="${item.price}"
                                                             class="mt-2 btn btn-outline-light btn-in-grid font-weight-light font-20">
                                                            Подробнее
                                                        </div>
                                                    </#if>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>
            <div class="col-6 p-2 p-sm-3 m-0 border-left-solid card-in-grid d-none">
                <#list itemList as item>
                    <#if item.additionalItem == "1">
                        <div class="mt-card-custom card-in-grid d-none">
                            <div class="flex-fill">
                                <div class="hover-effect" data-id="${item.id}">
                                    <#if mobile == "true">
                                        <p class="mt-3 font-weight-100">${item.title}</p>
                                    </#if>
                                    <#if mobile == "false">
                                        <p class="mt-3"></p>
                                    </#if>
                                    <div style="background-image: url('download?id=${item.id}_small')"
                                         class="img-responsive">
                                        <div class="overlay">
                                            <ul>
                                                <li class="likeCountLi animation-show">
                                                    <#if mobile == "false">
                                                        <span class="ml-1 price-in-grid font-weight-light font-20">${item.title} ${item.price}&#8381;</span>
                                                        <div data-id="${item.id}" data-name="${item.title}"
                                                             data-single="true"
                                                             data-price="${item.price}"
                                                             class="mt-2 btn btn-outline-light btn-in-grid font-weight-light font-20">
                                                            Подробнее
                                                        </div>
                                                    </#if>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>

            <#list itemList as item >
                <div id="${item.id}" class="d-none trapHeader card-in-row col-12">
                    <div class="flex-fill text-center">
                        <div class="card-title">${item.title}</div>
                        <div style="background-image: url('download?id=${item.id}')"
                             class="img-responsive-cards"></div>
                        <div class="card-price col-12">
                            <#if item.hide != "true">
                                <div data-id="${item.id}" data-name="${item.title}" data-single="true"
                                     data-price="${item.price}"
                                     class="add-to-cart buy-btn btn btn-outline-dark buy-btn-in-card">
                                    Купить за ${item.price}&#8381;
                                </div>
                            </#if>
                        </div>
                        <div class="card-description border-btm mt-3">${item.description}</div>
                    </div>
                </div>
            </#list>
        </div>
        <script src="js/index.js"></script>
</@t.page>