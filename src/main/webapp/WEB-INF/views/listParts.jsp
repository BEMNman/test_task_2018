<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
    <link href="/webapp/WEB-INF/static/w3.css" rel="stylesheet">
</head>
<body>

<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>

<table style="width: 600px; padding-bottom: 20px">
    <tr>
        <td>
            <form action="<c:url value='/searchPart' />">
                <input type="text" title="Не менее 3 символов" pattern="^[A-Za-zА-Яа-яЁё0-9\s]{3,}" name="search" placeholder="Поиск по имени"/>
                <button>Поиск</button>
                <input type="hidden" name="inSearch" value="true">
            </form>
        </td>
        <td align="right">
            <form action="<c:url value='/addPart' />" method="post">
                <button>Добавить</button>
            </form>
        </td>
    </tr>
</table>
<p>Фильтровать по:</p>
<form action="<c:url value="/selectListPartsForView"/> " method="post">
    <p><select name="listMenu">
        <c:if test="${selectListPartsForView.equals('All')}">
            <option selected value="All">Все</option>
            <option value="isNeeded">Необходимые</option>
            <option value="options">Опциональные</option>
        </c:if>
        <c:if test="${selectListPartsForView.equals('isNeeded')}">
            <option value="All">Все</option>
            <option selected value="isNeeded">Необходимые</option>
            <option value="options">Опциональные</option>
        </c:if>
        <c:if test="${selectListPartsForView.equals('options')}">
            <option value="All">Все</option>
            <option value="isNeeded">Необходимые</option>
            <option selected value="options">Опциональные</option>
        </c:if>
    </select></p>
    <input type="submit" value="Выбрать">
</form>

<c:if test="${parts.size() > 0}">
    <table class="w3-centered" bgcolor="#808080" style="border: 2px solid; width: 600px; text-align:center;">

        <tr>
            <th class="w3-centered" bgcolor="white">Наименование</th>
            <th class="w3-centered" bgcolor="white">Необходимость</th>
            <th class="w3-centered" bgcolor="white">Количество</th>
            <th class="w3-centered" bgcolor="white">Действие</th>
        </tr>
        <tbody class="w3-centered" bgcolor="white">
        <c:forEach items="${parts}" var="part">
            <tr>
                <td height="30px" align="left"><c:out value="${part.namePart}"/></td>
                <c:if test="${part.isNeeded == true}">
                    <td><c:out value="да"/></td>
                </c:if>
                <c:if test="${part.isNeeded == false}">
                    <td><c:out value="нет"/></td>
                </c:if>
                <td><c:out value="${part.amount}"/></td>
                <td>
                    <style>
                        form {
                            margin: 1px; /* Убираем отступы */
                        }
                    </style>
                    <form action="<c:url value='/updatePart' />">
                        <input type="text" name="updatePart" value="${part.id}" hidden/>
                        <button style="height:21px;width:110px">Редактировать</button>
                    </form>
                    <form action="<c:url value='/deletePart' />">
                        <input type="text" name="deletePart" value="${part.id}" hidden/>
                        <button style="height:21px;width:110px">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${parts.size() == 0}">
    <p>В списке нет комплектующих</p>
</c:if>

<table width="600px" bgcolor="#808080" border="2px" style="margin-top: 20px;  text-align:center">
    <tr>
        <td width="40%" height="30px" bgcolor="white" align="left">Можно собрать</td>
        <td width="20%" bgcolor="white">
            <c:out value="${countComputer}"/>
        </td>
        <c:if test="${(countComputer >= 2 && countComputer <= 4) || (countComputer > 20 && countComputer%10 >= 2 && countComputer%10 <= 4)}">
            <td bgcolor="white"> компьютера</td>
        </c:if>
        <c:if test="${((countComputer == 0 || countComputer >= 5) && countComputer <= 20) || (countComputer > 20 && countComputer%10 >= 5 && countComputer%10 <= 9)}">
            <td bgcolor="white"> компьютеров</td>
        </c:if>
        <c:if test="${countComputer == 1 || (countComputer != 11 && countComputer%10 == 1)}">
            <td bgcolor="white"> компьютер</td>
        </c:if>
    </tr>
</table>
</body>
</html>