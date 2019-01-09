<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
    <%--<link href="/webapp/WEB-INF/static/w3.css" rel="stylesheet">--%>
</head>

<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>
<strong>
    Новый товар
</strong>

<form name="part" action="<c:url value='/createPart' />" method="post">
    <p>
        Наименование:
        <input type="text" title="Наименование" name="namePart" value="новые комплектующие">
    </p>
    <p>
        Необходимость:
        <input type="checkbox" name="isNeeded" value="1">
        <span>
            да, необходимы для сборки
        </span>
    </p>
    <p>
        Количество:
        <input type="text" title="Введите число" pattern="^[0-9]+$" name="amount" value="0">
    </p>
    <p>
        <button>Добавить</button>
        <a href="<c:url value='/'/>">
            <button type="button">Отмена</button>
        </a>
    </p>
</form>
<c:if test="${partExist == true}">
    <h1>
        Добавить невозможно! Такое наименование комплектующего уже есть на скаладе.
    </h1>
</c:if>
</body>
</html>