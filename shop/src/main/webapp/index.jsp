<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>auto-syndicate</title>

    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
        }
        .catalog, .cart {
            width: 50%;
            padding: 10px;
        }
        .product {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }
        img {
            display: block;
            margin-bottom: 5px;
        }
        .desc {
            font-size: 13px;
            color: #555;
            margin-top: 5px;
        }
    </style>
</head>

<body>

<h1>auto-syndicate</h1>

<div class="container">

    
    <div class="catalog">
        <h2>Каталог товаров</h2>

        <c:forEach var="p" items="${applicationScope.catalog.values()}">
            <div class="product">

                <img src="<c:url value='/images/${p.image}'/>" width="120">

                <p>
                    <b>${p.name}</b><br>

                    Цена:
                    <fmt:formatNumber value="${p.price}"
                                      type="number"
                                      groupingUsed="true"/> ₽
                    <br>

                    Остаток: ${p.stock}
                </p>


                <p class="desc">
                        ${p.description}
                </p>

                <c:choose>
                    <c:when test="${p.stock > 0}">
                        <form method="post" action="shop">
                            <input type="hidden" name="id" value="${p.id}">
                            <button type="submit">Добавить в корзину</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <b>Нет в наличии</b>
                    </c:otherwise>
                </c:choose>

            </div>
        </c:forEach>
    </div>


    <div class="cart">
        <h2>Корзина</h2>

        <c:if test="${empty sessionScope.cart}">
            <p>Корзина пуста</p>
        </c:if>

        <c:if test="${not empty sessionScope.cart}">

            <c:set var="total" value="0"/>

            <c:forEach var="item" items="${sessionScope.cart}">
                <p>
                        ${item.key.name} —
                        ${item.value} шт. —
                    <fmt:formatNumber
                            value="${item.key.price * item.value}"
                            type="number"
                            groupingUsed="true"/> ₽
                </p>

                <c:set var="total"
                       value="${total + (item.key.price * item.value)}"/>
            </c:forEach>

            <hr>

            <b>
                Итого:
                <fmt:formatNumber value="${total}"
                                  type="number"
                                  groupingUsed="true"/> ₽
            </b>

        </c:if>
    </div>

</div>

</body>
</html>