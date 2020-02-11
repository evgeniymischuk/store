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
                </ul>
                <a class="navbar-brand" href="#">
                    <img data-toggle="modal" data-target="#cart" width="24" height="24" src="/svg/shopping.svg" alt="basket"/>
                    <span class="total-count basket"></span>
                </a>
            </div>
        </nav>
    </header>
    <body>
    <script src="js/jquery.js"></script>
<#--    <script src="js/popper.min.js"></script>-->
    <script src="js/bootstrap.min.js"></script>
    <main class="pt-5">
        <#nested>
    </main>
    <script src="js/basket.js"></script>
    </body>
    </html>
</#macro>