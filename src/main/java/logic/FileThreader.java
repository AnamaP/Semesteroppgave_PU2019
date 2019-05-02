package logic;

public class FileThreader implements Runnable {
    private static final int TIME_TO_SLEEP = 5_000;

    @Override
    public void run() {
        System.out.println("Starter den store oppgaven...");


        try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException e) {
                System.err.println("Ups, I was interrupted...");
            }

            System.out.println("Den store oppgaven er ferdig gjennomført...");
        }

}
