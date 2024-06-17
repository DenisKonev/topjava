<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Calories management</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        .header-links {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>
<section>
    <div class="header-links">
        <h3><a href="meals">Meals</a></h3>
        <h3><a href="index.jsp">Logout</a></h3>
        <c:if test="${user.isAdmin}">
            <h3><a href="users">Users</a></h3>
        </c:if>
    </div>
    <hr/>
</section>
</body>
</html>