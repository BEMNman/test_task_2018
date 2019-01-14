<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
</head>
<body>
<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>
<div>
    <h1>Новые комплектующие</h1>
    <form:form action="createPart" method="post" modelAttribute="partAdd">
        <table>
            <tr>
                <td>Наименование:</td>
                <td><form:input path="namePart" pattern="[0-9A-Za-zа-яА-Я\s]{1,25}" required="true" maxlength="30"
                                autofocus="true"/></td>
            </tr>
            <tr>
                <td>Необходимость:</td>
                <td><form:checkbox path="isNeeded" value="false"/></td>
            </tr>
            <tr>
                <td>Количество:</td>
                <td><form:input path="amount" type="number" min="0" max="999999"/></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form:form>
    <c:if test="${isExistPart == true}">
        <h1>
            Добавить невозможно!
        </h1>
        <h1>
            Такое наименование комплектующего уже есть на складе.
        </h1>
    </c:if>
</div>
</body>
</html>