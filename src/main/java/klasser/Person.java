package klasser;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String address;
    private String zipcode;
    private String postal;
    private String tlf;
    private String email;
    private String age;

    public Person(String firstname, String surname, String address, String zipcode, String postal, String tlf, String email, String age) {

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
        if(zipcode != null){
            this.zipcode = zipcode;
        }
        if(postal != null){
            this.postal = postal;
        }
        if(tlf != null){
            this.tlf = tlf;
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
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public void setTlf(String tlf) {
        this.tlf = tlf;
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
    public String getZipcode() {
        return zipcode;
    }
    public String getPostal() {
        return postal;
    }
    public String getTlf() {
        return tlf;
    }
    public String getEmail() {
        return email;
    }
    public String getAge() {
        return age;
    }

    public String toString() {
        return firstname +";"+ lastname +";"+ address +";"+ zipcode +";"+ postal +";"+tlf+";"+ email +";"+ age;
    }
}
