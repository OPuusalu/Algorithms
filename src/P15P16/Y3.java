package P15P16;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Comparator;


public class Y3 {
    public static void main(String[] args) throws IOException {
        NimedegaNaabrusmaatriks m = MaatriksAbi.loeNaabrusmaatriks(new File("graaf2.tsv"));
        System.out.println(m);
        List<Tipp> graaf = MaatriksAbi.maatriksGraafiks(m);
//        Abi.kuvaGraaf(graaf);

        Tipp algtipp = graaf.getFirst();
        List<Tipp> toes = primiAlgoritm(algtipp, graaf);

        Abi.kuvaGraaf(toes);

        double kogukaal = leiaKogukaal(toes);
        System.out.println(kogukaal);

    }

    private static double leiaKogukaal(List<Tipp> toes) {
        double kaal = 0;
        for (Tipp toe : toes) {
            for (Kaar kaar : toe.kaared) {
                kaal += kaar.kaal;
            }
        }
        return kaal/2;
    }

    private static List<Tipp> primiAlgoritm(Tipp algtipp, List<Tipp> graaf) {
        List<Kaar> kaared = leiaToeseKaared(algtipp, graaf);
        return koostaToes(kaared, graaf);
    }

    private static List<Kaar> leiaToeseKaared(Tipp algtipp, List<Tipp> graaf) {
        List<Kaar> toeseKaared = new ArrayList<>();
        PriorityQueue<Tipp> jarjekord = new PriorityQueue<>(Comparator.comparingDouble(t -> t.x));
        Set<Tipp> kylastatud = new HashSet<>();

        jarjekord.add(algtipp);

        while (!jarjekord.isEmpty()){

            Tipp praegune = jarjekord.poll();

            for (Kaar kaar : praegune.kaared) {
                Tipp kaarelopp = kaar.lõpp;
                if (!kylastatud.contains(kaarelopp)){
                    if (kaar.kaal < kaarelopp.x || kaarelopp.x == 0) {
                        kaarelopp.x = kaar.kaal;
                        kaarelopp.z = praegune;
                        jarjekord.add(kaarelopp);
                    }
                }
            }
            kylastatud.add(praegune);
        }
        for (Tipp tipp : kylastatud) {
            if (tipp.z != null) {
                Kaar kaar = new Kaar(tipp, tipp.z, tipp.x);
                toeseKaared.add(kaar);
            }
        }
        return toeseKaared;
    }

    private static List<Tipp> koostaToes(List<Kaar> kaared, List<Tipp> graaf) {
        List<Tipp> toeseGraaf = new ArrayList<>();
        HashMap<String, Tipp> viited = new HashMap<>();
        for (Tipp tipp : graaf){
            Tipp toeseTipp = new Tipp(tipp.info);
            viited.put(tipp.info, toeseTipp);
            toeseGraaf.add(toeseTipp);
        }
        for (Kaar kaar : kaared) {
            Tipp toeseTipp1 = viited.get(kaar.alg.info);
            Tipp toeseTipp2 = viited.get(kaar.lõpp.info);
            Kaar kaar1 = new Kaar(toeseTipp1, toeseTipp2, kaar.kaal);
            Kaar kaar2 = new Kaar(toeseTipp2, toeseTipp1, kaar.kaal);
            toeseTipp1.kaared.add(kaar1);
            toeseTipp2.kaared.add(kaar2);
        }
        return toeseGraaf;
    }
}
