package logic;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SetTableHelper {
    public void setTempJobsTable(TableColumn<TableTempJobs, String> tcContactPerson, TableColumn<TableTempJobs, String> tcPhoneNo,
                                 TableColumn<TableTempJobs, String> tcSector, TableColumn<TableTempJobs, String> tcCompanyName,
                                 TableColumn<TableTempJobs, String> tcAddress, TableColumn<TableTempJobs, String> tcIndustry,
                                 TableColumn<TableTempJobs, String> tcJobTitle, TableColumn<TableTempJobs, String> tcJobType,
                                 TableColumn<TableTempJobs, String> tcKWorkfields){
        tcContactPerson.setCellValueFactory(cellData->cellData.getValue().contactPersonProperty());
        tcPhoneNo.setCellValueFactory(cellData->cellData.getValue().phoneNoProperty());
        tcSector.setCellValueFactory(cellData->cellData.getValue().sectorProperty());
        tcCompanyName.setCellValueFactory(cellData->cellData.getValue().companyNameProperty());
        tcAddress.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        tcIndustry.setCellValueFactory(cellData->cellData.getValue().industryProperty());
        tcJobTitle.setCellValueFactory(cellData->cellData.getValue().jobTitleProperty());
        tcJobType.setCellValueFactory(cellData->cellData.getValue().jobTypeProperty());
        tcKWorkfields.setCellValueFactory(cellData->cellData.getValue().workfieldsProperty());
    }

    public void setJobbseekerTable(TableColumn<TableJobseekers, String> tcFistname, TableColumn<TableJobseekers, String> tcLastname,
                                   TableColumn<TableJobseekers, String> tcAddress, TableColumn<TableJobseekers, String> tcZipcode,
                                   TableColumn<TableJobseekers, String> tcPostal, TableColumn<TableJobseekers, String> tcPhoneNo,
                                   TableColumn<TableJobseekers, String> tcEmail, TableColumn<TableJobseekers, String> tcAge,
                                   TableColumn<TableJobseekers, String> tcEducation, TableColumn<TableJobseekers, String> tcStudy,
                                   TableColumn<TableJobseekers, String> tcExperience, TableColumn<TableJobseekers, String> tcWorkfields){
        tcFistname.setCellValueFactory(cellData->cellData.getValue().firstnameProperty());
        tcLastname.setCellValueFactory(cellData->cellData.getValue().lastnameProperty());
        tcAddress.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        tcZipcode.setCellValueFactory(cellData->cellData.getValue().zipcodeProperty());
        tcPostal.setCellValueFactory(cellData->cellData.getValue().postalProperty());
        tcPhoneNo.setCellValueFactory(cellData->cellData.getValue().phoneNoProperty());
        tcEmail.setCellValueFactory(cellData->cellData.getValue().emailProperty());
        tcAge.setCellValueFactory(cellData->cellData.getValue().ageProperty());
        tcEducation.setCellValueFactory(cellData->cellData.getValue().educationProperty());
        tcStudy.setCellValueFactory(cellData->cellData.getValue().studyProperty());
        tcExperience.setCellValueFactory(cellData->cellData.getValue().experienceProperty());
        tcWorkfields.setCellValueFactory(cellData->cellData.getValue().workfieldsProperty());
    }
}
