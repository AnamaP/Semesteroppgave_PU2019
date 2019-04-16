package feilhaandtering;

import javafx.scene.control.TextField;
import logikk.RegVikariatHjelper;
import org.openjfx.RegSokerController;

public class ValideringsHjelper {

    // opprette exception klasser som kaster en feilmelding dersom det intreffer i public metoden
    // en public metode som kaller på  alle de private enkelt metodene med parameterne deres og kaster en exception
    // private metoder som sjekker inputs med regex

    public static boolean sjekkInput() throws InvalidInputException{
        // if .. else throw new InvalidInputException
        return true;
    }

    private boolean sjekkNavn() throws InvalidInputException{
        // fungerer for fornavn, etternavn, poststed ?
        // if... else throw new InvalidNavnException

        return true;
    }
    private boolean sjekkAdresse()throws InvalidInputException{
        // if... else throw new ..
        return true;
    }

    private boolean sjekkPostnr(){
        return true;
    }

    private boolean sjekkTlf(){
        return true;
    }

    private boolean sjekkKategorier(){
        // if .. else throw new InvalidKategoriException
        return true;
    }

    // metode som sjekker for om utdannelse og studieretning er valgt
    // metode som sjekker Erfaring og referanser (kan bestå av både strenger og tall)

}
