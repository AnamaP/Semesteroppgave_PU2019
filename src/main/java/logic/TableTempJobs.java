package logic;

import javafx.beans.property.SimpleStringProperty;
import classes.Company;

public class TableTempJobs {
    private final SimpleStringProperty contactPerson; //0
    private final SimpleStringProperty phoneNo; //1
    private final SimpleStringProperty sector; //2
    private final SimpleStringProperty companyName; //3
    private final SimpleStringProperty address; //4
    private final SimpleStringProperty industry; //5
    private final SimpleStringProperty jobTitle; //6
    private final SimpleStringProperty jobType; //7
    private final SimpleStringProperty workfields; // 11,12,13,14
    private final SimpleStringProperty status; //15

    // en toVikariat metode som koverterer all data her til en Company
    public TableTempJobs(Company company){
        this.contactPerson = new SimpleStringProperty(company.getContactPerson());
        this.phoneNo = new SimpleStringProperty(company.getPhoneNo());
        this.sector = new SimpleStringProperty(company.getSector());
        this.companyName = new SimpleStringProperty(company.getCompanyName());
        this.address = new SimpleStringProperty(company.getAddress());
        this.industry = new SimpleStringProperty(company.getIndustry());
        this.jobTitle = new SimpleStringProperty(company.getTempJob().getJobTitle());
        this.jobType = new SimpleStringProperty(company.getTempJob().getJobType());
        this.workfields = new SimpleStringProperty(company.getTempJob().workfieldsToString());
        this.status = new SimpleStringProperty(company.getTempJob().getStatus());
    }

    public String getContactPerson() {
        return contactPerson.get();
    }

    public String getSector() {
        return sector.get();
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getIndustry() {
        return industry.get();
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public String getJobType() {
        return jobType.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson.set(contactPerson);
    }

    public void setSector(String sector) {
        this.sector.set(sector);
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setIndustry(String industry) {
        this.industry.set(industry);
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }

    public void setJobType(String jobType) {
        this.jobType.set(jobType);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty contactPersonProperty() {
        return contactPerson;
    }
    public String getPhoneNo() {
        return phoneNo.get();
    }
    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
    }
    public SimpleStringProperty sectorProperty() {
        return sector;
    }
    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }
    public SimpleStringProperty addressProperty() {return address;}
    public SimpleStringProperty industryProperty() {
        return industry;
    }
    public SimpleStringProperty jobTitleProperty() {
        return jobTitle;
    }
    public SimpleStringProperty jobTypeProperty(){ return jobType;}
    public String getWorkfields() {
        return workfields.get();
    }
    public SimpleStringProperty workfieldsProperty() {
        return workfields;
    }
    public void setWorkfields(String workfields) {
        this.workfields.set(workfields);
    }
    public SimpleStringProperty statusProperty() { return status; }
}
