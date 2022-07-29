package com.sliit.project_elephas;

public class Admin {

    private int id;
    private String name;
    private String nic;
    private String passportno;
    private String email;
    private String phone;
    private String password;

    //constructor
    public Admin() {
        this.id = 0;
        this.name = null;
        this.nic = null;
        this.passportno = null;
        this.email = null;
        this.phone = null;
        this.password = null;
    }

    //constructor with parameters
    public Admin(int id, String name, String nic, String passportno, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.nic = nic;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
