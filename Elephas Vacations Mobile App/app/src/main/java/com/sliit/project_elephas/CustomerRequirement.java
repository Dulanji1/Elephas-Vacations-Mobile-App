package com.sliit.project_elephas;

public class CustomerRequirement {

    private int id;
    private String passPortNo;
    private String name;
    private String nationality;
    private String noOfPeople;
    private String noOfDays;
    private String arrivalDate;
    private String departureDate;
    private String starCategory;
    private String remark;


    //constructor
    public CustomerRequirement() {
        this.id = 0;
        this.name = null;
        this.nationality = null;
        this.passPortNo = null;
        this.noOfPeople = null;
        this.noOfDays = null;
        this.arrivalDate = null;
        this.departureDate = null;
        this.starCategory = null;
        this.remark = null;
    }

    //constructor with parameters
    public CustomerRequirement(int id,String name,String nationality,String passPortNo, String noOfPeople, String noOfDays, String arrivalDate, String departureDate, String starCategory, String remark) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.passPortNo = passPortNo;
        this.noOfPeople = noOfPeople;
        this.noOfDays = noOfDays;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.starCategory = starCategory;
        this.remark = remark;
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassPortNo() {
        return passPortNo;
    }

    public void setPassPortNo(String passPortNo) {
        this.passPortNo = passPortNo;
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

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getStarCategory() {
        return starCategory;
    }

    public void setStarCategory(String starCategory) {
        this.starCategory = starCategory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



}
