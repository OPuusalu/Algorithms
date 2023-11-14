import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 4
 * Teema: Paisktabelid
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *
 *****************************************************************************/

public class Kodu4 {
    /**
     * Genereerib isikukoodi lähtudes reeglitest püstitatud <a href=https://et.wikipedia.org/wiki/Isikukood>siin.</a>
     * <br>
     * Numbrite tähendused:
     * <ul style="list-style-type:none">
     *      <li> 1 - sugu ja sünniaasta esimesed kaks numbrit, (1...8) </li>
     *      <li> 2-3 - sünniaasta 3. ja 4. numbrid, (00...99) </li>
     *      <li> 4-5 - sünnikuu, (01...12) </li>
     *      <li> 6-7 - sünnikuupäev (01...31) </li>
     *      <li> 8-10 - järjekorranumber samal päeval sündinute eristamiseks (000...999) </li>
     *      <li> 11 - kontrollnumber (0...9) </li>
     * </ul>
     *
     * @return Eesti isikukoodi reeglitele vastav isikukood
     */
    static long genereeriIsikukood() {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        LocalDate sünnikuupäev = LocalDate.ofEpochDay(juhus.nextLong(-62091, 84006)); // Suvaline kuupäeva a 1800-2199
        long kood = ((sünnikuupäev.getYear() - 1700) / 100 * 2 - juhus.nextInt(2)) * (long) Math.pow(10, 9) +
                sünnikuupäev.getYear() % 100 * (long) Math.pow(10, 7) +
                sünnikuupäev.getMonthValue() * (long) Math.pow(10, 5) +
                sünnikuupäev.getDayOfMonth() * (long) Math.pow(10, 3) +
                juhus.nextInt(1000);
        int korrutisteSumma = 0;
        int[] iAstmeKaalud = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        for (int i = 0; i < 10; i++) {
            korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iAstmeKaalud[9 - i]);
        }
        int kontroll = korrutisteSumma % 11;
        if (kontroll == 10) {
            int[] iiAstmeKaalud = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            korrutisteSumma = 0;
            for (int i = 0; i < 10; i++) {
                korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iiAstmeKaalud[9 - i]);
            }
            kontroll = korrutisteSumma % 11;
            kontroll = kontroll < 10 ? kontroll : 0;
        }
        return kood * 10 + kontroll;
    }

    /**
     * Sorteerib isikukoodid sünniaja järgi:
     * <ul style="list-style-type:none">
     *     <li> a) järjestuse aluseks on sünniaeg, vanemad inimesed on eespool; </li>
     *     <li> b) kui sünniajad on võrdsed, määrab järjestuse isikukoodi järjekorranumber (kohad 8-10); </li>
     *     <li> c) kui ka järjekorranumber on võrdne, siis määrab järjestuse esimene number. </li>
     * </ul>
     *
     * @param isikukoodid sorteeritav isikukoodide massiiv
     */
    public static void sort(long[] isikukoodid) {
        //sorteerime kimbumeetodi (bucket sort) alusel, kimbud sorteeritakse java sisseehtitatud sortiga

        //teeeme hasmapi, kus võti on sünniaasta ja väärtus on sellel aastal sündinud inimeste isikukoodide List
        HashMap<Integer, List<Long>> hs = new HashMap<>();

        for (long kood : isikukoodid) {
            int sajand = (int) ((kood / 10000000000L) % 10);
            int aasta = (int) ((kood / 100000000L) % 100);
            //vinge valem baasaasta ehk 1800, 1900 jne leidmiseks
            int baasaasta = ((sajand+1)/2 - 1) * 100 + 1800;
            List<Long> list = hs.getOrDefault(baasaasta + aasta, new ArrayList<>());
            list.add(kood);
            hs.put(baasaasta + aasta, list);
        }
        int loendur = 0;
        for (int i = 1800; i <= 2199; i++) {
            //kui aastaarvu pole hashmapis, siis on meil tühi List
            List<Long> koodid = hs.getOrDefault(i, new ArrayList<>());
            // kui List pole tühi, sorteerime listi (kimbu) ja kirjutame tulemused algsesse massiivi
            if (!koodid.isEmpty()) {
                koodid.sort(new vordleja());
                for (long kood : koodid
                ) {
                    isikukoodid[loendur] = kood;
                    loendur++;
                }
            }
        }
    }

    /**
     * vordleja meetod, mille alusel me sama aastaarvuga isikukoode võrdleme
     */
    static class vordleja implements Comparator<Long> {
        @Override
        public int compare(Long o1, Long o2) {
            //arvutame isikukoodist võrreldavad numbrid
            int esnum1 = (int) ((o1 / 10000000000L) % 10);
            int esnum2 = (int) ((o2 / 10000000000L) % 10);
            int kuu1 = (int) ((o1 / 1000000L) % 100);
            int kuu2 = (int) ((o2 / 1000000L) % 100);
            int paev1 = (int) ((o1 / 10000L) % 100);
            int paev2 = (int) ((o2 / 10000L) % 100);
            int jarknum1 = (int) ((o1 / 10L) % 1000);
            int jarknum2 = (int) ((o2 / 10L) % 1000);

            if (kuu1 < kuu2)
                return -1;
            else if (kuu1 > kuu2)
                return 1;
            if (paev1 < paev2)
                return -1;
            else if (paev1 > paev2)
                return 1;
            if (jarknum1 < jarknum2)
                return -1;
            else if (jarknum2 < jarknum1)
                return 1;
            if (esnum1 < esnum2)
                return -1;
            else
                return 1;
        }
    }

    public static void main(String[] args) {

            long[] isikukoodid = new long[10];

        for (int i = 0; i < 10; i++) {
            isikukoodid[i] = genereeriIsikukood();
        }
        System.out.println(Arrays.toString(isikukoodid));
        sort(isikukoodid);
        System.out.println(Arrays.toString(isikukoodid));


    }

}