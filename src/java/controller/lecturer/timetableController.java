/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;


import controller.auth.lecturer.BaseAuthorizationController;
import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.*;
import util.DateTimeHelper;

/**
 *
 * @author Khangnekk
 */
public class timetableController extends BaseAuthorizationController {


    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp,Account account) throws ServletException, IOException {
        String email = req.getParameter("email");        
        LecturerDBContext lecturerDB = new LecturerDBContext();
        int leid = lecturerDB.getIdByEmail(email);
        String raw_from = req.getParameter("from");
        java.sql.Date from = null;
        java.sql.Date to = null;
        if(raw_from ==null || raw_from.length() ==0)
        {
            Date today = new Date();
            int todayOfWeek = DateTimeHelper.getDayofWeek(today);
            Date e_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
            Date e_to = DateTimeHelper.addDays(today, 8-todayOfWeek);
            from = DateTimeHelper.toDateSql(e_from);
            to = DateTimeHelper.toDateSql(e_to);
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
            java.util.Date e_from = DateTimeHelper.toDateUtil(from);
            java.util.Date e_to = DateTimeHelper.addDays(e_from, 6);
            from = DateTimeHelper.toDateSql(e_from);
            to = DateTimeHelper.toDateSql(e_to);
        }
        req.setAttribute("leid", leid);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("dates", DateTimeHelper.getDateList(from, to));
        
        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();
        req.setAttribute("slots", slots);
        
        GroupDBContext groupDB = new GroupDBContext();
        ArrayList<Group> groups = groupDB.listGroupByLeid(leid);
        req.setAttribute("groups", groups);
        
        SubjectDBContext subDB = new SubjectDBContext();
        ArrayList<Subject> subjects = subDB.listSubjectByLeid(leid);
        req.setAttribute("subjects", subjects);
        
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.filter(leid, from, to);
        req.setAttribute("sessions", sessions);
        
        LecturerDBContext lecDB = new LecturerDBContext();
        Lecturer lecturers = lecDB.get(leid);
        req.setAttribute("lecturers", lecturers);

        
        req.getRequestDispatcher("view/lecturer/timeTable.jsp").forward(req, resp);
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
