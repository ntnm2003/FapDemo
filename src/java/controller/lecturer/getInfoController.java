/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import controller.auth.lecturer.BaseAuthorizationController;
import dal.GroupDBContext;
import dal.LecturerDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Group;
import model.Session;

/**
 *
 * @author Khangnekk
 */
public class getInfoController extends BaseAuthorizationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String email = req.getParameter("emailInfo");
        int gid = Integer.parseInt(req.getParameter("gidInfo"));
        int seid = Integer.parseInt(req.getParameter("seidInfo"));
        req.setAttribute("emailInfo", email);
        req.setAttribute("gidInfo", gid);
        req.setAttribute("seidInfo", seid);
        resp.sendRedirect("takeAttendance?seid=" + seid);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        LecturerDBContext lecturerDB = new LecturerDBContext();
        GroupDBContext groupDB = new GroupDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        String email = req.getParameter("email");
        int lid = lecturerDB.getIdByEmail(email);
        int gid = Integer.parseInt(req.getParameter("gid"));

        String emailInfo = email;
        int gidInfo = gid;
        int seInfo = Integer.parseInt(req.getParameter("seid"));

        
        Group group = groupDB.get(gid);
        ArrayList<Group> groups = groupDB.listGroupByLeid(lid);
        ArrayList<Session> sessionsByGidAndLeid = sesDB.getSessionByGidAndLeid(lid, gid);

        req.setAttribute("groups", groups);
        req.setAttribute("sessionsByGidAndLeid", sessionsByGidAndLeid);
        req.setAttribute("group", group);
        req.getSession().setAttribute("email", email);
        req.getSession().setAttribute("seid", req.getParameter("seid"));

        req.setAttribute("emailInfo", emailInfo);
        req.setAttribute("gidInfo", gidInfo);
        req.setAttribute("seInfo", seInfo);
        req.getRequestDispatcher("add/getInfo.jsp").forward(req, resp);
    }

}
