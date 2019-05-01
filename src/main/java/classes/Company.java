package classes;

import java.io.Serializable;

public class Company implements Serializable {
    private String contactPerson;
    private String phoneNo;
    private String sector;
    private String companyName;
    private String address;
    private String industry;
    private TempJob tempJob;

    public Company(String contactPerson, String phoneNo, String sector, String companyName,
                   String address, String industry, TempJob tempJob) {
        this.contactPerson = contactPerson;
        this.phoneNo = phoneNo;
        this.sector = sector;
        this.companyName = companyName;
        this.address = address;
        this.industry = industry;
        this.tempJob = tempJob;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public String getSector() {
        return sector;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getAddress() {
        return address;
    }
    public String getIndustry() {
        return industry;
    }
    public TempJob getTempJob() {
        return tempJob;
    }

    public String toString(){
        String out = "";
        out += contactPerson +";"+ phoneNo +";"+ sector +";"+ companyName +";"+ address +";"+ industry +";"+ tempJob.toString();
        return out;
    }


}
