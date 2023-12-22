package P11;

import AbiFailid.Abi1;
import P10.KOTipp;
import ee.ut.dendroloj.Dendrologist;

public class Y4 {

    public static void main(String[] args) {

        Dendrologist.setUIScale(2);
        KOTipp KOTipp = Abi1.juhuslikPuu(10);
        System.out.println(harudesvordselt(KOTipp));
        Abi1.joonista(KOTipp);

    }

    public static int harudesvordselt(KOTipp KOTipp){
        if (KOTipp == null) return 0;
        int vasemal = harudesvordselt(KOTipp.v);
        int paremal = harudesvordselt(KOTipp.p);
        KOTipp.x = 1 + tippeHarus(KOTipp.v) + tippeHarus(KOTipp.p);
        KOTipp.info = Integer.toString(KOTipp.x);

        if (tippeHarus(KOTipp.v) == tippeHarus(KOTipp.p))
            return 1 + vasemal + paremal;
        return vasemal + paremal;
    }

    private static int tippeHarus(KOTipp KOTipp) {
        if (KOTipp == null) return 0;
        return KOTipp.x;
    }

}
