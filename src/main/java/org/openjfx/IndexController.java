package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.*;


public class IndexController {

    @FXML
    private void btnJobseeker(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regSoker.fxml", event);
    }

    @FXML
    private void btnTempJob(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regVikariat.fxml", event);
    }

    public void btnViewJobseekers(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnViewTempJobs(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml",event);

    }

}
