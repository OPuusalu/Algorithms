package P15P16;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Y1 {
    public static void main(String[] args) throws IOException {

        List<Tipp> graaf = Abi.loeKülgnevusstruktuur(new File("puu1.tsv"));
        Abi.kuvaGraaf(graaf);

        Tipp algtipp = graaf.get(0);
        läbiSügavutiEesjärjestuses(algtipp);
        System.out.println();
        Tipp algtipp2 = graaf.get(3);
        läbiSügavutiEesjärjestuses(algtipp2);

    }

    private static void läbiSügavutiEesjärjestuses(Tipp algtipp) {

        Set<Tipp> töödeldud = new HashSet<>();
        Deque<Tipp> järgmised = new ArrayDeque<>();
        järgmised.add(algtipp);

        while (!järgmised.isEmpty()){
            Tipp vaadeldav = järgmised.pop();
            töödeldud.add(vaadeldav);
            System.out.print(vaadeldav.info + " ");

            for (Kaar kaar : vaadeldav.kaared) {
                if (!töödeldud.contains(kaar.lõpp))
                    järgmised.push(kaar.lõpp);
            }

        }


    }
}
