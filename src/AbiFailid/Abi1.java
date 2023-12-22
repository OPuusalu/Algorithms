package AbiFailid;

import P10.KOTipp;
import ee.ut.dendroloj.Dendrologist;
import java.util.concurrent.ThreadLocalRandom;

public class Abi1 {

    public static void joonista(KOTipp KOTipp) {
        Dendrologist.drawBinaryTree(KOTipp, t -> t.info, t -> t.v, t -> t.p);
    }
    public static KOTipp juhuslikPuu(int n) {
        if (n == 0) return null;
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        String juhuslikTäht = Character.toString(juhus.nextInt('A', 'Z' + 1));
        int vasakule = juhus.nextInt(n);
        return new KOTipp(juhuslikTäht, juhuslikPuu(vasakule), juhuslikPuu(n - 1 - vasakule));
    }

    public static KOTipp juhuslikAvaldis(int n) {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        if (n == 0) {
            return new KOTipp(Integer.toString((juhus.nextBoolean() ? 1 : -1) * juhus.nextInt(1, 40)));
        }
        String tehe = switch (juhus.nextInt(3)) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "*";
            default -> throw new AssertionError("Võimatu juhuarv");
        };
        int vasakule = juhus.nextInt(n);
        return new KOTipp(tehe, juhuslikAvaldis(vasakule), juhuslikAvaldis(n - 1 - vasakule));
    }

    public static KOTipp juhuSümmeetriline(int n) {
        if (n == 0) return null;
        if (n == 1) return new KOTipp(juhutäht());
        String juhutäht = juhutäht();
        KOTipp vasem = new KOTipp(juhutäht);
        KOTipp parem = new KOTipp(juhutäht);
        KOTipp KOTipp = new KOTipp(juhutäht(), vasem, parem);
        ehitaSümmeetrilist(n - 2, vasem, parem);
        return KOTipp;
    }

    private static void ehitaSümmeetrilist(int n, KOTipp vasem, KOTipp parem) {
        if (n <= 0) return;
        int juhuarv = (int) (Math.random() * 4);
        if (juhuarv <= 2) {
            String juhutäht = juhutäht();
            vasem.v = new KOTipp(juhutäht);
            parem.p = new KOTipp(juhutäht);
            ehitaSümmeetrilist(juhuarv == 2 ? (int) (Math.random() * n) : n - 1, vasem.v, parem.p);
        }
        if (juhuarv >= 1) {
            String juhutäht = juhutäht();
            vasem.p = new KOTipp(juhutäht);
            parem.v = new KOTipp(juhutäht);
            ehitaSümmeetrilist(juhuarv == 1 ? (int) (Math.random() * n) : n - 1, vasem.p, parem.v);
        }
    }

    private static String juhutäht() {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        return Character.toString(juhus.nextInt('A', 'Z' + 1));
    }
}
