package thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReaderThreadStarter {

    public static <T> ArrayList<T> startReader(String path) throws InterruptedException, ExecutionException {
        // problem med reader som går evig ? eller fucker seg ved å lese tom fil : lag en file - new file, sjekk om filen eksisterer eller har lengde lik null, returnere isådall tom arraylist

        ExecutorService service = Executors.newFixedThreadPool(1);
        ArrayList<T> result;
        Future<ArrayList<T>> returnValue = service.submit(new ReaderThread<T>(path));
        result = returnValue.get();

        return result;
    }
}
