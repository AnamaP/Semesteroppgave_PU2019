package org.openjfx;

import org.openjfx.model.thread.ReaderThreadStarter;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Testing {

    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(ReaderThreadStarter.startReader("C:\\Users\\anama\\OneDrive\\Dokumenter\\GitHub\\Semesteroppgave_PU\\hello-world\\tempJobsDB.csv")));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
}
