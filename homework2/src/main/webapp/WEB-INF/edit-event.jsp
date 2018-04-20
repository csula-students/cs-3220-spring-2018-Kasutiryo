<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-event</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1 class='title'>World of Coins Framework - Edit Event</h1>
</head>
<body>
    <form class='box' action='<c:url value="/admin/events/edit?id=${id}"/>' method='POST'>
        <label>Event Name: </label>
        <input class='input' type='text' name='event_name'>
        <label>Event Description: </label>
        <input class='input' type='text' name='event_description'>
        <label>Trigger At: </label>
        <input class='input' type='number' name='trigger_at'>
        <input class='button' type='submit' value='Edit Event'>
    </form>
</body>
</html>