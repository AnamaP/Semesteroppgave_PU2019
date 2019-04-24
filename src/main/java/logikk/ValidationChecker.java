package logikk;

import Exceptions.*;

import java.util.regex.Pattern;

public class ValidationChecker {
    private String invalidInputs = "";

    // begrunne i rapporten hvorfor vi har kjørt følgende regex (f.eks strenger vil ikke krasje systemet og vi har derfor
    // valgt å ikke ha spesiell regex validering for dem, men tlf nr er for.eks viktig da det kan krasje programmet)
    // samle alle strenger i en egen exception klasse - lage en checkStringFormat metode - med indiv. metoder under
    // samme for NumberFormat - for alt med integer
    // Felt som er avhengige av et spesielt regex mønster kan ha sine egne metoder
    // cbx, radiobtn, combobox - alle kan samles under egen NullpointerMetode...


    public String inputJobseekerCollector(String firstname, String lastname, String address, String zipcode, String postal,
                                          String phoneNmbr, String email, String age, String experience, String reference,
                                          String salary, Object education, Object study, Boolean sales, Boolean admin,
                                          Boolean it, Boolean economy) {

        checkFirstname(firstname);
        checkLastname(lastname);
        checkAddress(address);
        checkZipCode(zipcode);
        checkPostal(postal);
        checkPhoneNmbr(phoneNmbr);
        checkEmail(email);
        checkAge(age);
        checkExperience(experience);
        checkSalary(salary);
        checkValueSelected(education, study);
        checkWorkfields(sales, admin, it, economy);

        return invalidInputs;
    }

    public String inputJobAdvertCollector(String firstname, String phoneNmbr, String sector, String companyName,
                                          String orgNmbr, String industry, String jobTitle, String jobDescription,
                                          String duration, String salary, String qualif, Boolean sales, Boolean admin,
                                          Boolean it, Boolean economy, Boolean fullTime, Boolean partTime) {
        checkFirstname(firstname);
        checkPhoneNmbr(phoneNmbr);
        checkSector(sector);
        checkCompanyInfo(companyName, industry);
        checkOrgNmbr(orgNmbr);
        checkLengthJobadvert(jobTitle, jobDescription);
        checkLengthJobadvert1(duration,qualif);
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

    private boolean checkFirstname(String firstname) {
        try {
            if (checkStringFormat(firstname)) {
                return true;
            }
        }
        catch (InvalidStringFormatException e) {
            invalidInputs += (String.format("%s er et ugyldig navn\n, kun bokstaver Aa-åÅ tillatt \n", firstname));
        }
        return false;
    }

    private boolean checkLastname(String lastname) {
        try {
            if (checkStringFormat(lastname)){
                return true;
            }
        }
        catch(InvalidStringFormatException e){
            invalidInputs += String.format("%s er et ugyldig navn, kun bokstaver Aa-åÅ tillatt \n", lastname);
        }
        return false;
    }

    private boolean checkPostal(String postal){
        try{
            if(checkStringFormat(postal)){
                return true;
            }
        }
        catch(InvalidStringFormatException e){
            invalidInputs += String.format("%s er et ugyldig poststed, kun bokstaver Aa-åÅ tillatt \n", postal);
        }
        return false;
    }

    private boolean checkSector(String sector){
        try{
            if(checkStringFormat(sector)){
                return true;
            }
        }
        catch(InvalidStringFormatException e){
            invalidInputs += String.format("%s er en ugydlig sektor, kun bokstaver Aa-åÅ tillatt \n", sector);
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

    private boolean ckeckValidZipCode(String zipcode) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]+",zipcode) || zipcode.length() != 4 || zipcode.isEmpty()){
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

    private boolean checkValidPhoneNmbr(String phoneNmbr) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9]+",phoneNmbr) || phoneNmbr.length() != 8 || phoneNmbr.isEmpty()){
            throw new InvalidNumberFormatException("Feil i tlfnr!");
        }
        return true;
    }

    private boolean checkPhoneNmbr(String phoneNmbr){
        try{
            if(checkValidPhoneNmbr(phoneNmbr)){
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += (String.format("%s : er et ugyldig tlfnr, må bestå av 8 tall mellom 0-9 og uten mellomrom \n", phoneNmbr));
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

    private boolean checkValidLength(String string1, String string2) throws InvalidTextIfNullException {
        if(string1.isEmpty() || string2.isEmpty()){
            throw new InvalidTextIfNullException("Ett eller flere obligatoriske felt er ikke fylt ut!");
        }
        return true;
    }

    private boolean checkCompanyInfo(String companyName, String industry){
        try{
            if(checkValidLength(companyName, industry)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Firmanavn eller bransje står tomt, dette må fylles ut!";
        }
        return false;
    }

    private boolean checkLengthJobadvert(String jobTitle, String jobDescription){
        try{
            if(checkValidLength(jobTitle,jobDescription)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Stillingstittel/Stillingsbeskrivelse står tomt, dette må fylles ut! \n";
        }
        return false;
    }

    private boolean checkLengthJobadvert1(String duration, String qualif){
        try{
            if(checkValidLength(duration, qualif)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Varighet/kvalifikasjoner står tomt, dette må fylles ut! \n";
        }
        return false;
    }

    private boolean checkLengtExperience(String experience) throws InvalidTextIfNullException{
        if(experience.isEmpty()){
            throw new InvalidTextIfNullException("Ett eller flere obligatoriske felt står tomme");
        }
        return true;
    }

    private boolean checkExperience(String experience){
        try{
            if(checkLengtExperience(experience)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Erfaring står tomt, dette må fylles ut! \n";
        }
        return false;
    }

    private boolean checkValidAge(String age) throws InvalidNumberFormatException {
        if(!Pattern.matches("[1-9]{2}+",age) || age.isEmpty()){
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
            invalidInputs += (String.format("%s : er en ugyldig alder, kun tall( ingen mellomrom)\n", age));
        }
        return false;
    }

    private boolean checkSalaryFormat(String salary) throws InvalidNumberFormatException {
        if(!Pattern.matches("[0-9\\p{Space}]{0,7}+",salary)){
            throw new InvalidNumberFormatException("Feil nummer format");
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
            invalidInputs += (String.format("%s : er en ugyldig lønn, tillatt: tall 0-9 (maks: mio)\n",salary));
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

    private boolean checkValidOrgNmbr(String orgNmbr) throws InvalidNumberFormatException{
        if(!Pattern.matches("[0-9{9}\\p{Space}]+", orgNmbr) || orgNmbr.isEmpty()){
            throw new InvalidNumberFormatException("Feil i organisasjonsnr, må bestå av 9 tall");
        }
        return true;

    }

    private boolean checkOrgNmbr(String orgNmbr){
        try{
            if(checkValidOrgNmbr(orgNmbr)){
                return true;
            }
        }
        catch(InvalidNumberFormatException e){
            invalidInputs += String.format("%s er et ugyldig organisasjonsnr, må bestå av 9 tall \n", orgNmbr);
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
