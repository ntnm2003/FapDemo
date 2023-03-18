/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Khangnekk
 */
public class Account {
    private String email;
    private String password;
    private String username;
    private ArrayList<Lecturer> lectures = new ArrayList<>();
    private ArrayList<Role> roles = new ArrayList<>();
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<Lecturer> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecturer> lectures) {
        this.lectures = lectures;
    }
    
    
    
    
}
