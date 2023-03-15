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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <style>
        table tr td{
            padding: 10px 3.5px;
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
                        <a href="home" class="nav-link-pc"><img style="width: 3em;" src="assets/img/fpt-logo.png">FPT University
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
                                <a target="_blank" href="https://apps.apple.com/app/id1527723314" ><img height="20px"  src="assets/favicon/appStore.png"> App Store</a>
                            </li>
                            <li>
                                <a target="_blank" href="https://play.google.com/store/apps/details?id=com.fuct" ><img height="20px" src="assets/favicon/googlePlay.png"> Google Play</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-10 right">
                        <div>
                            <marquee>
                                <p style="color: red">
                                    If the percentage of absent is greater than 20, 
                                    the student will not be able to take the exam for
                                    that semester of that subject
                                </p>
                            </marquee>
                            <div class="group">
                                <div class="gcontent">
                                    <form action="group" method="post">

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
                                        <input style="border-radius: 6px; border: 1px solid #ccc;" type="reset" value="Re-select"/>
                                    </form>
                                </div>                        
                            </div>
                            <div class="group-details">
                                <c:if test="${requestScope.sessionsByGidAndLeid ne null}">
                                    <table class="table-details">
                                        <tr class="dhead">
                                            <td>STUDENT</td>
                                            <td style="white-space: pre-wrap;">GROUP NAME</td>
                                            <c:forEach items="${requestScope.sessionsByGidAndLeid}" var="sesByGAL">
                                                <td>Slot ${sesByGAL.index}<br>${sesByGAL.date}</td>
                                                </c:forEach>
                                            <td>Present</td>
                                        </tr> 
                                        <c:forEach items="${requestScope.students}" var="stu">
                                            <tr>
                                                <td class="fullname">${stu.name}</td>
                                                <td>
                                                    ${requestScope.group.name}
                                                </td>
                                                <% int x = 0; int y = 0; double k;%>
                                                <c:forEach items="${requestScope.sessionsByGidAndLeid}" var="sesByGAL">
                                                    <td>
                                                        <c:forEach items="${requestScope.attendances}" var="att">
                                                            <c:if test="${(att.student.id eq stu.id) and (att.session.id  eq sesByGAL.id)}">

                                                                <c:if test="${!sesByGAL.attendated}">
                                                                    <a style="color: black">- not yet -</a>
                                                                </c:if>
                                                                <c:if test="${sesByGAL.attendated}">
                                                                    <c:if test="${att.present}">
                                                                        <!--<a style="color: #5cb85c">present</a>-->
                                                                        <img src="assets/img/attend.png">
                                                                    </c:if>
                                                                    <c:if test="${!att.present}">
                                                                        <% x++; %>
                                                                        <img src="assets/img/absent.png">
                                                                    </c:if>
                                                                </c:if>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <% y++; %>
                                                </c:forEach>

                                                <%
                                                k = (x*100)/y;
                                                %>

                                                <% if (k>20) { %>
                                                <td style="color: red">
                                                    <%=100-k%> %
                                                </td>
                                                <% } else { %>
                                                <td style="color: green">
                                                    <%=100-k%> %
                                                </td>
                                                <% } %>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:if>
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