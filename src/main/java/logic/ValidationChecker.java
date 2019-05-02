package logic;

import exceptions.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.RegTempJobHelper.tempJobsList;

public class ValidationChecker {
    private String invalidInputs = "";
    private int invalidInputsAmount;

    // Begrunne i rapporten:
    // - Alle input felter med strenger er plassert i samme "checkStringFormat" metode med samme regex mønster,
    // - vi har valgt å gjøre det slik ettersom strengene ikke vil kransje systemet
    // - telfnr, postnr og andre int verdier har egne metoder meg individuelle regex mønster da disse brukes for å
    //   bl.a. validere registreringer, i søkemotor for filtrering o.l. for at det ikke skal krasje programmet.
    // - har også separert andre metoder som er avhengige av et spesielt regex mønster (f.eks. e-post)

    public String inputJobseekerCollector(String firstname, String lastname, String address, String zipcode, String postal,
                                          String phoneNo, String email, String age, String experience, String salary,
                                          Object education, Object study, Object workfields) {
        checkMandatoryInputs(address);
        checkMandatoryInputs(experience);
        checkStringFormat(firstname);
        checkStringFormat(lastname);
        checkStringFormat(postal);
        checkZipCode(zipcode);
        checkPhoneNo(phoneNo);
        checkDuplicatePhoneNo(jobseekersList, phoneNo);
        checkEmail(email);
        checkAge(age);
        checkSalary(salary);
        checkValue(education, study);
        checkWorkfields(workfields);

        return invalidInputs;
    }

    public String inputJobAdvertCollector(String contactPerson, String phoneNo, String sector, String companyName,
                                          String industry, String address, String jobTitle, String description,
                                          String duration, String salary, String qualif, String jobType, Object workfields) {


        checkStringFormat(contactPerson);
        checkPhoneNo(phoneNo);
        checkDuplicatePhoneNo(tempJobsList, phoneNo);
        checkStringFormat(sector);
        checkMandatoryInputs(companyName);
        checkMandatoryInputs(industry);
        checkMandatoryInputs(address);
        checkMandatoryInputs(jobTitle);
        checkMandatoryInputs(description);
        checkMandatoryInputs(duration);
        checkMandatoryInputs(qualif);
        checkSalary(salary);
        checkJobType(jobType);
        checkWorkfields(workfields);

        if(invalidInputsAmount > 5){
            invalidInputs = "Du har ikke fylt inn flere obligatoriske felter.";
        }

        return invalidInputs;
    }

    private boolean checkMandatoryInputs(String string){
        try{
            if(checkValidMandatoryInputs(string)){
                return true;
            }
        }
        catch(InputEmptyException e){
            invalidInputs += "Feil i ett eller flere obligatoriske felt : " + e.getMessage() +"\n";
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkValidMandatoryInputs(String string) throws InputEmptyException {
        if(string.isEmpty()){
            throw new InputEmptyException("Obligatoriske felt må fylles ut!");
        }
        return true;
    }

    private boolean checkStringFormat(String name) {
        try {
            if (checkValidStringFormat(name)) {
                return true;
            }
        }
        catch (InvalidStringFormatException e) {
            invalidInputs += (String.format("%s er ugyldig : " + e.getMessage() +"\n", name));
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkValidStringFormat(String string) throws InvalidStringFormatException {
        if (!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-\\.]+", string) && !string.isEmpty()) {
            throw new InvalidStringFormatException("Feil formatering i tekst, kun bokstaver!");
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
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + "\n", zipcode));
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean ckeckValidZipCode(String zipCode) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{4}+",zipCode) && !zipCode.isEmpty()){
            throw new InvalidNumberFormatException("Postnr er formatert feil, skal ha 4 tall!");
        }
        return true;
    }

    private boolean checkPhoneNo(String phoneNo){
        try{
            if(checkPhoneNoFormat(phoneNo)) {
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + "\n", phoneNo));
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkPhoneNoFormat(String phoneNo) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{8}+",phoneNo) && !phoneNo.isEmpty() || phoneNo.startsWith("0")) {
            throw new InvalidNumberFormatException("Feil formatering i telefonnr, må bestå av 8 tall uten mellomrom!");
        }
        return true;
    }

    private boolean checkDuplicatePhoneNo(ArrayList arrayList, String phoneNo){
        try {
            if(checkIfDuplicatePhoneNo(arrayList, phoneNo)) {
                return true;
            }
        }
        catch (DuplicatePhoneNoException e) {
            invalidInputs += String.format("%s er ugyldig : " + e.getCause() +"\n", phoneNo);
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkIfDuplicatePhoneNo(ArrayList arrayList, String phoneNo) throws DuplicatePhoneNoException {
        for (int i = 0; i < arrayList.size()-1; i++) {
            String [] row = arrayList.get(i).toString().split(";");
            System.out.println(row[1] + " " + row[5]);
            if (row[1].equals(phoneNo) || row[5].equals(phoneNo)) {
                throw new DuplicatePhoneNoException("Telefonnr finnes allerede i databasen");
            }
        }
        return true;
    }

    private boolean checkEmail(String email){
        try{
            if(checkEmailFormat(email)){
                return true;
            }
        }
        catch(InvalidEmailFormatException e){
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + "\n", email));
            invalidInputsAmount++;
        }
        return false;
    }

    private static boolean checkEmailFormat(String email) throws InvalidEmailFormatException {
        if(!Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",email) && !email.isEmpty()){
            throw new InvalidEmailFormatException("Feil i epost format, riktig format er f.eks.: kari@test.no");
        }
        return true;
    }

    private boolean checkAge(String age){
        try{
            if(checkAgeFormat(age)){
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + "\n", age));
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkAgeFormat(String age) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{2}+",age) && !age.isEmpty() || age.startsWith("0")){
            throw new InvalidNumberFormatException("Alder har feil format, kun positive tall!");
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
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + " \n",salary));
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkSalaryFormat(String salary) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9\\p{Space}]{0,7}+",salary)){
            throw new InvalidNumberFormatException("Feil format på lønn, kun positive tall (maks 7 sifret)!");
        }
        return true;
    }

    private boolean checkValue(Object education, Object study){
        try{
            if(checkIfValueSelected(education,study)){
                return true;
            }
        }
        catch(NoValueSelectedException e){
            invalidInputs += "Obs! Ingen verdier valgt : " + e.getMessage() + "\n";
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkIfValueSelected(Object education, Object study) throws NoValueSelectedException {
        if(education.equals("not selected") || study.equals("not selected")){
            throw new NoValueSelectedException("Utdanning/studieretning må velges!");
        }
        return true;
    }

    private boolean checkWorkfields(Object workfields){
        try{
            if(checkIfWorkfieldsSelected(workfields)){
                return true;
            }
        }
        catch(NoValueSelectedException e){
            invalidInputs += "Feil i arbeidsområde : . " + e.getMessage() + "\n";
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkIfWorkfieldsSelected(Object workfields) throws NoValueSelectedException{
        if(workfields.equals("")){
            throw new NoValueSelectedException("Minst ett arbeidsområde må velges!");
        }
        return true;
    }

    private boolean checkJobType(String jobType){
        try{
            if(checkIfJobTypeSelected(jobType)){
                return true;
            }
        }
        catch(NoValueSelectedException e){
            invalidInputs += "Feil i stillingstype : " + e.getMessage() + "\n";
            invalidInputsAmount++;
        }
        return false;
    }

    private boolean checkIfJobTypeSelected(String jobType) throws NoValueSelectedException {
        System.out.println(jobType);
        if(jobType.equals("Arbeidstid ikke valgt")){
            throw new NoValueSelectedException("Stillingstype må velges!");
        }
        return true;
    }
}
