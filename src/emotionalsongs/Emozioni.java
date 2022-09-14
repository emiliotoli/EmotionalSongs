package emotionalsongs;

import java.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Emozioni {
    private final static String sep = System.getProperty("file.separator");
    private final static String[] Emozioni={"Amazement" , "Solemnity" , "Tenderness" , "Nostalgia" , "Calmness" , "Power" , "Joy" , "Tension" , "Sadness"};
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static Scanner scan=new Scanner(System.in);
    

    /*public boolean inserisciEmozioni(String IDUtente , String canzone , String Emozione, int Intensità , String note) throws IOException {
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
    }*/

    public static void inserisciEmozioni() throws IOException {
        String emozione = null;
        String spiegazione;
        int punteggio ;

        
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
            punteggio=scan.nextInt();
            //punteggio = br.read();
            if (punteggio < 1 || punteggio > 5) {
                System.out.println("punteggio non valido, inserire da 1 a 5: ");
            }
        } while (punteggio < 1 || punteggio > 5);

        //passo 4
        System.out.println("inserisci Note (massimo 250 caratteri):  ");

        String note = br.readLine().toLowerCase();
        //String note =scan.nextLine();

        do {
            if (note.length()==0) {
                System.out.println("Reinserire una nota");
                //note = br.readLine().toLowerCase();
                note =scan.nextLine();
            } else {
                if (note.length() > 250) {
                    System.out.println("nota troppo lunga.");
                    System.out.println("inserire al massimo 250 caratteri.");
                    note = br.readLine().toLowerCase();
                    //note =scan.nextLine();
                }
            }
        } while (note.length() > 250 || note.length()==0);

        String emozioneNuova= emozione +"|"+ spiegazione +"|"+ punteggio +"|"+ note;


        File file = new File("data" + sep + "Emozioni.dati.txt");
        String path = file.getAbsolutePath();

        /*BufferedWriter bw = new BufferedWriter(new FileWriter(path, true)); // sbagliata questa riga
        bw.write(emozioneNuova);
        bw.flush(); //svuoto lo stream
        bw.close();*/
        String filePath= ".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Emozioni.dati.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
        bw.write(emozioneNuova);
        bw.newLine();
        bw.close();
    }

    public static void visualizzaEmozioni(String tit) throws IOException{
        FileReader fread = new FileReader(".." + sep + "EmotionalSongs" + sep + ".data" + sep + "Emozioni.dati.txt");
        BufferedReader bufread = new BufferedReader(fread);
        int count_Amazement=0, count_Solemnity=0, count_Tenderness=0, count_Nostalgia=0, count_Calmness=0,count_Power=0, count_Joy=0, count_Tension=0, count_Sadness=0;
        int countGenerale=0;
        String supportoTitolo=null;
        String supportoEmozione=null;
        String sup;
        while ((sup = bufread.readLine()) != null) {
            String[] supporto = sup.split("\\|");
            supportoTitolo = supporto[0].trim().toLowerCase();
            supportoEmozione = supporto[1].trim().toLowerCase();
            if (tit.trim().toLowerCase().equals(supportoTitolo.trim().toLowerCase())) {
                countGenerale++;
                if(supportoEmozione.equals("amazement")){
                    count_Amazement++;
                }
                if(supportoEmozione.equals("solemnity")){
                    count_Solemnity++;
                }
                if(supportoEmozione.equals("tenderness")){
                    count_Tenderness++;
                }
                if(supportoEmozione.equals("nostalgia")){
                    count_Nostalgia++;
                }
                if(supportoEmozione.equals("calmness")){
                    count_Calmness++;
                }
                if(supportoEmozione.equals("power")){
                    count_Power++;
                }
                if(supportoEmozione.equals("joy")){
                    count_Joy++;
                }
                if(supportoEmozione.equals("tension")){
                    count_Tension++;
                }
                if(supportoEmozione.equals("sadness")){
                    count_Sadness++;
                }
            }
        }

        System.out.println("la percentuale delle emozioni di questa canzone sono: "+ "\n");
        System.out.println("Amazement = " + (count_Amazement/countGenerale)*100 +"%");
        System.out.println("Solemnity = "+ (count_Solemnity/countGenerale)*100 +"%");
        System.out.println("Tenderness = "+ (count_Tenderness/countGenerale)*100 +"%");
        System.out.println("Nostalgia = "+ (count_Nostalgia/countGenerale)*100 +"%");
        System.out.println("Calmness = "+ (count_Calmness/countGenerale)*100 +"%");
        System.out.println("Power = "+ (count_Power/countGenerale)*100 +"%");
        System.out.println("Joy = "+ (count_Joy/countGenerale)*100 +"%");
        System.out.println("Tension = "+ (count_Tension/countGenerale)*100 +"%");
        System.out.println("Sadness = "+ (count_Sadness/countGenerale)*100 +"%");
    }

    private static boolean controlloEsisteEmozione(String emozione){
        for(int i=0;i< Emozioni.length;i++){
            if(Emozioni[i].equals(emozione))
                return true;
        }
        return false;
    }
}

