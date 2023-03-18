/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth.lecturer;

import dal.AccountDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/lecturer/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email").trim().toLowerCase();
        String password = request.getParameter("password");
        AccountDBContext db = new AccountDBContext();
        Account account = db.get(email, password);
        if (account == null) {
            request.getSession().setAttribute("account", account);
            response.sendRedirect("home");
//            response.getWriter().println("login failed!"); --bo
//            request.setAttribute("noti", "* Incorrect email or password");
//            request.getRequestDispatcher("view/lecturer/auth/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("account", account);
            response.sendRedirect("home");
        }
    }
}
