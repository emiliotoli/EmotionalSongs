public class Canzoni {

    private String titolo;
    private String autore;
    private String anno;

    private Canzoni(String Titolo , String Autore , String Anno)
    {
        this.autore=Autore;
        this.anno=Anno;
        this.titolo=Titolo;
    }

    public void creaCanzone(String Titolo , String Autore , String Anno)
    {
        new Canzoni(Titolo , Autore , Anno);
    }
}

