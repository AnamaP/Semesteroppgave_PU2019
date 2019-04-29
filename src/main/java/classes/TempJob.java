package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class TempJob implements Serializable {
    private String jobTitle;
    private String jobType;
    private String description;
    private String duration;
    private String salary;
    private String qualif;
    private ArrayList<String> workfields;
    private String status;

    public TempJob(String jobTitle, String jobType, String description, String duration,
                   String salary, String qualif, ArrayList<String> workfields, String status) {
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.description = description;
        this.duration = duration;
        this.salary = salary;
        this.qualif = qualif;
        this.workfields = workfields;
        this.status = status;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public void setQualif(String qualif) {
        this.qualif = qualif;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public String getJobType() {
        return jobType;
    }
    public String getDescription() {
        return description;
    }
    public String getDuration() {
        return duration;
    }
    public String getSalary() {
        return salary;
    }
    public String getQualif() {
        return qualif;
    }
    public ArrayList<String> getWorkfields() {
        return workfields;
    }
    public String getStatus() {
        return status;
    }

    public String workfieldsToString() {
        String sb = "";
        for(int i = 0; i < workfields.size(); i++){
            if(i == workfields.size() -1){
                sb += workfields.get(i);
            }
            else{
                sb += workfields.get(i) + ", ";
            }
        }
        return sb;
    }

    public String toString(){
        return jobTitle +";"+ jobType +";"+ description +";"+ duration +";"+ salary +";"+ qualif +";"+ workfieldsToString()+";"+status;
    }
}
