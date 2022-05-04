public class Utenti {
    private String nome;
    private String cognome;
    private int cap;
    private String provincia;
    private String comune;
    private String via;
    private String numeroCivico;
    private String codiceFiscale;
    private String email;
    private String userid;
    private String password;

    public Utenti(String Nome, String Cognome, int Cap , String Provincia,
                  String Comune, String Via , String NumeroCivico , String CF , String Email,
                  String UserID, String PW) {

        this.nome=Nome;
        this.cognome=Cognome;
        this.cap=Cap;
        this.provincia=Provincia;
        this.comune=Comune;
        this.via=Via;
        this.numeroCivico=NumeroCivico;
        this.codiceFiscale=CF;
        this.email=Email;
        this.userid=UserID;
        this.password=PW;
    }
}
