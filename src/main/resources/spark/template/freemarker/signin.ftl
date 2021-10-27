<!DOCTYPE html>
<! –– and the comment closes with ––>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>Web Checkers | </title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
    <div class="page">
        <h1>Web Checkers</h1>

        <div class="navigation">
                        <a href="/">home</a>
                    </div>


        <form action="/signin" METHOD="post">

            <label for="name">Name: </label><br>
            <input type="text" id="name" name="name"><br>

            <label for="password">Password: </label><br>
            <input type="text" id="password" name="password"><br>

            <input type="submit" name="sign-in" value="Sign-in"/>
        </form>
        <#include "message.ftl" />
    </div>
</body>




