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

    public void registraPlaylist(String ID, String NomePlay) throws IOException {
        PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt", true)), true);
        StringBuilder str = new StringBuilder();
        String brano , aggiuntaCanzoni="";
        int linea;
        boolean uscitaciclo=true;

        Scanner sc = new Scanner(System.in);
        if (!controlloPlaylistEsistente(ID, NomePlay)) {
            //new Playlist(idutente, nomeplaylist);
            //scriviFileRegistrazione(ID, NomePlay, ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt");
            str.append(ID + "|");
            str.append(NomePlay);
            System.out.println("Cerca canzone da aggiungere alla playlist: ");
            brano = sc.nextLine();
            do{
                System.out.println("Vuoi aggiungere altre canzoni alla lista? Digitare si o no");
                aggiuntaCanzoni= sc.nextLine().trim().toLowerCase();
                switch (aggiuntaCanzoni){
                    case "si":
                        System.out.println("Cerca altra canzone da aggiungere alla playlist: ");
                        brano = sc.nextLine();
                        if(!Canzoni.ricercaCanzoni(brano)){
                            System.out.println("nessuna canzone trovata");
                            uscitaciclo=false;
                        }
                        else {
                            System.out.println("inserisci codice canzone da aggiungere");
                            linea = sc.nextInt();
                            while(linea<0 || linea> Canzoni.numeroTotaleCanzoni()) {
                                System.err.println("hai inserito un numero errato! riprova");
                                linea = sc.nextInt();
                            }
                            str.append("|" + insertByLine(linea));
                        }
                        break;
                    case "no":
                        uscitaciclo=true;
                        break;
                    default:
                        System.err.println("errore nell'inserimento!");
                        System.out.println("Si prega di inserire correttamente si oppure no ");
                        break;
                }

            }while(!uscitaciclo);
            /*do{
                System.out.println("Cerca canzone da aggiungere alla playlist: ");
                choice = sc.nextLine();
                if(!Canzoni.ricercaCanzoni(choice))
                    System.out.println("nessuna canzone trovata");
                else{
                    System.out.println("inserisci codice canzone da aggiungere");
                    linea=sc.nextInt();
                    str.append("|" + insertByLine(linea)); //controllare che il numero di linea inserito non sballi tutto pd!
                }

            }while(!choice.equals("FINE INSERIMENTO"));*/
            wr.println(str.toString());
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

    public static boolean controlloPlaylistEsistente(String IdUtente, String NomePlay) throws IOException {
        FileReader fread = new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        boolean esistente = false;
        String sup;
        String NomePlayListFile;
        String idutenteFile;

        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            idutenteFile = supporto[0].trim().toLowerCase();
            NomePlayListFile = supporto[1].trim().toLowerCase();

            if ( IdUtente.equals(idutenteFile) && NomePlay.equals(NomePlayListFile) ) {
                return true;
            }
        }
        return false;
       /* if (esistente) {
            System.out.println("Nome playlist gia esistente per quel id Utente");
        }
        else {
            System.out.println("play list aggiuta con successo.");
        }*/

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

    private static boolean aggiungiCanzonePlaylist(String playlist , String titolo , String uid) throws IOException {

        StringBuilder stb;
        if(!controlloPlaylistEsistente(uid,playlist)){
            System.err.println("Playlist non esistente!");
            return false;
        }
        else {
            if (!Canzoni.controlloCanzoneEsistente(titolo)) {
                System.err.println("Canzone non esistente");
                return false;
            } else {
                BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
                String sup;
                String[] spl;
                stb = new StringBuilder();
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
            }
        }
        BufferedWriter wr = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt" , false));
        wr.write(stb.toString());
        System.out.println("Inserimento andato a buon fine!");
        return true;
    }

    public static boolean eliminaCanzoneDaPlaylist(String uid , String nomeplaylist , String titolo) throws IOException {
        StringBuilder stb;
        if(!controlloPlaylistEsistente(uid,nomeplaylist)){
            System.err.println("Playlist non esistente!");
            return false;
        }
        else {
            if (!Canzoni.controlloCanzoneEsistente(titolo)) {
                System.err.println("Canzone non esistente");
                return false;
            } else {
                BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
                String sup;
                String[] spl;
                stb = new StringBuilder();
                while ((sup = br.readLine()) != null) {
                    spl=sup.split("\\|");
                    if(spl[0].equals(uid) && spl[1].equals(nomeplaylist)){      //se nome utente e nome playlist coincidono vado ad aggiungere sullo stringbuilder tutti i titoli delle canzoni, tranne quello che si voleva eliminare
                        for(int i=2;i<spl.length;i++){
                            stb.append(spl[0] + "|" + spl[1]);
                            if(!spl[i].equals(titolo))
                                stb.append("|" + spl[i]);
                        }
                    }
                    else{
                        stb.append(sup + "\n");
                    }
                }
            }
        }
        BufferedWriter wr = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt" , false));
        wr.write(stb.toString());
        System.out.println("Cancellazione andata a buon fine!");
        return true;
    }


    public static void visualizzaPlaylist(String idUtente) throws IOException{ // da vedere il senso, se si vogliono visualizzare tutte le playlist dell'utente o se si vogliono vedere la canzoni di una certa playlist
        BufferedReader bufread = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt"));
        String sup;
        String supportoIdUtente;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            supportoIdUtente = supporto[0].trim().toLowerCase();
            if (idUtente.equals(supportoIdUtente.trim().toLowerCase())){

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
        BufferedWriter wr;
        StringBuilder stb= new StringBuilder();

        if(!controlloPlaylistEsistente(uid,nomeplaylist)){
            System.err.println("La playlist che hai inserito non è esistente!");
            return false;
        }

        while((sup=br.readLine())!=null){
            spl = sup.split("//|");
            if(!spl[1].equals(nomeplaylist)){
                stb.append(sup);
            }
        }
        wr=new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt", false));
        wr.write(stb.toString());
        System.out.println("Cancellazione andata a buon fine!");
        return true;
    }


}
