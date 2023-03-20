
package controller.auth.student;

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
        StudentDBContext stu= new StudentDBContext();
        List<Student> s= stu.list();
        Account account = db.get(email, password);
        if(account == null || !stu(s, account.getEmail()))
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
       private boolean stu(List<Student> s, String str){
        
        for (Student st: s){
            if (st.getEmail().equalsIgnoreCase(str))
                return true;
        }
        return false;
    }

}
