package emotionalsongs;

public class Playlist {
    private String nomeplaylist;
    private String nomebrano;
    private String idutente; // fare una variabile nel main (globale) per vedere se l'utente e loggato o meno

    public Playlist(){}

    private Playlist(String NomePlaylist,String NomeBrano){
        this.nomeplaylist=NomePlaylist;
        this.nomebrano=NomeBrano;
    }
    public void CreaPlaylist(String NomePlay,String NomeBran)
    {
        new Playlist(nomeplaylist,nomebrano);
    }

    public String ToString(){
        return nomeplaylist+ " " +nomebrano;
    }
}
