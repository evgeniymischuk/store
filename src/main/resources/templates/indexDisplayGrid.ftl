<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#assign lastState = 0>
        <#list itemList as item >
            <#if x <= 0>
                <div class="mt-card-custom card-in-grid d-none">
            </#if>
            <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
                <div class="hover-effect" data-id="${item.id}">
                    <div style="background-image: url('download?name=${item.id}_small')" class="img-responsive">
                        <div class="overlay">
                            <ul>
                                <li class="likeCountLi animation-show" style="opacity: 0;">
                                    <span class="like-count" data-instagram="${item.instagramLikeUrl}"></span>
                                    <span class="mr-1">
                                        <img width="19" height="19" src="svg/heart.svg" alt="heart">
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <#assign x++>
            <#if x < 3 && !(item_has_next)>
                <#list 1..(3-x) as i>
                    <div class="flex-fill ${(i_has_next)?then('', 'mr-card-custom')}">
                    </div>
                </#list>
            </#if>
            <#if x == 3 || !(item_has_next)>
                </div>
                <#assign x = 0>
            </#if>
        </#list>
        <#list itemList as item >
            <div id="${item.id}" class="d-none trapHeader card-in-row">
                <div class="flex-fill">
                    <div class="card-title">${item.title}</div>
                    <div style="background-image: url('download?name=${item.id}')" class="img-responsive-cards"></div>
                    <div class="card-description">${item.description}</div>
                    <div class="card-price border-btm mt-3">
                        <div data-id="${item.id}" data-name="${item.title}" data-single="true"
                             data-price="${item.price}"
                             class="add-to-cart buy-btn btn btn-outline-dark">
                            Купить
                        </div>
                        <span class="ml-4">${item.price}&#8381;</span>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <script src="js/postloadgrid.js"></script>
</@t.page>