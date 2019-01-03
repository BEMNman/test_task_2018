<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%!--%>
<%--int i = 1;--%>
<%--%>--%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
    <link href="/webapp/WEB-INF/static/w3.css" rel="stylesheet">
</head>
<body>

<div>
    <a href="<c:url value='/listProducts' />">Главная</a>
</div>

<table style="width: 600px; padding-bottom: 20px">
    <tr>
        <td>
            <form action="<c:url value='/searchProduct' />">
                <input type="text" name="search" placeholder="Поиск по имени"/>
                <button>Поиск</button>
            </form>
        </td>
        <td align="right">
            <form action="<c:url value='/addProduct' />" method="post">
                <button>Добавить</button>
            </form>
        </td>
    </tr>
</table>

<table class="w3-centered" bgcolor="#808080" style="border: 2px solid; width: 600px; text-align:center;">
    <tr>
        <%--<th class="w3-centered" , bgcolor="white">№</th>--%>
        <th class="w3-centered" bgcolor="white">Наименование</th>
        <th class="w3-centered" bgcolor="white">Необходимость</th>
        <th class="w3-centered" bgcolor="white">Количество</th>
        <th class="w3-centered" bgcolor="white">Действие</th>
    </tr>
    <tbody class="w3-centered" bgcolor="white">
    <c:forEach items="${product}" var="product">
        <tr>
                <%--<td><%= i %>--%>
            <td height="30px" align="left"><c:out value="${product.nameProduct}"/></td>
            <c:if test="${product.isNeeded == 1}">
                <td><c:out value="да"/></td>
            </c:if>
            <c:if test="${product.isNeeded == 0}">
                <td><c:out value="нет"/></td>
            </c:if>
            <td><c:out value="${product.amount}"/></td>
            <td>
                <a href="<c:url value='/updateProduct' />">Редактировать</a>
                <a href="<c:url value='/deleteProduct' />">Удалить</a>
            </td>
                <%--<%--%>
                <%--i++;--%>
                <%--%>--%>

                <%--<td style="text-align: center"><a th:href="@{'/edit/{id}'(id=${product.id})}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>--%>
                <%--<td style="text-align: center"><a th:href="@{'/deleteProduct/{id}'(id=${product.id})}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>--%>

        </tr>
    </c:forEach>
    <%--<%--%>
    <%--i = 1;--%>
    <%--%>--%>
    </tbody>
</table>

<table width="600px" bgcolor="#808080" border="2px" style="margin-top: 20px;  text-align:center">
    <tr>
        <td width="40%" height="30px" bgcolor="white" align="left">Можно собрать</td>
        <td width="20%" bgcolor="white">2</td>
        <td bgcolor="white">компьютеров</td>
    </tr>

</table>
</body>
</html>