package AbiFailid;

import ee.ut.dendroloj.Dendrologist;

public class KOTipp1 {
    public int väärtus;
    public KOTipp1 v;
    public KOTipp1 p;
    int x; // abiväli

    public KOTipp1(int väärtus, KOTipp1 v, KOTipp1 p) {
        this.väärtus = väärtus;
        this.v = v;
        this.p = p;
    }

    public KOTipp1(int väärtus) {
        this.väärtus = väärtus;
    }

    public static void kuvaKahendotsimispuu(KOTipp1 juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);
    }
}

