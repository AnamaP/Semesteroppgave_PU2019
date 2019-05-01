package logic;

public class FiltrationHelper {

    public static Boolean filtrateTempJobTable(TableTempJobs tempJob, String lowerCaseFilter){
        if(tempJob.getContactPerson().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getPhoneNo().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getSector().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getCompanyName().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getAddress().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getWorkfields().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getIndustry().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getJobTitle().toLowerCase().contains(lowerCaseFilter) ||
                tempJob.getJobType().toLowerCase().contains(lowerCaseFilter)){
            return true;
        }
        return false;
    }

    public static Boolean filtrateJbseekerTable(TableJobseekers jobseeker, String lowerCaseFilter){
        if(jobseeker.getFirstname().toLowerCase().contains(lowerCaseFilter) ||
                jobseeker.getLastname().toLowerCase().contains(lowerCaseFilter) ||
                jobseeker.getZipCode().toLowerCase().contains(lowerCaseFilter) ||
                jobseeker.getPostal().toLowerCase().contains(lowerCaseFilter) ||
                jobseeker.getPhoneNo().toLowerCase().contains(lowerCaseFilter) ||
                jobseeker.getWorkfields().toLowerCase().contains(lowerCaseFilter)){
            return true;
        }
        return false;
    }
}
