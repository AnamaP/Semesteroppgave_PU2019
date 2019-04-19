package Exceptions;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.util.regex.Pattern;

public class ValidationChecker {
    private String invalidInputs = "";

    // TODO : Må man legge inn regex for whitespace / ikke tillate whitespace??

    public String inputCollector(String firstname, String lastname, String address, String zipcode, String postal,
                                 String phoneNmbr, String email, String age, String experience, String reference,
                                 String salary){

        checkName(firstname, lastname);
        checkAddress(address);
        checkZipCode(zipcode);
        checkPostal(postal);
        checkPhoneNmbr(phoneNmbr);
        checkEmail(email);
        checkAge(age);
        checkLength(experience, reference);
        checkSalary(salary);
        //checkValueSelected(education,study);
       // checkWorkfields(workfields);

        return invalidInputs;
    }

    private boolean checkValidName(String firstname, String lastname) throws InvalidNameFormatException {
        if((!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-]+",firstname) || firstname.isEmpty()) ||
        (!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-]+",lastname) || lastname.isEmpty())){
            throw new InvalidNameFormatException("Feil i Fornavn / Etternavn, tillatt: Aa-åÅ og bindestrek(-)");
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
            invalidInputs += (String.format("Feil i enten fornavn/etternavn, tillatt: Aa-åÅ og bindestrek(-) \n"));
        }
        return false;
    }

    private boolean checkValidAddress(String address)throws InvalidAddressFormatException {
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ0-9_\\p{Space}\\-]+",address) || address.isEmpty()){
            throw new InvalidAddressFormatException("Feil i adresse, tillatt: Aa-åÅ, tall 0-9");
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
            invalidInputs += (String.format("%s : er en ugyldig adresse, tillatt: Aa-åÅ og tall 0-9 \n", address));
        }
        return false;
    }

    private boolean ckeckValidZipCode(String zipcode) throws InvalidZipCodeFormatException {
        if(!Pattern.matches("[0-9]+",zipcode) || zipcode.length() != 4 || zipcode.isEmpty()){
            throw new InvalidZipCodeFormatException("Feil i postnr, må bestå av 4 tall mellom 0-9");
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
            invalidInputs += (String.format("%s : er et ugyldig postnr, må bestå av 4 tall mellom 0-9 \n", zipcode));
        }
        return false;
    }

    private boolean checkValidPostal(String postal) throws InvalidPostalFormatException {
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}]+",postal) || postal.isEmpty()){
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
            invalidInputs += (String.format("%s : er et ugyldig poststed, tillatt: Aa-åÅ og mellomrom \n", postal));
        }
        return false;
    }

    private boolean checkValidPhoneNmbr(String phoneNmbr) throws InvalidPhoneNmbrException {
        if(!Pattern.matches("[0-9_\\s]+",phoneNmbr) || phoneNmbr.length() != 8 || phoneNmbr.isEmpty()){
            throw new InvalidPhoneNmbrException("Feil i tlfnr, må bestå av 8 tall mellom 0-9 tillatt!");
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
            invalidInputs += (String.format("%s : er et ugyldig tlfnr, må bestå av 8 tall mellom 0-9 \n", phoneNmbr));
        }
        return false;
    }


    private static boolean checkValidEmail(String email) throws InvalidEmailFormatException {
        if(!Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",email) || email.isEmpty()){
            throw new InvalidEmailFormatException("Feil i epost formt, riktig format er f.eks.: kari@test.no");
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
            invalidInputs += (String.format("%s : er en ugyldig epost, riktig format er f.eks.: kari@test.no \n", email));
        }
        return false;
    }

    private boolean checkValidLength(String experience, String reference) throws InvalidTextIfNullException {
        if(experience.isEmpty() || reference.isEmpty()){
            throw new InvalidTextIfNullException("Erfaring/referanse må fylles ut!");
        }
        return true;
    }

    private boolean checkLength(String experience, String reference){
        try{
            if(checkValidLength(experience,reference)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Erfaring/referanse må fylles ut! \n";
        }
        return false;
    }

    private boolean checkValidAge(String age) throws InvalidAgeFormatException {
        if(!Pattern.matches("[1-9]{2}+",age) || age.isEmpty()){
            throw new InvalidAgeFormatException("Ugyldig alder, kun tall (ingen mellomrom)");
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
            invalidInputs += (String.format("%s : er en ugyldig alder, kun tall (ingen mellomrom) \n", age));
        }
        return false;
    }

    private boolean checkValidSalary(String salary) throws InvalidSalaryFormatException {
        if(!Pattern.matches("[0-9_\\p{Space}]{0,7}+",salary)){
            throw new InvalidSalaryFormatException("Feil i lønnskrav, tillatt : tall 0-9 (maks input er i mio)");
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
            invalidInputs += (String.format("%s : er et ugyldig lønnskrav, tillatt: tall 0-9 (maks input er i mio)\n",salary));
        }
        return false;
    }
    /*
    public boolean checkIfValueSelected(Object education, Object study) throws InvalidValueSelectedIsNullException{
        if(education.equals("Velg høyeste utdanning") || study.equals("Velg studieretning")){
            throw new InvalidValueSelectedIsNullException("Utdanning/studieretning er ikke valgt, vennligst velg en");
        }
        return true;
    }

    public boolean checkValueSelected(Object education, Object study){
        try{
            if(checkIfValueSelected(education,study)){
                return true;
            }
        }
        catch(InvalidValueSelectedIsNullException e){
            invalidInputs += "Utdanning/studieretning er ikke valgt, vennligst velg en";
        }
        return false;
    }*/

    // TODO : checkWorkfield metoden
    // TODO : metoder for registrering av vikariat sjekk
    // TODO : Metode som sjekker for om stillingstype er valgt(heltid/deltid)

}
