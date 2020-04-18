var shoppingCart = (function () {
    let cart = [];

    function Item(id, name, price, count, single) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.single = single;
    }

    function saveCart() {
        sessionStorage.setItem('shoppingCart', JSON.stringify(cart));
        __blockBasket();
    }

    function loadCart() {
        cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
    }

    if (sessionStorage.getItem("shoppingCart") != null) {
        loadCart();
    }
    const obj = {};
    obj.addItemToCart = function (id, name, price, count, single) {
        for (const i in cart) {
            const cartItem = cart[i];
            if (cartItem.id == id) {
                if (!single) {
                    cartItem.count++;
                    saveCart();
                }
                return false;
            }
        }
        cart.push(new Item(id, name, price, count, single));
        saveCart();
        return true;
    };
    obj.setCountForItem = function (id, count) {
        for (var i in cart) {
            const cartItem = cart[i];
            if (cartItem.id === id) {
                cartItem.count = count;
                break;
            }
        }
    };
    obj.removeItemFromCart = function (id) {
        for (const i in cart) {
            const cartItem = cart[i];
            if (cartItem.id === id) {
                cartItem.count--;
                if (cartItem.count === 0) {
                    cart.splice(i, 1);
                }
                break;
            }
        }
        saveCart();
    };
    obj.removeItemFromCartAll = function (id) {
        for (const i in cart) {
            const cartItem = cart[i];
            if (cartItem.id === id) {
                const els = $(".add-to-cart");
                $.each(els, function (i, e) {
                    const $el = $(e);
                    if (id === $el.data('id')) {
                        $el.html("Купить");
                        $el.removeClass("buy-btn-in-card-added");
                        return false;
                    }
                });
                cart.splice(i, 1);
                break;
            }
        }
        saveCart();
    };
    obj.clearCart = function () {
        for (const i in cart) {
            const els = $(".add-to-cart");
            $.each(els, function (i, e) {
                const $el = $(e);
                $el.html("Купить");
                $el.removeClass("buy-btn-in-card-added");
            });
        }
        cart = [];
        $(".total-count").html('');
        saveCart();
    };
    obj.totalCount = function () {
        let totalCount = 0;
        for (const i in cart) {
            totalCount += cart[i].count;
        }
        return totalCount;
    };
    obj.totalCart = function () {
        var totalCart = 0;
        for (const i in cart) {
            const cartItem = cart[i];
            totalCart += cartItem.price * cartItem.count;
        }
        return new Number(totalCart.toFixed(2));
    };
    obj.listCart = function () {
        const cartCopy = [];
        for (const i in cart) {
            let item = cart[i];
            let itemCopy = {};
            for (const k in item) {
                itemCopy[k] = item[k];
            }
            itemCopy.total = new Number(item.price * item.count).toFixed(2);
            cartCopy.push(itemCopy)
        }
        return cartCopy;
    };

    obj.hasListCart = function () {
        return cart && cart.length > 0;
    };

    function __blockBasket() {
        if (!(cart && cart.length > 0)) {
            $("#basket").removeAttr("data-toggle");
            $("#basketModal").modal('hide');
        } else {
            $("#basket").attr("data-toggle", "modal");
        }
    }

    __blockBasket();

    return obj;
})();
const $show_cart = $(".show-cart");
$(document).ready(function () {
    var msg = "";
    var elements = document.getElementsByTagName("INPUT");

    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function (e) {
            if (!e.target.validity.valid) {
                switch (e.target.id) {
                    case 'name' :
                        e.target.setCustomValidity("Введите имя");
                        break;
                    case 'email' :
                        e.target.setCustomValidity("Пожалуйста введите электронную почту");
                        break;
                    case 'postal' :
                        e.target.setCustomValidity("Пожалуйста введите адрес или индекс почтового отделения");
                        break;
                    default :
                        e.target.setCustomValidity("");
                        break;

                }
            }
        };
        elements[i].oninput = function (e) {
            e.target.setCustomValidity(msg);
        };
    }
});

function displayCart() {
    const cardArr = shoppingCart.listCart();
    let output = "";
    let sum = 0;
    let purchasesIds = "";
    for (const i in cardArr) {
        const card = cardArr[i];
        const cardId = card.id;
        if (purchasesIds.length > 0) {
            purchasesIds = purchasesIds + ",";
        }
        purchasesIds = purchasesIds + cardId;
        const cardName = card.name;
        const cardPrice = card.price;
        const cardTotal = card.total;
        const cardCount = card.count;
        const cardSingle = card.single;
        sum = sum + cardPrice;
        let $el;
        if (cardSingle) {
            const els = $(".add-to-cart");
            $.each(els, function (i, e) {
                $el = $(e);
                if (cardId === $el.data('id')) {
                    $el.addClass("buy-btn-in-card-added");
                    $el.html("В корзину");
                    return false;
                }
            });
            if ($el) {
                const imgSrc = "/download?id=" + $el.data("id") + ".jpg";
                const divImg = "<div style='background-image: url(" + imgSrc + ")' class='img-responsive-basket'></div>";
                output += "<tr>"
                    + "<td class='hw-38px'>"
                    + "<button class='delete-item btn btn-outline-dark hw-38px' data-id='" + cardId + "' data-name='" + cardName + "'>X</button>"
                    + "</td>"
                    + "<td class='hw-38px'>" + divImg + "</td>"
                    + "<td>" + cardName + "</td>"
                    + "<td>" + cardPrice + "&#8381;</td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>";
            }
        } else {
            output += "<tr>"
                + "<td class='w-38px'>"
                + "<button class='delete-item btn btn-outline-dark w-38px' data-id='" + cardId + "' data-name='" + cardName + "'>X</button>"
                + "</td>"
                + "<td>" + cardName + "</td>"
                + "<td> 1x" + cardPrice + "&#8381;</td>"
                + "<td>"
                + "<div class='input-group'>"
                + "<button class='minus-item input-group-addon btn btn-primary' data-id='" + cardId + "' data-name='" + cardName + "'>-</button>"
                + "<input type='number' class='item-count form-control' data-id='" + cardId + "' data-name='" + cardName + "' value='" + cardCount + "'>"
                + "<button class='plus-item btn btn-primary input-group-addon' data-id='" + cardId + "' data-name='" + cardName + "'>+</button>"
                + "</div>"
                + "</td>"
                + "<td>" + cardTotal + "&#8381;" + "</td>"
                + "</tr>";
        }
    }

    $show_cart.html(output);
    const totalCount = shoppingCart.totalCount();
    $('.total-count').html(totalCount > 0 ? totalCount : '');
    $('.total-sum').html(sum + "&#8381;");
    $("#priceTotal").val(sum);
    $("#purchasesIds").val(purchasesIds);
}

$('.add-to-cart').on("click", function () {
    event.preventDefault();
    const id = $(this).data('id');
    const name = $(this).data('name');
    const single = $(this).data('single');
    const price = new Number($(this).data('price'));
    const added = shoppingCart.addItemToCart(id, name, price, 1, single);
    if ($(this).html() === "В корзину") {
        $("#basketModal").modal();
    }
    if (single && added) {
        $(this).addClass("buy-btn-in-card-added");
        $(this).html("В корзину");
    }
    if (single && !added) {
        // shoppingCart.removeItemFromCart(id);
        // $(this).html($(this).data('id'));
    }
    displayCart();
});

$('.clear-cart').on("click", function () {
    shoppingCart.clearCart();
    displayCart();
});
$show_cart.on("click", ".delete-item", function () {
    const id = $(this).data('id');
    shoppingCart.removeItemFromCartAll(id);
    displayCart();
});
$show_cart.on("click", ".minus-item", function () {
    const id = $(this).data('id');
    shoppingCart.removeItemFromCart(id);
    displayCart();
});
$show_cart.on("click", ".plus-item", function () {
    const id = $(this).data('id');
    shoppingCart.addItemToCart(id);
    displayCart();
});
$show_cart.on("change", ".item-count", function () {
    const id = $(this).data('id');
    const count = new Number($(this).val());
    shoppingCart.setCountForItem(id, count);
    displayCart();
});
const $btn_order_show = $(".btn-order-show");
$('.btn-order-show').on("click", function () {
    const showText = $btn_order_show.html().trim();
    if (showText === "Перейти к оформлению заказа") {
        $("#basketForm").removeClass("dao-none");
        $(".show-cart").addClass("dao-none");
        $(".btn-order-back").removeClass("dao-none");
        $btn_order_show.html("Перейти к оплате");
    } else if (showText === "Перейти к оплате") {
        let count = 0;
        if ($("#postal").val()) {
            count = count + 1;
        }
        if ($("#email").val()) {
            count = count + 1;
        }
        if ($("#name").val()) {
            count = count + 1;
        }
        if (count !== 3) {
            $("#basketBtnSubmit").click();
        } else {
            $("#basketForm").addClass("dao-none");
            $(".pay-stage").removeClass("dao-none");
            $btn_order_show.html("Оформить заказ");
        }
    } else if (showText === "Оформить заказ") {
        $(".show-cart").removeClass("dao-none");
        $("#basketForm").addClass("dao-none");
        $(".btn-order-back").addClass("dao-none");
        $(".pay-stage").addClass("dao-none");
        $btn_order_show.html("Перейти к оформлению заказа");
        shoppingCart.clearCart();
        $("#basketBtnSubmit").click();
        $("#basketModalBtnClose").click();
    }
});
$('.btn-order-back').on("click", function () {
    const showText = $btn_order_show.html().trim();
    if (showText === "Перейти к оплате") {
        $(".show-cart").removeClass("dao-none");
        $("#basketForm").addClass("dao-none");
        $(".btn-order-back").addClass("dao-none");
        $btn_order_show.html("Перейти к оформлению заказа")
    } else if (showText === "Оформить заказ") {
        $("#basketForm").removeClass("dao-none");
        $(".pay-stage").addClass("dao-none");
        $btn_order_show.html("Перейти к оплате")
    }
});