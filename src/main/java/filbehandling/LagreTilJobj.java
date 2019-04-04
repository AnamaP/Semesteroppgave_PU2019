package filbehandling;

/* Lagring til .jobj fil med Javas støtte for serialisering.
   - I denne filtypen lagres data i et binært dataformat somm er kompatibelt med Java sine serialiseringsklasser.
   - */

import klasser.Jobbsoker;
import java.io.*;

public class LagreTilJobj extends LagreTilFil{
    //private static final long serialVersionUID = 1; // trenger vi denne? hvis klassen endres på i ettertid med nytt datafelt feks..

    @Override
    public void skrivPersonTilFil(Jobbsoker person, String path){

       try(FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(person);
        }
        catch(IOException e){
            e.printStackTrace();
        }



    }



}


