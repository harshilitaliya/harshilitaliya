package com.example.contactmanagerapp;

public class Users {
    private String name;
    private String email;
    private String password;
    private String number;

    public Users() {
    }

    public Users(String name, String email, String password, String uniqueid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = uniqueid;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
