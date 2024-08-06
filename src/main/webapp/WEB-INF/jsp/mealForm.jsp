<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link href="${contextPath}/webjars/bootstrap/4.6.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <div class="modal fade" id="addMealModal" tabindex="-1" role="dialog" aria-labelledby="addMealModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addMealModalLabel"><spring:message code="meal.add"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="mealForm" method="post" action="meals">
                        <input type="hidden" name="id" value="${meal.id}">
                        <div class="form-group">
                            <label for="dateTime"><spring:message code="meal.dateTime"/></label>
                            <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                                   value="${meal.dateTime}" required>
                        </div>
                        <div class="form-group">
                            <label for="description"><spring:message code="meal.description"/></label>
                            <input type="text" class="form-control" id="description" name="description"
                                   value="${meal.description}" required>
                        </div>
                        <div class="form-group">
                            <label for="calories"><spring:message code="meal.calories"/></label>
                            <input type="number" class="form-control" id="calories" name="calories"
                                   value="${meal.calories}" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><spring:message code="common.save"/></button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message
                                code="common.cancel"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>

<script src="${contextPath}/webjars/jquery/3.7.1/jquery.min.js"></script>
<script src="${contextPath}/webjars/bootstrap/4.6.2/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function () {
        $('#addMealModal').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#mealForm').on('submit', function (event) {
            event.preventDefault();
            $.ajax({
                url: $(this).attr('action'),
                type: $(this).attr('method'),
                data: $(this).serialize(),
                success: function (response) {
                    $('#addMealModal').modal('hide');
                    alert('Meal added successfully!');
                    window.location.href = '${contextPath}/meals';
                },
                error: function (xhr, status, error) {
                    alert('Error adding meal: ' + xhr.responseText);
                }
            });
        });
    });
</script>

</body>
</html>