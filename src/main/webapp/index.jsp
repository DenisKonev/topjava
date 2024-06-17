<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Calories management login</title>
</head>
<body>
<h2>Calories management login</h2>
<form action="login" method="post">
    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">Login</button>
    </div>
</form>
<p style="color:red;">
    <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
</p>
</body>
</html>