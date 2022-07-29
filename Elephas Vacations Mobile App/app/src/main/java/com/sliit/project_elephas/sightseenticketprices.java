package com.sliit.project_elephas;

public class sightseenticketprices {

    private String sightNo;
    private String sightName;
    private String tpriceChild;
    private String tpriceAdult;

    public sightseenticketprices() {
    }

    public sightseenticketprices(String sid, String sname, String scprice, String saprice) {
    }

    public String getSightNo() {
        return sightNo;
    }

    public void setSightNo(String sightNo) {
        this.sightNo = sightNo;
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }

    public String getTpriceChild() {
        return tpriceChild;
    }

    public void setTpriceChild(String tpriceChild) {
        this.tpriceChild = tpriceChild;
    }

    public String getTpriceAdult() {
        return tpriceAdult;
    }

    public void setTpriceAdult(String tpriceAdult) {
        this.tpriceAdult = tpriceAdult;
    }
}
