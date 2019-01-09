<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
    <%--<link href="/webapp/WEB-INF/static/w3.css" rel="stylesheet">--%>
</head>
<body>
<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>
<strong>
    Редактирование товара
</strong>

<form name="part" action="<c:url value='/updatePart' />" method="post">
    <input type="hidden" name = "partIdEdit" value="${editPart.id}">
    <p>
        Наименование:
        <input type="text" title="Наименование" name="newNamePart" value="<c:out value="${editPart.namePart}"/>">
    </p>
    <p>
        Необходимость:
        <c:if test="${editPart.isNeeded == true}">
            <input type="checkbox" checked name="newIsNeeded">
        </c:if>
        <c:if test="${editPart.isNeeded != true}">
            <input type="checkbox" name="newIsNeeded">
        </c:if>
        <span>
            да, необходимы для сборки
        </span>
    </p>
    <p>
        Количество:
        <input type="text" title="Введите число" pattern="^[0-9]+$" name="newAmount"
               value="<c:out value="${editPart.amount}"/>">
    </p>
    <p>
        <button>Применить</button>
        <a href="<c:url value='/'/>">
            <button type="button">Отмена</button>
        </a>
    </p>
</form>
</body>
</html>