<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-generator</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1>World of Coins Framework - Generators</h1>
</head>
<nav class='nav'>
        <a href='<c:url value="/admin/information"/>'>Information</a> |
        <a href='<c:url value="/admin/events"/>'>Events</a> |
        <a href='<c:url value="/admin/generators"/>'>Generators</a>
</nav>
<body>
        <form action='<c:url value="/admin/generators/edit?id=${id}"/>' method='POST'>
        <label>Generator Name</label>
        <input type='text' name='generator_name'>
        <label>Generator Rate</label>
        <input type='number' name='generator_rate'>
        <label>Base Cost</label>
        <input type='number' name='base_cost'>
        <label>Unlocks At</label>
        <input type='number' name='unlocks_at'>
        <label>Description</label>
        <input type='text' name='description'>
        <input class='button' type='submit' value='add'>
    </form>
</body>
</html>