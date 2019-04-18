package feilhaandtering;


import org.openjfx.RegSokerController;

import java.util.regex.Pattern;

public class ValideringsHjelper {
    private String ugyldigeVerdier = "";

    // TODO : Må man legge inn regex for whitespace / ikke tillate whitespace??

    public String sjekkAlleInputs(String fornavn, String etternavn, String adresse, String postnr, String poststed,
                                  String tlf, String epost, String alder, String erfaring, String referanse, String lonnskrav){

        sjekkNavn(fornavn, etternavn);
        sjekkAdresse(adresse);
        sjekkPostnr(postnr);
        sjekkPoststed(poststed);
        sjekkTlf(tlf);
        sjekkEpost(epost);
        sjekkAlder(alder);
        sjekkLengde(erfaring, referanse);
        sjekkLonnskrav(lonnskrav);

        return ugyldigeVerdier;
    }

    private boolean sjekkNavnFormat(String fornavn, String etternavn) throws InvalidNavnFormatException{
        if((!Pattern.matches("[a-zæøåA-ZÆØÅ ]+",fornavn) || fornavn.isEmpty()) ||
        (!Pattern.matches("[a-zæøåA-ZÆØÅ ]+",etternavn) || etternavn.isEmpty())){
            throw new InvalidNavnFormatException("Feil i Fornavn / Etternavn (må bestå av bokstaver A-Å), vennligst se over!");
        }
        return true;
    }

    private boolean sjekkNavn(String fornavn, String etternavn){
        try{
            if(sjekkNavnFormat(fornavn, etternavn)){
                return true;
            }
        }
        catch(InvalidNavnFormatException e){
            ugyldigeVerdier += "Feil i Fornavn / Etternavn (må bestå av bokstaver A-Å), vennligst se over! \n";
        }
        return false;
    }

    private boolean sjekkAdresseFormat(String adresse)throws InvalidAdresseFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ0-9]+",adresse) || adresse.isEmpty()){
            throw new InvalidAdresseFormatException("Feil i adresse (kun bokstaver og tall tillatt");
        }
        return true;
    }

    private boolean sjekkAdresse(String adresse){
        try{
            if(sjekkAdresseFormat(adresse)){
                return true;
            }
        }
        catch(InvalidAdresseFormatException e){
            ugyldigeVerdier += "Feil i adresse (kun bokstaver og tall tillatt! \n";
        }
        return false;
    }

    private boolean sjekkPostnrFormat(String postnr) throws InvalidPostnrFormatException{
        if(!Pattern.matches("[0-9]+",postnr) || postnr.length() != 4 || postnr.isEmpty()){
            throw new InvalidPostnrFormatException("Feil i postnr (kun tall mellom 0-9");
        }
        return true;
    }

    private boolean sjekkPostnr(String postnr){
        try{
            if(sjekkPostnrFormat(postnr)) {
                return true;
            }
        }
        catch(InvalidPostnrFormatException e){
            ugyldigeVerdier += "Feil i postnr (kun tall mellom 0-9! \n";
        }
        return false;
    }

    private boolean sjekkPoststedFormat(String poststed) throws InvalidPoststedFormatException{
        if(!Pattern.matches("[a-zæøåA-ZÆØÅ]+",poststed) || poststed.isEmpty()){
            throw new InvalidPoststedFormatException("Feil i poststed (kun bokstaver (A-Å)");
        }
        return true;
    }

    private boolean sjekkPoststed(String poststed){
        try{
            if(sjekkPoststedFormat(poststed)) {
                return true;
            }
        }
        catch(InvalidPoststedFormatException e){
            ugyldigeVerdier += "Feil i poststed (kun bokstaver A-Å tillatt)! \n";
        }
        return false;
    }

    private boolean sjekkTlfFormat(String tlf) throws InvalidTlfnrFormatException{
        if(!Pattern.matches("[0-9]+",tlf) || tlf.length() != 8 || tlf.isEmpty()){
            throw new InvalidTlfnrFormatException("Feil i tlfnr, kun tall mellom 0-9 tillat!");
        }
        return true;
    }

    private boolean sjekkTlf(String tlf){
        try{
            if(sjekkTlfFormat(tlf)){
                return true;
            }
        }
        catch(InvalidTlfnrFormatException e){
            ugyldigeVerdier += "Feil i tlfnr, kun tall mellom 0-9 tillat! \n";
        }
        return false;
    }


    private static boolean sjekkEpostFormat(String epost) throws InvalidEpostFormatException{
        if(!Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$",epost) || epost.isEmpty()){
            throw new InvalidEpostFormatException("Feil i epost format, riktig ex. kari@test.no");
        }
        return true;
    }

    private boolean sjekkEpost(String epost){
        try{
            if(sjekkEpostFormat(epost)){
                return true;
            }
        }
        catch(InvalidEpostFormatException e){
            ugyldigeVerdier += "Feil i epost format, riktig ex. kari@test.no \n";
        }
        return false;
    }

    private boolean sjekkLengdeFormat(String erfaring, String referanse) throws InvalidLengdeException{
        if((!Pattern.matches("[a-zæøåA-ZÆØÅ0-9. ]{5,40}+",erfaring) || erfaring.isEmpty()) ||
        (!Pattern.matches("[a-zæøåA-ZÆØÅ0-9. ]{0,30}+",referanse))){
            throw new InvalidLengdeException("Erfaring og Refereanse kan inneholde maks 20 tegn");
        }
        return true;
    }

    private boolean sjekkLengde(String erfaring, String referanse){
        try{
            if(sjekkLengdeFormat(erfaring,referanse)){
                return true;
            }
        }
        catch(InvalidLengdeException e){
            ugyldigeVerdier += "Maks 20 tegn tillatt i erfaring og referanse";
        }
        return false;
    }

    private boolean sjekkAlderFormat(String alder) throws InvalidAlderFormatException{
        if(!Pattern.matches("[1-9]{2}+",alder) || alder.isEmpty()){
            throw new InvalidAlderFormatException("Ugyldig alder");
        }
        return true;
    }

    private boolean sjekkAlder(String alder){
        try{
            if(sjekkAlderFormat(alder)){
                return true;
            }
        }
        catch(InvalidAlderFormatException e){
            ugyldigeVerdier += "Ugyldig alder \n";
        }
        return false;
    }

    private boolean sjekkLonnskravFormat(String lonnskrav) throws InvalidLonnskravFormatException{
        if(!Pattern.matches("[0-9]{0,7}+",lonnskrav)){
            throw new InvalidLonnskravFormatException("Lønnskrav må bestå av tall mellom 0 og 9 (maks input er i mio)");
        }
        return true;
    }

    private boolean sjekkLonnskrav(String lonnskrav){
        try{
            if(sjekkLonnskravFormat(lonnskrav)){
                return true;
            }
        }
        catch(InvalidLonnskravFormatException e){
            ugyldigeVerdier += "Lønnskrav må bestå av tall mellom 0 og 9 (maks input er i mio)";
        }
        return false;
    }

    // TODO : metode som sjekker for om utdannelse og studieretning er valgt
    // TODO : Metode som sjekker for om stillingstype er valg (heltid/deltid)
    // TODO : metoder for registrering av vikariat sjekk

}
