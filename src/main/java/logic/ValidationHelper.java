package logic;

import classes.Jobseeker;
import fileHandling.CsvFileHandler;
import fileHandling.FileHandler;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ValidationHelper {

// TODO : Flytte koden for kall på regex hit ?
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

        if (!invalidInputs.isEmpty()){
            AlertHelper.showError(invalidInputs);
        }
        else{

            AlertHelper.showConfirmation();
            String input = jobseeker.toString();

            // Lagrer til .csv
            FileHandler csvFileHandler = new CsvFileHandler();
            try {
                csvFileHandler.writeToDB(input, Paths.JOBSEEKER);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Tar brukeren med til neste side:
            NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
        }
    }
}
