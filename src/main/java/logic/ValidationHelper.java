package logic;

import classes.Company;
import classes.Jobseeker;
import fileHandling.CsvFileHandler;
import fileHandling.FileHandler;

import java.io.IOException;
import java.util.ArrayList;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.RegTempJobHelper.tempJobsList;

public class ValidationHelper {

    public static String invalidInputs;

    /**
     * Denne metoden tar inn en jobbsøker og kjører den inn i feilhåndteringsmetodene i ValidationChecker.
     * invalidInputs vil inneholde alle feilmeldingene som måtte inntreffe.
     */
    public static Boolean runJobseekerValidation(Jobseeker jobseeker){
        String inptFirstname = jobseeker.getFirstname();
        String inptLastname = jobseeker.getLastname();
        String inptAddress = jobseeker.getAddress();
        String inptZipcode = jobseeker.getZipCode();
        String inptPostal = jobseeker.getPostal();
        String inptPhoneNo = jobseeker.getPhoneNo();
        String inptEmail = jobseeker.getEmail();
        String inptAge = jobseeker.getAge();
        String inptExperience = jobseeker.getCv().getExperience();
        String inptSalary = jobseeker.getSalary();
        String inptEducation = jobseeker.getCv().getEducation();
        String inptStudy = jobseeker.getCv().getStudy();
        String inptWorkfields = jobseeker.getCv().workfieldsToString();

        ValidationChecker validation = new ValidationChecker();
        invalidInputs = validation.inputJobseekerCollector(inptFirstname, inptLastname, inptAddress, inptZipcode,
                inptPostal,inptPhoneNo, inptEmail, inptAge, inptExperience, inptSalary, inptEducation,
                inptStudy, inptWorkfields);


        String content = jobseeker.toString();

        if(checkResults(content, Paths.JOBSEEKER)) {
            return true;
        }
        return false;
    }

    /**
     * Likt som over, bare at denne metoden tar inn en jobbutlysning og kjører den inn i
     * feilhåndteringsmetodene i ValidationChecker.invalidInputs vil inneholde alle feilmeldingene
     * som måtte inntreffe. Bassert på om checkResults gikk fint skal den sende bruker til neste side.
     */
    public static boolean runTempJobValidation(Company company){
        String inptContactPerson = company.getContactPerson();
        String inptPhoneNo = company.getPhoneNo();
        String inptSector = company.getSector();
        String inptCompanyName = company.getCompanyName();
        String inptAddress = company.getAddress();
        String inptIndustry = company.getIndustry();
        String inptJobTitle = company.getTempJob().getJobTitle();
        String inptQualif = company.getTempJob().getQualif();
        String inptDuration = company.getTempJob().getDuration();
        String inptSalary = company.getTempJob().getSalary();
        String inptDescription = company.getTempJob().getDescription();
        String inptJobType = company.getTempJob().getJobType();
        String inptWorkfields = company.getTempJob().workfieldsToString();

        ValidationChecker validation = new ValidationChecker();
        invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNo, inptSector, inptCompanyName,
                inptAddress, inptIndustry, inptJobTitle, inptDescription,inptDuration, inptSalary, inptQualif,
                inptJobType, inptWorkfields);

        String content = company.toString();

        if(checkResults(content, Paths.TEMPJOB)) {
            return true;
        }
        return false;
    }

    /**
     * Her tar sjekker metoden om invalidInputs inneholder noe:
     * Fant feil? Feilmeldingen vises til bruker og det returneres false. Bruker blir på siden og får mulighet
     * til å kunne rette opp i feilene som er skrevet inn før raden blir registrert.
     * Fant ingen feil? Bruker får meldig om at alt så fint ut, objektet (her i toString() form) lagres som
     * ny linje i csv-filen og den returnerer true.
     */
    private static boolean checkResults(String content, String path){
        if (!invalidInputs.isEmpty()) {
            AlertHelper.showError(invalidInputs);
            return false;
        }
        else {
            AlertHelper.showConfirmation();

            FileHandler csvFileHandler = new CsvFileHandler();
            try {
                csvFileHandler.writeToDB(content, path);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public boolean validateFileInpt(Object object, String path, boolean csvFilType) {
        //TODO: Om csvFiletype er false er det er jobj-fil og den burde sjekkes deretter.
        //      Det har jeg ikke fått til. Se metoden under.

        String[] columns;
        columns = object.toString().split(";");

        System.out.println(columns.length);
        if(columns.length == 15) {
            if (columns[14].equals("\n") && path.equals(Paths.TEMPJOB)) {
                String phoneNo = columns[1];
                if(uniquePhoneNr(tempJobsList, phoneNo)) {
                    ViewHelper run = new ViewHelper();
                    Company nyCompany = run.getTempJobFromList(columns);
                    tempJobsList.add(nyCompany);
                    return true;
                }
                else{
                    invalidInputs += "Jobbutlysningen er allerede registrert.";
                    return false;
                }
            }
            if (!(columns[14].equals("\n")) && path.equals(Paths.JOBSEEKER)) {
                String phoneNo = columns[5];
                if(uniquePhoneNr(jobseekersList, phoneNo)) {
                    ViewHelper run = new ViewHelper();
                    Jobseeker jobseeker = run.getJobseekerFromList(columns);
                    jobseekersList.add(jobseeker);
                    return true;
                }
                else{
                    invalidInputs += "Jobbsøkeren er allerede registrert.";
                    return false;
                }
            }
            else {
                System.out.println("Havner du her mon tro...??");
                invalidInputs += "Feil i valgt fil.";
                return false;
            }
        }
        else {
            invalidInputs += "Feil lengde på fil.";
            System.out.println(columns.length);
            return false;
        }
    }

    private Boolean uniquePhoneNr(ArrayList arrayList, String phoneNo){
        for(int i = 0; i < arrayList.size(); i++) {
            String[] row = arrayList.get(i).toString().split(";");
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals(phoneNo)) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO: Prøver å finne ut av om objektet er en jobbsøker eller et vikariat, men fikk det ikke til..!!
    private String [] objectToList(Object object, String path){
        String [] columns = {};
        Jobseeker jobseeker;
        Company company;

        if(path == Paths.JOBSEEKER){
            try {
                jobseeker = (Jobseeker) object;
            }
            catch(NullPointerException e){
                jobseeker = null;
            }
            if(jobseeker != null){
                return jobseeker.toString().split(";");
            }
        }
        if(path == Paths.TEMPJOB){
            try{
                company = (Company) object;
            }
            catch (NullPointerException e) {
                company = null;
            }
            if(company != null){
                return company.toString().split(";");
            }
        }
        invalidInputs += "Ikke riktig formatert jobj-fil.";
        return columns;
    }
}
