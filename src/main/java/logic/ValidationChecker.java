package logic;

import exceptions.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.RegTempJobHelper.tempJobsList;

public class ValidationChecker {
    private String invalidInputs = "";

    // Begrunne i rapporten:
    // - Alle input felter med strenger er plassert i samme "checkString" metode med samme regex mønster,
    // - vi har valgt å gjøre det slik ettersom strengene ikke vil kransje systemet
    // - telfnr, postnr og andre int verdier har egne metoder meg individuelle regex mønster da disse brukes for å
    //   bl.a. validere registreringer, i søkemotor for filtrering o.l. for at det ikke skal krasje programmet.
    // - har også separert andre metoder som er avhengige av et spesielt regex mønster (f.eks. e-post)

    public String inputJobseekerCollector(String firstname, String lastname, String address, String zipcode, String postal,
                                          String phoneNo, String email, String age, String experience, String salary,
                                          Object education, Object study, Boolean sales, Boolean admin,
                                          Boolean it, Boolean economy) {

        checkString(firstname);
        checkString(lastname);
        checkString(postal);
        checkZipCode(zipcode);//
        checkValidString(address);
        checkValidString(experience);
        checkPhoneNo(phoneNo);
        checkDuplicatePhoneNo(jobseekersList, phoneNo);
        checkEmail(email);
        checkAge(age);
        checkSalary(salary);
        checkValueSelected(education, study);
        checkWorkfields(sales, admin, it, economy);

        return invalidInputs;
    }

    public String inputJobAdvertCollector(String contactPerson, String phoneNo, String sector, String companyName,
                                          String industry, String address, String jobTitle, String description,
                                          String duration, String salary, String qualif, Boolean sales, Boolean admin,
                                          Boolean it, Boolean economy, Boolean fullTime, Boolean partTime) {
        checkString(contactPerson);
        checkPhoneNo(phoneNo);
        checkDuplicatePhoneNo(tempJobsList, phoneNo);
        checkString(sector);
        checkValidString(companyName);
        checkValidString(industry);
        checkValidString(address);
        checkValidString(jobTitle);
        checkValidString(description);
        checkValidString(duration);
        checkValidString(qualif);
        checkSalary(salary);
        checkWorkfields(sales, admin, it, economy);
        checkJobTypeSelected(fullTime, partTime);

        return invalidInputs;
    }

    private boolean checkStringFormat(String string) throws InvalidStringFormatException {
        if (!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-]+", string) || string.isEmpty()) {
            throw new InvalidStringFormatException("Feil i ett av tekstfeltene");
        }
        return true;
    }

    private boolean checkString(String name) {
        try {
            if (checkStringFormat(name)) {
                return true;
            }
        }
        catch (InvalidStringFormatException e) {
            invalidInputs += (String.format("%s er ugyldig, kun bokstaver Aa-åÅ tillatt \n", name));
        }
        return false;
    }

    private boolean ckeckValidZipCode(String zipCode) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]+",zipCode) || zipCode.length() != 4 || zipCode.isEmpty()){
            throw new InvalidNumberFormatException("Feil i postnr");
        }
        return true;
    }

    private boolean checkZipCode(String zipcode){
        try{
            if(ckeckValidZipCode(zipcode)) {
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er et ugyldig postnr, må bestå av 4 tall mellom 0-9 \n", zipcode));
        }
        return false;
    }

    private boolean checkPhoneNo(String phoneNo){
        try{
            if(checkValidPhoneNo(phoneNo)) {
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er et ugyldig tlfnr, må bestå av positive tall og uten mellomrom \n", phoneNo));

        }
        return false;
    }

    private boolean checkValidPhoneNo(String phoneNo) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{8}+",phoneNo) || phoneNo.isEmpty() || phoneNo.startsWith("0")){
            throw new InvalidNumberFormatException("Feil i tlfnr!");
        }
        return true;
    }

    private boolean checkDuplicatePhoneNo(ArrayList arrayList, String phoneNo){
        try {
            if(checkIfDuplicatePhoneNo(arrayList, phoneNo)) {
                return true;
            }
        }
        catch (InvalidDuplicatePhoneNoException e) {
            invalidInputs += "Telefonnummeret er registrert fra før.\n";
        }
        return false;
    }

    private boolean checkIfDuplicatePhoneNo(ArrayList arrayList, String phoneNo) throws InvalidDuplicatePhoneNoException {
        for (int i = 0; i < arrayList.size() -1; i++) {
            String [] row = arrayList.get(i).toString().split(";");
            System.out.println(row[1] + " " + row[5]);
            if (row[1].equals(phoneNo) || row[5].equals(phoneNo)) {
                throw new InvalidDuplicatePhoneNoException("Duplikat telefonnr!");
            }
        }
        return true;
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

    private boolean checkIfValidString(String string) throws InvalidTextIfNullException {
        if(!Pattern.matches("^(?=.{1,200}$).*", string) || string.isEmpty()) {
            throw new InvalidTextIfNullException("Feil: Enten tomme obligatoriske felt eller oversteget 200 ord");
        }
        return true;
    }

    private boolean checkValidString(String string){
        try{
            if(checkIfValidString(string)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Feil i ett eller flere obligatoriske feil \n";
        }
        return false;
    }

    private boolean checkValidAge(String age) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{2}+",age) || age.isEmpty() || age.startsWith("0")){
            throw new InvalidNumberFormatException("Ugyldig alder");
        }
        return true;
    }

    private boolean checkAge(String age){
        try{
            if(checkValidAge(age)){
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er en ugyldig alder, kun positive tall(ingen mellomrom)\n", age));
        }
        return false;
    }

    private boolean checkSalaryFormat(String salary) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9\\p{Space}]{0,7}+",salary)){
            throw new InvalidNumberFormatException("Feil i lønn");
        }
        return true;
    }

    private boolean checkSalary(String salary){
        try{
            if(checkSalaryFormat(salary)){
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er en ugyldig lønn, tillatt: positive tall (maks: mio)\n",salary));
        }
        return false;
    }

    private boolean checkIfValueSelected(Object education, Object study) throws InvalidValueSelectedIsNullException{
        if(education.equals("null") && study.equals("null")){
            throw new InvalidValueSelectedIsNullException("En eller flere av valgalternativene er ikke valgt!");
        }
        return true;
    }

    private boolean checkValueSelected(Object education, Object study){
        try{
            if(checkIfValueSelected(education,study)){
                return true;
            }
        }
        catch(InvalidValueSelectedIsNullException e){
            invalidInputs += "Utdanning/studieretning er ikke valgt, vennligst velg en \n";
        }
        return false;
    }

    private boolean checkIfWorkfieldsSelected(Boolean sales, Boolean admin, Boolean it, Boolean economy) throws NullPointerException{
        if(!sales && !admin && !it && !economy){
            throw new NullPointerException("Ingen kategorier i 'arbeidsområde' er valgt");
        }
        return true;
    }

    private boolean checkWorkfields(Boolean sales, Boolean admin, Boolean it, Boolean economy){
        try{
            if(checkIfWorkfieldsSelected(sales,admin,it,economy)){
                return true;
            }
        }
        catch(NullPointerException e){
            invalidInputs += "Ingen kategorier i 'arbeidsområde' er valgt \n";
        }
        return false;
    }

    private boolean checkIfJobTypeSelected(Boolean partTime, Boolean fullTime) throws InvalidValueSelectedIsNullException{
        if(!partTime && !fullTime){
            throw new InvalidValueSelectedIsNullException("Stillingstype er ikke valgt");
        }
        return true;
    }

    private boolean checkJobTypeSelected(Boolean partTime, Boolean fullTime){
        try{
            if(checkIfJobTypeSelected(partTime,fullTime)){
                return true;
            }
        }
        catch(InvalidValueSelectedIsNullException e){
            invalidInputs += "Stillingstype er ikke valgt, velg enten heltid eller deltid \n";
        }
        return false;
    }

}
