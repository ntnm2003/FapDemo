<%-- 
    Document   : getInfo
    Created on : Oct 27, 2022, 8:33:30 PM
    Author     : Khangnekk
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <div class="main-content">
                            <h3>Select group and select session</h3>
                            <div class="getGroupAndSlot">
                                <form action="getInfo?gid=-1">
                                    <div class="input-info">
                                        <select id="groupid" name="gid" style="padding: 1.5px; border-radius: 6px;">
                                            <option  value="-1">-- Select Group --</option>
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
                                        <select id="gid_element" name="seid" style="text-align: center;">
                                            <option value="-1">-- Select slot --</option>
                                            <c:forEach items="${requestScope.sessionsByGidAndLeid}" var="SBI">
                                                <option id="gid_element" value="${SBI.id}" <c:if test="${SBI.id eq seid}">
                                                        selected="selected"
                                                    </c:if>>
                                                    Slot ${SBI.index} / ${SBI.date}                        
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" value="${sessionScope.email}" name="email">
                                        <input id="submit" type="submit" value="" style="border-radius: 6px">
                                    </div>
                                </form>
                            </div>
                            <div>
                                <form action="getInfo" method="post">
                                    <input type="hidden" value="${requestScope.emailInfo}" name="emailInfo">
                                    <input type="hidden" value="${requestScope.gidInfo}" name="gidInfo">
                                    <input type="hidden" value="${requestScope.seInfo}" name="seidInfo">
                                    <input id="submit2" type="submit" value="Take Attendance" style="border-radius: 6px">
                                </form>
                            </div>
                            <p id="content" style="margin-top: 5px; color: red"></p>
                            <p id="content2" style="margin-top: 5px; color: red"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    var x1 = document.getElementById("groupid").value;
    var x2 = document.getElementById("gid_element").value;
    const noti = document.getElementById("content");
    const noti2 = document.getElementById("content2");
    if (x1 <= 0) {
        document.getElementById("gid_element").style.display = 'none';
        document.getElementById("submit2").style.display = 'none';
        document.getElementById("submit").value = 'Search group';
        noti.innerHTML = "*Select the group you want to take attendance";
        noti2.innerHTML = "STATUS: Not ready to take attendance (1/3)";
    } else {
        document.getElementById("gid_element").style.display = 'initial';
        document.getElementById("submit2").style.display = 'none';
        document.getElementById("submit").value = 'Search session';
        noti.innerHTML = "*Now you have to choose the session you want to take attendance";
        noti2.innerHTML = "STATUS: Not ready to take attendance (2/3)";
    }
    if (x1 > 0 && x2 > 0) {
        noti.innerHTML = "STATUS: Ready to take attendance (3/3)";
        noti.style.color = 'green';
        noti2.innerHTML = "Click the button \"Take Attendance\" above to take attendance now";
        noti2.style.color = 'green';
        document.getElementById("submit2").style.display = 'initial';
    }
</script>
</html>