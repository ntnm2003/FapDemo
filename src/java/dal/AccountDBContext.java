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
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author sonnt
 */
public class AccountDBContext extends DBContext<Account> {

    public Account get(String email, String password) {
        try {
            String sql = "SELECT a.email, a.username,\n"
                    + "r.roid,r.roname,\n"
                    + "f.fid,f.fname,f.furl\n"
                    + "FROM Account a\n"
                    + "LEFT JOIN Role_Account ra ON a.email = ra.email\n"
                    + "LEFT JOIN [Role] r ON r.roid = ra.roid\n"
                    + "LEFT JOIN Role_Feature rf ON rf.roid = r.roid\n"
                    + "LEFT JOIN Feature f ON f.fid = rf.fid\n"
                    + "WHERE a.email = ? AND a.password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account account = null;
            Role currentRole = new Role();
            currentRole.setId(-1);
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setEmail(rs.getString("email"));
                    account.setUsername(rs.getString("username"));
                }
                int rid = rs.getInt("roid");
                if(rid!=0)
                {
                    if(rid!= currentRole.getId())
                    {
                        currentRole = new Role();
                        currentRole.setId(rs.getInt("roid"));
                        currentRole.setName(rs.getString("roname"));
                        account.getRoles().add(currentRole);
                    }
                }
                
                int fid = rs.getInt("fid");
                if(fid!=0)
                {
                    Feature f = new Feature();
                    f.setId(rs.getInt("fid"));
                    f.setName(rs.getString("fname"));
                    f.setUrl(rs.getString("furl"));
                    currentRole.getFeatures().add(f);
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
