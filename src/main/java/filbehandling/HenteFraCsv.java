package filbehandling;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class HenteFraCsv extends HenteFraFil {

    public void henteFraFil(String path){
        StringBuffer sb = new StringBuffer();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){

            // leser kun nå de 30 første linjene
            for(int i = 0; i < 30; i++){
                sb.append(lesFil.readLine());
                sb.append("\n");
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }

        System.out.println(sb);
    }
}
