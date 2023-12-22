package P15P16;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Y2 {
    public static void main(String[] args) throws IOException {
        List<Tipp> graaf = Abi.loeKülgnevusstruktuur(new File("puu1.tsv"));
        Abi.kuvaGraaf(graaf);

        int läbimõõt = leiaLäbimõõt(graaf);

        System.out.println(läbimõõt);

    }

    private static int leiaLäbimõõt(List<Tipp> graaf) {
        Tipp algtipp = graaf.getFirst();
        Tipp esmaneKaugeim = leiaKaugeim(algtipp, graaf);
        assert esmaneKaugeim != null;
        Tipp kaugeim = leiaKaugeim(esmaneKaugeim, graaf);
        assert kaugeim != null;
        return (int) kaugeim.x;
    }

    private static Tipp leiaKaugeim(Tipp algtipp, List<Tipp> graaf) {
        Set<Tipp> töödeldud = new HashSet<>();
        Deque<Tipp> järgmised = new ArrayDeque<>();
        int kaugus = 0;
        algtipp.x = kaugus;
        järgmised.add(algtipp);

        while (!järgmised.isEmpty()){
            Tipp vaadeldav = järgmised.pop();
            töödeldud.add(vaadeldav);
            kaugus = (int) vaadeldav.x;

            if (töödeldud.size() == graaf.size())
                return vaadeldav;

            for (Kaar kaar : vaadeldav.kaared) {
                Tipp lisatav = kaar.lõpp;
                if (!töödeldud.contains(lisatav)){
                    lisatav.x = kaugus+1;
                    järgmised.add(lisatav);
                }
            }

        }
        return null;
    }
}
