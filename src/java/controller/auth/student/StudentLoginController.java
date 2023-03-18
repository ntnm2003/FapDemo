/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth.student;

import dal.AccountDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Khangnekk
 */
public class StudentLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/student/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String email = request.getParameter("email").trim().toLowerCase();
        String password = request.getParameter("password");
        AccountDBContext db = new AccountDBContext();
        Account account = db.get(email, password);
        if(account == null)
        {
//            response.getWriter().println("login failed!");
            request.setAttribute("noti", "* Incorrect email or password");
            request.getRequestDispatcher("view/student/auth/login.jsp").forward(request, response);
        }
        else
        {
            request.getSession().setAttribute("account", account);
            response.sendRedirect("student_home");
        }
    }

}
