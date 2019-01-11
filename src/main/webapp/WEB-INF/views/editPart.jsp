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
</head>
<body>
<div>
    <a href="<c:url value='/listParts' />">Главная</a>
</div>

<div align="center">
    <h1>Редактирование позиции товара</h1>
    <c:url var="updatePart" value="/updatePart"/>
    <form:form id="updatePart" method="POST" modelAttribute="editPart" action="${updatePart}">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Наименование:</td>
                <td><form:input path="namePart"/></td>
            </tr>
            <tr>
                <td>Необходимость:</td>
                <td><form:checkbox path="isNeeded"/></td>
            </tr>
            <tr>
                <td>Количество:</td>
                <td><form:input path="amount"/></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="Сохранить"/></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>