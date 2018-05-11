<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>edit-generator</title>
    <link rel='stylesheet' href='<c:url value="/app.css"/>'>
    <h1 class='title'>World of Coins Framework - Edit Generator</h1>
</head>
<body>
    <form class='box' action='<c:url value="/admin/generators/edit?id=${id}"/>' method='POST'>
        <label>Generator Name</label>
        <input class='input' type='text' name='generator_name'>
        <label>Generator Rate</label>
        <input class='input' type='number' name='generator_rate'>
        <label>Base Cost</label>
        <input class='input' type='number' name='base_cost'>
        <label>Unlocks At</label>
        <input class='input' type='number' name='unlocks_at'>
        <label>Description</label>
        <input class='input' type='text' name='description'>
        <input class='button' type='submit' value='Edit Generator'>
    </form>
</body>
</html>