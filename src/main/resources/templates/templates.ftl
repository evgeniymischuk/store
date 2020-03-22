<#macro page>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/style.css">
        <title>Your Sol</title>
        <link rel=icon href="/static/img/favicon.png" sizes="16x16" type="image/png">
    </head>
    <header class="header-wrap fixed-top pr-0">
        <nav class="nav-wrap navbar navbar-expand-lg navbar-light m-auto">
            <a class="navbar-brand" href="/">
                <img src="img/your-sol-logo.png" width="120" height="20" alt="your-sol-logo">
            </a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto ml-auto">
                </ul>
                <a id="basket" data-toggle="modal" data-target="#basketModal" class="navbar-brand"
                   href="javascript:void(0);">
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
    <div class="modal fade" id="basketModal" tabindex="-1" role="dialog" aria-labelledby="basketModalTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="basketModalTitle">Корзина</h5>
                    <button id="basketModalBtnClose" type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table class="show-cart mb-0 table">
                    </table>
                    <form id="basketForm" type="GET" action="">
                        <div class="mt-3px">
                            <input type="text"
                                   id="name"
                                   name="name"
                                   class="form-control"
                                   placeholder="Имя"
                                   aria-label="Имя"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="email"
                                   id="email"
                                   name="email"
                                   class="form-control"
                                   placeholder="Электронная почта"
                                   aria-label="Электронная почта"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="postal"
                                   name="postal"
                                   class="form-control"
                                   placeholder="Почтовый индекс"
                                   aria-label="Почтовый индекс"
                                   required="required"
                            >
                        </div>
                        <div class="mt-3px">
                            <input type="text"
                                   id="address"
                                   name="address"
                                   class="form-control"
                                   placeholder="Адрес (необязательно)"
                                   aria-label="Адрес (необязательно)"
                            >
                        </div>
                        <button type="submit" class="btn btn-primary d-none" id="basketBtnSubmit">Отправить</button>
                    </form>
                </div>
                <div class="modal-footer justify-content-center">
                    <button onclick="$('#basketBtnSubmit').click()" type="button" class="btn btn-outline-dark">Оформить заказ</button>
                </div>
            </div>
        </div>
    </div>
    <script src="js/basket.js"></script>
    </body>
    </html>
</#macro>