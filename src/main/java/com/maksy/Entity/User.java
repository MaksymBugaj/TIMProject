package com.maksy.Entity;

public class User {

    private int id;
    private String firstname;
    private String surname;
    private String PESEL;
    private String sex;
    private String email;
    private String phone;
    private String password;
    private Steing type; 

    public User(int id, String firstname, String surname, String PESEL, String sex, String email, String phone, String password, String type) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.PESEL = PESEL;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.type = type;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPESEL() {
        return this.PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Steing getType() {
        return this.type;
    }

    public void setType(Steing type) {
        this.type = type;
    }
}
