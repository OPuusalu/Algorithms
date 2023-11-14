import java.util.HashMap;
import java.util.Stack;

/*****************************************************************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2023/2024 sügissemester
 *
 * Kodutöö. Ülesanne nr 3b
 * Teema: Magasin ja järjekord
 * Autor: Oliver Puusalu
 *
 * Mõningane eeskuju:
 *
 *****************************************************************************/
public class Kodu3b {

    public static void main(String[] args) {
        int n = 2000;
        System.out.println(rahakott(n));
    }

    /**
     * @param n otsitav müntide summa
     * @return tagastab mitu võimalust on võtta summat n
     */
    public static long rahakott(int n) {
        Stack<Integer> myndid = new Stack<>();
        HashMap<Integer, Long> summad = new HashMap<>();

        summad.put(0, 1L);
        // lisame mündid stacki
        for (int i = 0; i < 10; i++)
            myndid.push(200);
        for (int i = 0; i < 10; i++)
            myndid.push(100);
        for (int i = 0; i < 15; i++)
            myndid.push(50);
        for (int i = 0; i < 15; i++)
            myndid.push(20);
        for (int i = 0; i < 15; i++)
            myndid.push(10);

        while (!myndid.isEmpty()) {
            // võtame stackist mündi
            int mynt = myndid.pop();
            HashMap<Integer, Long> uuedSummad = new HashMap<>();
            System.out.println(summad);
            //lisame summad väärtused uuedSummad hashmappi
            for (int summa : summad.keySet()) {
                uuedSummad.put(summa, summad.get(summa));
            }
            // todo
            // see kood peaks lisama igale summale mündi juurde, aga see ei tööta
            for (int summa : summad.keySet()) {
                uuedSummad.put(summa + mynt, (summad.getOrDefault(summa+mynt, 0L) + uuedSummad.getOrDefault(summa+mynt, 1L)));
            }
            summad = uuedSummad;
        }
        // tagastame kui mitu n väärtust leitsime või nulli
        return summad.getOrDefault(n, 0L);
    }


}
