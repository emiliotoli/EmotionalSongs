package emotionalsongs;

import java.io.*;

public class Canzoni {
    private String titolo, autore,anno,album,durata,genere;
    static InputStreamReader isr=new InputStreamReader(System.in);
    static BufferedReader br=new BufferedReader(isr);

    public Canzoni(){}

    private Canzoni(String Titolo , String Autore , String Anno,String Album,String Durata,String Genere)
    {
        this.titolo=Titolo;
        this.autore=Autore;
        this.anno=Anno;
        this.album=Album;
        this.durata=Durata;
        this.genere=Genere;
    }

    public void creaCanzone(String Titolo , String Autore , String Anno, String Album, String Durata, String Genere)
    {
        new Canzoni(Titolo,Autore,Anno,Album,Durata,Genere);
    }

    public String ToString( String Titolo,String Autore,String Anno,String Album,String Durata,String Genere ){

        return Titolo+"|"+Autore+ "|" +Anno+"|"+Album+"|"+Durata+"|"+Genere;

    }

    public static void ScriviFile(String testo, String filePath) throws IOException {
        //FileWriter fw=new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(testo);
        bw.newLine();
        bw.flush(); //svuoto lo stream
        bw.close();
    }

}

