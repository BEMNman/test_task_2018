<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комплектующие</title>
</head>
<body>
<table style="width: 570px; padding-bottom: 20px; padding-top: 18px">
    <tr>
        <td>
            <form action="<c:url value='/searchPart'/>" method="get">
                <input type="text" title="Не менее 3 символов" pattern="^[A-Za-zА-Яа-яЁё0-9\s]{3,}" name="search"
                        <c:if test="${!search.equals('')}">
                            value=" <c:out value="${search}"/>"
                        </c:if>
                        <c:if test="${search.equals('')}">
                            placeholder="Поиск по имени"
                        </c:if>
                />
                <button>Поиск</button>
            </form>
        </td>
        <td align="right">
            <form action="<c:url value='/addPart'/>">
                <button>Добавить</button>
            </form>
        </td>
    </tr>
</table>
<div style="padding-bottom: 10px">
    <form action="<c:url value="/selectListPartsForView"/> " method="get">
        <table style="padding-top: 20px">
            <th>Фильтровать по:</th>
            <th>
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
            </th>
            <th><input type="submit" value="Выбрать"></th>
        </table>
    </form>
</div>
<c:if test="${parts.size() > 0}">
    <table class="w3-centered" bgcolor="#808080" style="border: 2px solid; width: 570px; text-align:center">
        <tr>
            <th class="w3-centered" bgcolor="white" style="width: 200px">Наименование</th>
            <th class="w3-centered" bgcolor="white" style="width: 140px">Необходимость</th>
            <th class="w3-centered" bgcolor="white" style="width: 110px">Количество</th>
            <th class="w3-centered" bgcolor="white" style="width: 120px">Действие</th>
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
                    <form action="<c:url value='/editPart'/>" method="post">
                        <input type="text" name="editPartId" value="${part.id}" hidden/>
                        <button style="height:21px;width:110px">Редактировать</button>
                    </form>
                    <form action="<c:url value='/deletePart' />" method="post">
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
<div style="padding-top: 20px">
    <table class="w3-centered" bgcolor="#808080" style="border: 2px solid; width: 448px; text-align:center;">
        <tr>
            <td style="width: 198px; height: 30px; align: left" bgcolor="white">Можно собрать</td>
            <td style="width: 140px" bgcolor="white">
                <c:out value="${countComputer}"/>
            </td>
            <c:if test="${(countComputer >= 2 && countComputer <= 4) || (countComputer > 20 && countComputer%10 >= 2 && countComputer%10 <= 4)}">
                <td style="width: 110px" bgcolor="white"> компьютера</td>
            </c:if>
            <c:if test="${((countComputer == 0 || countComputer >= 5) && countComputer <= 20) || (countComputer > 20 && countComputer%10 >= 5 && countComputer%10 <= 9)}">
                <td style="width: 110px" bgcolor="white"> компьютеров</td>
            </c:if>
            <c:if test="${countComputer == 1 || (countComputer != 11 && countComputer%10 == 1)}">
                <td style="width: 110px" bgcolor="white"> компьютер</td>
            </c:if>
        </tr>
    </table>
    <div style="padding-top: 20px">
        <c:url value="/list/${page-1}" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/list/${i.index}" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:url value="/list/${page+1}" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Next</a>
        </c:if>
    </div>
</div>
</body>
</html>