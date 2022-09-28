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

    public static void cercaBranoMusicaleTitolo(String Titolo,boolean loggato) throws IOException {

        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        //LineNumberReader lnread=new LineNumberReader(fread); // restituisce il numero della linea dove trova la canzone
        boolean trovato = false;
        String sup;
        String AutoreFile = null;
        String AnnoFile = null;
        String TitoloFile = null;
        String[] supporto=null;

        while ((sup = bufread.readLine()) != null) {
            supporto = sup.split("\\|");
            TitoloFile = supporto[0].trim().toLowerCase();
            if (Titolo.equals(TitoloFile.trim().toLowerCase())) {
                AutoreFile = supporto[1].trim().toLowerCase();
                AnnoFile = supporto[2].trim().toLowerCase();
                trovato = true;
                break;
            }
        }

        if (trovato) {
            //menu con visualizza emozione e se loggato inserisci emozione terzavozione menu principale
            int varswich;
            System.out.println("inormazioni canzone \n");
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
            System.out.println("\n");
            do{
                System.out.println("Digita 1:  per visualizzare le emozioni associate a questa canzone--> ");
                System.out.println("Digita 2:  per inserire le emozioni che hai provato ascoltando questa canzone--> ");
                System.out.println("NOTA--> per inserire emozioni di devi prima loggare");
                System.out.println("Digita 3:  per tornare al menu principale--> ");
                System.out.print("scelta: ");
                varswich = Integer.parseInt(br.readLine());
                switch (varswich){
                    case 1:
                        System.out.println("ora puoi visualizzare le emozioni");
                        Emozioni.visualizzaEmozioni(supporto[0]);
                        break;
                    case 2:
                        if(loggato){
                            System.out.println("inizio procedureper inserire le emozioni");
                            Emozioni.inserisciEmozioni(supporto[0]);
                            System.out.println("registrazione emozione effetuata con successo\n");
                        }
                        else{
                            System.out.println("ti devi ancora loggare");
                        }
                        break;
                    case 3:
                        varswich=0;
                }
            }while(varswich!=0);

        } else {
            System.out.println("Canzone non trovata");
        }
        bufread.close();

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

    public static void cercaBranoMusicaleAutoreAnno(String Autore, String Anno, boolean loggato) throws IOException { //vers1

        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        boolean trovato = false;
        String sup;
        String TitoloFile = null;
        String AutoreFile = null;
        String AnnoFile = null;

        while ((sup = bufread.readLine()) != null) {

            String[] supporto = sup.split("\\|");
            TitoloFile =supporto[0].trim().toLowerCase();
            AutoreFile = supporto[1].trim().toLowerCase();
            AnnoFile = supporto[2].trim().toLowerCase();
            if (Autore.equals(AutoreFile.trim().toLowerCase()) && Anno.equals(AnnoFile.trim().toLowerCase())) {
                trovato = true;
                break;
            }
        }
        if (trovato) {
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
            int varswich;
            do{

                System.out.println("Digita 1:  per visualizzare le emozioni associate a questa canzone--> ");
                System.out.println("Digita 2:  per inserire le emozioni che hai provato ascoltando questa canzone--> ");
                System.out.println("NOTA--> per inserire emozioni di devi prima loggare");
                System.out.println("Digita 3:  per tornare al menu principale--> ");
                System.out.print("scelta: ");
                varswich = Integer.parseInt(br.readLine());
                switch (varswich){
                    case 1:
                        System.out.println("ora puoi visualizzare le emozioni");
                        Emozioni.visualizzaEmozioni(TitoloFile);
                        break;
                    case 2:
                        if(loggato){
                            System.out.println("inizio procedura inserimento emozioni: \n");
                            Emozioni.inserisciEmozioni(TitoloFile);
                            System.out.println("registrazione Emozioni effuttata con successo \n");
                        }
                        else{
                            System.out.println("ti devi ancora loggare");
                        }
                        break;
                    case 3:
                        varswich=0;
                }
            }while(varswich!=0);


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
            if(supporto[0].toLowerCase().trim().equals(titolo.toLowerCase().trim()));
            {
                return true;
            }
        }
        return false;
    }

    public static boolean ricercaCanzoni(String titolo) throws IOException {

            FileReader fr = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
            BufferedReader br= new BufferedReader(fr);
            String srv;
            String[] spl;
            int linea=1;
            boolean trovato=false;
            while((srv=br.readLine())!=null)
            {
                spl=srv.split("\\|");
                if(spl[0].contains(titolo) || spl[0].trim().toLowerCase().equals(titolo)){
                    System.out.println(spl[0]);
                    System.out.println(spl[1]);
                    System.out.println(spl[2]);
                    System.out.println("La canzone ha codice: " + linea);
                    trovato=true;
                }
                linea++;
                //da usare anche in inserisci emozioni
            }
            return trovato;
    }

    public static int numeroTotaleCanzoni() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
        lnr.skip(Long.MAX_VALUE);
        return (lnr.getLineNumber() + 1);
    }
}

