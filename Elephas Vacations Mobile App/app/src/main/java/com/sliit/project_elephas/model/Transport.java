package com.sliit.project_elephas.model;

public class Transport {

    int ID;
    int passport;
    double grossMileage;
    double extraMileage;
    double totalMileage;
    double chargePerKm;
    double totalUsd;


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

    public double getGrossMileage() {
        return grossMileage;
    }

    public void setGrossMileage(double grossMileage) {
        this.grossMileage = grossMileage;
    }

    public double getExtraMileage() {
        return extraMileage;
    }

    public void setExtraMileage(double extraMileage) {
        this.extraMileage = extraMileage;
    }

    public double getChargePerKm() {
        return chargePerKm;
    }

    public void setChargePerKm(double chargePerKm) {
        this.chargePerKm = chargePerKm;
    }

    public double getTotalMileage(double grossMileage, double extraMileage) {

        this.grossMileage = grossMileage;
        this.extraMileage = extraMileage;

        totalMileage = grossMileage + extraMileage;
        return totalMileage;
    }

    /*public double getTotalMileage() {
        totalMileage = grossMileage + extraMileage;
        return totalMileage;
    }
*/
    public void setTotalMileage(double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public double getTotalUsd(double totalMileage,double chargePerKm) {

        double exchangeRate = 187;

        totalUsd = (totalMileage*chargePerKm)/exchangeRate;
        //totalUsd = totalMileage*chargePerKm;
        return totalUsd;
    }


    public void setTotalUsd(double totalUsd) {
        this.totalUsd = totalUsd;
    }
}
