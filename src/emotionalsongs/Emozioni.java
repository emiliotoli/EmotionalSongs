package emotionalsongs;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Emilio Daverio
 * la classe serve per inserire e visualizzare le emozioni
 */
public class Emozioni {

    //<editor-fold desc="Attributi">
    private final static String sep = System.getProperty("file.separator");
    private final static String[] Emozioni = {"Amazement", "Solemnity", "Tenderness", "Nostalgia", "Calmness", "Power", "Joy", "Tension", "Sadness"};
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static Scanner scan = new Scanner(System.in);
    //</editor-fold>

    /**
     * @param titolo titolo della canzone
     * @throws IOException
     * @author Emilio Daverio
     * metodo che serve per inserire una nuova emozione
     */
    //<editor-fold desc="Inserisci Nuova Emozione">
    public static void inserisciEmozioni(String titolo) throws IOException {

        String emozione = null;
        //</editor-fold>
        String spiegazione;
        int punteggio = 0;
        boolean controlloScelta;
        boolean controlloPunteggio;

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
        int scelta = 0;

        do {
            do {
                try {
                    scelta = Integer.parseInt(br.readLine());
                    controlloScelta = true;

                } catch (Exception e) {
                    System.out.println("puoi inserire solo dei numeri conpresi tra 1 e 9");
                    System.out.println("reinserisci la scelta: ");
                    controlloScelta = false;
                }
            } while (!controlloScelta);

            if (scelta < 1 || scelta > 9) {
                System.out.println("scelta non valida, inserire da 1 a 9: ");
            }

        } while (scelta < 1 || scelta > 9);

        if (scelta == 1) {
            emozione = "Amazement";
        }
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
        spiegazione = br.readLine().toLowerCase();
        do {
            if (spiegazione.length() == 0) {
                System.out.println("inserisci almeno una spiegazione: ");
                spiegazione = br.readLine().toLowerCase();
            } else {
                break;
            }
        } while (spiegazione.length() == 0);
        do {
            if (spiegazione.length() > 50) {
                System.out.println("spiegazione troppo lungha.");
                System.out.println("inserire al massimo 50 caratteri.");
                spiegazione = br.readLine().toLowerCase();
            } else {
                break;
            }
        } while (spiegazione.length() > 50);

        //passo 3
        System.out.println("inserisci punteggio emopzione da 1 a 5: ");
        do {
            do {
                try {
                    punteggio = Integer.parseInt(br.readLine());
                    controlloPunteggio = true;
                } catch (Exception e) {
                    System.out.println("Puoi inserire solo un numero da 1 a 5 ");
                    System.out.println("reinserisci il punteggio: ");
                    controlloPunteggio = false;
                }
            } while (!controlloPunteggio);

            //punteggio = br.read();
            if (punteggio < 1 || punteggio > 5) {
                System.out.println("punteggio non valido, inserire da 1 a 5: ");
            }
        } while (punteggio < 1 || punteggio > 5);

        //passo 4
        System.out.println("inserisci Note (massimo 250 caratteri):  ");

        String note = br.readLine().toLowerCase();
        do {
            if (note.length() == 0) {
                System.out.println("Reinserire una nota");
                note = br.readLine();
            }
            else{
                break;
            }
        } while (note.length()==0);

        do {
             if(note.length() > 250) {
                    System.out.println("nota troppo lunga.");
                    System.out.println("inserire al massimo 250 caratteri.");
                    note = br.readLine().toLowerCase();
                }
             else {
                 break;
            }
        } while (note.length() > 250 );

        String emozioneNuova =titolo + "|" + emozione + "|" + spiegazione + "|" + punteggio + "|" + note;


        File file = new File("data" + sep + "Emozioni.dati.txt");
        String path = file.getAbsolutePath();
        String filePath = path;
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(emozioneNuova);
        bw.newLine();
        bw.close();
    }
    //</editor-fold>


    /**
     * @param tit
     * @throws IOException
     * @author Emilio Daverio
     * metodo per visualizzare le emozioni
     */
    //<editor-fold desc="Visulaizzare Le Emozioni">
    public static void visualizzaEmozioni(String tit) throws IOException {
        File file = new File("data" + sep + "Emozioni.dati.txt");
        String path = file.getAbsolutePath();
        FileReader fread = new FileReader(path);
        BufferedReader bufread = new BufferedReader(fread);
        double count_Amazement = 0, count_Solemnity = 0, count_Tenderness = 0, count_Nostalgia = 0, count_Calmness = 0, count_Power = 0, count_Joy = 0, count_Tension = 0, count_Sadness = 0;
        double countGenerale = 0;
        String supportoTitolo = null;
        String supportoEmozione = null;
        String sup;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            supportoTitolo = supporto[0].trim().toLowerCase();
            supportoEmozione = supporto[1].trim().toLowerCase();
            if (tit.trim().toLowerCase().equals(supportoTitolo.trim().toLowerCase())) {
                countGenerale++;
                if (supportoEmozione.equals("amazement")) {
                    count_Amazement++;
                }
                if (supportoEmozione.equals("solemnity")) {
                    count_Solemnity++;
                }
                if (supportoEmozione.equals("tenderness")) {
                    count_Tenderness++;
                }
                if (supportoEmozione.equals("nostalgia")) {
                    count_Nostalgia++;
                }
                if (supportoEmozione.equals("calmness")) {
                    count_Calmness++;
                }
                if (supportoEmozione.equals("power")) {
                    count_Power++;
                }
                if (supportoEmozione.equals("joy")) {
                    count_Joy++;
                }
                if (supportoEmozione.equals("tension")) {
                    count_Tension++;
                }
                if (supportoEmozione.equals("sadness")) {
                    count_Sadness++;
                }
            }
        }

        String pattern = "#.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        System.out.println("la percentuale delle emozioni di questa canzone sono: " + "\n");

        String formattedDouble = decimalFormat.format((count_Amazement / countGenerale) * 100);
        System.out.println("Amazement = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Solemnity / countGenerale) * 100);
        System.out.println("Solemnity = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Tenderness / countGenerale) * 100);
        System.out.println("Tenderness = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Nostalgia / countGenerale) * 100);
        System.out.println("Nostalgia = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Calmness / countGenerale) * 100);
        System.out.println("Calmness = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Power / countGenerale) * 100);
        System.out.println("Power = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Joy / countGenerale) * 100);
        System.out.println("Joy = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Tension / countGenerale) * 100);
        System.out.println("Tension = " + formattedDouble + "%");

        formattedDouble = decimalFormat.format((count_Sadness / countGenerale) * 100);
        System.out.println("Sadness = " + formattedDouble + "%");
    }
    //</editor-fold>
}

