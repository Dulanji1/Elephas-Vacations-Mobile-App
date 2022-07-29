package com.sliit.project_elephas.model;

public class Invoice {
    int Passport;
    double transport;
    double driver;
    double additional;
    double totoalCost;

    public int getPassport() {
        return Passport;
    }

    public void setPassport(int passport) {
        Passport = passport;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public double getDriver() {
        return driver;
    }

    public void setDriver(double driver) {
        this.driver = driver;
    }

    public double getAdditional() {
        return additional;
    }

    public void setAdditional(double additional) {
        this.additional = additional;
    }

    public double getTotoalCost(double transport,double driver) {

        totoalCost = transport + driver;

        return totoalCost;
    }

    public void setTotoalCost(double totoalCost) {
        this.totoalCost = totoalCost;
    }
}
