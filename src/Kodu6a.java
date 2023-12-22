import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 6a
 * Teema: Kahendkuhjad
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *   https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
 *****************************************************************************/

public class Kodu6a {

    /**
     * Meetod koodipuu koostamiseks
     * @param sisu sisendi tekst
     * @return tagastab koodipuu juurtipu
     */
    public static Tipp koostaKoodipuu(String sisu) {
        // a) teksti põhjal luua sümbolite sagedustabel;
        HashMap<Character, Integer> sagedused = new HashMap<>();

        for (char symbol : sisu.toCharArray()) {
            sagedused.put(symbol, 1 + sagedused.getOrDefault(symbol, 0));
        }

        // b) sagedustabeli põhjal luua loetelu ühetipulistest puudest (igas tipus on siis info kujul sümbol + selle esinemiste arv)
        PriorityQueue<Tipp> puudeHunnik = new PriorityQueue<>(Comparator.comparingInt(t -> t.x));

        for (Character voti : sagedused.keySet()) {
            Tipp tipp = new Tipp(voti.toString(), null, null);
            tipp.x = sagedused.get(voti);
            puudeHunnik.add(tipp);
        }

        // c) selliste puude metsast Huffmani koodipuu loomine (koodipuu lehttippudes peaks olema .info välja väärtuseks vastav sümbol)
        while (puudeHunnik.size() > 1) {
            Tipp vasak = puudeHunnik.poll();
            Tipp parem = puudeHunnik.poll();

            Tipp uusTipp = new Tipp(null, vasak, parem);
            uusTipp.x = vasak.x + parem.x;

            puudeHunnik.add(uusTipp);
        }

        return puudeHunnik.poll();
    }

    /**
     * Meetod, mis kodeerib sisendi teksti koodupuu järgi boolean Arrayks
     * @param koodipuu sisendi teksti järgi loodud koodipuu
     * @param sisu sisendi tekst
     * @return Tagastab kodeerimise tulemusena saadud tõeväärtuste massiivi
     */
    public static boolean[] kodeeri(Tipp koodipuu, String sisu) {
        // d) leida koodipuust igale sümbolile vastav uus kood -> prefikskood
        HashMap<Character, String> koodid = new HashMap<>();
        loekoodid(koodipuu, "", koodid);

        // e) algtekst, prefikskoodid, koodipuu -> pakitud tekst (bittide massiiv, kus true on 1 ja false on 0)
        List<Boolean> pakitud = new ArrayList<>();
        for (char symbol : sisu.toCharArray()){
            String kood = koodid.get(symbol);
            for (int i = 0; i < kood.length(); i++) {
                pakitud.add(kood.charAt(i) == '1');
            }
        }
        boolean[] pakitudTekst = new boolean[pakitud.size()];
        for (int i = 0; i < pakitud.size(); i++) {
            pakitudTekst[i] = pakitud.get(i);
        }
        return pakitudTekst;
    }

    /**
     * Abimeetod prefikskoodide hasmapi tegemiseks < Sümbol, kood >
     * @param tipp koodipuu juurtipp
     * @param kood kood, millele rekursiivselt lisame nullisid ja ühtesi
     * @param koodid koodide hashmap, kuhu lisame koode
     */
    private static void loekoodid(Tipp tipp, String kood, HashMap<Character, String> koodid) {
        if (tipp != null){
            if (tipp.v == null && tipp.p == null){
                koodid.put(tipp.info.charAt(0), kood);
            }
            loekoodid(tipp.v, kood + "0", koodid);
            loekoodid(tipp.p, kood + "1", koodid);
        }
    }

    /**
     * Meetod bitide jada dekodeerimiseks
     * @param koodipuu sisendi teksti koodipuu
     * @param bitid kodeeritud teksti bitid
     * @return tagastab dekodeeritud tesksti Stringiga
     */
    public static String dekodeeri(Tipp koodipuu, boolean[] bitid) {
        // f) koodipuu, pakitud tekst -> lahtipakitud tekst
        StringBuilder tekst = new StringBuilder();

        Tipp tipp = koodipuu;
        for (boolean bit : bitid) {
            tipp = bit ? tipp.p : tipp.v;

            if (tipp.info != null) {
                tekst.append(tipp.info);
                tipp = koodipuu;
            }
        }

        return tekst.toString();
    }

    public static void main(String[] args) throws IOException {
        // Näide meetodite rakendamisest

        // Tekstifailist algse teksti lugemine
        String sisu = Files.readString(new File("Kõrboja_permees.txt").toPath());

        // Koodipuu koostamine, kodeerimine ja dekodeerimine
        Tipp koodipuu = koostaKoodipuu(sisu);

        boolean[] bitid = kodeeri(koodipuu, sisu);
        String dekodeeritud = dekodeeri(koodipuu, bitid);
        System.out.println(dekodeeritud);

        // Näide abiklassi FailiSisu kasutamisest

        // Andmete faili kirjutamine
//         Tipp koodipuu = koostaKoodipuu(sisu);
//         boolean[] bitid = kodeeri(koodipuu, sisu);
//         FailiSisu.kirjutaFaili(new File("kodeeritud.dat"), koodipuu, bitid);
//
        // Andmete failist lugemine
//         FailiSisu failiSisu = FailiSisu.loeFailist(new File("kodeeritud.dat"));
//         String dekodeeritud = dekodeeri(failiSisu.koodipuu, failiSisu.bitid);
//         System.out.println(dekodeeritud);
    }

}

