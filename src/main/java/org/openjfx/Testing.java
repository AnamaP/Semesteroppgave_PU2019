package org.openjfx;

import org.openjfx.model.thread.ReaderThreadStarter;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Testing {

    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(ReaderThreadStarter.startReader("/Users/signeeide/Documents/Oslo Met/S2_Programutvikling/Semesteroppgave/Semesteroppgave_PU2019/AlvaTemp.csv")));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
