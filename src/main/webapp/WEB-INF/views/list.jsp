<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
<table style="border: 2px solid; width: 500px; text-align:center">
    <thead style="">
    <tr>
        <th>Наименование</th>
        <th>Необходимость</th>
        <th>Количество</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${product}" var="product">
        <c:url var="viewProduct" value="/list"/>
        <tr>
            <td><c:out value="${product.nameProduct}"/></td>
            <c:if test="${product.isNeeded == 1}">
                <td><c:out value="Yes"/></td>
            </c:if>
            <c:if test="${product.isNeeded == 0}">
                <td><c:out value="No"/></td>
            </c:if>
            <td><c:out value="${product.amount}"/></td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
</html>
