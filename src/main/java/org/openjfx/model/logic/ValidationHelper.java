package org.openjfx.model.logic;

import org.openjfx.model.dataClasses.Company;
import org.openjfx.model.dataClasses.Jobseeker;
import org.openjfx.model.fileHandling.CsvFileHandler;
import org.openjfx.model.fileHandling.FileHandler;

import java.io.IOException;
import java.util.ArrayList;

import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;

public class ValidationHelper {

    public static String invalidInputs;

    /**
     * Kjører vailhåndtering på en jobbsøker. invalidInputs vil inneholde alle feilmeldingene som måtte inntreffe.
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
        String inptWorkfields = jobseeker.getCv().getWorkfields();

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
     * Kjører validering på et vikariat. invalidInputs vil inneholde alle feilmeldingene som måtte inntreffe.
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
        String inptWorkfields = company.getTempJob().getWorkfields();

        ValidationChecker validation = new ValidationChecker();
        invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNo, inptSector, inptCompanyName,
                inptAddress, inptIndustry, inptJobTitle, inptDescription, inptDuration, inptSalary, inptQualif,
                inptJobType, inptWorkfields);

        String content = company.toString();

        if(checkResults(content, Paths.TEMPJOB)) {
            return true;
        }
        return false;
    }

    /**
     * Sjekker om invalidInputs inneholder noe:
     * Fant feil? Feilmeldingen vises til bruker og bruker blir på siden.
     * Ingen feil? Objektet (her i toString() form) lagres til csv-fil.
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

    /**
     * Denne
     */
    public boolean validateFileInpt(Object object, String path, boolean csvFileType) {
        //TODO: Om csvFiletype er false er det er jobj-fil og den burde sjekkes deretter.
        //      Det har jeg ikke fått til. Se metoden under.

        String[] columns;
        if(!csvFileType){
            columns = objectToList(object, path);
        }

        columns = object.toString().split(";");

        System.out.println(columns.length);
        if((columns.length == 15) && (path.equals(Paths.JOBSEEKER))) {
            String phoneNo = columns[5];
            if (uniquePhoneNo(jobseekersList, phoneNo)) {
                ViewHelper run = new ViewHelper();
                Jobseeker jobseeker = run.getJobseekerFromList(columns);
                jobseekersList.add(jobseeker);
                return true;
            } else {
                invalidInputs += "Jobbsøkeren er allerede registrert.";
                return false;
            }
        }
        if ((columns.length == 14) && (path.equals(Paths.TEMPJOB))) {
                String phoneNo = columns[1];
                if(uniquePhoneNo(tempJobsList, phoneNo)) {
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
        else {
            invalidInputs += "Feil lengde på fil.";
            System.out.println(columns.length);
            return false;
        }
    }

    private Boolean uniquePhoneNo(ArrayList arrayList, String phoneNo){
        for(int i = 0; i < arrayList.size(); i++) {
            String[] row = arrayList.get(i).toString().split(";");
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals(phoneNo) || phoneNo.isEmpty()) {
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
            catch(NullPointerException | ClassCastException e){
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
            catch (NullPointerException | ClassCastException e) {
                company = null;
            }
            if(company != null){
                return company.toString().split(";");
            }
        }
        invalidInputs += "Feil format på .jobj fil.";
        return columns;
    }
}
