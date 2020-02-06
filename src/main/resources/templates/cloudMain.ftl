<#import "templates.ftl" as t>
<@t.page>
    <link rel="stylesheet" href="css/cloud/cloud.css">
    <script src="js/cloud/cloud.js"></script>
    <div class="container-fluid">
        <#--        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">-->
        <#--        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>-->
        <#--        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>-->
        <!------ Include the above in your HEAD tag ---------->

        <#--        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">-->
        <div class="container">
            <form action="/cloudAdd" enctype="multipart/form-data" method="post">
                <fieldset class="form-group">
                    <a href="javascript:void(0)" onclick="$('#pro-image').click()">Загрузить фотографии</a>
                    <input type="file" id="pro-image" name="pro-image" style="display: none;" class="form-control"
                           multiple>
                    <input type="text" class="text-center" id="dir" name="dir" placeholder="Имя альбома"/>
                    <span class="float-right" id="image-load-text"><button>Загрузить</button></span>
                </fieldset>
            </form>
            <div class="preview-images-zone">
            </div>
        </div>
        <script src="js/jqueryui.js"></script>
    </div>
</@t.page>