package P11;

import AbiFailid.KOTipp1;
import ee.ut.dendroloj.Dendrologist;

public class Y5 {

    public static void main(String[] args) {
        Dendrologist.setUIScale(3);
        int h = 5;
        KOTipp1 tipp = juhupuu(h);
        KOTipp1.kuvaKahendotsimispuu(tipp);
    }

    static KOTipp1 juhupuu(int h) {
        if (h == 0) return null;
        else if (h == 1) return new KOTipp1(0);
        else if (Math.random() < 1.0/3.0) {
            return new KOTipp1(0, juhupuu(h-1), juhupuu(h-1));
        } else if (Math.random() < 0.5) {
            return new KOTipp1(0, juhupuu(h-2), juhupuu(h-2));
        }
        return new KOTipp1(0, juhupuu(h-2), juhupuu(h-1));
    }

}
