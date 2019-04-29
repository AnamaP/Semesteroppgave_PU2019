package classes;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String address;
    private String zipCode;
    private String postal;
    private String phoneNo;
    private String email;
    private String age;

    public Person(String firstname, String surname, String address, String zipCode, String postal, String phoneNo, String email, String age) {

        // Feilhåndtering - sjekk heller her om variabelen er null og kaste en feilmelding
        if(firstname != null){
            this.firstname = firstname;
        }
        if(surname != null){
            this.lastname = surname;
        }
        if(address != null){
            this.address = address;
        }
        if(zipCode != null){
            this.zipCode = zipCode;
        }
        if(postal != null){
            this.postal = postal;
        }
        if(phoneNo != null){
            this.phoneNo = phoneNo;
        }
        if(email != null){
            this.email = email;
        }
        this.age = age;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getAddress() {
        return address;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getPostal() {
        return postal;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public String getEmail() {
        return email;
    }
    public String getAge() {
        return age;
    }

    public String toString() {
        return firstname +";"+ lastname +";"+ address +";"+ zipCode +";"+ postal +";"+ phoneNo +";"+ email +";"+ age;
    }
}
