import java.util.Arrays;
import java.util.Random;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 1
 * Teema: Sorteerimismeetodite ajalise keerukuse võrdlus
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *       https://www.geeksforgeeks.org/insertion-sort/
 *       https://www.geeksforgeeks.org/quick-sort/?ref=lbp
 *
 *****************************************************************************/
public class Kodu1 {
    public static void main(String[] args) {

        //jooksutame testid, mis kontrollivad, kas leitud massiiv on mitte-kasvav
        System.out.println("Testid pistemeetodiga");
        System.out.println();
        testidpiste();
        System.out.println();
        System.out.println();
        System.out.println("Testid kiirmeetodiga");
        System.out.println();
        testidkiir();


        //Testime aegu loodud meetoditega
        //aegkiir();
        //aegjavasort();
        //aegpiste();

    }

    /**
     * Leiame ajad pistemeetodiga
     */
    private static void aegpiste() {

        for (int i = 1000; i <= 40_000; i+=1_000) {

            int[] massiiv = suvamassiiv(i);
            long start = System.currentTimeMillis();
            pistemeetod(massiiv);
            long stop = System.currentTimeMillis();

            System.out.println((stop-start) + "ms");
        }
    }

    /**
     * Leiame ajad kiirmeetodiga
     */
    private static void aegkiir() {

        for (int i = 40_000; i <= 5_000_000; i+=200_000) {

            int[] massiiv = suvamassiiv(i);
            long start = System.currentTimeMillis();
            kiirmeetod(massiiv);
            long stop = System.currentTimeMillis();

            System.out.println((stop-start) + "ms");
        }
    }

    /**
     * Leiame ajad Arrays.sort meetodiga
     */
    private static void aegjavasort() {

        for (int i = 40_000; i <= 5_000_000; i+=200_000) {

            int[] massiiv = suvamassiiv(i);
            long start = System.currentTimeMillis();
            Arrays.sort(massiiv);
            long stop = System.currentTimeMillis();

            System.out.println((stop-start) + "ms");
        }
    }

    /**
     * Testid kiirmeetodiga
     */
    private static void testidkiir() {
        //Loome testimiseks järjendid
        int[][] a = {
                {4, 3, 6, 7, 1, 2, 2, 5, 8, -1, 10, 9},
                {4, 3, 6, 7, 1, 2, 2, 5, 8},
                {2,1},
                {5},
                {}
        };
        System.out.println("Testid etteantud masiividega:");
        for (int[] test : a
        ) {
            kiirmeetod(test);
            System.out.println(Arrays.toString(test));
            if(onSorteeritud(test)){
                System.out.println("Test läbitud.");
            }
        }
        System.out.println();
        System.out.println("Test suvalise massiiviga");
        //loome pikkusega 25 tühja massiivi
        int[] suvaline = suvamassiiv(25);
        System.out.println("Loodud suvaline massiiv: \n" + Arrays.toString(suvaline));
        kiirmeetod(suvaline);
        System.out.println("Sorteeritud massiiv: ");
        System.out.println(Arrays.toString(suvaline));
        if(onSorteeritud(suvaline)){
            System.out.println("Test läbitud.");
        }

    }

    /**
     * Testid pistemeetodiga
     */
    private static void testidpiste() {

        //Loome testimiseks järjendid
        int[][] a = {
                {4, 3, 6, 7, 1, 2, 2, 5, 8, -1, 10, 9},
                {4, 3, 6, 7, 1, 2, 2, 5, 8},
                {2,1},
                {5},
                {}
        };
        System.out.println("Testid etteantud masiividega:");
        for (int[] test : a
        ) {
            pistemeetod(test);
            System.out.println(Arrays.toString(test));
            if(onSorteeritud(test)){
                System.out.println("Test läbitud.");
            }
        }
        System.out.println();
        System.out.println("Test suvalise massiiviga");
        //loome pikkusega 25 tühja massiivi
        int[] suvaline = suvamassiiv(25);
        System.out.println("Loodud suvaline massiiv: \n" + Arrays.toString(suvaline));
        pistemeetod(suvaline);
        System.out.println("Sorteeritud massiiv: ");
        System.out.println(Arrays.toString(suvaline));
        if(onSorteeritud(suvaline)){
            System.out.println("Test läbitud.");
        }

    }

    /**
     * Theta (n log n) keskmise keerukusega sorteerimismeetod
     * @param a sorteeritav järjend
     */
    private static void kiirmeetod(int[] a) {
        kiirmeetodRek(a, 0, a.length-1);
    }

    /**
     * kiirmeetod() meetodi rekursiivne abimeetod
     * @param a sorteeritav järjend
     * @param vasak järjendi väikseim indeks
     * @param parem järjendi suurim indeks
     */
    private static void kiirmeetodRek(int[] a, int vasak, int parem) {

        if (vasak < parem){
            //peale jaotamist selle elemendi indeks, mille alusel jaotasime
            int kesk = jaotamine(a, vasak, parem);
            //nüüd sorteerime vasaku poole sama pritsiibi järgi
            kiirmeetodRek(a, vasak, kesk-1);
            //sorteerime ka parema poole
            kiirmeetodRek(a, kesk+1, parem);
        }
    }

    /**
     * jaotame järjendi kaheks, kus lõpuks valitud elemendist vasakul pool olevad on sellest väiksemad ja
     * paremal pool sellest suuremad
     * @param a järjend, mida sorteerime
     * @param alumine väikseim vaadeldava alamjärjendi indeks
     * @param ylemine suurim vaadeldava alamjärjendi indeks
     * @return tagastab selle indeksi, kus on keskmine arv
     */
    private static int jaotamine(int[] a, int alumine, int ylemine) {

        int vorreldav = a[alumine];
        int vasak = alumine + 1;
        int parem = ylemine;

        while (vasak <= parem) {
            //liigutame vasakut indeksit niikaua paremale kuni leiame võrreldavast väiksema arvu
            while (vasak <= parem && a[vasak] > vorreldav)
                vasak++;
            //liigutame paremat indeksit niikaua vasakule kuni leiame võrrledavast suurema arvu
            while (vasak <= parem && a[parem] < vorreldav)
                parem--;
            // kui vasak indeks on väiksem võrdne paremaga, siis vahetame indeksitel olevad elemendid ära
            if (vasak <= parem) {
                int ajutine = a[vasak];
                a[vasak] = a[parem];
                a[parem] = ajutine;
                vasak++;
                parem--;
            } else {
                break;
            }
        }
        //lõpuks vahetame võrreldava paremalt esimese temast väiksemaga ära
        a[alumine] = a[parem];
        a[parem] = vorreldav;
        return parem;
    }

    /**
     * Theta (n^2) keskmise keerukusega sorteerimismeetod
     * @param a sorteeritav järjend
     */
    private static void pistemeetod(int[] a) {
        int pikkus = a.length;
        for (int i = 1; i < pikkus; i++) {
            // võrreldava elemendi indeks j
            int j = i-1;
            //liigutame järjest vasakule kuni element pole enam suurem enda vasakul olevast
            while (a[j+1] > a[j]) {
                //Salvestame ajutise elemendi, et neid saaks vahetada
                int ajutine = a[j+1];
                //vahetame elemendid
                a[j+1] = a[j];
                a[j] = ajutine;
                //kui j pole 0, siis vähendame seda
                if (j != 0) {j--;}
            }
        }
    }

    /**
     * kontrollib, kas iga järgmine element on väiksem kui eelmine
     * @param a kontrollitav järjend
     * @return tagastab tõeväärutuse true või false
     */
    private static boolean onSorteeritud(int[] a){

        int pikkus = a.length;

        if (pikkus == 2){
            if (a[0] < a[1])
                return false;
        }
        for (int i = 0; i < pikkus-1; i++) {
            if (a[i] < a[i+1])
                return false;
        }
        return true;
    }

    /**
     * loob suvalise massiivi arvudega kuni 1000
     * @param pikkus oodatava massiivi pikkus
     * @return tagastab suvalise massiivi antud pikkusega
     */
    private static int[] suvamassiiv(int pikkus){
        int[] suvaline = new int[pikkus];
        for (int i = 0; i < pikkus; i++) {
            suvaline[i] = new Random().nextInt(Integer.MAX_VALUE);
        }
        return suvaline;
    }

}
