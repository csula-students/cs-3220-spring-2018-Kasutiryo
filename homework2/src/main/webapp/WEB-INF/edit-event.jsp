<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-event</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1>World of Coins Framework - Events</h1>
</head>
<nav class='nav'>
        <a href='<c:url value="/admin/information"/>'>Information</a> |
        <a href='<c:url value="/admin/events"/>'>Events</a> |
        <a href='<c:url value="/admin/generators"/>'>Generators</a>
</nav>
<body>
        <form action='<c:url value="/admin/events/edit?id=${id}"/>' method='POST'>
            <label>Event Name: </label>
            <input type='text' name='event_name'>
            <label>Event Description: </label>
            <input type='text' name='event_description'>
            <label>Trigger At: </label>
            <input type='number' name='trigger_at'>
            <input class='button' type='submit' value='add'>
        </form>
</body>
</html>