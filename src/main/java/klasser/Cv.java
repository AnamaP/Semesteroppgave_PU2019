package klasser;

import java.io.Serializable;
import java.util.ArrayList;

public class Cv implements Serializable {
    private String education;
    private String study;
    private String experience;
    private String reference;
    private ArrayList<String> categories;

    public Cv(String education, String stucy, String experience, ArrayList<String> categories) {
        this.education = education;
        this.study = stucy;
        this.experience = experience;
        this.reference = "";
        this.categories = categories;
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
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
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

    public String categoriesToString() {
        String sb = "";
        for(int i = 0; i < categories.size(); i++){
            if(i == categories.size() -1){
                sb += categories.get(i);
            }
            else{
                sb += categories.get(i) + ", ";
            }
        }
        return sb;
    }

    public String toString(){
        return education +";"+ study +";"+ experience +";"+ reference +";"+ categoriesToString();
    }
}
