/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.auth.student.BaseAuthorizationStudentController;
import dal.AttendanceDBContext;
import dal.GroupDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;

public class studentAttendanceReport extends BaseAuthorizationStudentController {


    void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        AttendanceDBContext attDB = new AttendanceDBContext();
         ArrayList<Attendance> attendances = attDB.list();
        StudentDBContext stDB = new StudentDBContext();
        GroupDBContext groupDB = new GroupDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        int gid = Integer.parseInt(req.getParameter("gid"));
        Group group = groupDB.get(gid);     
        String email = req.getParameter("email");
        int stuid = stDB.getIdByEmail(email);
        Student student = stDB.get(stuid);
        ArrayList<Group> groups = groupDB.listGroupByStudentId(stuid);
        ArrayList<Session> sessionsByGid = sesDB.listSessionByGid(gid);
        req.setAttribute("sessionsByGid", sessionsByGid);
        req.setAttribute("group", group);
        req.setAttribute("attendances", attendances);
        req.setAttribute("student", student);
        req.setAttribute("groups", groups);
        req.setAttribute("email", email);
        req.getRequestDispatcher("view/student/AttendanceReport.jsp").forward(req, resp);
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
}
