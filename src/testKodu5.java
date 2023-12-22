import AbiFailid.Abi1;
import ee.ut.dendroloj.Dendrologist;

import java.util.concurrent.ThreadLocalRandom;

public class testKodu5 {

    public static void main(String[] args) {

//        KOTipp juur = new KOTipp(7);
//        KOTipp l1 = new KOTipp(5);
//        KOTipp l2 = new KOTipp(3);
//        l1.v = new KOTipp(4);
//        l1.p = new KOTipp(6);
//        l1.v.v = l2;
//        juur.v = l1;



//        juur = Kodu5.parempööre(juur);
        KOTipp juur = new KOTipp(2);
//        juur.v = new KOTipp(1);
//        juur.v.p = new KOTipp(2);
//        Dendrologist.drawBinaryTree(juur, t -> String.valueOf(t.väärtus), t -> t.v, t -> t.p);
        juur = Kodu5.lisaKirje(juur, 1);
        juur = Kodu5.lisaKirje(juur, 4);
        juur = Kodu5.lisaKirje(juur, 3);
        juur = Kodu5.lisaKirje(juur, 5);
        juur = Kodu5.lisaKirje(juur, 6);
//        juur = Kodu5.vasakpööre(juur);
//        juur = Kodu5.parempööre(juur);
        Dendrologist.drawBinaryTree(juur, t -> String.valueOf(t.väärtus), t -> t.v, t -> t.p);




    }

}
