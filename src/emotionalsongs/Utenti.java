package emotionalsongs;

import java.io.*;
import java.util.Scanner;

/**
 * @author Emilio Daverio
 *         la classe fa dei controlli sugli input fatti dall' utente e registra
 *         un nuovo utente se non si è ancora registrato
 */
public class Utenti {

    // <editor-fold desc="Attributi">
    static int codfisLunghezza = 16;// usato per verificare la lunghezza del codice fiscale
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader brr = new BufferedReader(isr);
    private String nome, cognome, codiceFiscale, via, numeroCivico, comune, provincia, email, userid, password;
    private int cap;
    private final static String sep = System.getProperty("file.separator");
    private boolean loginEffettuato = false;
    // </editor-fold>

    public Utenti() {
    }

    /**
     * @param Nome
     * @param Cognome
     * @param CF
     * @param Via
     * @param NumeroCivico
     * @param Cap
     * @param Comune
     * @param Provincia
     * @param Email
     * @param UserID
     * @param PW
     * @throws IOException
     * @author Emilio Daverio
     *         metodo per registrare un nuonvo utente
     */
    // <editor-fold desc="Registrazione nuovo Utente">
    public static void Registrazione(String Nome, String Cognome, String CF, String Via, String NumeroCivico,
            String Cap, String Comune,
            String Provincia, String Email, String UserID, String PW) throws IOException {

        File file = new File("data" + sep + "UtentiRegistrati.dati.txt");
        String path = file.getAbsolutePath();
        BufferedWriter wr = new BufferedWriter(new FileWriter(path, true));
    }
    // </editor-fold>

    /**
     * @param Nome
     * @param Cognome
     * @param CF
     * @param Via
     * @param NumeroCivico
     * @param Cap
     * @param Comune
     * @param Provincia
     * @param Email
     * @param UserID
     * @param PW
     * @return ritorna i dati personali dell'utente
     * @author Emilio Daverio
     */
    public static String toString(String Nome, String Cognome, String CF, String Via, String NumeroCivico, String Cap,
            String Comune, String Provincia, String Email, String UserID, String PW) {

        return Nome + "|" + Cognome + "|" + CF + "|" + Via + "|" + NumeroCivico + "|" + Cap + "|" + Comune + "|"
                + Provincia + "|" + Email + "|" + UserID + "|" + PW;
    }

    /**
     * @param cf
     * @return codice fiscale nella forma corretta
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che il il codice fiscale inserito segua il formato
     *         richiesto: 6 lettere, 2 numeri, 1 lettera, 2 numeri, 1 letterea, 3
     *         numeri, 1 lettere (lunghezza =16)
     */
    // <editor-fold desc="Controllo Codice Fiscale">
    static String ControlloFormatocf(String cf) throws IOException {
        do {
            if ((cf.length() == codfisLunghezza)
                    && cf.matches("([A-Za-z]{6})([0-9]{2})([A-Za-z])([0-9]{2})([A-Za-z])([0-9]{3})([A-Za-z])")) {// se
                                                                                                                 // il
                                                                                                                 // codice
                                                                                                                 // fiscale
                                                                                                                 // inserito
                                                                                                                 // rispetta
                                                                                                                 // la
                                                                                                                 // lunghezza
                                                                                                                 // e il
                                                                                                                 // formato
                                                                                                                 // ritorna
                                                                                                                 // true
                break;
            } else {
                System.out.println("inserimento non valido");
                System.out.print("reinserisci il Codice Fiscale: ");
                cf = brr.readLine();
            }
        } while ((cf.length() != codfisLunghezza)
                && !cf.matches("([A-Za-z]{6})([0-9]{2})([A-Za-z])([0-9]{2})([A-Za-z])([0-9]{3})([A-Za-z])"));
        return cf;
    }
    // </editor-fold>

    /**
     * @param mail
     * @return ritorna la mail nel formato corretto
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che la mail inserita segue il seguente fomato: cominciare
     *         con una sequenza di caratteri alfanumerici, seguiti dal simbolo '@' ,
     *         seguiti da altri caratteri alfanumerici, seguiti dal punto, seguiti
     *         da due o tre lettere.
     */
    // <editor-fold desc="Controllo Formato Mail">
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
    // </editor-fold>

    /**
     * @param pwd1 password inserita dall'utente
     * @return password corretta
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che la password non sia nulla e che segua il formato
     *         richiesto: deve contenere un carattere speciale, lunga almeno 8, deve
     *         avere una maiuscola e una minuscola
     */
    // <editor-fold desc="Controlli della Password inserita">
    static String controlloPassword(String pwd1) throws IOException {
        String pwd2;

        pwd1 = Utenti.controlloNonNulla(pwd1);

        System.out.println(" digita nuovamente la password per confermarla: ");
        pwd2 = brr.readLine();
        System.out.println("controllo delle password per vedere se coincidono: ");
        Utenti.controlloPassUguale(pwd1, pwd2);
        return pwd1;
    }

    /**
     * @param pw controllo che non sia nulla e poi chiamo un altro metodo di
     *           controllo
     * @return pw--> mi restituisce la password non nulla e con il corretto formato
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che la password non sia nulla
     */
    // <editor-fold desc="Password non nulla">
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

    // </editor-fold>

    /**
     * @param pass
     * @return ritorno il formato corretto
     * @throws IOException
     * @author Emilio Daverio
     *         contrrola che il formato della password sia rispettato
     */
    // <editor-fold desc="Formato Password">
    private static String controlloFormato(String pass) throws IOException {
        do {
            if (pass.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!_.]).{8,20})")) {
                break;
            } else {
                System.out.println("inserimento  formato della password non valido ");
                System.out.println("inserire  la password:");
                pass = brr.readLine();
            }

        } while (!(pass.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!_]).{8,20})")));
        return pass;
    }
    // </editor-fold>

    /**
     * @param pw1
     * @param pw2
     * @return restituisco pw1 (prima password inserita) perchè le password inserite
     *         sono uguali
     * @throws IOException
     * @author Emilio Daverio
     *         conferma della password: richiedo di confermare la password e
     *         controllo che siano uguali
     */
    // <editor-fold desc="Conferma Password">
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
    // </editor-fold>

    // </editor-fold>

    /**
     * @param username
     * @return nome utente
     * @throws IOException
     * @author Emilio Daverio
     *         questo metodo controlla che il nome utente inserito non sia gia
     *         presente nel fil e richiama il metodo privato controlloUser
     */
    public static String controlloUserEsistente(String username) throws IOException {
        boolean esisteut;
        do {
            esisteut = esisteUtente(username);

            if (!esisteut) {
                System.out.println("nome utente valido.");
                break;

            } else {

                System.out.println("ID UTENTE gia' esistente! Scegliere un nuovo nomeUtente");
                System.out.println("rinserire il nome utente: ");
                username = brr.readLine();
            }
        } while (esisteut);

        username = Utenti.controlloUser(username);
        return username;
    }

    /**
     * @param user
     * @return nome utente corretto
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che il nome utente sia lungo almeno 3 e massimo 15. Ci
     *         possono essere , nel nome, solo lettere maiscole/minuscole, numeri e
     *         i seguenti caratteri "_" "-" NOTA posso esseci anche solo lettere
     */
    // <editor-fold desc="Controllo UserId">
    // nome utente formato da caratteri alfanumerici e da _ e - e deve essere di
    // lungezza min 3 e max 15
    private static String controlloUser(String user) throws IOException {

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
    // </editor-fold>

    // <editor-fold desc="Metodi per il controllo del nome e cognome dell'utente">

    /**
     * @param nome
     * @return nome corretto secondo le richieste
     * @throws IOException
     * @author Emilio Daverio
     *         controla che il nome dell' utente sia almeno lungo 3 e che sia
     *         composto da solo caratteri alfanumerici
     */
    // <editor-fold desc="Controllo Nome">
    static String LunghezzaNome(String nome) throws IOException {
        do {
            if (nome != null && nome.length() >= 3) {
                break;
            } else {
                System.out.println("non hai inserito nessun nome o il nome inserito è tropppo corto.");
                System.out.print("reinsirire il nome: ");
                nome = brr.readLine();
            }
        } while (nome == null || nome.length() < 3);
        nome = Utenti.soloLettere(nome);
        return nome;
    }
    // </editor-fold>

    /**
     * @param nominativo
     * @return stringa passata non contiene lettere
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che la stringa passata come parametro sia composto da
     *         caratteri alfanumerici
     */
    // <editor-fold desc="Controllo solo Lettere">
    private static String soloLettere(String nominativo) throws IOException {
        do {
            if (nominativo.matches("[a-zA-Z]*${3,20}")) {
                break;
            } else {
                System.out.println("il nominativo appena inserito contiene numero o caratteri speciali.");
                System.out.println("reinserire il nominativo con solo le lettere");
                nominativo = brr.readLine();
            }
        } while (!(nominativo.matches("[a-zA-Z]*${3,20}")));
        return nominativo;
    }
    // </editor-fold>

    /**
     * @param cognome
     * @return cognome che contiene solo caratteri alfanumerici
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che il cognome inserito sia lungo almeno 3 e che sia
     *         formato da sole lettere
     */
    // <editor-fold desc="Controllo Cognome">
    static String LunghezzaCognome(String cognome) throws IOException {
        do {
            if (cognome != null && cognome.length() >= 3) {
                break;
            } else {
                System.out.println("non hai inserito nessun cognome o il cognome appena inserito è troppo corto.");
                System.out.print("reinsirire il cognome: ");
                cognome = brr.readLine();
            }
        } while (cognome == null || cognome.length() < 3);
        cognome = Utenti.soloLettere(cognome);
        return cognome;
    }
    // </editor-fold>

    // </editor-fold>

    /**
     * @param cap
     * @return cap corretto secondo i requisiti
     * @author Emilio Daverio
     *         controlla che il cap inserito sia lungo almeno 5 e che sia compreso
     *         tra [10,97100]
     */
    // <editor-fold desc="Controllo formato e lunghezza cap">
    static String controlloCAP(int cap) {
        boolean check = false;
        do {
            if (cap < 10 || cap > 97100) {
                System.out.println("cap non valido");
                System.out.println("reinserire il cap: ");
                Scanner in = new Scanner(System.in);
                cap = in.nextInt();
                in.close();
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
    // </editor-fold>

    /**
     * @param numcivico
     * @return numero civico corretto secondo le specifiche
     * @throws IOException
     * @author Emilio Daverio
     *         controlla che il numero civico segua il formato prestabilito
     */
    // <editor-fold desc="Controllo Numero Civico">
    public static String controlloNumeroCivico(String numcivico) throws IOException {

        while (!numcivico.matches("[0-9]+([A-Za-z]*)")) {
            System.out.println("il numero civico inserito non rispetta il formato");
            System.out.println("reinserire il numro civico");
            numcivico = brr.readLine();
        }
        return numcivico;
    }
    // </editor-fold>

    /**
     * @param testo
     * @param filePath
     * @throws IOException
     * @author Emilio Daverio
     *         metodo per salvare i dati sul file Utenti.dati
     */
    public static void ScriviFile(String testo, String filePath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(testo);
        bw.newLine();
        bw.close();
    }

    /**
     * @param Id
     * @param Pw
     * @return vero/falso a seconda di come va a finire l'operazione di login
     * @throws IOException
     * @author Emilio Daverio
     *         metodo che serve per fare il login
     */
    // <editor-fold desc="Login">
    public static boolean Login(String Id, String Pw) throws IOException {

        File file = new File("data" + sep + "UtentiRegistrati.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fread = new FileReader(path);
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
            System.out.println("\nlogin inserito Errato.\n");
            System.out.println("inserimento sbagliato dello UserId e/o della password .");
            System.out.println("oppure non vi siete ancora registrati.");
            esiste = false;
        }
        bufread.close();
        return esiste;
    }
    // </editor-fold>

    /**
     * @param nomeUtente
     * @return vero/falso che dipende se esiste o meno l'utente nel file
     * @throws IOException
     * @author Emilio Daverio
     *         controllas se esiste gia un utente con lo stesso UsernId
     */
    // <editor-fold desc="UserId Esistente">
    private static boolean esisteUtente(String nomeUtente) throws IOException {
        File file = new File("data" + sep + "UtentiRegistrati.dati.txt");
        String path = file.getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str;
        String[] supporto;

        while ((str = br.readLine()) != null) {
            supporto = str.split("\\|");
            if (supporto[9].equals(nomeUtente))
                return true;
        }
        br.close();
        return false;
    }
    // </editor-fold>

}
