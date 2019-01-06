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
<body>
<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>
Редактирование

<form action="<c:url value='/searchPart' />">
    <input type="text" name="search" placeholder="Поиск по имени"/>
    <button>Поиск</button>
</form>

<menu type="toolbar ">
    <li>пункт меню</li>
    <li>пункт меню</li>
</menu>

<select>
    <option>Пункт 1</option>
    <option>Пункт 2</option>
    <option>Пункт 3</option>
    <option>Пункт 4</option>
    <option>Пункт 5</option>
</select>
<p>
    
    <%
        String string = request.getParameter("selectListPartsForView");
        String bool = request.getParameter("inSearch");
        out.print(string);
        out.print(bool);
    %>
</p>
<p>
    <c:out value="${selectListPartsForView}"/>
    <c:out value="${inSearch}"/>
</p>
</body>
</html>
