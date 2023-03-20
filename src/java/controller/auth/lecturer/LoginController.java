/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth.lecturer;

import dal.AccountDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Student;

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
        StudentDBContext stu= new StudentDBContext();
        List<Student> s= stu.list();
        Account account = db.get(email, password);
        if (account == null || stu(s, account.getEmail())) {

            //           response.getWriter().println("login failed!"); --bo
            request.setAttribute("noti", "* Incorrect email or password");
            request.getRequestDispatcher("view/lecturer/auth/login.jsp").forward(request, response);
            request.getSession().setAttribute("account", account);
        } else {
            request.getSession().setAttribute("account", account);
            response.sendRedirect("home");
        }
    }
    private boolean stu(List<Student> s, String str){
        
        for (Student st: s){
            if (st.getEmail().equalsIgnoreCase(str))
                return true;
        }
        return false;
    }
}
