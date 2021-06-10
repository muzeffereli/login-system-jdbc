package com.company.entity;

public class Contact {
    private int idcontact_details;
    private String mainphonenumber;
    private String secondphonenumber;
    private String thirdphonenumber;
    private String firstemail;
    private String secondemail;
    private String residenceplace;
    private int userid;

    public Contact(int idcontact_details, String mainphonenumber, String secondphonenumber, String thirdphonenumber, String firstemail, String secondemail, String residenceplace, int userid) {
        this.idcontact_details = idcontact_details;
        this.mainphonenumber = mainphonenumber;
        this.secondphonenumber = secondphonenumber;
        this.thirdphonenumber = thirdphonenumber;
        this.firstemail = firstemail;
        this.secondemail = secondemail;
        this.residenceplace = residenceplace;
        this.userid = userid;
    }

    public Contact() {
    }

    public int getIdcontact_details() {
        return idcontact_details;
    }

    public void setIdcontact_details(int idcontact_details) {
        this.idcontact_details = idcontact_details;
    }

    public String getMainphonenumber() {
        return mainphonenumber;
    }

    public void setMainphonenumber(String mainphonenumber) {
        this.mainphonenumber = mainphonenumber;
    }

    public String getSecondphonenumber() {
        return secondphonenumber;
    }

    public void setSecondphonenumber(String secondphonenumber) {
        this.secondphonenumber = secondphonenumber;
    }

    public String getThirdphonenumber() {
        return thirdphonenumber;
    }

    public void setThirdphonenumber(String thirdphonenumber) {
        this.thirdphonenumber = thirdphonenumber;
    }

    public String getFirstemail() {
        return firstemail;
    }

    public void setFirstemail(String firstemail) {
        this.firstemail = firstemail;
    }

    public String getSecondemail() {
        return secondemail;
    }

    public void setSecondemail(String secondemail) {
        this.secondemail = secondemail;
    }

    public String getResidenceplace() {
        return residenceplace;
    }

    public void setResidenceplace(String residenceplace) {
        this.residenceplace = residenceplace;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
