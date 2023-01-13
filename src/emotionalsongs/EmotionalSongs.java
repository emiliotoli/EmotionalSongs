package emotionalsongs;

//! Con Java.io.Console si può nasconodere la password
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Cristian Stinga
 * @author Stefano Farina
 *         è il main dell' applicazione, si trovano tutte le opzioni che un
 *         utente può fare
 */
public class EmotionalSongs {

  public static void main(String[] args)
    throws IOException, NumberFormatException {
    String nome, cognome, codfisc, indirizzo, numerocivico, comune =
      null, provincia, email, username, password;
    String cap = null;
    String titolo, autore, anno, album, durata, genere;
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    final String sep = System.getProperty("file.separator");
    String nomePlaylist;
    String idUtenteGlob = null;
    String idUtente;
    String errorMessageMain =
      "\nI soli valori ammessi sono quelli presenti nel menù.\n" +
      "Per terminare le operazioni puoi digitare 6\n";
    String errorMessagePersonal =
      "\nI soli valori ammessi sono quelli presenti nel menù.\n";
    String[] FrasiMenu = new String[] {
      "Inizio procedura di ricerca in base al titolo\n",
      "Inizio procedura di ricerca in base all'autore e titolo\n",
      
    };
    Boolean controlloCap;
    boolean esiste;
    boolean loggatoglob = false;
    boolean controlloMenuPrincipale;
    boolean controllomenupersonale;
    boolean controlloComune;
    boolean nonLoggato;
    int varswich = 0;
    int varmenu = 0;

    File file = new File("data" + sep + "UtentiRegistrati.dati.txt");
    String path = file.getAbsolutePath();
    String TestoMenu = null;
    do {
      do {
        if (!loggatoglob) {
          TestoMenu =
            "\n" +
            "-------------------- Menu' Emotional Songs ----------------------\n" +
            "Digitare 1 per cercare una canzone in base al titolo;\n" +
            "Digitare 2 per cercare in base all'autore oppure all'anno;\n" +
            "Digitare 3 per registrarsi;\n" +
            "Digitare 4 per eseguire il login;\n" +
            "Digitare 5 per accedere all'area personale\n" +
            "Digitare 6 per terminare le operazioni;\n" +
            "N.B.: Per accedere all'area personale prima è necessario eseguire il login.\n" +
            "-------------------------------------------------------------------------\n";
          System.out.println(TestoMenu);
        } else {
          TestoMenu =
            "\n" +
            "-------------------- Menu' Emotional Songs ----------------------\n" +
            "Digitare 1 per cercare una canzone in base al titolo;\n" +
            "Digitare 2 per cercare in base all'autore oppure all'anno;\n" +
            "Digitare 3 per registrarsi;\n" +
            "Digitare 4 per eseguire il logout;\n" +
            "Digitare 5 per accedere all'area personale\n" +
            "Digitare 6 per terminare le operazioni;\n" +
            "N.B.: Per accedere all'area personale prima è necessario eseguire il login.\n" +
            "-------------------------------------------------------------------------\n";

          System.out.println(TestoMenu);
        }
        System.out.print("Scelta: ");

        try {
          varswich = Integer.parseInt(br.readLine());

          if (varswich < 1 || varswich > 6) {
            controlloMenuPrincipale = false;
            System.out.println(errorMessageMain);
          } else {
            controlloMenuPrincipale = true;
          }
        } catch (Exception e) {
          System.out.println(errorMessageMain);

          controlloMenuPrincipale = false;
        }
      } while (!controlloMenuPrincipale);

      // controllare lo switch

      switch (varswich) {
        case 1:
          // <editor-fold desc="RICERCA BRANO PER TITOLO">
          System.out.println(FrasiMenu[varswich]);
          System.out.println("Inserisci titolo da cercare: ");
          titolo = br.readLine().trim().toLowerCase();
          Canzoni.cercaBranoMusicaleTitolo(titolo, loggatoglob);
          break;
        // </editor-fold>
        case 2:
          // <editor-fold desc="RICERCA BRANO PER AUTORE ED ANNO">
          System.out.println(FrasiMenu[varswich]);

          System.out.print("\nInserisci nome autore: ");
          autore = br.readLine().trim().toLowerCase();
          System.out.print("Inserisci anno: ");
          anno = br.readLine().trim().toLowerCase();
          Canzoni.cercaBranoMusicaleAutoreAnno(autore, anno, loggatoglob);
          break;
        // </editor-fold>
        case 3:
          // <editor-fold desc="REGISTRAZIONE NUOVO UTENTE">
          System.out.println("Inizio procedura di registrazione utente\n");

          System.out.print("Inserisci nome: ");
          nome = br.readLine().toLowerCase();
          nome = Utenti.LunghezzaNome(nome);

          System.out.print("Inserisci cognome: ");
          cognome = br.readLine().toLowerCase();
          cognome = Utenti.LunghezzaCognome(cognome);

          System.out.print("Inserisci codice fiscale: ");
          codfisc = br.readLine().toLowerCase();
          codfisc = Utenti.ControlloFormatocf(codfisc);
          // controllo che il codice fiscale sia nel formato corretto

          System.out.print("Inserisci indirizzo: ");
          indirizzo = br.readLine();

          System.out.print("Inserisci numero civico: ");
          numerocivico = br.readLine();
          numerocivico = Utenti.controlloNumeroCivico(numerocivico);

          System.out.print("Inserisci CAP: ");
          do {
            try {
              cap = br.readLine();
              cap = Utenti.controlloCAP(Integer.parseInt(cap));
              controlloCap = true;
            } catch (Exception e) {
              System.out.println(
                "Sono ammessi solo valori numerici.\nInserisci nuovamente."
              );
              controlloCap = false;
            }
          } while (!controlloCap);

          System.out.print("Inserisci il comune: ");
          comune = br.readLine();
          comune = Utenti.controlloCOMUNEnonNull(comune);

          System.out.print("Inserisci la provincia: ");
          provincia = br.readLine();
          provincia = Utenti.controlloProvincianonNull(provincia);

          System.out.print("Inserisci e-mail: ");
          email = br.readLine();
          Utenti.controlloMail(email);

          System.out.print("Inserisci un nome utente per il login: ");
          username = br.readLine();
          username = Utenti.controlloUserEsistente(username);

          System.out.print("Inserisci password: ");
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
          System.out.println("\nRegistrazione Completata.");
          break;
        // </editor-fold>

        case 4:
          if (!loggatoglob) {
            do {
              System.out.print("Inserisci nome utente: ");
              idUtente = br.readLine().trim().toLowerCase();
              System.out.print("Inserisci la password: ");
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
              System.out.println("Benvenuto " + idUtente + "!\n");
            }
          } else {
            loggatoglob = false;
            varmenu = 0;
            break;
          }

          break;
        case 5:
          String playlist, brano;
          if (loggatoglob) {
            do {
              do {
                TestoMenu =
                  "-------------------- menu' area personale ----------------------\n" +
                  "Digitare 1 per creare una playlist;\n" +
                  "Digitare 2 per visualizzare lista delle playlist;\n" +
                  "Digitare 3 per visualizzare una playlist;\n" +
                  "Digitare 4 per aggiungere una canzone ad una playlist;\n" +
                  "Digitare 5 per rimuovere una canzone da una playlist\n" +
                  "Digitare 6 per eliminare una playlist;\n" +
                  "Digitare 7 per tornare al menù principale;\n" +
                  "Digitare 8 per eseguire il logout.\n" +
                  "----------------------------------------------------------------\n\n";

                System.out.print(TestoMenu + "Scelta: ");

                try {
                  varmenu = Integer.parseInt(br.readLine());
                  if (varmenu < 0 || varmenu > 8) {
                    System.out.println(errorMessagePersonal);
                    controllomenupersonale = false;
                  } else {
                    controllomenupersonale = true;
                  }
                } catch (Exception e) {
                  System.out.println("\n" + errorMessagePersonal + "\n");
                  controllomenupersonale = false;
                }
              } while (!controllomenupersonale);

              switch (varmenu) {
                case 1:
                  boolean valido = true;
                  System.out.println("Inserire nome della playlist: ");
                  do {
                    valido =
                      Playlist.registraPlaylist(idUtenteGlob, br.readLine());
                    if (!valido) {
                      System.out.println(
                        "Playlist con questo nome già esistente\n" +
                        "Per favore, inserisci un nome che non è già in uso"
                      );
                    }
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
                    if (!srv3) System.out.println("Nome playlist non in uso! ");
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
                      "Inserire nome brano da eliminare dalla playlist: "
                    );
                    brano = br.readLine();
                    srv =
                      Playlist.eliminaCanzoneDaPlaylist(
                        idUtenteGlob,
                        playlist,
                        brano
                      );
                    if (!srv) System.out.println(
                      "Canzone inesistente!\nReinserire il titolo della canzone: "
                    );
                  } while (!srv);
                  break;
                case 6:
                  System.out.println("Inserisci nome playlist da eliminare: ");
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
            System.out.println("Non è ancora stato eseguito il login!\n");
          }
          break;
        case 6:
          System.out.println("Grazie e Arrivederci.");
          varswich = 6;
      }
    } while (varswich != 6);
  }
}
