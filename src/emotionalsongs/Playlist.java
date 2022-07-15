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
    public void CreaPlaylist( String ID,String NomePlay)
    {
        new Playlist(idutente,nomeplaylist);
    }


    public static void ScriviFile(String testo, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(testo);
        bw.newLine();
        bw.flush(); //svuoto lo stream
        bw.close();
    }

    public static boolean ControlloPlaylistEsistente(String IdUtente, String NomePlay) throws IOException {
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
        }
        if (esistente) {
            System.out.println("Nome playlist gia esistente per quel id Utente");
        }
        else {
            System.out.println("play list aggiuta con successo.");
        }
        return esistente;
    }

    public String ToString(String Idutente,String NomePlay){
        return Idutente+"|"+NomePlay;
    }

    public static boolean aggiungiCanzone(String titolo , String playList , String idUtente) throws IOException {
        boolean esito=false;
        String[] a;
        BufferedReader playlistReader = new BufferedReader(new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt"));
        String sup;
        while((sup= playlistReader.readLine())!=null) {
            a=sup.split("//|");
            if(a[1].equals(playList) && a[0].equals(idUtente) && esisteCanzone(titolo)){


            }
        }
        return true; //messo solo per non generare errore
    }

    private static boolean esisteCanzone(String nomeCanzone) throws IOException {
        BufferedReader songReader= new BufferedReader(new FileReader(".." +sep+"EmotionalSongs"+sep+".data"+sep+"Playlist.dati.txt"));
        String[] a;
        String sup;
        while((sup=songReader.readLine())!=null)
        {
            a=sup.split("||/");
            if(a[0].equals(nomeCanzone))
                return true;
        }
        return false;
    }
}
