package P11;

import AbiFailid.Abi;
import AbiFailid.Abi1;
import AbiFailid.KOTipp;

import java.util.Arrays;

public class Y6 {

    public static void main(String[] args) {

        int[] taidis = Abi.juhumassiiv(20, 0, 40);
        System.out.println(Arrays.toString(taidis));
        KOTipp tipp = Y5.juhupuu(5);
        taida(tipp, taidis);
        KOTipp.kuvaKahendotsimispuu(tipp);

    }

    private static void taida(KOTipp tipp, int[] taidis) {
        taidaRek(tipp, 0, taidis);
    }

    private static int taidaRek(KOTipp tipp, int i, int[] taidis) {
        if (tipp == null) return 0;
        int vasemal = taidaRek(tipp.v, i, taidis);
        tipp.väärtus = taidis[i + vasemal];
        int paremal = taidaRek(tipp.p, i+vasemal+1, taidis);
        return vasemal + paremal + 1;
    }
}
