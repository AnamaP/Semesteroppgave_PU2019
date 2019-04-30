package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.*;


public class IndexController {

    @FXML
    private void btnJobseeker(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regJobseeker.fxml", event);
    }

    @FXML
    private void btnTempJob(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regTempJob.fxml", event);
    }

    public void btnViewJobseekers(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
    }

    public void btnViewTempJobs(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml",event);

    }

}
