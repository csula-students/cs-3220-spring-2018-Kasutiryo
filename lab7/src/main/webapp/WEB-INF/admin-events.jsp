<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='eng'>

<head>
    <meta charset='UTF-8'>
    <title>admin-events</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1>World of Coins Framework - Events</h1>       
</head>
<nav class='nav'>
    <a href='<c:url value="/admin/information"/>'>Information</a> |
    <a href='<c:url value="/admin/events"/>'>Events</a> |
    <a href='<c:url value="/admin/generators"/>'>Generators</a>
</nav>
<main>
    <div class='content'>
            <form class='box' action='<c:url value="/admin/events"/>' method='POST'>
                <label>Event Name: </label>
                <input class='input' type='text' name='event_name'>
                <label>Event Description: </label>
                <input class='input' type='text' name='event_description'>
                <label>Trigger At: </label>
                <input class='input' type='number' name='trigger_at'>
                <input class='button' type='submit' value='Add Event'>
            </form>
        <table class='table'>
            <tr class='header'>
                <th>Event Name</th>
                <th>Event Description</th>
                <th>Triggered At</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.getName()}</td>
                    <td>${event.getDescription()}</td>
                    <td>${event.getTriggerAt()}</td>
                    <td>
                        <a href="<c:url value='/admin/events/edit'/>?id=${event.getId()}">Edit</a> |
                         <a href="<c:url value='/admin/events/delete'/>?id=${event.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>    
    </div>
</main>
<footer>
    <form class='box' action='<c:url value="/admin/auth"/>' method="DELETE">
        <input type="submit" value="Logout">
    </form> 
</footer>

</html>