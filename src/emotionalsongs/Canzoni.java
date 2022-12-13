package emotionalsongs;

import javax.swing.*;
import java.io.*;
import java.util.*;


/**
 * @author Stefano Farina
 * la classe serve per ricercare una canzone nel file Canzoni.dati
 */
public class Canzoni {

    //<editor-fold desc="Attributi">
    private String titolo, autore, anno, album, durata, genere;
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    private final static String sep = System.getProperty("file.separator");
    //</editor-fold>

    public String toString(String Titolo, String Autore, String Anno, String Album, String Durata, String Genere) {
        return Titolo + "|" + Autore + "|" + Anno + "|" + Album + "|" + Durata + "|" + Genere;
    }

    /**
     * @param Titolo
     * @param loggato
     * @throws IOException
     * @author Stefano Farina
     * ricerca la canzone, per il titolo passato come parametro al metodo, nel file Canzoni.dati
     */
    //<editor-fold desc="Ricerca Canzone per Titolo">
    public static void cercaBranoMusicaleTitolo(String Titolo, boolean loggato) throws IOException {

        File file = new File("data" + sep + "Canzoni.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fread = new FileReader(path);
        BufferedReader bufread = new BufferedReader(fread);
        boolean trovato = false;
        String sup;
        String AutoreFile = null;
        String AnnoFile = null;
        String TitoloFile = null;
        String[] supporto = null;

        while ((sup = bufread.readLine()) != null) {
            supporto = sup.split("\\|");
            TitoloFile =supporto[0].trim().toLowerCase();
            if (Titolo.equals(TitoloFile.trim().toLowerCase())) {
                AutoreFile = supporto[1].trim().toLowerCase();
                AnnoFile = supporto[2].trim().toLowerCase();
                trovato = true;
                break;
            }
        }
        // se trova la canzone visualizza le sue informazioni che si trovano nel file e poi visualizza un menu con le operazioni che puoi fare
        if (trovato) {
            int varswich;
            System.out.println("inormazioni canzone \n");
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
            System.out.println("\n");
            //menu con: visualizza emozione e se loggato inserisci emozione terza voce menu principale
            do {
                System.out.println("Digita 1:  per visualizzare le emozioni associate a questa canzone--> ");
                System.out.println("Digita 2:  per inserire le emozioni che hai provato ascoltando questa canzone--> ");
                System.out.println("NOTA--> per inserire le emozioni di devi prima loggare");
                System.out.println("Digita 3:  per tornare al menu principale--> ");
                System.out.print("scelta: ");
                varswich = Integer.parseInt(br.readLine());
                switch (varswich) {
                    case 1:
                        System.out.println("ora puoi visualizzare le emozioni");
                        Emozioni.visualizzaEmozioni(supporto[0]);
                        break;
                    case 2:
                        if (loggato) {
                            System.out.println("inizio procedureper inserire le emozioni");
                            Emozioni.inserisciEmozioni(supporto[0]);
                            System.out.println("registrazione emozione effetuata con successo\n");
                        } else {
                            System.out.println("ti devi ancora loggare");
                        }
                        break;
                    case 3:
                        varswich = 0;
                }
            } while (varswich != 0);

        } else {
            System.out.println("Canzone non trovata");
        }
        bufread.close();

    }
    //</editor-fold>


    /**
     * @param Autore
     * @param Anno
     * @param loggato
     * @throws IOException
     * @author Stefano Farina
     * ricerca la canzone per autore ed anno, ne file Canzoni.dati
     */
    //<editor-fold desc="Ricerca canzone per Autore ed Anno">
    public static void cercaBranoMusicaleAutoreAnno(String Autore, String Anno, boolean loggato) throws IOException {

        File file = new File("data" + sep + "Canzoni.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fread = new FileReader(path);
        BufferedReader bufread = new BufferedReader(fread);
        boolean trovato = false;
        String sup;
        String TitoloFile = null;
        String AutoreFile = null;
        String AnnoFile = null;

        while ((sup = bufread.readLine()) != null) {

            String[] supporto = sup.split("\\|");
            TitoloFile = supporto[0].trim().toLowerCase();
            AutoreFile = supporto[1].trim().toLowerCase();
            AnnoFile = supporto[2].trim().toLowerCase();
            if (Autore.equals(AutoreFile.trim().toLowerCase()) && Anno.equals(AnnoFile.trim().toLowerCase())) {
                trovato = true;
                break;
            }
        }
        // se trova la canzone stampa le info relative ad essa e visualizza un menu
        if (trovato) {
            System.out.println("Titolo: " + TitoloFile);
            System.out.println("Autore: " + AutoreFile);
            System.out.println("Anno: " + AnnoFile);
            int varswich;
            // menu--> le operazioni che un utente può fare dopo aver trovato la canzone e se è loggato
            do {
                System.out.println("Digita 1:  per visualizzare le emozioni associate a questa canzone--> ");
                System.out.println("Digita 2:  per inserire le emozioni che hai provato ascoltando questa canzone--> ");
                System.out.println("NOTA--> per inserire emozioni di devi prima loggare");
                System.out.println("Digita 3:  per tornare al menu principale--> ");
                System.out.print("scelta: ");
                varswich = Integer.parseInt(br.readLine());
                switch (varswich) {
                    case 1:
                        System.out.println("ora puoi visualizzare le emozioni");
                        Emozioni.visualizzaEmozioni(TitoloFile);
                        break;
                    case 2:
                        if (loggato) {
                            System.out.println("inizio procedura inserimento emozioni: \n");
                            Emozioni.inserisciEmozioni(TitoloFile);
                            System.out.println("registrazione Emozioni effuttata con successo \n");
                        } else {
                            System.out.println("ti devi ancora loggare");
                        }
                        break;
                    case 3:
                        varswich = 0;
                }
            } while (varswich != 0);


        } else {
            System.out.println("Canzone non trovata");
        }
        bufread.close();
    }
    //</editor-fold>


    /**
     * @param titolo
     * @return vero/falso dipende se la canzone esiste o no nel file
     * @throws IOException
     * @author Stefano Farina
     * controlla se una canzone è presente o meno nel file
     */
    //<editor-fold desc="Controllo canzone esistente">
    public static boolean controlloCanzoneEsistente(String titolo) throws IOException {

        File file = new File("data" + sep + "Canzoni.dati.txt");
        String path = file.getAbsolutePath();
        boolean esiste = false;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        String[] supporto;
        while ((str = br.readLine()) != null) {
            supporto = str.split("\\|");
            if ((supporto[0].toLowerCase().trim()).equals(titolo.toLowerCase().trim()))
            {
                return true;
            }
        }
        return false;
    }
    //</editor-fold>


    /**
     * @param titolo
     * @return vero/falso se trova o meno la canzone, questo risultato servirà in un altro metodo
     * @throws IOException
     * @author Stefano Farina
     * ricerca la canzone e mi dice il numero della riga in cui si trova nel file
     */
    //<editor-fold desc="Ricerca canzone e numero riga">
    public static boolean ricercaCanzoni(String titolo) throws IOException {

        File file = new File("data" + sep + "Canzoni.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String srv;
        String[] spl;
        int linea = 1;
        boolean trovato = false;
        while ((srv = br.readLine()) != null) {
            spl = srv.split("\\|");
            if (spl[0].contains(titolo) || spl[0].trim().toLowerCase().equals(titolo)) {
                System.out.println(spl[0]);
                System.out.println(spl[1]);
                System.out.println(spl[2]);
                System.out.println("La canzone ha codice: " + linea);
                trovato = true;
            }
            linea++;
        }
        return trovato;
    }
    //</editor-fold>


    /**
     * @return mi restituisce il numero totale di canzoni presenti nel file
     * @throws IOException
     * @author Stefano Farina
     * conta le canzoni nel file e mi dice quante sono
     */
    //<editor-fold desc="numero canzoni nel file">
    public static int numeroTotaleCanzoni() throws IOException {

        File file = new File("data" + sep + "Canzoni.dati.txt");
        String path = file.getAbsolutePath();
        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        return (lnr.getLineNumber() + 1);
    }
    //</editor-fold>

}

