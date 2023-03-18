<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FAP University Academic Portal</title>
        <!-- link favicon logo -->
        <link rel="icon" href="assets/favicon/fu-favicon.jpg" />
        <!-- link bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- link Style.css -->
        <link rel="stylesheet" href="assets/css/myStyle/Style.css">
        <!-- link font-awesome -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <style>
        table tr td{
            padding: 10px 15px;
        }
    </style>
    <body>
        <button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top">
            <i class="fas fa-arrow-up"></i> Back to top
        </button>
        <div class="header">
            <nav class="nav-main">
                <!-- Navigation for PC -->
                <nav class="nav-pc" data-spy="affix">
                    <div>
                        <a href="#" class="nav-link-pc"><img style="width: 3em;" src="assets/img/fpt-logo.png">FPT University
                        </a>
                    </div>
                    <div class="nav-pc-left">               
                        <a href="#"><i class="fa-solid fa-bell"></i></a>
                        <div class="dropdown nav-pc-left">
                            <a href="#"><i class="fa-solid fa-user-tie"></i></a>
                            <div class="dropdown-content" >
                                <a href="#">
                                    <i style="color: black;" class="fa-solid fa-address-card"></i>
                                    <c:if test="${sessionScope.account ne null}">
                                        ${sessionScope.account.username}
                                    </c:if>
                                </a>
                                <a href="#"><i style="color: black;" class="fa-solid fa-earth-americas"></i> Language</a>
                                <a href="#"><i style="color: black;" class="fa-solid fa-comment"></i> Feedback</a>
                                <a href="#"><i style="color: black;" class="fa-solid fa-circle-half-stroke"></i> Dark Mode (Off)</a>
                                <a href="logout"><i style="color: black;" class="fa-solid fa-right-from-bracket"></i> Logout</a>
                            </div>
                        </div>
                    </div>      
                </nav>
            </nav>
        </div>
        <div class="main">
            <br>    
            <div class="container-fluid main-content-home">
                <div class="row">   
                    <div class="col-md-2 left">
                        <ul style="padding: 0;">
                            <li>
                                <a href="timeTable?email=${sessionScope.account.email}" ><i class="fas fa-table"></i> Timetable</a>
                            </li>
                            <li>
                                <a href="group?email=${sessionScope.account.email}&gid=-1" ><i class="fa-solid fa-file"></i> View Report Attendance</a>
                            </li>
                            <li>
                                <a href="getInfo?emailInfo=${sessionScope.account.email}&gidInfo=-1&seInfo=-1&gid=-1&seid=-1&email=${sessionScope.account.email}" ><i class="fas fa-calendar-check"></i> Take Attendance</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-comment"></i> Feedback</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-scale-balanced"></i> Regulations</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-not-equal"></i> Others</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-c"></i> FPTU-Coursera</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-building"></i> Dormitory</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-triangle-exclamation"></i> Important Notice</a>
                            </li>
                        </ul>
                        <h2 style="font-size: 20px;">Available on</h2>
                        <ul style="padding: 0;">
                            <li>
                                <a href="" ><img height="20px"  src="assets/favicon/appStore.png"> App Store</a>
                            </li>
                            <li>
                                <a href="" ><img height="20px" src="assets/favicon/googlePlay.png"> Google Play</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-10 right">
                        <div>
                            <h3>Take Attendance for ${requestScope.ses.group.name}</h3>
                            Date: ${requestScope.ses.date}<br>
                            Room: ${requestScope.ses.room.name}<br>
                            Status Attendance: 
                            <c:if test="${requestScope.ses.attendated}">
                                <a style="color:green; font-weight: bold">Attended</a>
                            </c:if>
                            <c:if test="${!requestScope.ses.attendated}">
                                <a style="color:red;font-weight: bold">Not yet</a>
                            </c:if><br>

                        </div>
                        <form method="POST" action="takeAttendance">
                            <input type="hidden" name="seid" value="${requestScope.seid}"/>
                            <div class="table">
                                <table>
                                    <tr class="htable">
                                        <td></td>
                                        <td>GROUP</td>
                                        <td>ROLLNUMBER</td>
                                        <td>FULL NAME</td>
                                        <td>PRESENT</td>
                                        <td>ABSENT</td>
                                        <td>COMMENT</td>
                                        <td>
                                            <input type="checkbox" name="show_image"/>SHOW IMAGE
                                        </td>
                                    </tr>
                                    <c:forEach items="${requestScope.ses.attendances}" var="a" varStatus="loop">
                                        <tr style="border-bottom: 2px solid #ccc; text-align: center">
                                            <td>${loop.index+1}</td>
                                            <td>${requestScope.ses.group.name}
                                                <input type="hidden" name="stuid" value="${a.student.id}"/>
                                            </td>
                                            <td>${a.student.id}</td>
                                            <td>${a.student.name}</td>
                                            <td><input type="radio"
                                                       <c:if test="${a.present}">
                                                           checked="checked"
                                                       </c:if>
                                                       name="present${a.student.id}" value="present" /> present</td>
                                            <td><input type="radio"
                                                       <c:if test="${!a.present}">
                                                           checked="checked"
                                                       </c:if>
                                                       name="present${a.student.id}" value="absent" /> absent</td>
                                            <td><input type="text" name="description${a.student.id}" value="${a.description}" /></td>
                                            <td class="avt_img">
                                                <img src="assets/img/avt.jpeg" alt="alt"/>
                                            </td>
                                        </tr>   

                                    </c:forEach>                    
                                </table>
                            </div>
                            <div class="add_button">
                                <input type="submit" value="Add"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        //Get the button
        let mybutton = document.getElementById("btn-back-to-top");

        // When the user scrolls down 20px from the top of the document, show the button
        window.onscroll = function () {
            scrollFunction();
        };

        function scrollFunction() {
            if (
                    document.body.scrollTop > 20 ||
                    document.documentElement.scrollTop > 20
                    ) {
                mybutton.style.display = "block";
            } else {
                mybutton.style.display = "none";
            }
        }
        // When the user clicks on the button, scroll to the top of the document
        mybutton.addEventListener("click", backToTop);

        function backToTop() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>
</html>