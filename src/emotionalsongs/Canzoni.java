package emotionalsongs;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Canzoni {
    private String titolo, autore, anno, album, durata, genere;
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private final static String sep = System.getProperty("file.separator");


    public Canzoni() {
    }

   /* private Canzoni(String Titolo, String Autore, String Anno, String Album, String Durata, String Genere) {
        this.titolo = Titolo;
        this.autore = Autore;
        this.anno = Anno;
        this.album = Album;
        this.durata = Durata;
        this.genere = Genere;
    }*/

    public void creaCanzone(String Titolo, String Autore, String Anno, String Album, String Durata, String Genere) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
        br.write(toString(Titolo, Autore, Anno, Album, Durata, Genere));
    }

    public String toString(String Titolo, String Autore, String Anno, String Album, String Durata, String Genere) {

        return Titolo + "|" + Autore + "|" + Anno + "|" + Album + "|" + Durata + "|" + Genere;

    }

    public static void ScriviFile(String testo, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(testo);
        bw.newLine();
        bw.flush(); //svuoto lo stream
        bw.close();
    }

    public void cercaBranoMusicaleTitolo(String Titolo) throws IOException {

        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        //LineNumberReader lnread=new LineNumberReader(fread); // restituisce il numero della linea dove trova la canzone
        boolean trovato = false;
        String sup;
        String AutoreFile = null;
        String AnnoFile = null;
        String TitoloFile = null;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            TitoloFile = supporto[0].trim().toLowerCase();
            if (Titolo.equals(TitoloFile.trim().toLowerCase())) {
                AutoreFile = supporto[1].trim().toLowerCase();
                AnnoFile = supporto[2].trim().toLowerCase();
                trovato = true;
                break;
            }
        }
        if (trovato) {
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
            //richiama metodo visua emozioni
            //if loggato richiam metodo inserisci emozioni

        } else {
            System.out.println("Canzone non trovata");
        }
        br.close();

    }

    public static void cercaBranoMusicaleDalTitolo(String titolo) throws IOException {

        BufferedReader bufread = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
        boolean trovato=false;
        String str;

        while((str= bufread.readLine())!=null){
            String[] supporto=str.split("//|");
            if((supporto[0].toLowerCase()).contains(titolo.toLowerCase()))   //controlla se la stringa inserita come titolo sia compresa nel titolo della canzone
            {
                System.out.println(supporto[0]);
            }
        }

    }

    public static void visualizzaInformazioniBrano(String titolo) throws IOException {
        BufferedReader bufread = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
        String str;
        String[] supporto;
        boolean trovato=false;
        while((str=bufread.readLine())!=null)
        {
            if(trovato)
                break;
            supporto=str.split("//|");
            if(supporto[0].equals(titolo))
            {
                trovato=true;
                System.out.println("titolo: " + supporto[0]);
                System.out.println("autore: " + supporto[1]);
                System.out.println("anno: " + supporto[2]);
            }
        }
    }

    //public static String visualizzaEmozioniBrano(String titolo){}

    public void cercaBranoMusicaleAutoreAnno(String Autore, String Anno) throws IOException {

        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        boolean trovato = false;
        String sup;
        String TitoloFile = null;
        String AutoreFile = null;
        String AnnoFile = null;

        while ((sup = bufread.readLine()) != null) {

            String[] supporto = sup.split("\\|");
            AutoreFile = supporto[1].trim().toLowerCase();
            AnnoFile = supporto[2].trim().toLowerCase();
            if (Autore.equals(AutoreFile.trim().toLowerCase()) && Anno.equals(AnnoFile.trim().toLowerCase())) {
                TitoloFile = supporto[0].trim().toLowerCase();
                trovato = true;
                break;
            }
        }
        if (trovato) {
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
        } else {
            System.out.println("Canzone non trovata");
        }
        bufread.close();
    }


    public static boolean controlloCanzoneEsistente(String titolo) throws IOException {
        boolean esiste=false;
        BufferedReader br= new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
        String str;
        String[] supporto;
        while((str=br.readLine())!=null){
            supporto=str.split("\\|");
            if(supporto[0].equals(titolo))
            {
                return true;
            }
        }
        return false;
    }
}

