package emotionalsongs;

import java.io.*;

public class Utenti {

    //<editor-fold desc="Attributi">
    static int codfisLunghezza = 16;//usato per verificare la lunghezza del codice fiscale
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader brr = new BufferedReader(isr);
    private String nome, cognome, codiceFiscale, via, numeroCivico, comune, provincia, email, userid, password;
    private int cap;
    private final static String sep = System.getProperty("file.separator");

    private boolean loginEffettuato = false;

    //</editor-fold>

    public Utenti() {
    }

    private Utenti(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune,
                   String Provincia, String Email, String UserID, String PW) {
        this.nome = Nome;
        this.cognome = Cognome;
        this.codiceFiscale = CF;
        this.via = Via;
        this.numeroCivico = NumeroCivico;
        this.cap = Cap;
        this.comune = Comune;
        this.provincia = Provincia;
        this.email = Email;
        this.userid = UserID;
        this.password = PW;
    }

    public Utenti Registrazione(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune,
                                String Provincia, String Email, String UserID, String PW) {
        return new Utenti(Nome, Cognome, CF, Via, NumeroCivico, Cap, Comune, Provincia, Email, UserID, PW);
    }

    public String ToString(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune, String Provincia, String Email, String UserID, String PW) {

        return Nome + "|" + Cognome + "|" + CF + "|" + Via + "|" + NumeroCivico + "|" + Cap + "|" + Comune + "|" + Provincia + "|" + Email + "|" + UserID + "|" + PW;
    }

    //metodi per i controlli

    //<editor-fold desc="Funzione che controlla il codice fiscale sia lungo 16 caratteri e nel seguente formato:">
    static String ControlloFormatocf(String cf) throws IOException {
        do {
            if ((cf.length() == codfisLunghezza) && cf.matches("([A-Za-z]{6})([0-9]{2})([A-Za-z])([0-9]{2})([A-Za-z])([0-9]{3})([A-Za-z])")) {// se il codice fiscale inserito rispetta la lunghezza e il formato ritorna true
                break;
            } else {
                System.out.println("inserimento non valido");
                System.out.print("reinserisci il Codice Fiscale: ");
                cf = brr.readLine();
            }
        } while ((cf.length() != codfisLunghezza) && !cf.matches("([A-Za-z]{6})([0-9]{2})([A-Za-z])([0-9]{2})([A-Za-z])([0-9]{3})([A-Za-z])"));
        return cf;
    }
    //</editor-fold>

    //<editor-fold desc="funzione che il nome sia lungo almeo 3">
    static String LunghezzaNome(String nome) throws IOException {
        while (nome.length() < 3) {
            System.out.println("Nome inserito troppo corto.");
            System.out.print("reinsirire il nome: ");
            nome = brr.readLine();
        }
        return nome;
    }
    //</editor-fold>

    //<editor-fold desc="funzione che il cognome sia lungo almeo 3">
    static String LunghezzaCognome(String cognome) throws IOException {
        while (cognome.length() < 3) {
            System.out.println("Nome inserito troppo corto.");
            System.out.print("reinsirire il nome: ");
            cognome = brr.readLine();
        }
        return cognome;
    }
    //</editor-fold>

    //<editor-fold desc="funzione che controlla che il cap sia lungo 5">
    static int LunghezzaCap(int cap) throws IOException {
        do {
            if (cap > 10 && cap <= 97100)
                break;
            else {
                System.out.println("valore cap non valido.");
                System.out.print("reinserire cap: ");
                cap = Integer.parseInt(brr.readLine());
            }
        } while (cap < 10 || cap > 97100);
        if(cap<1000){

        }
        return cap;
    }
    //</editor-fold>

    public static void ScriviFile(String testo, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(testo);
        bw.newLine();
        bw.close();
    }

    public static boolean Login(String Id, String Pw) throws IOException {

        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        boolean esiste = false;
        String sup;
        String idutenteFile;
        String passwordFile;

        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            idutenteFile = supporto[9].trim().toLowerCase();
            passwordFile = supporto[10].trim();
            if (Id.equals(idutenteFile) && Pw.equals(passwordFile)) {
                esiste = true;
                break;
            }
        }
        if (!esiste) {
            System.out.println("non ti sei ancora registrato");
            //richimare funzione regitra
        }
        return esiste;
    }


    public static boolean loginUtente(String UID, String PW) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt"));
        String str;
        boolean esistente = false;
        while ((str = br.readLine()) != null) {
            String[] a = str.split("\\|");
            esistente = comparaLogin(a, UID, PW);
            if (esistente) {
                //loginEffettuato=true;                 //bisogna mettere un campo per stabilire se l'utente sia loggato o meno, da controllare poi quando si userà la playlist
                return true;                            //non so come modificarlo dato che non posso modificare un attributo con un metodo statico
            }
        }
        return false;
    }

    /*
    public static int loginUtente(String UID, String PW) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + ".UtentiRegistrati.dati.txt"));
        String str;
        boolean esistente = false;
        while ((str = br.readLine()) != null) {
            String[] a = str.split("\\|");
            esistente = comparaLogin(a, UID, PW);
            if (esistente) {
                return 1;                   secondo me si potrebbe sfruttare il return, se è 1 è loggato, altrimenti no.
            }
        }
        return 0;
    }
    */
    private static boolean comparaLogin(String[] a, String UID, String PW) {
        if (UID.equals((a[9].trim())) && PW.equals((a[10].trim())))
            return true;
        return false;
    }

}
