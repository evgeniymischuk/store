<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#list images as item >
            <div class="d-flex mt-card-custom">
                <div class="flex-fill">
                    <div class="img-responsive-watch"
                         style="background: url('${item}');"></div>
                </div>
            </div>
        </#list>
    </div>
</@t.page>