<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href='<c:url value="/game-app.css"/>'>
        <title>World of Coins</title>
        <script>
            window.game = ${state};
        </script>
    </head>

    <body>
        <div class="header">
            <div class="gameTitle">World of Coins</div>
        </div>
        <div class="content">

            <game-story-book class="terminal"></game-story-book>

            <div class="coinPouchRow">
                <div class="coinPouch">COINS: </div>
                <game-counter class="coinPouch"></game-counter>
                <game-button class="coinPouch"></game-button>
            </div>
            <div class="generatorRow">
                <game-generator class="generatorBox" data-id="0"></game-generator>
                <game-generator class="generatorBox" data-id="1"></game-generator>
                <game-generator class="generatorBox" data-id="2"></game-generator>
            </div>
        </div>
        <div class="footer">
            Copyright © 2018 - CS 3220 - California State University, Los Angeles
        </div>
        <!-- <script src="app.js"></script> -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/webcomponentsjs/1.1.0/webcomponents-lite.js"></script>
        <script src='<c:url value="/app.bundle.js"/>'></script>
    </body>

    </html>