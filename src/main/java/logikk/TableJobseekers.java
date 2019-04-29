package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Jobbsoker;

public class TableJobseekers {

    private final SimpleStringProperty firstname; //0
    private final SimpleStringProperty lastname;// 1
    private final SimpleStringProperty address;//2
    private final SimpleStringProperty zipcode;//3
    private final SimpleStringProperty postal;//4
    private final SimpleStringProperty phoneNo;//5
    private final SimpleStringProperty email;//6
    private final SimpleStringProperty age;//7
    private final SimpleStringProperty salary;//8
    private final SimpleStringProperty education;//9
    private final SimpleStringProperty study;//10
    private final SimpleStringProperty experience; //11
    private final SimpleStringProperty reference; //12
    private final SimpleStringProperty workfields;//13, 14, 15
    private final SimpleStringProperty status; //16

    public TableJobseekers(Jobbsoker jobbsoker) {
        this.firstname = new SimpleStringProperty(jobbsoker.getFirstname());
        this.lastname = new SimpleStringProperty(jobbsoker.getLastname());
        this.address = new SimpleStringProperty(jobbsoker.getAddress());
        this.zipcode = new SimpleStringProperty(jobbsoker.getZipcode());
        this.postal = new SimpleStringProperty(jobbsoker.getPostal());
        this.phoneNo = new SimpleStringProperty(jobbsoker.getTlf());
        this.email = new SimpleStringProperty(jobbsoker.getEmail());
        this.age = new SimpleStringProperty(jobbsoker.getAge());
        this.salary = new SimpleStringProperty(jobbsoker.getSalary());
        this.education = new SimpleStringProperty(jobbsoker.getCv().getEducation());
        this.study = new SimpleStringProperty(jobbsoker.getCv().getStudy());
        this.experience = new SimpleStringProperty(jobbsoker.getCv().getExperience());
        this.reference = new SimpleStringProperty(jobbsoker.getCv().getReference());
        this.workfields = new SimpleStringProperty(jobbsoker.getCv().categoriesToString());
        this.status = new SimpleStringProperty(jobbsoker.getStatus());
    }

    public String getFirstname() {
        return firstname.get();
    }
    public String getLastname() {
        return lastname.get();
    }
    public String getZipcode() {
        return zipcode.get();
    }
    public String getPostal() {
        return postal.get();
    }
    public String getPhoneNo() {
        return phoneNo.get();
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
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