import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 7a
 * Teema: Kaugusalgoritmid
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *
 *****************************************************************************/

public class Kodu7a {

    /**
     * Meetod linnade leidmiseks, kuhu jõuame k tankimisega
     * @param lähtelinn linn, kust algab algoritm
     * @param x mitu kilomeetrit saab läbida auto ühe tankimisega
     * @param k lubatud tankimiste arv
     * @param linnad linnade massiiv
     * @param M kauguste maatriks
     * @return tagastab linnade massiivi, kuhu jõuame k tankimisega
     */
    public static String[] jõuame(String lähtelinn, int x, int k, String[] linnad, int[][] M) {
        Queue<String> praegused = new ArrayDeque<>();
        Set<String> läbitud = new HashSet<>();
        praegused.add(lähtelinn);
        while (k > 0){

            Queue<String> järgmised = new ArrayDeque<>();

            while (!praegused.isEmpty()) {
                String järgmineLinn = praegused.poll();
                int linnaIndeks = Arrays.asList(linnad).indexOf(järgmineLinn);
                läbitud.add(järgmineLinn);
                for (int j = 0; j < M.length; j++) {
                    if (M[linnaIndeks][j] > 0 && M[linnaIndeks][j] <= x){
                        if (!läbitud.contains(linnad[j]) && !järgmised.contains(linnad[j]) && !praegused.contains(linnad[j]))
                            järgmised.add(linnad[j]);
                    }
                }
            }
            System.out.println(järgmised);
            praegused = järgmised;
            k--;
        }

        String[] tulemus = new String[praegused.size()];
        int i = 0;
        while (!praegused.isEmpty()) {
            tulemus[i] = praegused.poll();
            i++;
        }
        return tulemus;
    }

    public static void main(String[] args) throws IOException {

        // Kood, mille "laenasin" praktikumi failist MaatriksAbi.java

        List<String> read = Files.readAllLines((new File("linnade_kaugused.tsv")).toPath());
        String[] linnad = read.get(0).split("\t");
        int n = read.size() - 1;
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] väärtused = read.get(i + 1).split("\t");
            for (int j = 0; j < väärtused.length; j++)
                M[i][j] = Integer.parseInt(väärtused[j]);
        }

        String[] tulemus = jõuame("Paide", 96, 4, linnad, M);
        System.out.println(Arrays.toString(tulemus));

    }
}