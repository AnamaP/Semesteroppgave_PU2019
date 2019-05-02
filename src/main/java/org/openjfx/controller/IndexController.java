package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logic.*;

public class IndexController {

    /**
     * Sender bruker til registrer jobbsøker.
     */
    @FXML
    private void btnJobseeker(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regJobseeker.fxml", event);
    }

    /**
     * Sender bruker til registrer jobbutlysning.
     */
    @FXML
    private void btnTempJob(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/regTempJob.fxml", event);
    }

    /**
     * Sender bruker til oversikt over jobbsøkere.
     */
    public void btnViewJobseekers(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
    }

    /**
     * Sender bruker til en oversikt over jobbutlysnigner.
     */
    public void btnViewTempJobs(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml",event);

    }
}
