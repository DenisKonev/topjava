<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="ru">
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title>Подсчет калорий</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css"/>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="meal.title"/></h3>

    <form method="get" action="meals/filter">
        <dl>
            <dt><spring:message code="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><spring:message code="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><spring:message code="meal.filter"/></button>
        <button type="button" id="resetFilter"><spring:message code="meal.resetFilter"/></button>
    </form>
    <hr>
    <a href="meals/create"><spring:message code="meal.add"/></a>
    <hr>
    <table id="mealsTable">
        <thead>
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr data-meal-excess="${meal.excess}">
                <td>${fn:formatDateTime(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><spring:message code="common.update"/></a></td>
                <td>
                    <button class="delete-btn" data-id="${meal.id}"><spring:message code="common.delete"/></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="${contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
<script src="${contextPath}/webjars/datatables/1.13.5/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#mealsTable').DataTable();
        $('.delete-btn').on('click', function (event) {
            event.preventDefault();
            var mealId = $(this).data('id');
            $.ajax({
                url: '/topjava/ui/meals/' + mealId,
                type: 'DELETE',
                success: function (result) {
                    $('button[data-id="' + mealId + '"]').closest('tr').remove();
                },
                error: function (xhr, status, error) {
                    alert('Ошибка при удалении записи: ' + error);
                }
            });
        });
        $('#resetFilter').on('click', function () {
            $('input[type="date"], input[type="time"]').val('');
        });
    });
</script>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>