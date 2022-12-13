package emotionalsongs;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Emilio Toli
 *         la classe serve per lavorare sulla playlist.
 */
public class Playlist {
    private String nomeplaylist;
    private String nomebrano;
    String idutente;
    private final static String sep = System.getProperty("file.separator");

    /**
     * @param ID       id dell'utente al quale viene associata la playlist
     * @param NomePlay nome della playlist da registrare
     * @return true o false a seconda che la registrazione sia andata a buon fine o
     *         meno
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Metodo che inserisce una nuova playlist e nuove canzoni all'interno
     *         di essa
     */
    // <editor-fold desc="Registrazione Playlist">
    public static boolean registraPlaylist(String ID, String NomePlay) throws IOException {
        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(path)), true);
        StringBuilder str = new StringBuilder();
        String brano, aggiuntaCanzoni;
        int linea;
        boolean uscitaciclo = false;

        Scanner sc = new Scanner(System.in);
        if (!controlloPlaylistEsistente(ID, NomePlay)) {
            str.append(ID + "|");
            str.append(NomePlay);

            do {
                System.out.println("Vuoi aggiungere canzoni alla Playlist? Digitare si o no");
                sc.reset();
                aggiuntaCanzoni = sc.next().trim().toLowerCase();
                switch (aggiuntaCanzoni) {
                    case "si":
                        int tmp = aggiuntaCanzoniServ();
                        if (tmp > 0)
                            str.append("|" + insertByLine(tmp));
                        break;
                    case "no":
                        uscitaciclo = true;
                        break;
                    default:
                        uscitaciclo = false;
                        break;
                }

            } while (!uscitaciclo);
            System.out.println(str);
            wr.println(str.toString());
            wr.close();
            sc.close();
            return true;
        }
        System.out.println("PLAYLIST GIA' ESISTENTE!");
        sc.close();
        wr.close();
        return false;
    }
    // </editor-fold>

    /**
     * @return ritorna il numero di linea che identifica univocamente una
     *         canzone,per poterla poi inserire nella playlist
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Metodo privato che viene chiamato da metodi pubblici per selezionare
     *         canzoni dato il titolo o una parte di esso
     */
    // <editor-fold desc="Aggiungi Canzone">
    private static int aggiuntaCanzoniServ() throws IOException {
        String brano;
        Scanner sc = new Scanner(System.in);
        boolean uscitaciclo;
        int linea;
        System.out.println("Cerca canzone da aggiungere alla playlist: ");
        brano = sc.next();
        if (!Canzoni.ricercaCanzoni(brano)) {
            System.out.println("nessuna canzone trovata");
            uscitaciclo = false;
            sc.close();
            return -1;
        } else {
            System.out.println("inserisci codice canzone da aggiungere");
            linea = sc.nextInt();
            while (linea < 0 || linea > Canzoni.numeroTotaleCanzoni()) {
                System.out.println("hai inserito un numero errato! riprova");
                linea = sc.nextInt();
            }
            sc.close();
            return linea;
        }

    }
    // </editor-fold>

    /**
     * @param IdUtente id dell'utente associato alla playlist
     * @param NomePlay nome della playlist da verificare l'esistenza
     * @return true o false a seconda che la playlist sia gia'esistente o meno
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     */
    // <editor-fold desc="Controllo Playlist Esistente">
    public static boolean controlloPlaylistEsistente(String IdUtente, String NomePlay) throws IOException { // metodo
                                                                                                            // che
                                                                                                            // ritorna
                                                                                                            // true se
                                                                                                            // esiste,
                                                                                                            // false se
                                                                                                            // non
                                                                                                            // esiste

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fread = new FileReader(path);
        BufferedReader bufread = new BufferedReader(fread);
        String sup;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            if (IdUtente.equals(supporto[0].trim().toLowerCase()) && NomePlay.equals(supporto[1].trim())) {
                return true;
            }
        }
        bufread.close();
        return false;
    }
    // </editor-fold>

    public String toString(String Idutente, String NomePlay) {
        return Idutente + "|" + NomePlay;
    }

    /**
     * @param linea inserito come parametro il numero di linea al quale si trova la
     *              canzone
     * @return ritornato il nome del titolo che verrà aggiunto poi alla playlist
     * @throws IOException eccezione input/output
     * @autor Emilio Toli
     *        Metodo che restituisce il nome della canzone dato il numero della
     *        linea in cui si trova all'interno del file per
     *        poi farla inserire nel file Playlist
     */
    // <editor-fold desc="insertByLine">
    private static String insertByLine(int linea) throws IOException {
        String srv;
        String[] spl;
        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String titolo = " ";
        int i = 1;
        while ((srv = br.readLine()) != null) {
            spl = srv.split("\\|");
            if (i == linea) {
                titolo = spl[0];
                break;
            }
            i++;
        }
        br.close();
        return titolo;
    }
    // </editor-fold>

    /**
     * @param playlist indica il nome della playlist in cui si richiede l'aggiunta
     *                 di una canzone
     * @param uid      nome utente al quale è associata la playlist
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Metodo per l'aggiunta di una canzone nella playlist, slegata dalla
     *         creazione di essa
     */
    // <editor-fold desc="Aggiunta canzine in un secondo momento">
    public static void aggiungiDopoInPlaylist(String playlist, String uid) throws IOException {

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        Scanner sc = new Scanner(System.in);
        StringBuilder stb = new StringBuilder();
        boolean uscitaciclo = false;
        String uscitaCicloInterno;
        String scelta = "si";
        if (!controlloPlaylistEsistente(uid, playlist)) {
            System.out.println("Playlist non esistente!");
            // return false;
        } else {
            do {
                stb.setLength(0);
                if (scelta.trim().toLowerCase().equals("si")) {
                    int linea = aggiuntaCanzoniServ();
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    String sup;
                    String[] spl;
                    int TOT_PLAYLISTS = Playlist.numTotPlaylist(), i = 0;

                    while ((sup = br.readLine()) != null) {
                        spl = sup.split("\\|");
                        if (spl[0].equals(uid) && spl[1].equals(playlist)) {
                            sup += "|" + insertByLine(linea);
                            if (i < TOT_PLAYLISTS)
                                sup += "\n";
                            stb.append(sup);
                        } else {
                            if (i < TOT_PLAYLISTS)
                                sup += "\n";
                            stb.append(sup);
                        }
                        i++;
                    }
                    System.out.println("inserire altre canzoni? Digitare si o no");
                    uscitaCicloInterno = sc.next();
                    if (uscitaCicloInterno.trim().toLowerCase().equals("si"))
                        uscitaciclo = false;
                    else
                        uscitaciclo = true;

                    br.close();
                }
            } while (!uscitaciclo);

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)), true);
            String tmp = stb.toString();
            pw.println(tmp);
            System.out.println("inserimento andato a buon fine si spera");
            sc.close();
            pw.close();
        }

    }
    // </editor-fold>

    /**
     * @param uid          user id associato all'utente
     * @param nomeplaylist nome della playlist in cui eliminare una canzone
     * @param titolo       nome della canzone da eliminare dalla playlist
     * @return true o false a seconda che la cancellazione sia andata a buon fine o
     *         meno
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Eliminazione di una canzone da una playlist
     */
    // <editor-fold desc="Eliminazione canzone da playlist">
    public static boolean eliminaCanzoneDaPlaylist(String uid, String nomeplaylist, String titolo) throws IOException {

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        StringBuilder stb;
        int TOT_PLAYLISTS = Playlist.numTotPlaylist(), j = 0;
        if (!Canzoni.controlloCanzoneEsistente(titolo)) {
            System.out.println("Canzone non esistente");
            return false;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String sup;
            String[] spl;
            stb = new StringBuilder();
            while ((sup = br.readLine()) != null) {
                spl = sup.split("\\|");
                j++;
                if (spl[0].equals(uid) && spl[1].equals(nomeplaylist)) { // se nome utente e nome playlist coincidono
                                                                         // vado ad aggiungere sullo stringbuilder tutti
                                                                         // i titoli delle canzoni, tranne quello che si
                                                                         // voleva eliminare
                    stb.append(spl[0] + "|" + spl[1]);
                    for (int i = 2; i < spl.length; i++) {
                        if (!(spl[i].trim()).equals(titolo.trim()))
                            stb.append("|" + spl[i]);
                        if (i >= spl.length - 1) {
                            if (j < TOT_PLAYLISTS)
                                stb.append("\n");
                        }
                    }
                } else {
                    if (j > TOT_PLAYLISTS)
                        stb.append(sup);
                    else {
                        stb.append(sup);
                        stb.append("\n");
                    }
                }

            }
            br.close();
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)), true);
        String tmp = stb.toString();
        pw.println(tmp);
        System.out.println("Cancellazione andata a buon fine!");
        pw.close();
        return true;
        // }
    }
    // </editor-fold>

    /**
     * @param idUtente nome utente di cui bisogna visualizzare le playlist
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         metodo per visualizzare tutte le playlist associate ad un id utente
     */
    // <editor-fold desc="Visualizzare playlist">
    public static void visualizzaPlaylistUtente(String idUtente) throws IOException { // da vedere il senso, se si
                                                                                      // vogliono visualizzare tutte le
                                                                                      // playlist dell'utente o se si
                                                                                      // vogliono vedere la canzoni di
                                                                                      // una certa playlist

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        BufferedReader bufread = new BufferedReader(new FileReader(path));
        String sup;
        String supportoIdUtente;

        System.out.println("playlist dell'utente: ");
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            supportoIdUtente = supporto[0].trim().toLowerCase();
            if (idUtente.trim().toLowerCase(Locale.ROOT).equals(supportoIdUtente.trim().toLowerCase())) {
                System.out.println(supporto[1]);
            } else {
                System.out.println("questo utente non ha ancora una playlist");
            }
        }
        bufread.close();
    }
    // </editor-fold>

    /**
     * @param uid          nome dell'utente associato alla playlist
     * @param nomeplaylist nome della playlist da eliminare
     * @return true o false a seconda che la cancellazione sia andata a buon fine o
     *         meno
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Eliminazione di una playlist
     */
    // <editor-fold desc="Cancellazione playlist">
    public static boolean cancellaPlaylist(String uid, String nomeplaylist) throws IOException {

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        int TOT_PLAYLIST = Playlist.numTotPlaylist(), j = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String sup;
        String[] spl;
        PrintWriter pw;
        StringBuilder stb = new StringBuilder();

        if (!controlloPlaylistEsistente(uid, nomeplaylist)) {
            System.out.println("La playlist che hai inserito non esiste!");
            return false;
        }

        while ((sup = br.readLine()) != null) {
            j++;
            spl = sup.split("\\|");
            if (!spl[1].toLowerCase().trim().equals(nomeplaylist.toLowerCase().trim())) {
                stb.append(sup);
                if (j < numTotPlaylist())
                    stb.append("\n");
            }
        }
        pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)), true);
        pw.println(stb.toString());
        System.out.println("Cancellazione andata a buon fine!");
        br.close();
        pw.close();
        return true;
    }
    // </editor-fold>

    /**
     * @return ritorna il numero totale di playlist
     * @throws IOException eccezione input/output
     * @author Emilio Toli
     *         Metodo che ritorna il numero totale di playlist nel file
     */
    // <editor-fold desc="Totale di playlist">
    private static int numTotPlaylist() throws IOException {
        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        lnr.close();
        return (lnr.getLineNumber()) - 1;
    }
    // </editor-fold>

    /**
     * @author Emilio Toli
     * @param UID          nome utente
     * @param nomeplaylist nome della playlist di cui visualizzare le canzoni
     * @throws IOException eccezione input/output
     *                     Metodo che visualizza i titoli delle canzoni presenti in
     *                     una playlist
     */
    // <editor-fold desc="Visualizza canzoni in una playlist">
    public static void visualizzaCanzoniPlaylist(String UID, String nomeplaylist) throws IOException {

        File file = new File("data" + sep + "Playlist.dati.txt");
        String path = file.getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String sup;
        String[] spl;
        while ((sup = br.readLine()) != null) {
            spl = sup.split("\\|");
            if (spl[0].equals(UID) && spl[1].equals(nomeplaylist)) {
                if (spl.length > 2) {
                    for (int i = 2; i < spl.length; i++) {
                        System.out.println(spl[i]);
                    }
                } else {
                    System.out.println("Nessuna canzone presente nella playlist");
                }
            }
        }
        br.close();
    }
    // </editor-fold>

}
