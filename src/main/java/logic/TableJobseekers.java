package logic;

import javafx.beans.property.SimpleStringProperty;
import classes.Jobseeker;

public class TableJobseekers {

    private final SimpleStringProperty firstname;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty address;
    private final SimpleStringProperty zipcode;
    private final SimpleStringProperty postal;
    private final SimpleStringProperty phoneNo;
    private final SimpleStringProperty email;
    private final SimpleStringProperty age;
    private final SimpleStringProperty salary;
    private final SimpleStringProperty education;
    private final SimpleStringProperty study;
    private final SimpleStringProperty experience;
    private final SimpleStringProperty reference;
    private final SimpleStringProperty workfields;
    private final SimpleStringProperty status;

    public TableJobseekers(Jobseeker jobseeker) {
        this.firstname = new SimpleStringProperty(jobseeker.getFirstname());
        this.lastname = new SimpleStringProperty(jobseeker.getLastname());
        this.address = new SimpleStringProperty(jobseeker.getAddress());
        this.zipcode = new SimpleStringProperty(jobseeker.getZipCode());
        this.postal = new SimpleStringProperty(jobseeker.getPostal());
        this.phoneNo = new SimpleStringProperty(jobseeker.getPhoneNo());
        this.email = new SimpleStringProperty(jobseeker.getEmail());
        this.age = new SimpleStringProperty(jobseeker.getAge());
        this.salary = new SimpleStringProperty(jobseeker.getSalary());
        this.education = new SimpleStringProperty(jobseeker.getCv().getEducation());
        this.study = new SimpleStringProperty(jobseeker.getCv().getStudy());
        this.experience = new SimpleStringProperty(jobseeker.getCv().getExperience());
        this.reference = new SimpleStringProperty(jobseeker.getCv().getReference());
        this.workfields = new SimpleStringProperty(jobseeker.getCv().getWorkfields());
        this.status = new SimpleStringProperty(jobseeker.getStatus());
    }

    public String getFirstname() {
        return firstname.get();
    }
    public String getLastname() {
        return lastname.get();
    }
    public String getZipCode() {
        return zipcode.get();
    }
    public String getPostal() {
        return postal.get();
    }
    public String getPhoneNo() {
        return phoneNo.get();
    }
    public String getWorkfields() {
        return workfields.get();
    }
    public void setWorkfields(String workfields) {
        this.workfields.set(workfields);
    }
    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }
    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }
    public SimpleStringProperty addressProperty() {
        return address;
    }
    public SimpleStringProperty zipcodeProperty() {
        return zipcode;
    }
    public SimpleStringProperty postalProperty() {
        return postal;
    }
    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }
    public SimpleStringProperty emailProperty() {
        return email;
    }
    public SimpleStringProperty ageProperty() {
        return age;
    }
    public SimpleStringProperty salaryProperty() {
        return salary;
    }
    public SimpleStringProperty educationProperty() {
        return education;
    }
    public SimpleStringProperty studyProperty() {
        return study;
    }
    public SimpleStringProperty experienceProperty() {
        return experience;
    }
    public SimpleStringProperty referenceProperty() {
        return reference;
    }
    public SimpleStringProperty workfieldsProperty() {
        return workfields;
    }
    public SimpleStringProperty statusProperty(){return status;}
}