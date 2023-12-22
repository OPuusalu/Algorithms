package P12;

import ee.ut.dendroloj.Dendrologist;

public class Tipp {
    int info;
    Tipp v;
    Tipp p;
    int x; // abiväli

    Tipp(int info, Tipp v, Tipp p) {
        this.info = info;
        this.v = v;
        this.p = p;
    }

    Tipp(int info) {
        this.info = info;
    }

    static void kuvaKahendpuu(Tipp juurTipp) {
        if (juurTipp != null) Dendrologist.drawBinaryTree(juurTipp, t -> Integer.toString(t.info), t -> t.v, t -> t.p);
    }
}
