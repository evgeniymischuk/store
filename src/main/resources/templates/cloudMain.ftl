<#import "templates.ftl" as t>
<@t.page>
    <link rel="stylesheet" href="css/cloud/cloud.css">
    <script src="js/cloud/cloud.js"></script>
    <div class="container-fluid pb-1 pt-3">
            <form id="loaderForm" action="/cloud/add" enctype="multipart/form-data" method="post">
                <fieldset class="form-group text-center col-12 p-0 ">
                    <div class="col-12 col-md-3 float-left p-0 pl-md-0 pr-md-1 mb-1 mb-md-0">
                        <button type="button" class="form-control btn btn-outline-dark"
                                onclick="$('#pro-image').click()">
                            Выбрать
                        </button>
                    </div>
                    <input type="file" id="pro-image" name="pro-image" style="display: none;" multiple>
                    <div class="col-12 col-md-6 float-left p-0 pl-md-1 pr-md-1 mb-1 mb-md-0">
                        <input type="text" class="form-control text-center" id="dir"
                               name="dir" placeholder="Имя альбома"
                               aria-label="Имя альбома"/>
                    </div>
                    <div class="col-12 col-md-3 float-left p-0 pr-md-0 pl-md-1" id="image-load-text">
                        <button class="btn btn-outline-dark form-control btn-submit" type="button">Загрузить</button>
                    </div>
                </fieldset>
            </form>
            <div class="preview-images-zone col-12">
            </div>
        <script src="js/jqueryui.js"></script>
    </div>
</@t.page>