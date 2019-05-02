package org.openjfx.model.logic;

import org.openjfx.model.exceptions.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;

/**
 * Feilhåndtering for registrering/redigering av nye jobbsøkere og stillingsutlysninger :
 *  To public metoder som "samler inn" og kaller på private valideringssjekker. Dersom feil i input kastes
 *  egendefinerte exception og bruker får passende feilmelding.
 *
 *  Input felter som ikke har noen "påvirkning" på programmet blir kun sjekket for at de ikke står tomme.
 *  Der det kun skal stå strenger har vi en metode som validerer dette. Felt som er avhengige av et spesielt regex
 *  mønster (f.eks epost, tlfnr, checkBox..) er det laget egne metodesjekker for.
 *  Tlf nr benyttes for eksempel som id for å få tak i objekter ifm skriv til fil og ansettelse, derfor viktig at
 *  det er unikt og på et spesifikt format.
 */

public class ValidationChecker {
    private String invalidInputs = "";
    private int invalidInputsAmount;


    /**
     * Samler inn valideringsjekker for Jobbsøkere.
     */
    public String inputJobseekerCollector(String firstname, String lastname, String address, String zipCode, String postal,
                                          String phoneNo, String email, String age, String experience, String salary,
                                          Object education, Object study, Object workfields) {

        checkMandatoryInputs(address);
        checkMandatoryInputs(experience);
        checkStringFormat(firstname);
        checkStringFormat(lastname);
        checkStringFormat(postal);
        checkZipCode(zipCode);
        checkPhoneNo(phoneNo);
        checkDuplicatePhoneNo(jobseekersList, phoneNo);
        checkEmail(email);
        checkAge(age);
        checkSalary(salary);
        checkValue(education, study);
        checkWorkfields(workfields);

        // For å unngå lang liste med feilmld har vi en if sjekk som overskriv alle
        // feilmld dersom det overstiger 5 stk med en felles feilmld.
        if(invalidInputsAmount > 5){
            invalidInputs = "Flere obligatoriske felt inneholder feil tegn eller er ikke fylt ut!";
        }

        return invalidInputs;
    }

    /**
     * Samler inn valideringsjekker for bedrift/stillingsutlysninger.
     */
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

        // For å unngå lang liste med feilmld har vi en if sjekk som overskriv alle
        // feilmld dersom det overstiger 5 stk med en felles feilmld.
        if(invalidInputsAmount > 5){
            invalidInputs = "Flere obligatoriske felt inneholder feil tegn eller er ikke fylt ut!";
        }

        return invalidInputs;
    }

    private boolean checkMandatoryInputs(String text){
        try{
            if(checkValidMandatoryInputs(text)){
                return true;
            }
        }
        catch(InputEmptyException e){
            invalidInputs += "Feil i ett eller flere obligatoriske felt : " + e.getMessage() +"\n";
            invalidInputsAmount++;
        }
        return false;
    }

    /**
     * Metode som sjekker at diverse input felt ikke står tomme
     */
    private boolean checkValidMandatoryInputs(String text) throws InputEmptyException {
        if(text.isEmpty()){
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

    /**
     * Regex validering for strenger (noen tegn tillates)
     */
    private boolean checkValidStringFormat(String string) throws InvalidStringFormatException {
        if (!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-\\.]+", string) || string.isEmpty()) {
            throw new InvalidStringFormatException("Feil formatering i tekst, kun bokstaver!");
        }
        return true;
    }

    private boolean checkZipCode(String zipCode){
        try{
            if(ckeckValidZipCode(zipCode)) {
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er ugyldig : " + e.getMessage() + "\n", zipCode));
            invalidInputsAmount++;
        }
        return false;
    }

    /**
     * Regex validering for postnr
      */
    private boolean ckeckValidZipCode(String zipCode) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{4}+",zipCode) || zipCode.isEmpty()){
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

    /**
     *  Regex validering for telefonnr
     */
    private boolean checkPhoneNoFormat(String phoneNo) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{8}+",phoneNo) || phoneNo.isEmpty() || phoneNo.startsWith("0")) {
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

    /**
     * Metoden gir en feilmld til bruker hvis tlfnr som forsøkes å registreres allerede er registrert. 
     */
    private boolean checkIfDuplicatePhoneNo(ArrayList arrayList, String phoneNo) throws DuplicatePhoneNoException {
        for (int i = 0; i < arrayList.size()-1; i++) {
            String [] row = arrayList.get(i).toString().split(";");
            System.out.println(row[1] + " " + row[5]);
            if (row[1].equals(phoneNo) || row[5].equals(phoneNo)) {
                throw new DuplicatePhoneNoException("Telefonnr finnes allerede i databasen.");
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

    /**
     *  Regex validering for epost
     */
    private static boolean checkEmailFormat(String email) throws InvalidEmailFormatException {
        if(!Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",email) || email.isEmpty()){
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

    /**
     * Regex validering for alder
     */
    private boolean checkAgeFormat(String age) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]{2}+",age) || age.isEmpty() || age.startsWith("0")){
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

    /**
     * Regex validering for lønnskrav / lønnsbetingelser
     */
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

    /**
     *  Metode som sjekker om det er valgt en utdannelse eller studieretning
     */
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
            invalidInputs += "Feil i arbeidsområde : " + e.getMessage() + "\n";
            invalidInputsAmount++;
        }
        return false;
    }

    /**
     * Metode som sjekker for om arbeidsområde er valgt
     */
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

    /**
     * Metode som sjekker for om jobbtype er valgt
     */
    private boolean checkIfJobTypeSelected(String jobType) throws NoValueSelectedException {
        System.out.println(jobType);
        if(jobType.equals("Arbeidstid ikke valgt")){
            throw new NoValueSelectedException("Heltid eller deltid må velges!");
        }
        return true;
    }
}
