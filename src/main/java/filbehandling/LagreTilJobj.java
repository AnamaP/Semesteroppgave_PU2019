package filbehandling;

/* Lagril tl .jobj fil med Javas støtte for serialisering.
   - I denne filtypen lagres data i et binært dataformat somm er kompatibelt med Java sine serialiseringsklasser.
   - */


import klasser.Jobbsoker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class LagreTilJobj extends LagreTilFil implements Serializable {
    // private static final long serialVersionUID = 1; // trenger vi denne? hvis klassen endres på i ettertid med nytt datafelt feks..

    /*
    Object liste = skrivPersonTilFil();
    if(liste != null){
        System.out.println("Liste " + liste.toString());
    }*/

    @Override
    public void skrivPersonTilFil(Jobbsoker person, String path) throws IOException{
        /*try(FileInputStream fin = new FileInputStream(fil);
            ObjectInputStream oin = new ObjectInputStream(fin))
        {
          //  Object loadeObj = oin.readObject();
        }*/



    }



}


