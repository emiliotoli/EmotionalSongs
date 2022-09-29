package emotionalsongs;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

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

   /* private Utenti(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune,
                   String Provincia, String Email, String UserID, String PW) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt"));
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
        wr.write(this.toString(nome,cognome,CF,via,numeroCivico,cap,comune,provincia,email,userid,password));
    } */

    public static void Registrazione(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune,
                                     String Provincia, String Email, String UserID, String PW) throws IOException {

        boolean nonesistente = true;
        BufferedWriter wr = new BufferedWriter(new FileWriter(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt", true));
        //return new Utenti(Nome, Cognome, CF, Via, NumeroCivico, Cap, Comune, Provincia, Email, UserID, PW);
        nonesistente = esisteUtente(UserID);
        if (!nonesistente) {
            wr.write(toString(Nome, Cognome, CF, Via, NumeroCivico, Cap, Comune, Provincia, Email, UserID, PW));
        } else {
            System.out.println("ID UTENTE gia' esistente! Utente non creato");
        }
    }

    public static String toString(String Nome, String Cognome, String CF, String Via, String NumeroCivico, int Cap, String Comune, String Provincia, String Email, String UserID, String PW) {

        return Nome + "|" + Cognome + "|" + CF + "|" + Via + "|" + NumeroCivico + "|" + Cap + "|" + Comune + "|" + Provincia + "|" + Email + "|" + UserID + "|" + PW;
    } //da rendere privato non ha senso pubblico

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

    //cominciare  con una sequenza di caratteri alfanumerici,  seguiti dal simbolo chiocciola, seguiti da altri caratteri alfanumerici, seguiti dal punto, seguiti da due o tre lettere.
    static String controlloMail(String mail) throws IOException {
        do {
            if (mail.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}")) {
                break;
            } else {
                System.out.println("inserimento della mail non valido");
                System.out.print("reinserisci la mail: ");
                mail = brr.readLine();
            }

        } while (!mail.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}"));
        return mail;
    }

    static String controlloPassword(String pwd1) throws IOException {

        String pwd2;
        pwd1 = Utenti.controlloNonNulla(pwd1);

        System.out.println(" digita nuovamente la password per confermarla: ");
        pwd2 = brr.readLine();
        pwd2 = Utenti.controlloNonNulla(pwd2);
        System.out.println("controllo delle password per vedere se coincidono: ");
        Utenti.controlloPassUguale(pwd1, pwd2);
        return pwd1;
    }

    private static String controlloNonNulla(String pw) throws IOException {

        do {
            if (pw != null) {
                break;
            } else {
                System.out.println("non hai inserito nessuna password: ");
                System.out.println("reinserisci la password: ");
                pw = brr.readLine();
            }
        } while (pw == null);
        pw = Utenti.controlloFormato(pw);
        return pw;
    }

    private static String controlloFormato(String pass) throws IOException {
        do {
            if (pass.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
                break;
            } else {
                System.out.println("inserimento  formato della password non valido ");
                System.out.println("inserire nuavamente la password:");
                pass = brr.readLine();
            }

        } while (!(pass.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")));
        return pass;
    }

    private static String controlloPassUguale(String pw1, String pw2) throws IOException {
        do {
            if (pw1.equals(pw2)) {
                System.out.println("password corrette");
                break;
            } else {
                System.out.println("le password non coincidono.");
                System.out.println("reinserire la password di conferma.");
                pw2 = brr.readLine();
            }

        } while (!(pw1.equals(pw2)));
        return pw1;
    }

    //nome utente formato da caratteri alfanumerici e da _ e - e deve essere di lungezza min 3 e max 15
    static String controlloUser(String user) throws IOException {
        do {
            if (user.matches("^[a-zA-Z0-9_-]{3,15}$")) {
                break;
            } else {
                System.out.println("inserimento dello userId non valido");
                System.out.print("reinserisci lo UserId: ");
                user = brr.readLine();
            }

        } while (!user.matches("^[a-zA-Z0-9_-]{3,15}$"));
        return user;
    }


    static String LunghezzaNome(String nome) throws IOException {
        do {
            if (nome != null && nome.length() >= 3) {
                break;
            } else {
                System.out.println("non hai inserito nessun nome o il nome inserito è tropppo corto.");
                System.out.print("reinsirire il nome: ");
                nome = brr.readLine();
            }
        } while (nome == null && nome.length() < 3);
        nome = Utenti.soloLettere(nome);
        return nome;
    }

    private static String soloLettere(String nominativo) throws IOException {
        do {
            if (nominativo.matches("[a-zA-Z]*${3,20}")) {
                break;
            } else {
                System.out.println("il nominativo appena inserito contiene numero o caratteri speciali.");
                System.out.println("reinserire il nominativo con solo le lettre");
                nominativo = brr.readLine();
            }
        } while (!(nominativo.matches("[a-zA-Z]*${3,20}")));
        return nominativo;
    }

    //<editor-fold desc="funzione che il cognome sia lungo almeo 3">
    static String LunghezzaCognome(String cognome) throws IOException {
        do {
            if (cognome != null && cognome.length() >= 3) {
                break;
            } else {
                System.out.println("non hai inserito nessun cognome o il cognome appena inserito è troppo corto.");
                System.out.print("reinsirire il nome: ");
                cognome = brr.readLine();
            }
        } while (cognome == null && cognome.length() < 3);
        cognome = Utenti.soloLettere(cognome);
        return cognome;
    }
    //</editor-fold>

    static String controlloCAP(int cap) {
        boolean check = false;
        do {
            if (cap < 10 || cap > 97100) {
                System.out.println("cap non valido");
                System.out.println("reinserire il cap: ");
                Scanner in = new Scanner(System.in);
                in.nextInt();
            } else {
                check = true;
            }

        } while (!check);

        StringBuilder CAP = new StringBuilder();
        while (CAP.length() < 5 - Integer.toString(cap).length()) {
            CAP.append(0);
        }
        CAP.append(cap);

        return CAP.toString();
    }

    public static String controlloNumeroCivico(String numcivico) throws IOException {
        do {
            if (numcivico != null) {
                break;
            } else {
                System.out.println("non hai inserito niente.");
                System.out.println("reinserire il numero civico");
                numcivico = brr.readLine();
            }
        } while (numcivico == null);
        numcivico = Utenti.formatoNumCivico(numcivico);
        return numcivico;
    }

    private static String formatoNumCivico(String civico) throws IOException {
        do {
            if (civico.matches("([0-9]{1,4})([a-zA-Z]{1})")) {
                break;
            }
            else {
                System.out.println("il numero civico inserito non rispetta il formato");
                System.out.println("reinserire il numro civico");
                civico= brr.readLine();
            }
        } while (!(civico.matches("([0-9]{2,4})([A-Z]{1})")));
        return civico;
    }

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

    private static boolean comparaLogin(String[] a, String UID, String PW) {
        if (UID.equals((a[9].trim())) && PW.equals((a[10].trim())))
            return true;
        return false;
    }

    private static boolean esisteUtente(String nomeUtente) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt"));
        String str;
        String[] supporto;

        while ((str = br.readLine()) != null) {
            supporto = str.split("\\|");
            if (supporto[9].equals(nomeUtente))
                return true;
        }
        return false;
    }

}
