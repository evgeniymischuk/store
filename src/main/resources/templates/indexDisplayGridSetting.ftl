<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#assign hasButton = 0>
        <#list itemList as item >
            <#if x <= 0>
                <div class="mt-card-custom  card-in-grid d-none">
            </#if>
            <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
                <div class="hover-effect" data-id="${item.id}">
                    <div style="background-image: url('../download?name=${item.id}_small')" class="img-responsive">
                        <div class="overlay">
                            <ul>
                                <li class="likeCountLi animation-show">
                                    <span class="like-count" data-instagram="${item.instagramLikeUrl}"></span>
                                    <span class="mr-1">
                                        <svg aria-label="Unlike" class="_8-yf5 " fill="#ed4956"
                                             height="19" viewBox="0 0 48 48"
                                             width="19">
                                            <path clip-rule="evenodd"
                                                  d="M35.3 35.6c-9.2 8.2-9.8 8.9-11.3 8.9s-2.1-.7-11.3-8.9C6.5 30.1.5 25.6.5 17.8.5 9.9 6.4 3.5 13.7 3.5 20.8 3.5 24 8.8 24 8.8s3.2-5.3 10.3-5.3c7.3 0 13.2 6.4 13.2 14.3 0 7.8-6.1 12.3-12.2 17.8z"
                                                  fill-rule="evenodd">
                                                        </path>
                                        </svg>
                                    </span>
                                    <span class="ml-1 price-in-grid">${item.price}&#8381;</span>
                                    <div id="removeButton" class="mt-2 add-to-cart buy-btn btn btn-outline-light d-block"
                                         type="button" data-id="${item.id}">Remove
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <#assign x++>
            <#if x < 3 && !(item_has_next)>
                <#list 1..(3-x) as i>
                    <div class="flex-fill ${(!i_has_next)?then('', 'mr-card-custom')}">
                        <#if hasButton == 0>
                            <div class="hover-effect w-100 h-100">
                                <div class="img-responsive bg-white">
                                    <div class="overlay opacity-1 bg-white w-100 h-100">
                                        <ul class="ulAddBtn m-0">
                                            <li class="mr-0 h-100 w-100">
                                                <button id="addButton" name="addButton"
                                                        type="button"
                                                        class="p-3 btn btn-outline-dark w-100 h-100">
                                                    Add
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <#assign hasButton = 1>
                        </#if>
                    </div>
                </#list>
            </#if>
            <#if x == 3 || !(item_has_next)>
                </div>
                <#assign x = 0>
            </#if>
        </#list>
        <#if hasButton == 0>
            <div class="mt-card-custom  card-in-grid d-flex">
                <div class="flex-fill">
                    <div class="hover-effect w-100 h-100">
                        <div class="img-responsive bg-white">
                            <div class="overlay opacity-1 bg-white w-100 h-100">
                                <ul class="ulAddBtn m-0">
                                    <li class="mr-0 h-100 w-100">
                                        <button id="addButton" name="addButton"
                                                type="button"
                                                class="p-3 btn btn-outline-dark w-100 h-100">
                                            Add
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex-fill">
                </div>
                <div class="flex-fill">
                </div>
            </div>
        </#if>
    </div>
    <div class="modal fade" id="settingModal" tabindex="-1" role="dialog" aria-labelledby="settingModalTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="settingModalTitle"></h5>
                    <button id="settingModalBtnClose" type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="settingForm" enctype="multipart/form-data" method="post" action="../settings/add"
                          class="row">
                        <div class="mt-3px col-12">
                            <input type="text"
                                   id="id"
                                   name="id"
                                   class="form-control"
                                   placeholder="Identificator"
                                   aria-label="Identificator"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <input type="text"
                                   id="title"
                                   name="title"
                                   class="form-control"
                                   placeholder="Heading"
                                   aria-label="Heading"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <input type="text"
                                   id="price"
                                   name="price"
                                   class="form-control"
                                   placeholder="Price"
                                   aria-label="Price"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <input type="text"
                                   id="description"
                                   name="description"
                                   class="form-control"
                                   placeholder="Description"
                                   aria-label="Description"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <input type="text"
                                   id="instagramLikeUrl"
                                   name="instagramLikeUrl"
                                   class="form-control"
                                   placeholder="Instagram link"
                                   aria-label="Instagram link"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <label for="imageSmall" class="col-4">Miniature image</label>
                            <button id="imageSmall" type="button" class="form-control btn btn-outline-dark col-4"
                                    onclick="$('#pro-small-image').click()">
                                Choose file
                            </button>
                            <input type="file"
                                   id="pro-small-image"
                                   name="pro-small-image"
                                   style="display: none;"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px col-12">
                            <label for="imageFull" class="col-4">Full-size image</label>
                            <button id="imageFull" type="button" class="form-control btn btn-outline-dark col-4"
                                    onclick="$('#pro-image').click()">
                                Choose file
                            </button>
                            <input type="file"
                                   id="pro-image"
                                   name="pro-image"
                                   style="display: none;"
                                   required="required"
                            >
                        </div>
                        <button type="submit" class="btn btn-primary d-none" id="settingBtnSubmit"></button>
                    </form>
                </div>
                <div class="modal-footer justify-content-center col-12">
                    <button onclick="$('#settingBtnSubmit').click()" type="button" class="btn btn-outline-dark col-4"
                            style="line-height: 4em;">
                        Add new item
                    </button>
                </div>
            </div>
        </div>
        <script src="js/postloadgridsetting.js"></script>
</@t.page>