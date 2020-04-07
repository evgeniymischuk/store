var shoppingCart = (function () {
    let cart = [];

    function Item(name, price, count, single) {
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
    obj.addItemToCart = function (name, price, count, single) {
        for (const i in cart) {
            if (cart[i].name === name) {
                if (!single) {
                    cart[i].count++;
                    saveCart();
                }
                return false;
            }
        }
        cart.push(new Item(name, price, count, single));
        saveCart();
        return true;
    };
    obj.setCountForItem = function (name, count) {
        for (var i in cart) {
            if (cart[i].name === name) {
                cart[i].count = count;
                break;
            }
        }
    };
    obj.removeItemFromCart = function (name) {
        for (const i in cart) {
            if (cart[i].name === name) {
                cart[i].count--;
                if (cart[i].count === 0) {
                    cart.splice(i, 1);
                }
                break;
            }
        }
        saveCart();
    };
    obj.removeItemFromCartAll = function (name) {
        for (const i in cart) {
            if (cart[i].name === name) {
                var els = $(".add-to-cart");
                $.each(els, function (i, e) {
                    var $el = $(e);
                    if (name === $el.data('name')) {
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
            var els = $(".add-to-cart");
            $.each(els, function (i, e) {
                var $el = $(e);
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
            totalCart += cart[i].price * cart[i].count;
        }
        return Number(totalCart.toFixed(2));
    };
    obj.listCart = function () {
        const cartCopy = [];
        for (i in cart) {
            let item = cart[i];
            let itemCopy = {};
            for (const p in item) {
                itemCopy[p] = item[p];
            }
            itemCopy.total = Number(item.price * item.count).toFixed(2);
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
    const cartArray = shoppingCart.listCart();
    let output = "";
    let sum = 0;
    for (const i in cartArray) {
        const card = cartArray[i];
        const cartArrayName = card.name;
        const cartArrayPrice = card.price;
        const cartArrayTotal = card.total;
        const cartArrayCount = card.count;
        const cartArraySingle = card.single;
        sum = sum + cartArrayPrice;
        let $el;
        if (cartArraySingle) {
            var els = $(".add-to-cart");
            $.each(els, function (i, e) {
                $el = $(e);
                if (cartArrayName === $el.data('name')) {
                    $el.addClass("buy-btn-in-card-added");
                    $el.html("В корзину");
                    return false;
                }
            });
            if ($el) {
                const imgSrc = "/download?name=" + $el.data("id") + ".jpg";
                const divImg = "<div style='background-image: url(" + imgSrc + ")' class='img-responsive-basket'></div>";
                output += "<tr>"
                    + "<td class='hw-38px'>"
                    + "<button class='delete-item btn btn-outline-dark hw-38px' data-name='" + cartArrayName + "'>X</button>"
                    + "</td>"
                    + "<td class='hw-38px'>" + divImg + "</td>"
                    + "<td>" + cartArrayName + "</td>"
                    + "<td>" + cartArrayPrice + "&#8381;</td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>";
            }
        } else {
            output += "<tr>"
                + "<td class='w-38px'>"
                + "<button class='delete-item btn btn-outline-dark w-38px' data-name='" + cartArrayName + "'>X</button>"
                + "</td>"
                + "<td>" + cartArrayName + "</td>"
                + "<td> 1x" + cartArrayPrice + "&#8381;</td>"
                + "<td>"
                + "<div class='input-group'>"
                + "<button class='minus-item input-group-addon btn btn-primary' data-name='" + cartArrayName + "'>-</button>"
                + "<input type='number' class='item-count form-control'  data-name='" + cartArrayName + "' value='" + cartArrayCount + "'>"
                + "<button class='plus-item btn btn-primary input-group-addon' data-name='" + cartArrayName + "'>+</button>"
                + "</div>"
                + "</td>"
                + "<td>" + cartArrayTotal + "&#8381;" + "</td>"
                + "</tr>";
        }
    }

    $show_cart.html(output);
    const totalCount = shoppingCart.totalCount();
    $('.total-count').html(totalCount > 0 ? totalCount : '');
    $('.total-sum').html(sum + "&#8381;");
}

$('.add-to-cart').on("click", function () {
    event.preventDefault();
    const name = $(this).data('name');
    const single = $(this).data('single');
    const price = Number($(this).data('price'));
    const added = shoppingCart.addItemToCart(name, price, 1, single);
    if ($(this).html() === "В корзину") {
        $("#basketModal").modal();
    }
    if (single && added) {
        $(this).addClass("buy-btn-in-card-added");
        $(this).html("В корзину");
    }
    if (single && !added) {
        // shoppingCart.removeItemFromCart(name);
        // $(this).html($(this).data('name'));
    }
    displayCart();
});

$('.clear-cart').on("click", function () {
    shoppingCart.clearCart();
    displayCart();
});
$show_cart.on("click", ".delete-item", function () {
    const name = $(this).data('name');
    shoppingCart.removeItemFromCartAll(name);
    displayCart();
});
$show_cart.on("click", ".minus-item", function () {
    const name = $(this).data('name');
    shoppingCart.removeItemFromCart(name);
    displayCart();
});
$show_cart.on("click", ".plus-item", function () {
    const name = $(this).data('name');
    shoppingCart.addItemToCart(name);
    displayCart();
});
$show_cart.on("change", ".item-count", function () {
    const name = $(this).data('name');
    const count = Number($(this).val());
    shoppingCart.setCountForItem(name, count);
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
        $("basketBtnSubmit").click();
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