package emotionalsongs;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Cristian Stinga
 * @author Stefano Farina
 *         è il main dell' applicazione, si trovano tutte le opzioni che un
 *         utente può fare
 */
public class EmotionalSongs {

  public static void main(String[] args)
    throws IOException, NumberFormatException {
    String nome, cognome, codfisc, indirizzo, numerocivico, comune, provincia, email, username, password;
    String cap = null;
    String titolo, autore, anno, album, durata, genere;
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    final String sep = System.getProperty("file.separator");
    String nomePlaylist;
    String idUtenteGlob = null; // variabile globale che serve ad avere l'id-utente
    String idUtente;
    Boolean controlloCap;
    boolean esiste;
    boolean loggatoglob = false;
    boolean controlloMenuPrincipale;
    boolean controllomenupersonale;
    boolean nonLoggato;
    int varswich = 0;
    int varmenu = 0;

    File file = new File("data" + sep + "UtentiRegistrati.dati.txt");
    String path = file.getAbsolutePath();

    do {
      do {
        if (!loggatoglob) {
          System.out.println("\n");
          System.out.println(
            "-------------------- Menu' principale applicazione ----------------------"
          );
          System.out.println(
            "Digitare 1 per cercare una canzone in base al Titolo:"
          );
          System.out.println(
            "Digitare 2 per cercare una canzone in base all'Autore e all'Anno:"
          );
          System.out.println("Digitare 3 per per registrarti:");
          System.out.println("Digitare 4 per eseguire il login:");
          System.out.println("Digitare 5 per accedere all'area personale: ");
          System.out.println("Digitare 6 per terminare tutte le operazioni");
          System.out.println(
            "Per accedere alla sezione 5, è necessario eseguire il login."
          );
          System.out.println(
            "-------------------------------------------------------------------------"
          );
        } else {
          System.out.println("\n");
          System.out.println(
            "-------------------- Menu' principale applicazione ----------------------"
          );
          System.out.println(
            "Digitare 1 per cercare una canzone in base al Titolo:"
          );
          System.out.println(
            "Digitare 2 per cercare una canzone in base all'Autore e all'Anno:"
          );
          System.out.println("Digitare 3 per per registrarti:");
          System.out.println("Digitare 4 per eseguire il login:");
          System.out.println("Digitare 5 per accedere all'area personale: ");
          System.out.println("Digitare 6 per terminare tutte le operazioni");
          System.out.println(
            "Per accedere alla sezione 5, è necessario eseguire il login."
          );
          System.out.println(
            "-------------------------------------------------------------------------"
          );
        }

        System.out.print("scelta: ");
        try {
          varswich = Integer.parseInt(br.readLine());
          controlloMenuPrincipale = true;
        } catch (Exception e) {
          System.out.println(
            "\nDEVI INSERIRE SOLO NUMERI DA 1 A 5 PER FARE LE OPERAZIONI DESCRITTE SUL MENU'"
          );
          System.out.println(
            "PER TERMINARE LE OPERAZIONI SUBITO PUOI DIGITARE 0 O 6"
          );
          controlloMenuPrincipale = false;
        }
      } while (!controlloMenuPrincipale);

      switch (varswich) {
        case 1:
          // <editor-fold desc="RICERCA BRANO PER TITOLO">
          System.out.println("inizio procedura di ricerca in base al titolo\n");
          System.out.print("inserisci titolo da cercare: ");
          titolo = br.readLine().trim().toLowerCase();
          Canzoni.cercaBranoMusicaleTitolo(titolo, loggatoglob);
          break;
        // </editor-fold>
        case 2:
          // <editor-fold desc="RICERCA BRANO PER AUTORE ED ANNO">
          System.out.println(
            "inizio procedura ricerca canzone per autore e titolo:"
          );
          System.out.print("\ninserisci autore: ");
          autore = br.readLine().trim().toLowerCase();
          System.out.print("Inserisci anno: ");
          anno = br.readLine().trim().toLowerCase();
          Canzoni.cercaBranoMusicaleAutoreAnno(autore, anno, loggatoglob);
          break;
        // </editor-fold>
        case 3:
          // <editor-fold desc="REGISTRAZIONE NUOVO UTENTE">
          System.out.println("inserisci utente da registrare: ");

          System.out.print("Inserisci nome: ");
          nome = br.readLine().toLowerCase();
          nome = Utenti.LunghezzaNome(nome);

          System.out.print("inserisci cognome: ");
          cognome = br.readLine().toLowerCase();
          cognome = Utenti.LunghezzaCognome(cognome);

          System.out.print("inserisci codice fiscale: ");
          codfisc = br.readLine().toLowerCase();
          codfisc = Utenti.ControlloFormatocf(codfisc); // controllo che il codice fiscale sia nel formato
          // corretto

          System.out.print("inserisci indirizzo: ");
          indirizzo = br.readLine();

          System.out.print("inserisci numero civico: ");
          numerocivico = br.readLine();
          numerocivico = Utenti.controlloNumeroCivico(numerocivico);

          System.out.print("inserisci cap: ");
          do {
            try {
              cap = br.readLine();
              cap = Utenti.controlloCAP(Integer.parseInt(cap));
              controlloCap = true;
            } catch (Exception e) {
              System.out.println("puoi inserire solo numeri");
              System.out.println("inserisci di nuovo: ");
              controlloCap = false;
            }
          } while (!controlloCap);

          System.out.print("inserire il comune: ");
          comune = br.readLine();

          System.out.print("inserire la provincia: ");
          provincia = br.readLine();

          System.out.print("inserisci email: ");
          email = br.readLine();
          Utenti.controlloMail(email);

          System.out.print("inserisci user: ");
          username = br.readLine();
          username = Utenti.controlloUserEsistente(username);

          System.out.print("inserisci password: ");
          password = br.readLine();
          password = Utenti.controlloPassword(password);

          Utenti.Registrazione(
            nome,
            cognome,
            codfisc,
            indirizzo,
            numerocivico,
            cap,
            comune,
            provincia,
            email,
            username,
            password
          );
          Utenti.ScriviFile(
            Utenti.toString(
              nome,
              cognome,
              codfisc,
              indirizzo,
              numerocivico,
              cap,
              comune,
              provincia,
              email,
              username,
              password
            ),
            path
          ); // inserisco un nuovo utente nel file
          System.out.println("\nregistrazione completata");
          break;
        // </editor-fold>

        case 4:
          if (!loggatoglob) {
            do {
              System.out.print("inserisci l'UserId:");
              idUtente = br.readLine().trim().toLowerCase();
              System.out.print("inserisci la password: ");
              password = br.readLine().trim();
              loggatoglob = Utenti.Login(idUtente, password);
              if (!loggatoglob) {
                nonLoggato = false;
                break;
              } else {
                nonLoggato = true;
              }
            } while (!loggatoglob);
            if (!nonLoggato) {
              loggatoglob = false;
            } else {
              loggatoglob = true;
              idUtenteGlob = idUtente;
              System.out.println("sei loggato");
            }
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
                System.out.println(
                  "-------------------- menu' area personale ----------------------"
                );
                System.out.println("Digitare 1 per creare una playlist: ");
                System.out.println("Digitare 2 per visualizzare le playList: ");
                System.out.println(
                  "Digitare 3 per visualizzare le canzoni di una playlist: "
                );
                System.out.println(
                  "Digitare 4 per inserire una canzone in una playlist esistente: "
                );
                System.out.println(
                  "Digitare 5 per eliminare una canzone da una playlist esistente: "
                );
                System.out.println("Digitare 6 per eliminare una playlist: ");
                System.out.println(
                  "Digitare 7 per tornare al menu' principale: "
                );
                System.out.println("Digitare 8 per fare il logout: ");
                System.out.println(
                  "----------------------------------------------------------------"
                );
                System.out.print("scelta: ");
                try {
                  varmenu = Integer.parseInt(br.readLine());
                  controllomenupersonale = true;
                } catch (Exception e) {
                  System.out.println(
                    "\nDEVI INSERIRE SOLO NUMERI DA 1 A 7 PER FARE LE OPERAZIONI DESCRITTE SUL MENU'\n"
                  );
                  controllomenupersonale = false;
                }
              } while (!controllomenupersonale);

              switch (varmenu) {
                case 1:
                  boolean valido = true;
                  System.out.println("INSERIRE IL NOME DELLA NUOVA PLAYLIST");
                  do {
                    valido =
                      Playlist.registraPlaylist(idUtenteGlob, br.readLine());
                    if (!valido) System.out.println(
                      "PLAYLIST GIA' ESISTENTE. INSERISCI UN NUOVO NOME PER LA TUA PLAYLIST"
                    );
                  } while (!valido); // controllo sul ritorno del metodo per capire se esista gia' una
                  // playlist associata all'idUtente

                  break;
                case 2:
                  Playlist.visualizzaPlaylistUtente(idUtenteGlob);
                  break;
                case 3:
                  boolean srv3 = false;
                  do {
                    System.out.println(
                      "Inserisci nome della playlist di cui visualizzare le canzoni: "
                    );
                    playlist = br.readLine();
                    srv3 =
                      Playlist.controlloPlaylistEsistente(
                        idUtenteGlob,
                        playlist
                      );
                    if (!srv3) System.out.println(
                      "Nome playlist inesistente! "
                    );
                  } while (!srv3);
                  Playlist.visualizzaCanzoniPlaylist(idUtenteGlob, playlist);
                  break;
                case 4:
                  System.out.println(
                    "Inserire nome playlist in cui aggiungere canzoni: "
                  );
                  playlist = br.readLine();
                  Playlist.aggiungiDopoInPlaylist(playlist, idUtenteGlob);
                  break;
                case 5:
                  boolean srv = false;
                  do {
                    System.out.println(
                      "Inserire nome playlist da cui eliminare canzoni: "
                    );
                    playlist = br.readLine();
                    srv =
                      Playlist.controlloPlaylistEsistente(
                        idUtenteGlob,
                        playlist
                      );
                    if (!srv) System.out.println(
                      "Errore, reinserire nome playlist"
                    );
                  } while (!srv);
                  srv = false;
                  do {
                    System.out.println(
                      "inserire nome brano da eliminare dalla playlist"
                    );
                    brano = br.readLine();
                    srv =
                      Playlist.eliminaCanzoneDaPlaylist(
                        idUtenteGlob,
                        playlist,
                        brano
                      );
                    if (!srv) System.out.println(
                      "Canzone inesistente! Reinserire il titolo della canzone"
                    );
                  } while (!srv);
                  break;
                case 6:
                  System.out.println("Inserisci nome playlist da eliminare");
                  playlist = br.readLine();
                  Playlist.cancellaPlaylist(idUtenteGlob, playlist);
                case 7:
                  varmenu = 0;
                  break;
                case 8:
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
  }
}
