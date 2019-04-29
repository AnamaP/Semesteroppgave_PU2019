package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logikk.*;


public class IndexController {

    @FXML
    private void btnJobseeker(ActionEvent event) {
        NavigationHelper.changePange("/org/openjfx/regSoker.fxml", event);
    }

    @FXML
    private void btnTempJob(ActionEvent event) {
        NavigationHelper.changePange("/org/openjfx/regVikariat.fxml", event);
    }

    public void btnViewJobseekers(ActionEvent event) {
        NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnViewTempJobs(ActionEvent event) {
        NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml",event);

    }

}
