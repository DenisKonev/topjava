<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
  <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
  <title>Add or Edit Meal</title>
</head>
<body>
<script>
  $(function () {
    $('input[name=dateTime]').datetimepicker({
      dateFormat: 'yy-mm-dd',
      timeFormat: 'HH:mm'
    });
  });
</script>

<form method="POST" action='/topjava/meals' name="frmAddMeal">
  Meal ID : <input type="text" name="mealId"
                   value="<c:out value="${meal.id}" />"/> <br/>
  Date and Time : <input type="text" name="dateTime"
                         value="<fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${meal.dateTime}" />"/> <br/>
  Description : <input type="text" name="description"
                       value="<c:out value="${meal.description}" />"/> <br/>
  Calories : <input type="text" name="calories"
                    value="<c:out value="${meal.calories}" />"/> <br/>
  <input type="submit" value="Submit"/>
</form>
</body>
</html>
