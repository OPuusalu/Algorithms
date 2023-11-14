
/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 2a
 * Teema: Rekursioon. Variantide läbivaatamine.
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *
 *****************************************************************************/

public class Kodu2A {
    /**
     * Leiab, kas massiivis a leidub selline elementide kombinatsioon, mille summa on 10000, kusjuures ei valita üle poolte elementide
     * @param a sisendi massiiv
     * @return tagastab tõeväärtuse
     */
    public static boolean valik(int[] a) {
        return valikRek(0, a, 0, 0);
    }

    /**
     * Rekursiivne meetod valik() eesmärkide täitmiseks
     * @param i vaadeldava elemendi indeks
     * @param a sisendi massiiv
     * @param sum summa
     * @param valitudElemente mitu elementi oleme massiivist a valinud
     * @return tagastab tõeväärtuse
     */
    private static boolean valikRek(int i, int[] a, int sum, int valitudElemente) {
        if (valitudElemente > (a.length/2) || sum > 10_000)
            return false;
        if (sum == 10_000)
            return true;
        if (i == a.length)
            return false;
        return valikRek(i+1, a, sum+a[i], valitudElemente+1) || valikRek(i+1, a, sum, valitudElemente);
    }

    public static void main(String[] args) {

        int[] kaalud = {9000, 50, 75, 366, 1000};

        System.out.println(valik(kaalud));

    }

}