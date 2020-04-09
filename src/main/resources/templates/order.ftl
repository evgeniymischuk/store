<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <div class="alert alert-info" role="alert">
            <h1>Номер вашего заказа : ${orderDto.number}</h1>
            <p>Имя ${orderDto.name}</p>
            <p>Электронная почта ${orderDto.email}</p>
            <p>Адрес или почтовый индекс ${orderDto.postal}</p>
            <p>Статус ${orderDto.status}</p>
            <p>Трек номер${orderDto.track}</p>
            <p>Дата заказа ${orderDto.date}</p>
            <p>Общая стоимость ${orderDto.priceTotal}</p>
        </div>
    </div>
</@t.page>