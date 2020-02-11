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
                        <div data-name="${item.name}" data-price="${item.price}" class="add-to-cart buy-btn btn btn-outline-dark">Купить</div>
                        <span class="ml-4">${item.price}&#8381;</span>
                    </div>
                </div>
            </div>
        </#list>
<#--        <!-- Nav &ndash;&gt;-->
<#--        <nav class="navbar navbar-inverse bg-inverse fixed-top bg-faded">-->
<#--            <div class="row">-->
<#--                <div class="col">-->
<#--                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cart">Cart (<span class="total-count"></span>)</button><button class="clear-cart btn btn-danger">Clear Cart</button></div>-->
<#--            </div>-->
<#--        </nav>-->

        <!-- Modal -->
        <div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cart</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="show-cart table">

                        </table>
                        <div>Total price: $<span class="total-cart"></span></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Order now</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.page>