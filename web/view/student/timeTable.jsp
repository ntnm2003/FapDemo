<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="DateTimeHelper" class="util.DateTimeHelper"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FAP University Academic Portal</title>
        <!-- link favicon logo -->
        <link rel="icon" href="assets/favicon/fu-favicon.jpg" />
        <!-- link bootstrap -->
        <link rel="stylesheet" href="assets/css/bootstrap/bootstrap.min.css"/>
        <!-- link Style.css -->
        <link rel="stylesheet" href="assets/css/myStyle/Style.css">
        <!-- link font-awesome -->
        <link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.2-web/css/all.min.css">
    </head>
    <body>
        <button type="button" class="btn btn-danger btn-floating btn-lg" id="btn-back-to-top">
            <i class="fas fa-arrow-up"></i> Back to top
        </button>
        <div class="header">
            <nav class="nav-main">
                <!-- Navigation for PC -->
                <nav class="nav-pc" data-spy="affix">
                    <div>
                        <a href="student_home" class="nav-link-pc"><img style="width: 3em;" src="assets/img/fpt-logo.png">FPT University
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
                                <a href="studentLogout"><i style="color: black;" class="fa-solid fa-right-from-bracket"></i> Logout</a>
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
                            <li style="background-color: rgb(201, 41, 198)">
                                <a href="studentTimetable?email=${sessionScope.account.email}" ><i class="fas fa-table"></i> Timetable</a>
                            </li>
                            <li>
                                <a href="studentAttendanceReport?email=${sessionScope.account.email}&gid=-1" ><i class="fa-solid fa-file"></i> View Report Attendance</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-solid fa-file"></i> Reports</a>
                            </li>
                            <li>
                                <a href="" ><i class="fa-sharp fa-solid fa-file-contract"></i> Registration/Application</a>
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
                                <a target="_blank" href="https://apps.apple.com/app/id1527723314" ><img height="20px"  src="assets/favicon/appStore.png"> App Store</a>
                            </li>
                            <li>
                                <a target="_blank" href="https://play.google.com/store/apps/details?id=com.fuct" ><img height="20px" src="assets/favicon/googlePlay.png"> Google Play</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-10 right">
                        <div >
                            <div class="description">
                                <div style="text-align: center">
                                    <form action="studentTimetable" method="POST">
                                        <input type="hidden" name="email" value="${sessionScope.account.email}"/>
                                        From: <input type="date" name="from" value="${requestScope.from}"/>
                                        <input type="submit" value="View"/> 
                                    </form>
                                </div>
                            </div>
                            <div class="table">
                                <table>
                                    <tr class="htable">
                                        <td></td>
                                        <c:forEach items="${requestScope.dates}" var="d">
                                            <td>${DateTimeHelper.getDayNameofWeek(d)}<br>${d}</td>
                                            </c:forEach>
                                    </tr>
                                    <c:forEach items="${requestScope.slots}" var="slot">
                                        <tr>
                                            <td class="timeslot"><a class="tname">${slot.name}: </a><br><a class="tdes">(${slot.description})</a></td>
                                                <c:forEach items="${requestScope.dates}" var="d">
                                                <td style="text-align: center">
                                                    <c:forEach items="${requestScope.sessions}" var="ses">
                                                        <c:if test="${DateTimeHelper.compare(ses.date,d) eq 0 and (ses.timeslot.id eq slot.id)}">
                                                            ${ses.room.name} <br><a>[ ${ses.lecturer.name} ]</a><br>
                                                            <a href="#">${ses.group.name}-${ses.group.subject.name}</a>
                                                            <br/>
                                                            <c:if test="${ses.attendated}">
                                                                <a class="attended">(Status: Attend)</a>
                                                            </c:if>
                                                            <c:if test="${!ses.attendated}">
                                                                <a class="not_yet">(Status: Not Yet)</a>
                                                            </c:if>

                                                        </c:if>

                                                    </c:forEach>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
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