<#import "templates.ftl" as t>
<@t.page>
    <div class="container-fluid">
        <div class="alert alert-info" role="alert">
            <h1>Номер вашего заказа : </h1>
        </div>
        <table>
            <thead>
            <tr>
                <th>Номер</th>
                <th>Статус</th>
                <th>Дата</th>
                <th>Трек номер</th>
                <th>Заказ</th>
                <th>Информация</th>
                <th>Стоимость</th>
            </tr>
            </thead>
            <tbody>
            <#list orderList as order >
                <tr>
                    <td>${order.number}</td>
                    <td>${order.status}</td>
                    <td>${order.date}</td>
                    <td>${order.track}</td>
                    <td>${order.purchasesIdsString}</td>
                    <td>${order.info}</td>
                    <td>${order.priceTotal}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.page>