<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='eng'>

<head>
    <meta charset='UTF-8'>
    <title>login</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1 class='title'>World of Coins Framework - Login</h1>
</head>
<main> 
    <form class='box' action='<c:url value="/admin/auth"/>' method='POST'>
       
        <label>Username: </label>
        <input class='input' type='text' name='username'>
        <label>Password: </label>
        <input class='input' type="password" name='password'>
        <label>Incorrect login. The username or password is incorrect. Try again</label>
        <input class='button' type='submit' value='Log in'>
    </form>
</main>

</html>
