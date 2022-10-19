package emotionalsongs;

import javax.swing.*;
import java.util.Locale;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Color;


/**
 * @author Emilio Toli
 * @author Stefano Farina
 * è il main dell' applicazione, si trovano tutte le opzioni che un utente può fare
 */
public class EmotionalSongs {

    public static void main(String[] args) throws IOException, NumberFormatException {
        String nome, cognome, codfisc, indirizzo, numerocivico, comune, provincia, email, username, password;
        int cap;
        String titolo, autore, anno, album, durata, genere;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        final String sep = System.getProperty("file.separator");
        String nomePlaylist;
        String idUtenteGlob = null; //variabile globale che serve ad avere l'id-utente
        String idUtente;
        boolean esiste;
        boolean loggatoglob = false;
        boolean controlloMenuPrincipale;
        boolean controllomenupersonale;
        int varswich = 0;
        int varmenu = 0;

        File file = new File("data" + sep + "Emozioni.dati.txt");
        String path = file.getAbsolutePath();
        System.out.println(path);


        do {
            do {

                if (!loggatoglob) {
                    System.out.println("\n");
                    System.out.println("-------------------- menu' principale applicazione ----------------------");
                    System.out.println("Digitare 1 per ricercare una canzone per Titolo:");
                    System.out.println("Digitare 2 per ricercare una canzone per Autore e Anno:");
                    System.out.println("Digitare 3 per per Registrati:");
                    System.out.println("Digitare 4 per Loggarti");
                    System.out.println("Digitare 5 per accedere all'area personale: ");
                    System.out.println("Digitare 6 per terminare tutte le operazioni");
                    //System.out.println("\n");
                    System.out.println("per accedre al case 5, prima bisogna effettuare il login");
                    System.out.println("-------------------------------------------------------------------------");
                } else {
                    System.out.println("\n");
                    System.out.println("-------------------- menu' principale applicazione ----------------------");
                    System.out.println("Digitare 1 per ricercare una canzone per Titolo:");
                    System.out.println("Digitare 2 per ricercare una canzone per Autore e Anno:");
                    System.out.println("Digitare 3 per per Registrati:");
                    System.out.println("Digitare 4 per il logout");
                    System.out.println("Digitare 5 per accedere all'area personale: ");
                    System.out.println("Digitare 6 per terminare tutte le operazioni");
                    System.out.println("per accedre al case 5, prima bisogna effettuare il login");
                    System.out.println("-------------------------------------------------------------------------");
                }

                System.out.print("scelta: ");
                try {
                    varswich = Integer.parseInt(br.readLine());
                    controlloMenuPrincipale = true;

                } catch (Exception e) {
                    System.out.println("\nDEVI INSERIRE SOLO NUMERI DA 1 A 5 PER FARE LE OPERAZIONI DESCRITTE SUL MENU'");
                    System.out.println("PER TERMINARE LE OPERZIONI SUBITO PUI DIGITARE 0 O 6");
                    controlloMenuPrincipale = false;
                }
            } while (!controlloMenuPrincipale);

            switch (varswich) {
                case 1:
                    //<editor-fold desc="RICERCA BRANO PER TITOLO">
                    System.out.println("inizio procedura di ricerca in base al titolo\n");
                    System.out.print("inserisci titolo da cercare: ");
                    titolo = br.readLine().trim().toLowerCase();
                    Canzoni.cercaBranoMusicaleTitolo(titolo, loggatoglob);
                    break;
                //</editor-fold>
                case 2:
                    //<editor-fold desc="RICERCA BRANO PER AUTORE ED ANNO">
                    System.out.println("inizio procedura ricerca canzone per autore e titolo:");
                    System.out.print("\ninserisci autore: ");
                    autore = br.readLine().trim().toLowerCase();
                    System.out.print("Inserisci anno: ");
                    anno = br.readLine().trim().toLowerCase();
                    Canzoni.cercaBranoMusicaleAutoreAnno(autore, anno, loggatoglob);
                    break;
                //</editor-fold>
                case 3:
                    //<editor-fold desc="REGISTRAZIONE NUOVO UTENTE">
                    System.out.println("inserisci utente da registrare: ");

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
                    numerocivico = Utenti.controlloNumeroCivico(numerocivico);

                    System.out.print("inserisci cap: ");
                    cap = Integer.parseInt(br.readLine());
                    cap = Integer.parseInt(Utenti.controlloCAP(cap));

                    System.out.print("inserire il comune: ");
                    comune = br.readLine();

                    System.out.print("inserire la provincia: ");
                    provincia = br.readLine();

                    System.out.print("inserisci email: ");
                    email = br.readLine();
                    Utenti.controlloMail(email);

                    System.out.print("inserisci user: ");
                    username = br.readLine();
                    Utenti.controlloUser(username);

                    System.out.print("inserisci password: ");
                    password = br.readLine();
                    password = Utenti.controlloPassword(password);


                    Utenti nuovoutente = new Utenti();
                    Utenti.Registrazione(nome, cognome, codfisc, indirizzo, numerocivico, cap, comune, provincia, email, username, password);
                    Utenti.ScriviFile(Utenti.toString(nome, cognome, codfisc, indirizzo, numerocivico, cap, comune, provincia, email, username, password), ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "UtentiRegistrati.dati.txt"); //inserisco un nuovo utente nel file
                    System.out.println("\nregistrazione completata");
                    break;
                //</editor-fold>

                case 4:
                    if (!loggatoglob) {
                        do {
                            System.out.print("inserisci l'UserId:");
                            idUtente = br.readLine().trim().toLowerCase();
                            System.out.print("inserisci la password: ");
                            password = br.readLine().trim();
                            loggatoglob = Utenti.Login(idUtente, password);
                        } while (!loggatoglob);
                        loggatoglob = true;
                        idUtenteGlob = idUtente;
                        System.out.println("sei loggato");
                    } else {
                        loggatoglob = false;
                        varmenu = 0;
                        break;
                    }

                    break;
                case 5: // ci pensa il davo
                    String playlist, brano;
                    if (loggatoglob) {
                        do {
                            do {
                                System.out.println("-------------------- menu' area personale ----------------------");
                                System.out.println("Digitare 1 per creare una playlist: ");
                                System.out.println("Digitare 2 per visualizzare le playList: ");
                                System.out.println("Digitare 3 per inserire una canzone in una playlist esistente: ");
                                System.out.println("Digitare 4 per eliminare una canzone da una playlist esistente: ");
                                System.out.println("Digitare 5 per eliminare una playlist: ");
                                System.out.println("Digitare 6 per tornare al menu' principale: ");
                                System.out.println("Digitare 7 per fare il logout: ");
                                System.out.println("----------------------------------------------------------------");
                                System.out.print("scelta: ");
                                try {
                                    varmenu = Integer.parseInt(br.readLine());
                                    controllomenupersonale = true;
                                } catch (Exception e) {
                                    System.out.println("\nDEVI INSERIRE SOLO NUMERI DA 1 A 7 PER FARE LE OPERAZIONI DESCRITTE SUL MENU'\n");
                                    controllomenupersonale = false;
                                }

                            } while (!controllomenupersonale);

                            switch (varmenu) {

                                case 1:
                                    boolean valido = true;
                                    System.out.println("INSERIRE IL NOME DELLA NUOVA PLAYLIST");
                                    do {
                                        valido = Playlist.registraPlaylist(idUtenteGlob, br.readLine());
                                        if (!valido)
                                            System.out.println("PLAYLIST GIA' ESISTENTE. INSERISCI UN NUOVO NOME PER LA TUA PLAYLIST");
                                    } while (!valido);            //controllo sul ritorno del metodo per capire se esista gia' una playlist associata all'idUtente

                                    break;
                                case 2:
                                    Playlist.visualizzaPlaylistUtente(idUtenteGlob);
                                    break;
                                case 3:

                                    System.out.println("Inserire nome playlist in cui aggiungere canzoni: ");
                                    playlist = br.readLine();
                                    Playlist.aggiungiDopoInPlaylist(playlist, idUtenteGlob);
                                    break;

                                case 4:

                                    System.out.println("Inserire nome playlist in cui aggiungere canzoni: ");
                                    playlist = br.readLine();
                                    System.out.println("inserire nome brano da aggiungere alla playlist");
                                    brano = br.readLine();
                                    Playlist.eliminaCanzoneDaPlaylist(idUtenteGlob, playlist, brano);
                                    break;

                                case 5:
                                    System.out.println("Inserisci nome playlist da eliminare");
                                    playlist = br.readLine();
                                    Playlist.cancellaPlaylist(idUtenteGlob, playlist);

                                case 6:
                                    varmenu = 0;
                                    break;
                                case 7:
                                    loggatoglob = false;
                                    varmenu = 0;
                                    break;

                            }

                        } while (varmenu != 0);

                    } else {
                        System.out.println("non ti sei ancora loggato!! \n");
                    }
                    break;
                case 6:
                    System.out.println("grazie e arrivederci");
                    varswich = 0;
            }

        } while (varswich != 0);

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

    }
}
