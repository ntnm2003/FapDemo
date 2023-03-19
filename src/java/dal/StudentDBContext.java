/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Major;
import model.Student;

public class StudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int studentid) {
        MajorDBContext majDB = new MajorDBContext();
        ArrayList<Major> majors = majDB.list();

        String sql = "SELECT * FROM Student WHERE stuid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, studentid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();

                int id = rs.getInt("stuid");
                String name = rs.getString("stuname");
                String email = rs.getString("stuemail");
                boolean gender = rs.getBoolean("stugender");
                Date dob = rs.getDate("studob`");
                String phone = rs.getString("stuphone");
                String address = rs.getString("stuaddress");
                int mid = rs.getInt("mid");

                s.setId(id);
                s.setName(name);
                s.setEmail(email);
                s.setGender(gender);
                s.setDob(dob);
                s.setPhone(phone);
                s.setAddress(address);

                s.setMid(majors.stream().filter(m -> m.getId() == mid).findAny().get());
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        MajorDBContext majDB = new MajorDBContext();
        ArrayList<Major> majors = majDB.list();

        String sql = "SELECT * FROM Student";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();

                int id = rs.getInt("stuid");
                String name = rs.getString("stuname");
                String email = rs.getString("stuemail");
                boolean gender = rs.getBoolean("stugender");
                Date dob = rs.getDate("studob`");
                String phone = rs.getString("stuphone");
                String address = rs.getString("stuaddress");
                int mid = rs.getInt("mid");

                s.setId(id);
                s.setName(name);
                s.setEmail(email);
                s.setGender(gender);
                s.setDob(dob);
                s.setPhone(phone);
                s.setAddress(address);

                s.setMid(majors.stream().filter(m -> m.getId() == mid).findAny().get());
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return students;
    }
    
    public int getIdByEmail(String stuemail){
        try {
            String sql = "SELECT stuid FROM Student WHERE stuemail = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuemail);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("stuid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
