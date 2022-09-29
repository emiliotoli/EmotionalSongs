package emotionalsongs;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Playlist {
    private String nomeplaylist;
    private String nomebrano;
    String idutente;
    private final static String sep = System.getProperty("file.separator");


    public Playlist(){}

    private Playlist(String Id,String NomePlaylist){
        this.idutente=Id;
        this.nomeplaylist=NomePlaylist;
        //pdpdpdpdpdp
    }
    public static boolean registraPlaylist(String ID, String NomePlay) throws IOException {
        PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt", true)), true);
        StringBuilder str = new StringBuilder();
        String brano , aggiuntaCanzoni;
        int linea;
        boolean uscitaciclo=false;

        Scanner sc = new Scanner(System.in);
        if (!controlloPlaylistEsistente(ID, NomePlay)) {
            //new Playlist(idutente, nomeplaylist);
            //scriviFileRegistrazione(ID, NomePlay, ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt");
            str.append(ID + "|");
            str.append(NomePlay);

            do{
                System.out.println("Vuoi aggiungere canzoni alla Playlist? Digitare si o no");
                sc.reset();
                aggiuntaCanzoni= sc.next().trim().toLowerCase();
                switch (aggiuntaCanzoni){
                    case "si":
                        int tmp = aggiuntaCanzoniServ();
                        if(tmp>0)
                            str.append("|" + insertByLine(tmp));
                        break;
                    case "no":
                        uscitaciclo=true;
                        break;
                    default:
                        uscitaciclo=false;
                        break;
                }

            }while(!uscitaciclo);
            System.out.println(str);
            wr.println(str.toString());
            wr.close();
            return true;
        }
        System.out.println("PLAYLIST GIA' ESISTENTE!");
        return false;
    }

    private static int aggiuntaCanzoniServ() throws IOException {
        String brano ;
        Scanner sc = new Scanner(System.in);
        boolean uscitaciclo;
        int linea;
        System.out.println("Cerca canzone da aggiungere alla playlist: ");
        brano = sc.next();
        if(!Canzoni.ricercaCanzoni(brano)){
            System.out.println("nessuna canzone trovata");
            uscitaciclo=false;
            return -1;
        }
        else {
            System.out.println("inserisci codice canzone da aggiungere");
            linea = sc.nextInt();
            while(linea<0 || linea> Canzoni.numeroTotaleCanzoni()) {
                System.out.println("hai inserito un numero errato! riprova");
                linea = sc.nextInt();
            }
            //str.append("|" + insertByLine(linea));
            return linea;
        }

    }
    /*private static void inserisciCanzone(String str) throws IOException {
        String srv;
        String[] spl;
        if(Canzoni.controlloCanzoneEsistente(str)){
            BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt"));
            Canzoni.ricercaCanzoni(str);
            System.out.println("inserisci codice canzone da aggiungere: ");

        }

    }*/
    public static void scriviFileRegistrazione(String ID , String testo, String filePath,  String[] canzoni) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(ID);
        bw.write("\\|");
        bw.write(testo);        //scrivo l'id e il nome della playlist nel file separati dal carattere '|'
        for(int i=0;i<canzoni.length;i++){
            if(esisteCanzone(canzoni[i])) {
                bw.write(canzoni[i]);
                bw.write("\\|");        //dopo aver scritto sul file il nome della canzone lo separo con il carattere '|' in moddo che risulti separato dalla canzone successiva
            }
        }
        bw.newLine();
        bw.flush(); //svuoto lo stream
        bw.close();
    }

    public static boolean controlloPlaylistEsistente(String IdUtente, String NomePlay) throws IOException {             //metodo che ritorna true se esiste, false se non esiste
        FileReader fread = new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        String sup;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            if (IdUtente.equals(supporto[0].trim().toLowerCase()) && NomePlay.equals(supporto[1].trim())) {
                return true;
            }
        }
        return false;
    }

    public String toString(String Idutente,String NomePlay){
        return Idutente+"|"+NomePlay;
    }

    /*public void aggiungiCanzone(String titolo , String playList , String idUtente) throws IOException {
        boolean esito=false;
        String[] a;
        BufferedReader playlistReader = new BufferedReader(new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt"));
        String sup;
        while((sup= playlistReader.readLine())!=null) {
            a=sup.split("//|");
            if(a[1].equals(playList) && a[0].equals(idUtente) && esisteCanzone(titolo)){


            }
        }

       // return true; //messo solo per non generare errore
    }

     */

    private static boolean esisteCanzone(String nomeCanzone) throws IOException {
        BufferedReader songReader= new BufferedReader(new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt"));
        String[] a;
        String sup;
        while((sup=songReader.readLine())!=null)
        {
        a=sup.split("\\|");
            if(a[0].equals(nomeCanzone))
                return true;
        }
        return false;
    }

    private static String insertByLine(int linea) throws IOException {
        String srv;
        String[] spl;
        FileReader fr = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        BufferedReader br= new BufferedReader(fr);
        String titolo=" ";
        int i=1;
        while((srv=br.readLine())!= null){
            spl=srv.split("\\|");
            if(i==linea)
            {
                titolo=spl[0];
                break;
            }
            i++;
        }
        return titolo;
    }

    public static void aggiungiCanzonePlaylist(String playlist , String uid) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder stb = new StringBuilder();
        if(!controlloPlaylistEsistente(uid,playlist)){
            System.out.println("Playlist non esistente!");
            //return false;
        }
        else {
            System.out.println("Inserisci titolo canzone:");
            String titolo = sc.next();
            if (!Canzoni.controlloCanzoneEsistente(titolo)) {
                System.out.println("Canzone non esistente");
                //return false;
            } else {
                BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
                String sup;
                String[] spl;

                while ((sup = br.readLine()) != null) {
                    spl=sup.split("\\|");
                    if(spl[0].equals(uid) && spl[1].equals(playlist)){
                        sup+= "|" + titolo;
                        stb.append(sup +"\n");
                    }
                    else{
                        stb.append(sup + "\n");
                    }
                }
                br.close();
            }
        }
        BufferedWriter wr = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt" , false));
        PrintWriter pw = new PrintWriter(wr , true);
        String tmp = stb.toString();
        pw.println(tmp);
        System.out.println("Inserimento andato a buon fine!");
        //return true;
        wr.close();

    }
    public static void aggiungiDopoInPlaylist(String playlist , String uid) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder stb=new StringBuilder();
        boolean uscitaciclo=false;
        String uscitaCicloInterno;
        String scelta = "si";
        if(!controlloPlaylistEsistente(uid,playlist)){
            System.out.println("Playlist non esistente!");
            //return false;
        }
        else{
            do {
                stb.setLength(0);
                if (scelta.trim().toLowerCase().equals("si")) {
                    int linea = aggiuntaCanzoniServ();
                    BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
                    String sup;
                    String[] spl;
                    while ((sup = br.readLine()) != null) {
                        spl = sup.split("\\|");
                        if (spl[0].equals(uid) && spl[1].equals(playlist)) {
                            sup += "|" + insertByLine(linea);
                            stb.append(sup).append("\n");
                        } else {
                            stb.append(sup).append("\n");
                        }
                    }
                    System.out.println("inserire altre canzoni? Digitare si o no");
                    uscitaCicloInterno = sc.next();
                    if (uscitaCicloInterno.trim().toLowerCase().equals("si"))
                        uscitaciclo = false;
                    else
                        uscitaciclo = true;
                }
            }while(!uscitaciclo);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt" , false)),true);
        String tmp = stb.toString();
        pw.println(tmp);
        System.out.println("inserimento andato a buon fine si spera");
    }

    public static boolean eliminaCanzoneDaPlaylist(String uid , String nomeplaylist , String titolo) throws IOException {
        StringBuilder stb;
        if(!controlloPlaylistEsistente(uid,nomeplaylist)){
            System.out.println("Playlist non esistente!");
            return false;
        }
        else {
            if (!Canzoni.controlloCanzoneEsistente(titolo)) {
                System.out.println("Canzone non esistente");
                return false;
            } else {
                BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
                String sup;
                String[] spl;
                stb = new StringBuilder();
                while ((sup = br.readLine()) != null) {
                    spl=sup.split("\\|");
                    if(spl[0].equals(uid) && spl[1].equals(nomeplaylist)){      //se nome utente e nome playlist coincidono vado ad aggiungere sullo stringbuilder tutti i titoli delle canzoni, tranne quello che si voleva eliminare
                        stb.append(spl[0] + "|" + spl[1]);
                        for(int i=2;i<spl.length;i++){
                            if(!(spl[i].trim()).equals(titolo.trim()))
                                stb.append("|" + spl[i]);
                        }
                    }
                    else{
                        stb.append(sup + "\n");
                    }
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt" , false)),true);
        String tmp=stb.toString();
        pw.println(tmp);
        System.out.println("Cancellazione andata a buon fine!");
        return true;
    }


    public static void visualizzaPlaylistUtente(String idUtente) throws IOException{ // da vedere il senso, se si vogliono visualizzare tutte le playlist dell'utente o se si vogliono vedere la canzoni di una certa playlist
        BufferedReader bufread = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
        String sup;
        String supportoIdUtente;

        System.out.println("playlist dell'utente: ");
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            supportoIdUtente = supporto[0].trim().toLowerCase();
            if (idUtente.trim().toLowerCase(Locale.ROOT).equals(supportoIdUtente.trim().toLowerCase())){
                System.out.println(supporto[1]);
            }
            else{
                System.out.println("questo utente non ha ancora una playlist");
            }
        }
    }


    public static boolean cancellaPlaylist(String uid , String nomeplaylist) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
        String sup;
        String[] spl;
        PrintWriter pw;
        StringBuilder stb= new StringBuilder();

        if(!controlloPlaylistEsistente(uid,nomeplaylist)){
            System.out.println("La playlist che hai inserito non è esistente!");
            return false;
        }

        while((sup=br.readLine())!=null){
            spl = sup.split("\\|");
            if(!spl[1].toLowerCase().trim().equals(nomeplaylist.toLowerCase().trim())){
                stb.append(sup + "\n");
            }
        }
        pw= new PrintWriter(new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt", false)),true);
        pw.println(stb.toString());
        System.out.println("Cancellazione andata a buon fine!");
        return true;
    }


}
