<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">

    <title>LIST</title>
</head>
<body>
<table style="border: 2px solid; width: 650px; text-align:center">
    <thead style="">
    <tr>
        <%--<th>ID</th>--%>
        <th>Product</th>
        <th>isNeeded</th>
        <th>amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${product}" var="product">
        <%--<c:url var="editUrl" value="/jvtestbd/main/users/edit?id=${user.id}" />--%>
        <c:url var="viewProduct" value="/list" />
        <tr>
            <%--<td><c:out value="${product.id}" /></td>--%>
            <td><c:out value="${product.nameProduct}" /></td>
            <td><c:out value="${product.isNeeded}" /></td>
            <td><c:out value="${product.amount}" /></td>
            <%--<td><a href="${editUrl}">Edit</a></td>--%>
            <%--<td><a href="${deleteUrl}">Delete</a></td>--%>
            <%--<td><a href="${addUrl}">Add</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
</html>
