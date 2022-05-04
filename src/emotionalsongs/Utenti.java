package emotionalsongs;

public class Utenti {
    private String nome,cognome,codiceFiscale,via,numeroCivico,comune,provincia,email,userid,password;
    private int cap;
    protected Utenti(String Nome, String Cognome, String CF,String Via,String NumeroCivico, int Cap,  String Comune,
                  String Provincia, String Email, String UserID, String PW) {
        this.nome=Nome;
        this.cognome=Cognome;
        this.codiceFiscale=CF;
        this.via=Via;
        this.numeroCivico=NumeroCivico;
        this.cap=Cap;
        this.comune=Comune;
        this.provincia=Provincia;
        this.email=Email;
        this.userid=UserID;
        this.password=PW;
    }
    public Utenti Registrazione(String Nome,String Cognome,String CF,String Via,String NumeroCivico,int Cap, String Comune,
                                String Provincia,String Email,String UserID,String PW){
       return new Utenti(Nome,Cognome,CF,Via,NumeroCivico,Cap,Comune,Provincia,Email,UserID,PW);
    }
    public String ToString(){
        String separatore = Character.toString((char) 2063);
        return this.nome+separatore+this.cognome+separatore+this.codiceFiscale+separatore+this.via+separatore+this.numeroCivico+separatore+this.cap+separatore+this.comune+separatore+this.provincia+separatore+this.email+separatore+this.userid+separatore+this.password;
    }



}
