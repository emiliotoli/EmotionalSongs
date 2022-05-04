package emotionalsongs;
import java.util.Locale;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmotionalSongs extends File{
        public static void main(String[] args) throws IOException{

        //<editor-fold desc="ATTRIBUTI">
        String nome,cognome,codfisc,indirizzo,numerocivico,comune,provincia,email,username,password;
        int cap;
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);

        //</editor-fold>

        System.out.println("inserisci utente da registrare: ");

        System.out.print("Inserisci nome: ");
        nome= br.readLine().toLowerCase();

        System.out.print("inserisci cognome: ");
        cognome= br.readLine().toLowerCase();

        System.out.print("inserisci codice fiscale: ");
        codfisc= br.readLine().toLowerCase();

        System.out.print("inserisci indirizzo: ");
        indirizzo= br.readLine();

        System.out.print("inserisci numero civico: ");
        numerocivico= br.readLine();

        System.out.print("inserisci cap: ");
        cap=br.read();

        System.out.print("inserisci comune: ");
        comune=br.readLine();

        System.out.print("inserisci provincia: ");
        provincia= br.readLine();

        System.out.print("inserisci email: ");
        email= br.readLine();

        System.out.print("inserisci user: ");
        username= br.readLine();

        System.out.print("inserisci password: ");
        password=br.readLine();

        Utenti nuovoutente=new Utenti(nome,cognome,codfisc,indirizzo,numerocivico,cap,comune,provincia,email,username,password);

        ScriviFile(nuovoutente.ToString(),"../EmotionalSongs/.data/UtentiRegistrati.dati.txt"); //inserixìsco un nuovo utente nel file
        System.out.println("registrazione completata");








    }
}
