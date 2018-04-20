<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='eng'>

<head>
    <meta charset='UTF-8'>
    <title>admin-generators</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1>World of Coins Framework - Generators</h1>
</head>
<nav class='nav'>
    <a href='<c:url value="/admin/information"/>'>Information</a> |
    <a href='<c:url value="/admin/events"/>'>Events</a> |
    <a href='<c:url value="/admin/generators"/>'>Generators</a>
</nav>
<main>
    <div class='content'>
        <form action='<c:url value="/admin/generators"/>' method='POST'>
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
        <table class='table'>
            <tr class='header'>
                <th>Gen. Name</th>
                <th>Gen. Rate</th>
                <th>Base Cost</th>
                <th>Unlock At</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${generators}" var="generator">
                <tr>
                    <td>${generator.getName()}</td>
                    <td>${generator.getRate()}</td>
                    <td>${generator.getBaseCost()}</td>
                    <td>${generator.getUnlockAt()}</td>
                    <td>${generator.getDescription()}</td>
                    <td>
                        <a href="<c:url value='/admin/generators/edit'/>?id=${generator.getId()}">Edit</a> |
                        <a href="<c:url value='/admin/generators/delete'/>?id=${generator.getId()}">Delete</a>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</main>

</html>