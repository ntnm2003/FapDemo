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
import model.Lecturer;
import model.TimeSlot;


public class TimeSlotDBContext extends DBContext<TimeSlot> {

    @Override
    public ArrayList<TimeSlot> list() {
        ArrayList<TimeSlot> timeslots = new ArrayList<>();
        String sql = "SELECT * FROM TimeSlot";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TimeSlot t = new TimeSlot();
                int id = rs.getInt("tid");
                String name = rs.getString("tname");
                String description = rs.getString("description");
                t.setId(id);
                t.setName(name);
                t.setDescription(description);
                timeslots.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return timeslots;
    }

    @Override
    public void insert(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(TimeSlot model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TimeSlot get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<TimeSlot> listSlotByLeid(int leid_input) {
        ArrayList<TimeSlot> timeslots = new ArrayList<>();
        String sql = "SELECT ts.tid FROM [Session] s\n"
                + "INNER JOIN TimeSlot ts ON ts.tid = s.tid\n"
                + "WHERE s.leid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid_input);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TimeSlot t = new TimeSlot();
                int id = rs.getInt("tid");
                String name = rs.getString("tname");
                String description = rs.getString("description");
                t.setId(id);
                t.setName(name);
                t.setDescription(description);
                timeslots.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return timeslots;
    }

}
