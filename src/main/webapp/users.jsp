<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>

<button type="button" onclick="location.href='users?action=create'">Add user</button>

<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Roles</th>
        <th>Enabled</th>
        <th>Registered</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.roles}</td>
            <td><input type="checkbox" disabled="disabled" <c:if test="${user.enabled}">checked</c:if>></td>
            <td><fmt:formatDate value="${user.registered}" pattern="yyyy-MM-dd"/></td>
            <td>
                <a href="users?action=update&id=${user.id}">✏</a>
                <a href="users?action=delete&id=${user.id}">❌</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>