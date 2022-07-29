package com.sliit.project_elephas.model;

public class Driver {
    int ID;
    int passport;
    double driver;
    double guide;
    double total_in_usd;



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public double getDriver() {
        return driver;
    }

    public void setDriver(double driver) {
        this.driver = driver;
    }

    public double getGuide() {
        return guide;
    }

    public void setGuide(double guide) {
        this.guide = guide;
    }

    public double getTotal_in_usd(double driver, double guide) {
        double exchangeRate = 187;

        total_in_usd = (driver + guide)/exchangeRate;

        return total_in_usd;
    }

    public void setTotal_in_usd(double total_in_usd) {
        this.total_in_usd = total_in_usd;
    }
}
