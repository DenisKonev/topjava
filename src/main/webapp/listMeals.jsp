<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .excess {
            background-color: red;
        }

        .normal {
            background-color: green;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
<h3><a href="index.jsp">Home</a></h3>
<h3><a href="addOrUpdateMeal.jsp">Добавить еду</a></h3>
<h3><a href="addOrUpdateMeal.jsp">Обновить еду</a></h3>
<hr>
<h2 style="text-align: center;">Моя еда</h2>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>${meal.id}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>