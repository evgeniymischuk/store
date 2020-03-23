var shoppingCart = (function () {
    cart = [];

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
    var obj = {};
    obj.addItemToCart = function (name, price, count, single) {
        for (var item in cart) {
            if (cart[item].name === name) {
                if (!single) {
                    cart[item].count++;
                    saveCart();
                }
                return false;
            }
        }
        var item = new Item(name, price, count, single);
        cart.push(item);
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
        for (var item in cart) {
            if (cart[item].name === name) {
                cart[item].count--;
                if (cart[item].count === 0) {
                    cart.splice(item, 1);
                }
                break;
            }
        }
        saveCart();
    };
    obj.removeItemFromCartAll = function (name) {
        for (var item in cart) {
            if (cart[item].name === name) {
                var els = $(".add-to-cart");
                $.each(els, function (i, e) {
                    var $el = $(e);
                    if (name === $el.data('name')) {
                        $el.html("Купить");
                        return false;
                    }
                });
                cart.splice(item, 1);
                break;
            }
        }
        saveCart();
    };
    obj.clearCart = function () {
        cart = [];
        saveCart();
    };
    obj.totalCount = function () {
        var totalCount = 0;
        for (var item in cart) {
            totalCount += cart[item].count;
        }
        return totalCount;
    };
    obj.totalCart = function () {
        var totalCart = 0;
        for (var item in cart) {
            totalCart += cart[item].price * cart[item].count;
        }
        return Number(totalCart.toFixed(2));
    };
    obj.listCart = function () {
        var cartCopy = [];
        for (i in cart) {
            item = cart[i];
            itemCopy = {};
            for (p in item) {
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

$('.add-to-cart').click(function (event) {
    event.preventDefault();
    var name = $(this).data('name');
    var single = $(this).data('single');
    var price = Number($(this).data('price'));
    var added = shoppingCart.addItemToCart(name, price, 1, single);
    if ($(this).html() === "Добавлено. Перейти в корзину") {
        $("#basketModal").modal();
    }
    if (single && added) {
        $(this).html("Добавлено. Перейти в корзину");
    }
    if (single && !added) {
        // shoppingCart.removeItemFromCart(name);
        // $(this).html($(this).data('name'));
    }
    displayCart();
});

$('.clear-cart').click(function () {
    shoppingCart.clearCart();
    displayCart();
});


function displayCart() {
    var cartArray = shoppingCart.listCart();
    var output = "";
    for (var i in cartArray) {
        var card = cartArray[i];
        var cartArrayName = card.name;
        var cartArrayPrice = card.price;
        var cartArrayTotal = card.total;
        var cartArrayCount = card.count;
        var cartArraySingle = card.single;
        var $el;
        if (cartArraySingle) {
            var els = $(".add-to-cart");
            $.each(els, function (i, e) {
                $el = $(e);
                if (cartArrayName === $el.data('name')) {
                    $el.html("Добавлено. Перейти в корзину");
                    return false;
                }
            });
            if ($el){
                var imgSrc = "/download?name=" + $el.data("id") + ".jpg";
                var divImg = "<div style='background-image: url(" + imgSrc + ")' class='img-responsive-basket'></div>";
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

    $('.show-cart').html(output);
    var totalCount = shoppingCart.totalCount();
    $('.total-count').html(totalCount > 0 ? totalCount : '');
}

$('.show-cart').on("click", ".delete-item", function (event) {
    var name = $(this).data('name');
    shoppingCart.removeItemFromCartAll(name);
    displayCart();
});
$('.show-cart').on("click", ".minus-item", function (event) {
    var name = $(this).data('name');
    shoppingCart.removeItemFromCart(name);
    displayCart();
});
$('.show-cart').on("click", ".plus-item", function (event) {
    var name = $(this).data('name');
    shoppingCart.addItemToCart(name);
    displayCart();
});
$('.show-cart').on("change", ".item-count", function (event) {
    var name = $(this).data('name');
    var count = Number($(this).val());
    shoppingCart.setCountForItem(name, count);
    displayCart();
});
$('.btn-order-show').on("click", function (event) {
   $("#basketForm").removeClass("d-none");
   $(".show-cart").addClass("d-none");
});

displayCart();
