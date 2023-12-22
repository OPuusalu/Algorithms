package P14;

import P13.Tipp;
import P13.Abi;
import P13.Kaar;
import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Y2b {
    public static void main(String[] args) throws IOException {
        Dendrologist.setUIScale(2);
        // Abstraktsemate katseandmetega:
        System.out.println("Abstraktsemate katseandmetega: (juhuslikult genereeritud graaf, igal korral erinev)");
        List<Tipp> tipud1 = Abi.juhuslikGraaf(8, 0.2);
        Abi.kuvaGraafKaaludeta(tipud1);
        for (Tipp tipp : tipud1) {
            System.out.print("Alustame tipust " + tipp.info + ". ");
            leiaKaugeimad(tipp);
        }

        // ÕISi andmetega:
        System.out.println("ÕISi andmetega:");
        List<Tipp> tipud2 = Abi.loeKülgnevusstruktuur(new File("õis_andmed.tsv"));
        //Abi.kuvaGraafKaaludeta(tipud2);
        Tipp tipp = Abi.leiaTipp("MTMM.00.340 (Kõrgem matemaatika I)", tipud2);
        leiaKaugeimad(tipp);
    }

    private static void leiaKaugeimad(Tipp algtipp) {
        int kaugus = 0; // Loendame siin muutujas, mitmendas kihis oleme. Alustame nullindast kihist.
        Set<Tipp> külastatud = new HashSet<>(); // Hoiame meeles juba varasemates kihtides külastatud tipud.
        Queue<Tipp> kiht = new ArrayDeque<>(); // Kihti kujutame järjekorrana.
        kiht.add(algtipp); // Nullindas kihis on vaid algtipp.
        while (true) { // Lõpmatu tüskkel, katkestame break-iga.
            Queue<Tipp> uuskiht = new ArrayDeque<>(); // Koostame jooksvalt uut kihti, alustame tühjaga.
            for (Tipp tipp : kiht) // Käime praeguse kihi elemendid läbi ja ..
                for (Kaar kaar : tipp.kaared) //  .. läbime kõik väljuvad kaared, mis viivad järgnevasse kihti.
                    if (!külastatud.contains(kaar.lõpp)) { // Kui kaare siht-tipp ei ole juba varem külastatud, siis ..
                        uuskiht.add(kaar.lõpp); // .. jätame ta uue kihi elemendina meelde ..
                        külastatud.add(kaar.lõpp);  // .. ja märgime ta külastatuks.
                        kaar.lõpp.z = tipp;
                    }
            if (uuskiht.isEmpty()) break; // Kui järgmine kiht puudub, siis oleme viimase kihi leidnud. Katkestame.
            kiht = uuskiht; // Muul juhul asume uuesti kihist veel järgnevat kihti leiddma.
            kaugus++; // Loendame kauguse ühe võrra suuremaks.
        } // Peale tsüklit väljastame viimase kihi ja kauguse loenduri väärtuse.
        System.out.print("Kaugeim on kiht " + kaugus + ", sellel kihil asuvad tipud:");
        for (Tipp kihielement : kiht) System.out.print(" " + kihielement.info);
        System.out.println();
        for (Tipp kaugeim : kiht){
            System.out.print("Teekond tipuni " + kaugeim.info + " on " + kaugeim.info);
            Tipp jooksev = kaugeim;
            while (jooksev.z != null){
                System.out.print(" <- " + jooksev.z.info);
                jooksev = jooksev.z;
            }
            System.out.println();
        }
        for (Tipp tipp : külastatud) {
            tipp.z = null;
        }
    }
}
