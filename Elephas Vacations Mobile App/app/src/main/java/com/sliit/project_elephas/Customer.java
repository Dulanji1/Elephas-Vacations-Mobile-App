package com.sliit.project_elephas;

public class Customer {

    private int id;
    private String name;
    private String nationality;
    private String passportno;
    private String email;
    private String phone;
    private String password;

    //default constructor
    public Customer() {
        id=0;
        name=null;
        nationality=null;
        passportno=null;
        email=null;
        phone=null;
        password=null;
    }

    //constructor with parameters
    public Customer(int id, String name, String nationality, String passportno, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.passportno = passportno;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportno() {
        return passportno;
    }

    public void setPassportno(String passportno) {
        this.passportno = passportno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
