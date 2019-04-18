package Exceptions;

import java.util.regex.Pattern;

public class ValidationChecker {
    private String ugyldigeVerdier = "";

    // TODO : Må man legge inn regex for whitespace / ikke tillate whitespace??

    public String inputCollector(String firstname, String lastname, String address, String zipcode, String postal,
                                 String phoneNmbr, String email, String age, String experience, String reference, String salary){

        checkName(firstname, lastname);
        checkAddress(address);
        checkZipCode(zipcode);
        checkPostal(postal);
        checkPhoneNmbr(phoneNmbr);
        checkEmail(email);
        checkAge(age);
        checkLength(experience, reference);
        checkSalary(salary);

        return ugyldigeVerdier;
    }

    private boolean checkValidName(String firstname, String lastname) throws InvalidNameFormatException {
        if((!Pattern.matches("[a-zæøåA-ZÆØÅ ]+",firstname) || firstname.isEmpty()) ||
        (!Pattern.matches("[a-zæøåA-ZÆØÅ ]+",lastname) || lastname.isEmpty())){
            throw new InvalidNameFormatException("Feil i Fornavn / Etternavn (må bestå av bokstaver A-Å), vennligst se over!");
        }
        return true;
    }

    private boolean checkName(String firstname, String lastname){
        try{
            if(checkValidName(firstname, lastname)){
                return true;
            }
        }
        catch(InvalidNameFormatException e){
            ugyldigeVerdier += "Feil i Fornavn / Etternavn (må bestå av bokstaver A-Å), vennligst se over! \n";
        }
        return false;
    }

    private boolean checkValidAddress(String address)throws InvalidAddressFormatException {
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ0-9]+",address) || address.isEmpty()){
            throw new InvalidAddressFormatException("Feil i adresse (kun bokstaver og tall tillatt");
        }
        return true;
    }

    private boolean checkAddress(String address){
        try{
            if(checkValidAddress(address)){
                return true;
            }
        }
        catch(InvalidAddressFormatException e){
            ugyldigeVerdier += "Feil i adresse (kun bokstaver og tall tillatt! \n";
        }
        return false;
    }

    private boolean ckeckValidZipCode(String zipcode) throws InvalidZipCodeFormatException {
        if(!Pattern.matches("[0-9]+",zipcode) || zipcode.length() != 4 || zipcode.isEmpty()){
            throw new InvalidZipCodeFormatException("Feil i postnr (kun tall mellom 0-9");
        }
        return true;
    }

    private boolean checkZipCode(String zipcode){
        try{
            if(ckeckValidZipCode(zipcode)) {
                return true;
            }
        }
        catch(InvalidZipCodeFormatException e){
            ugyldigeVerdier += "Feil i postnr (kun tall mellom 0-9! \n";
        }
        return false;
    }

    private boolean checkValidPostal(String postal) throws InvalidPostalFormatException {
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ]+",postal) || postal.isEmpty()){
            throw new InvalidPostalFormatException("Feil i poststed (kun bokstaver (A-Å)");
        }
        return true;
    }

    private boolean checkPostal(String postal){
        try{
            if(checkValidPostal(postal)) {
                return true;
            }
        }
        catch(InvalidPostalFormatException e){
            ugyldigeVerdier += "Feil i poststed (kun bokstaver A-Å tillatt)! \n";
        }
        return false;
    }

    private boolean checkValidPhoneNmbr(String phoneNmbr) throws InvalidPhoneNmbrException {
        if(!Pattern.matches("[0-9]+",phoneNmbr) || phoneNmbr.length() != 8 || phoneNmbr.isEmpty()){
            throw new InvalidPhoneNmbrException("Feil i tlfnr, kun tall mellom 0-9 tillat!");
        }
        return true;
    }

    private boolean checkPhoneNmbr(String phoneNmbr){
        try{
            if(checkValidPhoneNmbr(phoneNmbr)){
                return true;
            }
        }
        catch(InvalidPhoneNmbrException e){
            ugyldigeVerdier += "Feil i tlfnr, kun tall mellom 0-9 tillat! \n";
        }
        return false;
    }


    private static boolean checkValidEmail(String email) throws InvalidEmailFormatException {
        if(!Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",email) || email.isEmpty()){
            throw new InvalidEmailFormatException("Feil i epost format, riktig ex. kari@test.no");
        }
        return true;
    }

    private boolean checkEmail(String email){
        try{
            if(checkValidEmail(email)){
                return true;
            }
        }
        catch(InvalidEmailFormatException e){
            ugyldigeVerdier += "Feil i epost format, riktig ex. kari@test.no \n";
        }
        return false;
    }

    private boolean checkValidLength(String experience, String reference) throws InvalidLengthException {
        if((!Pattern.matches("[a-zæøåA-ZÆØÅ0-9. ]{5,40}+",experience) || experience.isEmpty()) ||
        (!Pattern.matches("[a-zæøåA-ZÆØÅ0-9. ]{0,30}+",reference))){
            throw new InvalidLengthException("Erfaring og Refereanse kan inneholde maks 20 tegn");
        }
        return true;
    }

    private boolean checkLength(String experience, String reference){
        try{
            if(checkValidLength(experience,reference)){
                return true;
            }
        }
        catch(InvalidLengthException e){
            ugyldigeVerdier += "Maks 20 tegn tillatt i erfaring og referanse";
        }
        return false;
    }

    private boolean checkValidAge(String age) throws InvalidAgeFormatException {
        if(!Pattern.matches("[1-9]{2}+",age) || age.isEmpty()){
            throw new InvalidAgeFormatException("Ugyldig alder");
        }
        return true;
    }

    private boolean checkAge(String age){
        try{
            if(checkValidAge(age)){
                return true;
            }
        }
        catch(InvalidAgeFormatException e){
            ugyldigeVerdier += "Ugyldig alder \n";
        }
        return false;
    }

    private boolean checkValidSalary(String salary) throws InvalidSalaryFormatException {
        if(!Pattern.matches("[0-9]{0,7}+",salary)){
            throw new InvalidSalaryFormatException("Lønnskrav må bestå av tall mellom 0 og 9 (maks input er i mio)");
        }
        return true;
    }

    private boolean checkSalary(String salary){
        try{
            if(checkValidSalary(salary)){
                return true;
            }
        }
        catch(InvalidSalaryFormatException e){
            ugyldigeVerdier += "Lønnskrav må bestå av tall mellom 0 og 9 (maks input er i mio)";
        }
        return false;
    }

    // TODO : metode som sjekker for om utdannelse og studieretning er valgt
    // TODO : Metode som sjekker for om stillingstype er valg (heltid/deltid)
    // TODO : metoder for registrering av vikariat sjekk

}
