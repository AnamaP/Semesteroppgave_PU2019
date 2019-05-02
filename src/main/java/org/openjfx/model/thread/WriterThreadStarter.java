package org.openjfx.model.thread;

public class WriterThreadStarter {

    public void writerThreadStarter(Object object, String path) {

        Thread threader = new Thread(new WriterThread());
        threader.start();

        try {
            threader.join();
        } catch (InterruptedException e) {

        }

        System.out.println("Ferdig med alle oppgaver");
    }
}
