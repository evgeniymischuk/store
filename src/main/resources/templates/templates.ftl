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
    <header class="header-wrap fixed-top pr-0">
        <nav class="nav-wrap navbar navbar-expand-lg navbar-light m-auto">
            <a class="navbar-brand" href="/">
                Your Sol
            </a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto ml-auto">
                </ul>
                <a data-toggle="modal" data-target="#cart" class="navbar-brand" href="#">
                    <img width="24" height="24" src="/svg/shopping.svg" alt="basket"/>
                    <span class="total-count basket"></span>
                </a>
            </div>
        </nav>
    </header>
    <body class="p-0">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <main class="pt-5">
        <#nested>
    </main>
    <div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="basketModalTitle" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="basketModalTitle">Корзина</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="show-cart table">
                    </table>
                </div>
                <div class="modal-footer justify-content-center">
                    <button type="button" class="btn btn-outline-dark">Оформить заказ</button>
                </div>
            </div>
        </div>
    </div>
    <script src="js/basket.js"></script>
    </body>
    </html>
</#macro>