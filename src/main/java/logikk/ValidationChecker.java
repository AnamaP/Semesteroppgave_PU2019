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
                                          Boolean it, Boolean economy){

        checkName(firstname, lastname);
        checkAddress(address);
        checkZipCode(zipcode);
        checkPostal(postal);
        checkPhoneNmbr(phoneNmbr);
        checkEmail(email);
        checkAge(age);
        checkLengthJobseeker(experience, reference);
        checkSalary(salary);
        checkValueSelected(education,study);
        checkWorkfields(sales, admin, it, economy);

        return invalidInputs;
    }

    public String inputJobAdvertCollector(String contactP, String phoneNmbr, String sector, String companyName,
                                          String orgNmbr, String industry, String jobTitle, String jobDescription,
                                          String duration, String salary, String qualif,Boolean sales, Boolean admin,
                                          Boolean it, Boolean economy, Boolean fullTime, Boolean partTime){
        checkContactP(contactP);
        checkPhoneNmbr(phoneNmbr);
        checkSector(sector);
        checkCompanyName(companyName);
        checkOrgNmbr(orgNmbr);
        checkIndustry(industry);
        checkLengthJobadvert(jobTitle,jobDescription,qualif);
        checkDuration(duration);
        checkSalary(salary);
        //checkValueJobType;
        checkWorkfields(sales,admin,it,economy);
        checkJobTypeSelected(fullTime, partTime);

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
        if(!Pattern.matches("[0-9_\\p{Space}]+",phoneNmbr) || phoneNmbr.length() != 8 || phoneNmbr.isEmpty()){
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

    private boolean checkValidLengthJobseeker(String experience, String reference) throws InvalidTextIfNullException {
        if(experience.isEmpty() || reference.isEmpty()){
            throw new InvalidTextIfNullException("Mangler informasjon, vennligst se over at alle obligatoriske felter er fylt ut!");
        }
        return true;
    }

    private boolean checkLengthJobseeker(String experience, String reference){
        try{
            if(checkValidLengthJobseeker(experience,reference)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Mangler informasjon, vennligst se over at alle obligatoriske felter er fylt ut! \n";
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
            invalidInputs += (String.format("%s : er en ugyldig lønn, tillatt: tall 0-9 (maks input er i mio)\n",salary));
        }
        return false;
    }

    private boolean checkIfValueSelected(Object education, Object study) throws NullPointerException{
        if(education.equals("null") || study.equals("null")){
            throw new NullPointerException("Utdanning/studieretning er ikke valgt, vennligst velg en");
        }
        return true;
    }

    private boolean checkValueSelected(Object education, Object study){
        try{
            if(checkIfValueSelected(education,study)){
                return true;
            }
        }
        catch(NullPointerException e){
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

    private boolean checkValidContactP(String contactP) throws InvalidNameFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ_\\p{Space}\\-]+",contactP) || contactP.isEmpty()){
            throw new InvalidNameFormatException("Feil i kontaktperson, Feil i kontaktperson, tillatt: Aa-åÅ og bindestrek(-)");
        }
        return true;
    }

    private boolean checkContactP(String contactP){
        try{
            if(checkValidContactP(contactP)){
                return true;
            }
        }
        catch(InvalidNameFormatException e){
            invalidInputs += String.format("%s er ugyldig, tillatt: Feil i kontaktperson, tillatt: Aa-åÅ og bindestrek(-)\n",contactP);
        }
        return false;
    }

    private boolean checkValidSector(String sector) throws InvalidSectorFormatException{
        if(Pattern.matches("[\\p{Upper}\\p{Lower}]+",sector) && (sector.equals("offentlig") || sector.equals("privat"))
        && (!sector.isEmpty())){
            throw new InvalidSectorFormatException("Feil i sektor, må enten stå privat eller offentlig");
        }
        return true;
    }

    private boolean checkSector(String sector){
        try{
            if(checkValidSector(sector)){
                return true;
            }
        }
        catch(InvalidSectorFormatException e){
            invalidInputs += String.format("%s er ugyldig sektor, tillatt: Offentlig eller Privat \n", sector);
        }
        return false;
    }

    private boolean checkValidCompanyName(String companyName) throws InvalidCompanyNameFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ0-9_ \\p{P}\\p{Space}]+", companyName) || companyName.isEmpty()){
            throw new InvalidCompanyNameFormatException("Feil i firmanavn, tillatt: Aa-åÅ, tall 0-9 og punktum");
        }
        return true;

    }

    private boolean checkCompanyName(String companyName){
        try{
            if(checkValidCompanyName(companyName)){
                return true;
            }
        }
        catch(InvalidCompanyNameFormatException e){
            invalidInputs += String.format("%s er et ugyldig firmanavn, tillatt: Aa-åÅ, tall 0-9 og punktum \n", companyName);
        }
        return false;
    }

    private boolean checkValidOrgNmbr(String orgNmbr) throws InvalidOrgNmbrFormatException{
        if(!Pattern.matches("[0-9{9}\\p{Space}]+", orgNmbr) || orgNmbr.isEmpty()){
            throw new InvalidOrgNmbrFormatException("Feil i organisasjonsnr, må bestå av 9 tall");
        }
        return true;

    }

    private boolean checkOrgNmbr(String orgNmbr){
        try{
            if(checkValidOrgNmbr(orgNmbr)){
                return true;
            }
        }
        catch(InvalidOrgNmbrFormatException e){
            invalidInputs += String.format("%s er et ugyldig organisasjonsnr, må bestå av 9 tall \n", orgNmbr);
        }
        return false;
    }

    private boolean checkValidIndustry(String industry) throws InvalidIndustryFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ]+",industry) || industry.isEmpty()){
            throw new InvalidIndustryFormatException("Feil formatering i 'industri', tillat: Aa-åÅ");
        }
        return true;
    }

    private boolean checkIndustry(String industry) {
        try {
            if (checkValidIndustry(industry)) {
                return true;
            }
        } catch (InvalidIndustryFormatException e) {
            invalidInputs += String.format("%s er en ugyldig, tillatt: Aa-åÅ \n", industry);
        }
        return false;
    }

    private boolean checkValidJobadvertLength(String jobTitle, String jobDescription, String qualif)
            throws InvalidTextIfNullException{
        if(jobTitle.isEmpty() || jobDescription.isEmpty() || qualif.isEmpty()){
            throw new InvalidTextIfNullException("Mangler informasjon i enten stillingstittel/stillingsbeskrivelse/kvalifikasjoner");
        }
        return true;
    }

    private boolean checkLengthJobadvert(String jobTitle, String jobDescription, String qualif){
        try{
            if(checkValidJobadvertLength(jobTitle,jobDescription,qualif)){
                return true;
            }
        }
        catch(InvalidTextIfNullException e){
            invalidInputs += "Mangler informasjon, vennligst se over at alle obligatoriske felter er fylt ut! \n";
        }
        return false;
    }

    private boolean checkIfJobTypeSelected(Boolean partTime, Boolean fullTime) throws InvalidValueSelectedIsNullException{
        if(!partTime && !fullTime){
            throw new InvalidValueSelectedIsNullException("Stillingstype er ikke valgt, du må velge enten heltid eller deltid");
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
            invalidInputs += "Stillingstype er ikke valgt, du må velge enten heltid eller deltid \n";
        }
        return false;
    }

    private boolean checkValidDuration(String duration) throws InvalidDurationFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ0-9\\p{Space}]+",duration) || duration.isEmpty()){
            throw new InvalidDurationFormatException("Feil i 'varighet', tillatt: Aa-åÅ og tall 0-9");
        }
        return true;
    }

    private boolean checkDuration(String duration){
        try{
            if(checkValidDuration(duration)){
                return true;
            }
        }
        catch(InvalidDurationFormatException e){
            invalidInputs += String.format("%s er ugyldig, tillatt: Aa-åÅ og tall 0-9 \n", duration);
        }
        return false;
    }

    // TODO : checkWorkfield metoden
    // TODO : Metode som sjekker for om stillingstype er valgt(heltid/deltid)

}
