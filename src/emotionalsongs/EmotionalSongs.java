package emotionalsongs;
import java.util.Locale;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EmotionalSongs {
        public static void main(String[] args) throws IOException{

                String nome,cognome,codfisc,indirizzo,numerocivico,comune,provincia,email,username,password;
                int cap;
                String titolo, autore,anno,album,durata,genere;
                InputStreamReader isr=new InputStreamReader(System.in);
                BufferedReader br=new BufferedReader(isr);

                System.out.println("inizio procedura inserimento canzoni:\n");
                System.out.print("inserisci il Titolo della canzone: ");
                titolo=br.readLine();

                System.out.print("inserisci l'Autore della canzone: ");
                autore=br.readLine();

                System.out.print("inserisci l' Anno della canzone: ");
                anno=br.readLine();

                System.out.print("inserisci l'Album della canzone: ");
                album=br.readLine();

                System.out.print("inserisci la Durata della canzone: ");
                durata=br.readLine();

                System.out.print("inserisci il Genere della canzone: ");
                genere=br.readLine();

                //Canzoni can=new Canzoni();
                //can.creaCanzone(titolo,autore,anno,album,durata,genere);
                //ScriviFile(can.ToString(), "../EmotionalSongs/.data/Canzoni.dati.txt");
                //Canzoni.ScriviFile(can.ToString(), "../EmotionalSongs/.data/Canzoni.dati1.txt");
                //System.out.println(can);
                System.out.println(titolo);
                System.out.println(autore);
                System.out.println(anno);
                System.out.println(album);
                System.out.println(durata);
                System.out.println(genere);


                Canzoni canzonenuova= new Canzoni();
                canzonenuova.creaCanzone(titolo,autore,anno,album,durata,genere);
                Canzoni.ScriviFile(canzonenuova.ToString(), "../EmotionalSongs/.data/Canzoni.dati.txt");

                System.out.println("\nprocedura terminata");





               /* System.out.println("inserisci utente da registrare: ");

                System.out.print("Inserisci nome: ");
                nome= br.readLine().toLowerCase();
                nome=Utenti.LunghezzaNome(nome);

                System.out.print("inserisci cognome: ");
                cognome= br.readLine().toLowerCase();
                cognome=Utenti.LunghezzaCognome(cognome);

                System.out.print("inserisci codice fiscale: ");
                codfisc= br.readLine().toLowerCase();
                codfisc=Utenti.ControlloFormatocf(codfisc); //controllo che il codice fiscale sia nel formato corretto

                System.out.print("inserisci indirizzo: ");
                indirizzo= br.readLine();

                System.out.print("inserisci numero civico: ");
                numerocivico= br.readLine();

                System.out.print("inserisci cap: ");
                cap=br.read();
                cap=Utenti.LunghezzaCap(cap);

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
                System.out.println("registrazione completata");*/
        }
}
