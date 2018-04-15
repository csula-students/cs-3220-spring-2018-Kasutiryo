<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='eng'>

<head>
    <meta charset='UTF-8'>
    <title>admin-events</title>
    <link rel='stylesheet' href='/app.css'>
    <h1>World of Coins Framework - Events</h1>
</head>
<nav class='nav'>
    <a href='/admin/information'>Information</a> |
    <a href='/admin/events'>Events</a> |
    <a href='/admin/generators'>Generators</a>
</nav>
<main>
    <div class='content'>
        <div class='forms'>
            <form action='/admin/events' method='post'>
                <label>Event ID(USE ONLY IF EDITTING AN EVENT): </label>
                <input type='number' name='event_id'>
                <label>Event Name: </label>
                <input type='text' name='event_name'>
                <label>Event Description: </label>
                <input type='text' name='event_description'>
                <label>Trigger At: </label>
                <input type='number' name='trigger_at'>
                <input type='submit' name='post' value='add'>
                <input type='submit' name='post' value='edit'>
            </form>
        </div>
        <table class='table'>
            <tr class='header'>
                <th>ID</th>
                <th>Event Name</th>
                <th>Event Description</th>
                <th>Triggered At</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.getId()}</td>
                    <td>${event.getName()}</td>
                    <td>${event.getDescription()}</td>
                    <td>${event.getTriggerAt()}</td>
                    <td>
                        <a href='${request.getContextPath()}/admin/events?id=${event.getId()}&action=delete'>Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>    
    </div>
</main>

</html>