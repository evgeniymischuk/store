<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <#assign x = 0>
        <#list itemList as item >
            <#if x <= 0>
                <div class="mt-card-custom  card-in-grid d-none">
            </#if>
            <div class="flex-fill ${(x == 2)?then('', 'mr-card-custom')}">
                <div class="hover-effect" data-id="${item.id}">
                    <div style="background-image: url('../download?name=${item.id}')" class="img-responsive">
                        <div class="overlay">
                            <ul>
                                <li class="mr-0">
                                    <button id="removeButton" name="removeButton" class="btn btn-outline-light" type="button" onclick="window.location.href = '/setting/remove?id=${item.id}'">Удалить</button>
                                </li>
                                <li class="likeCountLi animation-show" style="opacity: 0;">
                                    <span class="like-count" data-instagram="${item.instagramLikeUrl}"></span>
                                    <span class="mr-4">
                                        <img width="19" height="19" src="svg/heart.svg" alt="heart">
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <#assign x++>
            <#if x == 3 || !(item_has_next)>
                </div>
                <#assign x = 0>
            </#if>
        </#list>
        <div class="d-flex">
            <div class="w-100 h-100 flex-fill mr-card-custom text-center mt-3">
                <button onclick="$('#settingModal').modal()" id="addButton" name="addButton" type="button" class="p-3 btn btn-outline-dark">
                    Добавить
                </button>
            </div>
        </div>
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
                    <form id="settingForm" enctype="multipart/form-data" method="post" action="../setting/add">
                        <div class="mt-3px">
                            <input type="text"
                                   id="id"
                                   name="id"
                                   class="form-control"
                                   placeholder="ID"
                                   aria-label="ID"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="title"
                                   name="title"
                                   class="form-control"
                                   placeholder="Заголовок"
                                   aria-label="Заголовок"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="price"
                                   name="price"
                                   class="form-control"
                                   placeholder="Цена"
                                   aria-label="Цена"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="description"
                                   name="description"
                                   class="form-control"
                                   placeholder="Описание"
                                   aria-label="Описание"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="instagramLikeUrl"
                                   name="instagramLikeUrl"
                                   class="form-control"
                                   placeholder="Ссылка на инсту"
                                   aria-label="Ссылка на инсту"
                            >
                        </div>
                        <div class="mt-3px">
                            <button type="button" class="form-control btn btn-outline-dark"
                                    onclick="$('#pro-image').click()">
                                Выбрать
                            </button>
                            <input type="file" id="pro-image" name="pro-image" style="display: none;">
                        </div>
                        <button type="submit" class="btn btn-primary d-none" id="settingBtnSubmit">Отправить</button>
                    </form>
                </div>
                <div class="modal-footer justify-content-center">
                    <button onclick="$('#settingBtnSubmit').click()" type="button" class="btn btn-outline-dark">
                        Добавить
                    </button>
                </div>
            </div>
        </div>
        <script src="js/postloadgrid.js"></script>
</@t.page>