package org.openjfx.model.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReaderThreadStarter {

    public static String[] startReader(String path) throws InterruptedException, ExecutionException {
        // problem med reader som går evig ? eller fucker seg ved å lese tom fil : lag en file - new file, sjekk om filen eksisterer eller har lengde lik null, returnere isådall tom arraylist

        ExecutorService service = Executors.newFixedThreadPool(1);
        String[] result;
        Future<String[]> returnValue = service.submit(new ReaderThread(path));
        result = returnValue.get();

        return result;
    }
}
