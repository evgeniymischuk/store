<#macro page>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/style.css">
        <title>Your Sol</title>
        <link rel=icon href="/img/old/favicon.png" sizes="16x16" type="image/png">
    </head>
    <header class="header-wrap fixed-top">
        <nav class="nav-wrap navbar navbar-expand-lg navbar-light m-auto">
            <a class="navbar-brand" href="/">
                Your Sol
            </a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto ml-auto">
                    <#--                    <li class="nav-item active">-->
                    <#--                        <a class="nav-link" href="#">Главная <span class="sr-only">(current)</span></a>-->
                    <#--                    </li>-->
                    <#--                    <li class="nav-item">-->
                    <#--                        <a class="nav-link" href="#">Кольца</a>-->
                    <#--                    </li>-->
                    <#--                    <li class="nav-item">-->
                    <#--                        <a class="nav-link" href="#">Бутылки</a>-->
                    <#--                    </li>-->
                    <#--                    <li class="nav-item">-->
                    <#--                        <a class="nav-link" href="#">Пледы</a>-->
                    <#--                    </li>-->
                </ul>
                <a class="navbar-brand" href="#">
                    <img width="24" height="24" src="/svg/shopping.svg" alt="basket"/>
                    <span class="basket">1</span>
                </a>
            </div>
        </nav>
    </header>
    <body>
    <script src="js/jquery.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <main class="pt-5">
        <#nested>
    </main>
    </body>
    </html>
</#macro>