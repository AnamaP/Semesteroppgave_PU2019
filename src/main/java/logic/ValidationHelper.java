package logic;

import classes.Company;
import classes.Jobseeker;
import fileHandling.CsvFileHandler;
import fileHandling.FileHandler;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ValidationHelper {

    /**
     * Denne metoden tar inn en jobbsøker og kjører den inn i feilhåndteringsmetodene i ValidationChecker.
     * invalidInputs vil inneholde alle feilmeldingene som måtte inntreffe.
     */
    public static void runJobseekerValidation(Jobseeker jobseeker, ActionEvent event){
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
        String invalidInputs = validation.inputJobseekerCollector(inptFirstname, inptLastname, inptAddress, inptZipcode,
                inptPostal,inptPhoneNo, inptEmail, inptAge, inptExperience, inptSalary, inptEducation,
                inptStudy, inptWorkfields);


        String content = jobseeker.toString();

        if(checkResults(content, invalidInputs, Paths.JOBSEEKER)){
            //Tar brukeren med til neste side:
            NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
        }
    }

    /**
     * Likt som over, bare at denne metoden tar inn en jobbutlysning og kjører den inn i
     * feilhåndteringsmetodene i ValidationChecker.invalidInputs vil inneholde alle feilmeldingene
     * som måtte inntreffe. Bassert på om checkResults gikk fint skal den sende bruker til neste side.
     */
    public static void runTempJobValidation(Company company, ActionEvent event){
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
        String invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNo, inptSector, inptCompanyName,
                inptAddress, inptIndustry, inptJobTitle, inptDescription,inptDuration, inptSalary, inptQualif,
                inptJobType, inptWorkfields);

        String content = company.toString();

        if(checkResults(content, invalidInputs, Paths.TEMPJOB)) {
            NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml", event);
        }
    }

    /**
     * Her tar sjekker metoden om invalidInputs inneholder noe:
     * Fant feil? Feilmeldingen vises til bruker og det returneres false. Bruker blir på siden og får mulighet
     * til å kunne rette opp i feilene som er skrevet inn før raden blir registrert.
     * Fant ingen feil? Bruker får meldig om at alt så fint ut, objektet (her i toString() form) lagres som
     * ny linje i csv-filen og den returnerer true.
     */
    private static boolean checkResults(String content, String invalidInputs, String path){
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

}
