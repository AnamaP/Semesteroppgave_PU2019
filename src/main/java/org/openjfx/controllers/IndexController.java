package org.openjfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.openjfx.model.logic.*;

public class IndexController {

    /**
     * Sender bruker til registrer jobbsøker.
     */
    @FXML
    private void btnJobseeker(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/regJobseeker.fxml", event);
    }

    /**
     * Sender bruker til registrer jobbutlysning.
     */
    @FXML
    private void btnTempJob(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/regTempJob.fxml", event);
    }

    /**
     * Sender bruker til oversikt over jobbsøkere.
     */
    public void btnViewJobseekers(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
    }

    /**
     * Sender bruker til en oversikt over jobbutlysnigner.
     */
    public void btnViewTempJobs(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml",event);
    }
}
