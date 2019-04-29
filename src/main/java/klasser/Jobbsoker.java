package klasser;

public class Jobbsoker extends Person {
    private Cv cv;
    private String salary;
    private String status;


    public Jobbsoker(String firstname, String lastname, String adderss, String zipcode, String postal, String tlf,
                     String email, String age, Cv cv, String status) {
        super(firstname, lastname, adderss, zipcode, postal, tlf, email, age);
        this.cv = cv;
        this.salary = "";
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setSalary(String salary){
        this.salary = salary;
    }
    public Cv getCv() {
        return cv;
    }
    public String getSalary() {
        return salary;
    }
    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        String ut = super.toString() +";"+ salary +";"+cv.toString()+";"+status;
        return ut;
    }
}
