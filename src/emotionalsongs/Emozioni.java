package emotionalsongs;

import java.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Emozioni {
    private final static String sep = System.getProperty("file.separator");
    private final static String[] Emozioni={"Amazement" , "Solemnity" , "Tenderness" , "Nostalgia" , "Calmness" , "Power" , "Joy" , "Tension" , "Sadness"};
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    String emozione;
    String spiegazione;
    int punteggio;
    String note;

    public boolean inserisciEmozioni(String IDUtente , String canzone , String Emozione, int Intensità , String note) throws IOException {
        //cerca di percorso file
        //apertura stream
        if(!Canzoni.controlloCanzoneEsistente(canzone)) {
            System.out.println("canzone inserita non corretta!");
            return false;
        }
        if(!controlloEsisteEmozione(Emozione)){
            System.out.println("emozione inserita non corretta!");
            return false;
        }

        //scrittura sul file dell'id + nome canzone + emozione + intensità + note
        return true;
    }

    public void inserisciEmozioni() throws IOException {

        System.out.println("scegli categora emozioni: " + "\n");
        System.out.println("Digitare 1 per --> Amazement");
        System.out.println("Digitare 2 per --> Solemnity");
        System.out.println("Digitare 3 per --> Tenderness");
        System.out.println("Digitare 4 per --> Nostalgia");
        System.out.println("Digitare 5 per --> Calmness");
        System.out.println("Digitare 6 per --> Power");
        System.out.println("Digitare 7 per --> Joy");
        System.out.println("Digitare 8 per --> Tension");
        System.out.println("Digitare 9 per --> Sadness");

        System.out.print("scelta: ");
        int scelta;
        do {
            scelta = Integer.parseInt(br.readLine());
            if (scelta < 1 || scelta > 9) {
                System.out.println("scelta non valida, inserire da 1 a 9: ");
            }

        } while (scelta < 1 || scelta > 9);

        if (scelta == 1) {
            emozione = "Amazement";
        }
        //</editor-fold>
        if (scelta == 2) {
            emozione = "Solemnity";
        }
        if (scelta == 3) {
            emozione = "Tenderness";
        }
        if (scelta == 4) {
            emozione = "Nostalgia";
        }
        if (scelta == 5) {
            emozione = "Calmness";
        }
        if (scelta == 6) {
            emozione = "Power";
        }
        if (scelta == 7) {
            emozione = "Joy";
        }
        if (scelta == 8) {
            emozione = "Tension";
        }
        if (scelta == 9) {
            emozione = "Sadness";
        }


        //passo 2

        System.out.println("motiva la scelta di questa emozione con un breve frase: ");
        do {
            spiegazione = br.readLine().toLowerCase();
            if (spiegazione.length() == 0) {
                System.out.println("inserire una spiegazione");
            } else {
                if (spiegazione.length() > 50) {
                    System.out.println("spiegazione troppo lungha.");
                    System.out.println("inserire al massimo 50 caratteri.");
                }
            }
        } while (spiegazione.length() > 50);

        //passo 3
        System.out.println("inserisci punteggio emopzione da 1 a 5: ");
        do {
            punteggio = br.read();
            if (punteggio < 1 || punteggio > 5) {
                System.out.println("punteggio non valido, inserire da 1 a 5: ");
            }
        } while (punteggio < 1 || punteggio > 5);

        //passo 4
        System.out.println("inserisci Note (massimo 250 caratteri):  ");
        do {
            note = br.readLine().toLowerCase();
            if (note.length() == 0) {
                System.out.println("inserire una nota");
            } else {
                if (note.length() > 250) {
                    System.out.println("nota troppo lunga.");
                    System.out.println("inserire al massimo 250 caratteri.");
                }
            }
        } while (note.length() > 250);

        String emozioneNuova=emozione+ "|"+spiegazione+"|"+punteggio+"|"+note;

        File file = new File("data" + sep + "Emozioni.dati.txt");
        String path = file.getAbsolutePath();
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(emozioneNuova);
        bw.flush(); //svuoto lo stream
        bw.close();

    }

    public void visualizzaEmozioni(){

    }

    private static boolean controlloEsisteEmozione(String emozione){
        for(int i=0;i< Emozioni.length;i++){
            if(Emozioni[i].equals(emozione))
                return true;
        }
        return false;
    }
}

