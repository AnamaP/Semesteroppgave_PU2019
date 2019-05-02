package classes;

import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = 1;

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
        return contactPerson +";"+ phoneNo +";"+ sector +";"+ companyName +";"
                + address +";"+ industry +";"+ tempJob.toString();
    }
}
