/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;

public class LecturerDBContext extends DBContext<Lecturer> {

    @Override
    public Lecturer get(int lid) {
        try {
            String sql = "SELECT leid,lename FROM Lecturer WHERE leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("leid"));
                l.setName(rs.getString("lename"));
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lecturer getLeidByGid(int gid_input) {
        try {
            String sql = "SELECT l.leid FROM Lecturer l \n"
                    + "INNER JOIN [Group] g ON g.leid = l.leid\n"
                    + "WHERE g.gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid_input);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("leid"));
                l.setName(rs.getString("lename"));
                return l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdByEmail(String leemail) {
        try {
            String sql = "SELECT leid FROM Lecturer WHERE leemail = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, leemail);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("leid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void insert(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecturer model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Lecturer> list() {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM Lecturer";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lecturer t = new Lecturer();
                int id = rs.getInt("leid");
                String name = rs.getString("lename");
                String email = rs.getString("leemail");
                boolean gender = rs.getBoolean("legender");
                Date dob = rs.getDate("ledob");
                String phone = rs.getString("lephone");
                String address = rs.getString("leaddress");
                t.setId(id);
                t.setName(name);
                t.setEmail(email);
                t.setGender(gender);
                t.setDob(dob);
                t.setPhone(phone);
                t.setAddress(address);
                lecturers.add(t);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lecturers;
    }

}
