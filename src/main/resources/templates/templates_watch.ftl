<#macro page_watch>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="../css/style.css">
        <title>Your Sol</title>
        <link rel=icon href="../img/old/favicon.png" sizes="16x16" type="image/png">
        <style>
            .nav-wrap {
                background-color: rgba(255, 255, 255, 1);
                min-height: 20px;
                max-width: 935px;
                line-height: 20px;
            }
            .navbar-brand {
                -ms-flex: 0 0 100%;
                flex: 0 0 100%;
                max-width: 100%;
            }
            @media (min-width: 768px) {
                .navbar-brand {
                    -ms-flex: none;
                    flex: none;
                }
            }
        </style>
    </head>
    <header class="header-wrap">
        <nav class="nav-wrap navbar navbar-expand-lg navbar-light m-auto" style="max-width: 935px">
            <span class="navbar-brand ml-3">
                Your Sol - Photography
            </span>
            <div class="collapse navbar-collapse" id="navbarSupportedContent" style="width: 100%;">
                <ul class="navbar-nav mr-auto ml-auto">
                    <li class="nav-item ml-3">
                        <a id="downloadButton" class="nav-link" href="javascript:downloadImagesWithLinks()">СКАЧАТЬ ВСЕ ФОТОГРАФИИ ПО ОДНОЙ</a>
                    </li>
                    <li class="nav-item ml-3">
                        <a class="nav-link"
                           href="javascript:window.location.href = '/cloud/download/zip/?dir='+ encodeURIComponent('${dir}')">
                            СКАЧАТЬ АРХИВОМ(.ZIP)</a>
                    </li>
                    <#--                    <li class="nav-item">-->
                    <#--                        <button type="button" class="mt-1 btn btn-outline-dark col-12" onclick="downloadImagesWithLinks()">-->
                    <#--                            СКАЧАТЬ ВСЕ ФОТОГРАФИИ ПО ОДНОЙ-->
                    <#--                        </button>-->
                    <#--                    </li>-->
                    <#--                    <li class="ml-0 ml-md-1 nav-item">-->
                    <#--                        <button type="button" class="mt-1 btn btn-outline-dark col-12"-->
                    <#--                                onclick="window.location.href = '/cloud/download/zip/?dir='+ encodeURIComponent('${dir}')">-->
                    <#--                            СКАЧАТЬ АРХИВОМ(.ZIP)-->
                    <#--                        </button>-->
                    <#--                    </li>-->
                </ul>
            </div>
        </nav>
    </header>
    <body>
    <script src="../js/jquery.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <main style="background: black;">
        <#nested>
    </main>
    </body>
    </html>
</#macro>