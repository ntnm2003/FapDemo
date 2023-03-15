<%-- 
    Document   : accessDenied
    Created on : Oct 17, 2022, 11:02:21 PM
    Author     : Khangnekk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied!</title>
        <link rel="icon" href="assets/favicon/fu-favicon.jpg" />
        <style>
            body{
                background-color: black;
            }
            .content{
                margin: 10em auto;
                width: 50%;
                padding: 15px;
                text-align: center;
                border: 1px solid #ccc;
                border-radius: 9px;
            }
            .content h2{
                font-size: 50px;
                font-family: cursive;
                color: red;
            }
            button{
                border: 1px solid #ccc;
                border-radius: 9px;
                font-family: cursive;
                color: red;
                font-weight: bold;
                font-size: 16px;
            }
            .noti{
                width: 70%;
                text-align: center;
                margin: 10px auto;
                
            }
            .content h3{
                font-size: 18px;
                font-family: cursive;
                color: white;
            }
        </style>

    </head>
    <body>
        <div class="content">
            <img style="width: 100px;" src="assets/img/fpt-logo.png">
            <h2>Access denied!</h2>
            <h3><< You must have a student account to access >></h3>
            <div class="noti">
                <a style="color: white;
                   font-family: cursive;
                   font-weight: bold;
                   font-size: 16px;">If you are not logged in</a>
                <button onclick="Login();">> Click here to login</a</button
            </div>
            </div>
        </div>
        <script>
            function Login() {
                window.location = "studentLogin";
            }
        </script>
    </body>
</html>
