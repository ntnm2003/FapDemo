<%-- 
    Document   : login
    Created on : Oct 17, 2022, 9:29:29 PM
    Author     : Khangnekk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/myStyle/Style.css">
        <link rel="icon" href="assets/favicon/fu-favicon.jpg">
        <title>Login as Student</title>
        <style>
            *{
                margin: 0;
                padding: 0;
            }
            
            p{
                color: red;
                font-weight: bold;
            }

            .main{
                background-image: url("assets/download/bg.jpg");
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .glass{
                background: white;
                min-height: 70vh;
                width: 70%;
                padding: 1rem;
                border-radius: 2rem;
                background: linear-gradient(to right bottom,
                    rgba(59, 121, 255,0.7)
                    ,rgba(255, 65, 230, 0.3));
                display: flex;
                backdrop-filter: blur(0.75rem);
            }

            .notification{
                width: 60%;
                color: white;
                text-align: center;
                margin-top: 2rem;
                padding: 1rem;
                font-family: 'Courier New', Courier, monospace;
            }

            .login{
                text-align: center;
                margin-top: 3rem;
                width: 40%;
                padding: 1rem;
                font-family: 'Courier New', Courier, monospace;
                border-left: 2px solid #ccc;
            }

            .login-form{
                margin-top: 1rem;
            }

            input{
                background-color: azure;
                margin-top: 1rem;
                border-radius: 2rem;
                font-family: 'Courier New', Courier, monospace;
            }
            .usernameAndPassword{
                width: 80%;
                height: 2rem;
                padding: 0 0.5rem;
                border: 1px solid background;
            }
            .loginBtn{
                width: 20%;
                height: 2rem;
                border: 1px solid background;
                font-weight: bold;
                font-size: large;
            }


            @media screen and (max-width: 600px){
                .notification{
                    display: none;
                }
                .login{
                    width: 100%;
                    padding: 0;
                    border-left: 0ch;
                }
                .loginBtn{
                    width: 30%;
                }
            }
        </style>
    </head>

    <body>
        <div class="main">
            <div class="glass">
                <div class="notification">
                    <h4>Nice to see you again</h4>
                    <h1>WELCOME BACK</h1>
                    <div class="abc">
                        <img style="width: 400px; height: 212px;margin-top: 2rem;"
                             src="https://www.mystudentplan.ca/_images/student-plan-landing.png">
                    </div>
                </div>
                <div class="login">
                    <img style="width:5rem; height: 5rem; border-radius: 50%; border: 3px solid white;"
                         src="https://play-lh.googleusercontent.com/IrTWmVR5xviVUO-XH8G2IniRT2gJCY2k2iFPrIfO7-KQR1RrfLplg90orxHaD75Hn21t">
                    <h2 style="color: white;">Login as Student</h2>
                    <div class="login-form">
                        <form action="studentLogin" method="POST">
                            <input class="usernameAndPassword" type="text" name="email" placeholder="Email"><br>
                            <input class="usernameAndPassword" type="password" name="password" placeholder="Password"><br><br>
                            <p>${noti}</p>
                            <input class="loginBtn" type="submit" value="Login"><br>
                            <br>
                            <a href="" style="text-decoration: none; font-weight: bold;">Sign Up</a> |
                            <a href="" style="text-decoration: none; font-weight: bold;">Forget passsword?</a><br><br>
                            <a href="login" style="text-decoration: none; font-weight: bold;">Login as Lecturer</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
