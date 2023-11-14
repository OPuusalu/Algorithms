package P11;

import AbiFailid.KOTipp;
import ee.ut.dendroloj.Dendrologist;

public class Y5 {

    public static void main(String[] args) {
        Dendrologist.setUIScale(3);
        int h = 1;
        KOTipp tipp = juhupuu(h);
        KOTipp.kuvaKahendotsimispuu(tipp);
    }

    private static KOTipp juhupuu(int h) {
        if (h == 0) return null;
        else if (h == 1) return new KOTipp(0);
        else if (h > 1) {
            
        }
        return null;
    }

}
