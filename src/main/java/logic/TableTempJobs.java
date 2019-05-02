package logic;

import javafx.beans.property.SimpleStringProperty;
import classes.Company;

public class TableTempJobs {
    private final SimpleStringProperty contactPerson;
    private final SimpleStringProperty phoneNo;
    private final SimpleStringProperty sector;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty industry;
    private final SimpleStringProperty jobTitle;
    private final SimpleStringProperty jobType;
    private final SimpleStringProperty workfields;
    private final SimpleStringProperty status;

    public TableTempJobs(Company company){
        this.contactPerson = new SimpleStringProperty(company.getContactPerson());
        this.phoneNo = new SimpleStringProperty(company.getPhoneNo());
        this.sector = new SimpleStringProperty(company.getSector());
        this.companyName = new SimpleStringProperty(company.getCompanyName());
        this.address = new SimpleStringProperty(company.getAddress());
        this.industry = new SimpleStringProperty(company.getIndustry());
        this.jobTitle = new SimpleStringProperty(company.getTempJob().getJobTitle());
        this.jobType = new SimpleStringProperty(company.getTempJob().getJobType());
        this.workfields = new SimpleStringProperty(company.getTempJob().getWorkfields());
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

    public SimpleStringProperty contactPersonProperty() {
        return contactPerson;
    }
    public String getPhoneNo() {
        return phoneNo.get();
    }
    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
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
