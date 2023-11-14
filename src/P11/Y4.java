package P11;

import AbiFailid.Abi1;
import P10.Tipp;
import ee.ut.dendroloj.Dendrologist;

public class Y4 {

    public static void main(String[] args) {

        Dendrologist.setUIScale(2);
        Tipp tipp = Abi1.juhuslikPuu(10);
        System.out.println(harudesvordselt(tipp));
        Abi1.joonista(tipp);

    }

    public static int harudesvordselt(Tipp tipp){
        if (tipp == null) return 0;
        int vasemal = harudesvordselt(tipp.v);
        int paremal = harudesvordselt(tipp.p);
        tipp.x = 1 + tippeHarus(tipp.v) + tippeHarus(tipp.p);
        tipp.info = Integer.toString(tipp.x);

        if (tippeHarus(tipp.v) == tippeHarus(tipp.p))
            return 1 + vasemal + paremal;
        return vasemal + paremal;
    }

    private static int tippeHarus(Tipp tipp) {
        if (tipp == null) return 0;
        return tipp.x;
    }

}
