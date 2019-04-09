package filbehandling;

/* Lagring til .jobj fil med Javas støtte for serialisering.
   - I denne filtypen lagres data i et binært dataformat somm er kompatibelt med Java sine serialiseringsklasser.
   - */

import java.io.*;

public class LagreTilJobj extends LagreTilFil{
    //private static final long serialVersionUID = 1; // trenger vi denne? hvis klassen endres på i ettertid med nytt datafelt feks..

    @Override
    public void skrivPersonTilFil(String person, String path){

       try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(person);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void skrivVikariatTilFil(String nyUtlysning, String path){

        try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(nyUtlysning);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}


