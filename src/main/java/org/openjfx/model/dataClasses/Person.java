package org.openjfx.model.dataClasses;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1;

    private String firstname;
    private String lastname;
    private String address;
    private String zipCode;
    private String postal;
    private String phoneNo;
    private String email;
    private String age;

    public Person(String firstname, String surname, String address, String zipCode,
                  String postal, String phoneNo, String email, String age) {
        this.firstname = firstname;
        this.lastname = surname;
        this.address = address;
        this.zipCode = zipCode;
        this.postal = postal;
        this.phoneNo = phoneNo;
        this.email = email;
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
        return firstname +";"+ lastname +";"+ address +";"+ zipCode
                +";"+ postal +";"+ phoneNo +";"+ email +";"+ age;
    }
}
