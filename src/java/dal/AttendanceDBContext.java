/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Session;
import model.Student;

public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> list() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        StudentDBContext stuDB = new StudentDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Student> students = stuDB.list();
        ArrayList<Session> sessions = sesDB.list();
        String sql = "SELECT * FROM Attendance";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                int seid = rs.getInt("seid");
                int stuid = rs.getInt("stuid");
                boolean present = rs.getBoolean("present");
                String des = rs.getString("description");
                a.setPresent(present);
                a.setDescription(des);

                a.setStudent(students.stream().filter(t -> t.getId() == stuid).findAny().get());
                a.setSession(sessions.stream().filter(t -> t.getId() == seid).findAny().get());
                attendances.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attendances;
    }

    public ArrayList<Attendance> getAttendancesBySeid(int seid_input) {
        ArrayList<Attendance> attendances = new ArrayList<>();
        StudentDBContext stuDB = new StudentDBContext();
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Student> students = stuDB.list();
        ArrayList<Session> sessions = sesDB.list();
        
        String sql = "SELECT * FROM Attendance WHERE seid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, seid_input);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();

                int seid = rs.getInt("seid");
                int stuid = rs.getInt("stuid");
                boolean present = rs.getBoolean("present");
                String des = rs.getString("description");
                a.setPresent(present);
                a.setDescription(des);

                a.setStudent(students.stream().filter(st -> st.getId() == stuid).findAny().get());
                a.setSession(sessions.stream().filter(se -> se.getId() == seid).findAny().get());
                attendances.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return attendances;
    }

}
