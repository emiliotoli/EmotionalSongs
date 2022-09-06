package emotionalsongs;

import java.io.*;

public class Playlist {
    private String nomeplaylist;
    private String nomebrano;
    String idutente;
    private final static String sep = System.getProperty("file.separator");


    public Playlist(){}

    private Playlist(String Id,String NomePlaylist){
        this.idutente=Id;
        this.nomeplaylist=NomePlaylist;
    }

    public static boolean registraPlaylist(String ID, String NomePlay , String[] canzoni) throws IOException {
            if (controlloPlaylistEsistente(ID, NomePlay)) {
                //new Playlist(idutente, nomeplaylist);
                scriviFileRegistrazione(ID, NomePlay, ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt", canzoni);
                return true;
            }
        return false;
    }


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
                esistente = true;
                break;
            }
            else{


            }
        }
       /* if (esistente) {
            System.out.println("Nome playlist gia esistente per quel id Utente");
        }
        else {
            System.out.println("play list aggiuta con successo.");
        }*/
        return esistente;
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
}
