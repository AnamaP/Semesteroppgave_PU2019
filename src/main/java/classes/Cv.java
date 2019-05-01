package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Cv implements Serializable {
    private static final long serialVersionUID = 1;

    private String education;
    private String study;
    private String experience;
    private String reference;
    private ArrayList<String> workfields;

    public Cv(String education, String stucy, String experience, ArrayList<String> workfields) {
        this.education = education;
        this.study = stucy;
        this.experience = experience;
        this.reference = "";
        this.workfields = workfields;
    }

    public void setReference(String reference){
        this.reference = reference;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public void setStudy(String study) {
        this.study = study;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public void setWorkfields(ArrayList<String> workfields) {
        this.workfields = workfields;
    }
    public String getEducation() {
        return education;
    }
    public String getStudy() {
        return study;
    }
    public String getExperience() {
        return experience;
    }
    public String getReference() {
        return reference;
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
        return education +";"+ study +";"+ experience +";"+ reference +";"+ workfieldsToString();
    }
}
