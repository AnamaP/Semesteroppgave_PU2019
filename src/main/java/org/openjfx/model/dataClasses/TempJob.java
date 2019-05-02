package org.openjfx.model.dataClasses;

import java.io.Serializable;
import java.util.ArrayList;

import static org.openjfx.model.logic.WorkfieldsHelper.workfieldsToString;

public class TempJob implements Serializable {
    private static final long serialVersionUID = 1;

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
    public String getStatus() {
        return status;
    }
    public String getWorkfields() {
        return workfieldsToString(workfields);
    }

    public String toString(){
        return jobTitle +";"+ jobType +";"+ description +";"+ duration +";"+
                salary +";"+ qualif +";"+ workfieldsToString(workfields)+";"+status;
    }
}
