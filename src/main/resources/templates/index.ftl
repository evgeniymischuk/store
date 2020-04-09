<#import "templates.ftl" as t>
<@t.page>
    <script>let mobile = ${mobile};</script>
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
                                <li class="likeCountLi animation-show">
                                    <span class="like-count" data-instagram="${item.instagramLikeUrl}"></span>
                                    <span class="mr-1">
                                        <svg aria-label="Unlike" fill="#ed4956"
                                             height="19" viewBox="0 0 48 48"
                                             width="19">
                                            <path clip-rule="evenodd"
                                                  d="M35.3 35.6c-9.2 8.2-9.8 8.9-11.3 8.9s-2.1-.7-11.3-8.9C6.5 30.1.5 25.6.5 17.8.5 9.9 6.4 3.5 13.7 3.5 20.8 3.5 24 8.8 24 8.8s3.2-5.3 10.3-5.3c7.3 0 13.2 6.4 13.2 14.3 0 7.8-6.1 12.3-12.2 17.8z"
                                                  fill-rule="evenodd">
                                                        </path>
                                        </svg>
                                    </span>
                                    <#if mobile == "false">
                                        <span class="ml-1 price-in-grid">${item.price}&#8381;</span>
                                        <#if item.reservation == "true">
                                            <div class="mt-2 buy-btn btn btn-outline-light btn-in-grid">
                                                Зарезервировано
                                            </div>
                                        </#if>
                                        <#if item.reservation != "true">
                                            <div data-id="${item.id}" data-name="${item.title}" data-single="true"
                                                 data-price="${item.price}"
                                                 class="mt-2 add-to-cart buy-btn btn btn-outline-light btn-in-grid">
                                                Купить
                                            </div>
                                        </#if>
                                    </#if>
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
        <#if mobile == "true">
            <#list itemList as item >
                <div id="${item.id}" class="d-none trapHeader card-in-row">
                    <div class="flex-fill">
                        <div class="card-title">${item.title}</div>
                        <div style="background-image: url('download?name=${item.id}')"
                             class="img-responsive-cards"></div>
                        <div class="card-price">
                            <#if item.reservation == 'true'>
                                <div class="buy-btn btn btn-outline-dark buy-btn-in-card">
                                    Зарезервировано
                                </div>
                            </#if>
                            <#if item.reservation != 'true'>
                                <div data-id="${item.id}" data-name="${item.title}" data-single="true"
                                     data-price="${item.price}"
                                     class="add-to-cart buy-btn btn btn-outline-dark buy-btn-in-card">
                                    Купить
                                </div>
                            </#if>
                            <span class="ml-1 price-in-card">${item.price}&#8381;</span>
                            <span class="like-count like-count-in-card animation-show"
                                  data-instagram="${item.instagramLikeUrl}" style="opacity: 0"></span>
                            <svg onclick="location.href = '/#${item.id}'" aria-label="Share Post"
                                 fill="#262626" height="24" viewBox="0 0 48 48"
                                 width="24" style="position: absolute;left: 2.3rem;top: 3px;">
                                <path d="M46.5 3.5h-45C.6 3.5.2 4.6.8 5.2l16 15.8 5.5 22.8c.2.9 1.4 1 1.8.3L47.4 5c.4-.7-.1-1.5-.9-1.5zm-40.1 3h33.5L19.1 18c-.4.2-.9.1-1.2-.2L6.4 6.5zm17.7 31.8l-4-16.6c-.1-.4.1-.9.5-1.1L41.5 9 24.1 38.3z"></path>
                                <path d="M14.7 48.4l2.9-.7"></path>
                            </svg>
                            <span class="like-count-svg animation-show" style="opacity: 0">
                            <svg aria-label="Unlike" fill="#ed4956" height="24" viewBox="0 0 48 48"
                                 width="24">
                            <path clip-rule="evenodd"
                                  d="M35.3 35.6c-9.2 8.2-9.8 8.9-11.3 8.9s-2.1-.7-11.3-8.9C6.5 30.1.5 25.6.5 17.8.5 9.9 6.4 3.5 13.7 3.5 20.8 3.5 24 8.8 24 8.8s3.2-5.3 10.3-5.3c7.3 0 13.2 6.4 13.2 14.3 0 7.8-6.1 12.3-12.2 17.8z"
                                  fill-rule="evenodd">
                            </path>
                        </svg>
                        </span>
                        </div>
                        <div class="card-description border-btm mt-3">${item.description}</div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
    <script src="js/index.js"></script>
</@t.page>