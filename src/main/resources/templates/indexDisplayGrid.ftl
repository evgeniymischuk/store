<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#list itemList as item >
            <#if x <= 0>
                <div class="d-flex mt-card-custom">
            </#if>
            <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
                <div class="hovereffect" data-id="${item.id}">
                    <div style="background-image: url('img/product_${item.id}.jpg')" class="img-responsive">
                        <div class="overlay">
                            <ul>
                                <li>
                                    <span>${item.likeCount}</span>
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
            <#if x == 3>
                </div>
                <#assign x = 0>
            </#if>
        </#list>
    </div>
    <script>
        $(".hovereffect").on("click", function () {
            window.location.href = "/cards#" + $(this).data('id');
        });
    </script>
</@t.page>