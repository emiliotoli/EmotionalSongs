package emotionalsongs;

import java.util.Locale;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class EmotionalSongs {

    public static void main(String[] args) throws IOException {
        String nome, cognome, codfisc, indirizzo, numerocivico, comune, provincia, email, username, password;
        int cap;
        String titolo, autore, anno, album, durata, genere;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        final String sep = System.getProperty("file.separator");
        String nomePlaylist;
        String idUtente = "Pippo prova".trim().toLowerCase(); //variabile globale che serve ad avere l'id-utente
        boolean esiste;


        //creazione playlist
        System.out.println("inizio procedura creazione playList");
        do {
            System.out.print("inserisci il nome della playList: ");
            nomePlaylist = br.readLine().trim().toLowerCase();
            esiste = Playlist.ControlloPlaylistEsistente(idUtente, nomePlaylist);
        }while(esiste);
        Playlist pl = new Playlist();
        pl.CreaPlaylist(idUtente,nomePlaylist);
        Playlist.ScriviFile(pl.ToString(idUtente,nomePlaylist),".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Playlist.dati.txt");




        //<editor-fold desc="RICERCA BRANO PER AUTORE ED ANNO">
        /*System.out.println("inizio procedura ricerca canzone per autore e titolo:");
        Canzoni can = new Canzoni();
        System.out.print("\ninserisci autore: ");
        autore = br.readLine().trim().toLowerCase();
        System.out.print("Inserisci anno: ");
        anno = br.readLine().trim().toLowerCase();
        can.cercaBranoMusicaleAutoreAnoo(autore, anno);*/
        //</editor-fold>

        //<editor-fold desc="RICERCA BRANO PER TITOLO">
        /*System.out.println("inizio procedura di ricerca in base al titolo");
        Canzoni can = new Canzoni();
        System.out.print("inserisci titolo da cercare: ");
        titolo = br.readLine().toLowerCase().trim();
        can.cercaBranoMusicaleTitolo(titolo);*/
        //</editor-fold>

        //<editor-fold desc="REGISTRAZIONE NUOVA CANZONE">
        /*System.out.println("inizio procedura inserimento canzoni:\n");
        System.out.print("inserisci il Titolo della canzone: ");
        titolo = br.readLine();

        System.out.print("inserisci l'Autore della canzone: ");
        autore = br.readLine();

        System.out.print("inserisci l' Anno della canzone: ");
        anno = br.readLine();

        System.out.print("inserisci l'Album della canzone: ");
        album = br.readLine();

        System.out.print("inserisci la Durata della canzone: ");
        durata = br.readLine();

        System.out.print("inserisci il Genere della canzone: ");
        genere = br.readLine();

        Canzoni canzonenuova = new Canzoni();
        canzonenuova.creaCanzone(titolo, autore, anno, album, durata, genere);
        Canzoni.ScriviFile(canzonenuova.ToString(titolo, autore, anno, album, durata, genere), ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Canzoni.dati.txt");
        System.out.println("\nprocedura terminata");*/
        //</editor-fold>

        //<editor-fold desc="REGISTRAZIONE NUOVO UTENTE">
        /*System.out.println("inserisci utente da registrare: ");

        System.out.print("Inserisci nome: ");
        nome = br.readLine().toLowerCase();
        nome = Utenti.LunghezzaNome(nome);

        System.out.print("inserisci cognome: ");
        cognome = br.readLine().toLowerCase();
        cognome = Utenti.LunghezzaCognome(cognome);

        System.out.print("inserisci codice fiscale: ");
        codfisc = br.readLine().toLowerCase();
        codfisc = Utenti.ControlloFormatocf(codfisc); //controllo che il codice fiscale sia nel formato corretto

        System.out.print("inserisci indirizzo: ");
        indirizzo = br.readLine();

        System.out.print("inserisci numero civico: ");
        numerocivico = br.readLine();

        System.out.print("inserisci cap: ");
        cap = Integer.parseInt(br.readLine());
        cap = Utenti.LunghezzaCap(cap);

        System.out.print("inserire il comune: ");
        comune = br.readLine();

        System.out.print("inserire la provincia: ");
        provincia = br.readLine();

        System.out.print("inserisci email: ");
        email = br.readLine();

        System.out.print("inserisci user: ");
        username = br.readLine();

        System.out.print("inserisci password: ");
        password = br.readLine();

        Utenti nuovoutente = new Utenti();
        nuovoutente.Registrazione(nome, cognome, codfisc, indirizzo, numerocivico, cap, comune, provincia, email, username, password);
        Utenti.ScriviFile(nuovoutente.ToString(nome, cognome, codfisc, indirizzo, numerocivico, cap, comune, provincia, email, username, password), ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt"); //inserixìsco un nuovo utente nel file
        System.out.println("\nregistrazione completata");*/
        //</editor-fold>

    }
}
