/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import controller.auth.lecturer.BaseAuthorizationController;
import dal.AttendanceDBContext;
import dal.GroupDBContext;
import dal.LecturerDBContext;
import dal.SessionDBContext;
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

public class groupController extends BaseAuthorizationController {

    void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        LecturerDBContext lecturerDB = new LecturerDBContext();
        AttendanceDBContext attDB = new AttendanceDBContext();
        GroupDBContext groupDB = new GroupDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        Group group;

        String email = req.getParameter("email");
        int gid = Integer.parseInt(req.getParameter("gid"));
        int lid = lecturerDB.getIdByEmail(email);

        ArrayList<Attendance> attendances = attDB.list();
        ArrayList<Student> students = groupDB.listStudentBygid(gid);
        ArrayList<Session> sessionsByGidAndLeid = sesDB.getSessionByGidAndLeid(lid, gid);
        ArrayList<Group> groups = groupDB.listGroupByLeid(lid);
        group = groupDB.get(gid);
        req.setAttribute("sessionsByGidAndLeid", sessionsByGidAndLeid);
        req.setAttribute("group", group);
        req.setAttribute("groups", groups);
        req.setAttribute("students", students);
        req.setAttribute("attendances", attendances);
        req.setAttribute("email", email);
        req.getRequestDispatcher("view/lecturer/group.jsp").forward(req, resp);
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
