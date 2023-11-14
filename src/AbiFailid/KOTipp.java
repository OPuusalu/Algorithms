package AbiFailid;

import ee.ut.dendroloj.Dendrologist;

public class KOTipp {
    int väärtus;
    KOTipp v;
    KOTipp p;
    int x; // abiväli

    KOTipp(int väärtus, KOTipp v, KOTipp p) {
        this.väärtus = väärtus;
        this.v = v;
        this.p = p;
    }

    public KOTipp(int väärtus) {
        this.väärtus = väärtus;
    }

    public static void kuvaKahendotsimispuu(KOTipp juurTipp) {
        Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.väärtus), t -> t.v, t -> t.p);
    }
}

