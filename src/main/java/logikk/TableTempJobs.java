package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Arbeidsgiver;

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

    // en toVikariat metode som koverterer all data her til en Arbeidsgiver
    public TableTempJobs(Arbeidsgiver arbeidsgiver){
        this.contactPerson = new SimpleStringProperty(arbeidsgiver.getKontaktperson());
        this.phoneNo = new SimpleStringProperty(arbeidsgiver.getTlf());
        this.sector = new SimpleStringProperty(arbeidsgiver.getSektor());
        this.companyName = new SimpleStringProperty(arbeidsgiver.getFirmanavn());
        this.address = new SimpleStringProperty(arbeidsgiver.getAdresse());
        this.industry = new SimpleStringProperty(arbeidsgiver.getBransje());
        this.jobTitle = new SimpleStringProperty(arbeidsgiver.getVikariat().getTittel());
        this.jobType = new SimpleStringProperty(arbeidsgiver.getVikariat().getStillingstype());
        this.workfields = new SimpleStringProperty(arbeidsgiver.getVikariat().kategorierToString());
        this.status = new SimpleStringProperty(arbeidsgiver.getVikariat().getStatus());
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
