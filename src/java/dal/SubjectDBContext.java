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
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author Khangnekk
 */
public class SubjectDBContext extends DBContext<Subject> {

    @Override
    public void insert(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Subject> list() {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                int id = rs.getInt("subid");
                String name = rs.getString("subname");
                s.setId(id);
                s.setName(name);
                subjects.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return subjects;
    }

    public ArrayList<Subject> listSubjectByLeid(int leid_input) {
        ArrayList<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.subid FROM Lecturer l\n"
                + "LEFT JOIN [Group] g ON g.leid = l.leid\n"
                + "INNER JOIN [Subject] s ON s.subid = g.subid\n"
                + "WHERE l.leid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid_input);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                int id = rs.getInt("subid");
                String name = rs.getString("subname");
                s.setId(id);
                s.setName(name);
                subjects.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return subjects;
    }

}
