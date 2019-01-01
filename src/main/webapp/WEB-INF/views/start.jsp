<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gogolinsky
  Date: 23.12.2018
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>(((Test)))</title>
</head>
<body>
<div>TESTtestTEST</div>
<div>test <a href="<c:url value='/list' />">list</a></div>
<div>test <a href="<c:url value='/addProduct' />">AddProduct</a></div>
<div>test <a href="<c:url value='/deleteProduct' />">DeleteProduct</a></div>
<div>test1 <a href="<c:url value='/test1' />">TEST1</a></div>
<div><input type="text"/></div>
</body>
</html>
