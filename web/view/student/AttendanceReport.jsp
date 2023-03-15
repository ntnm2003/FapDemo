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
                            <li>
                                <a href="studentTimetable?email=${sessionScope.account.email}" ><i class="fas fa-table"></i> Timetable</a>
                            </li>
                            <li style="background-color: rgb(201, 41, 198);">
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
                        <div>
                            <div class="main-contain">                
                                <p style="font-size: 20px; margin: 0;">Attendance report for student: ${requestScope.student.name} - ${requestScope.student.id}
                                </p>
                                <marquee>
                                    <p style="color: red; margin: 0">
                                        If the percentage of absent is greater than 20, 
                                        the student will not be able to take the exam for
                                        that semester of that subject
                                    </p>
                                </marquee>
                                <div>
                                    <div class="gcontent">
                                        <form action="studentAttendanceReport" method="post">
                                            <select name="gid" style="padding: 1.5px; border-radius: 6px;">
                                                <option id="groupid" value="-1">-- Select Group --</option>
                                                <c:forEach items="${requestScope.groups}" var="g">                            
                                                    <option "
                                                            <c:if test="${requestScope.group.id eq g.id}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="${g.id}">
                                                        ${g.name} - ${g.subject.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" value="${sessionScope.account.email}" name="email">

                                            <input style="border-radius: 6px; border: 1px solid #ccc;" onload="loading()" type="submit" value="Search"/> 
                                        </form>
                                    </div>  
                                    <div class="group-details">
                                        <table class="table-details">
                                            <tr class="dhead">
                                                <td>No</td>
                                                <td>Date</td>
                                                <td>Slot</td>
                                                <td>Room</td>
                                                <td>Lecturer</td>
                                                <td>Group Name</td>
                                                <td>Attendance Status</td>
                                                <td>Lecturer comment</td>
                                            </tr> 
                                            <% int x = 0; int y = 0; double k;%>
                                            <c:forEach items="${requestScope.sessionsByGid}" var="sesBG">
                                                <tr>
                                                    <td>${sesBG.index}</td>
                                                    <td>${sesBG.date}</td>
                                                    <td>${sesBG.timeslot.name} (${sesBG.timeslot.description})</td>
                                                    <td>${sesBG.room.name}</td>
                                                    <td>${sesBG.lecturer.name}</td>
                                                    <td>${sesBG.group.name}</td>
                                                    <c:forEach items="${requestScope.attendances}" var="att">
                                                        <c:if test="${(att.student.id eq requestScope.student.id) and (att.session.id  eq sesBG.id)}">
                                                            <td>
                                                                <c:if test="${!sesBG.attendated}">
                                                                    <a>- not yet -</a>
                                                                </c:if>
                                                                <c:if test="${sesBG.attendated}">
                                                                    <c:if test="${att.present}">
                                                                    <img src="assets/img/attend.png">
                                                                    </c:if>
                                                                    <c:if test="${!att.present}">
                                                                        <% x++; %>
                                                                        <img src="assets/img/absent.png">
                                                                    </c:if>
                                                                </c:if>
                                                            </td>
                                                            <td>${att.description}</td>
                                                        </c:if>
                                                    </c:forEach>                                   
                                                </tr>
                                                <% y++; %>
                                            </c:forEach>
                                            <tr style="font-weight: bold">                                
                                                <%
                                                    if (y!=0) {
                                                    k = (x*100)/y;                                   
                                                %>

                                                <% if (k>20) { %>
                                                <td style="color: red" colspan="8">
                                                    Absent: <%=k%> % ~ Present: <%=100-k%> %
                                                    (Ineligible for the exam)
                                                </td>
                                                <% } else { %>
                                                <td style="color: green" colspan="8">
                                                    Absent: <%=k%> % ~ Present: <%=100-k%> %
                                                    (Eligible for the exam)
                                                </td>
                                                <% }} %>                                
                                            </tr>
                                        </table>

                                    </div>
                                </div>
                            </div>
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