package P15P16;

import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.GraphCanvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Abi {

    /**
     * Algväärtustab etteantud Tipu listi kõigi tippude abiväljade väärtused.
     */
    public static void puhastaAbiväljad(List<Tipp> tipud) {
        for (Tipp tipp : tipud) {
            tipp.x = 0;
            tipp.y = 0;
            tipp.z = null;
        }
    }

    /**
     * Loeb TSV failist graafi külgnevusstruktuurina.
     * Tagastab külgnevusstruktuuri tippude järjendina.
     */
    public static List<Tipp> loeKülgnevusstruktuur(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        Map<String, Tipp> tipud = new LinkedHashMap<>();
        for (String rida : read) {
            String[] väärtused = rida.split("\t");
            Tipp tipp = new Tipp(väärtused[0]);
            tipud.put(tipp.info, tipp);
        }
        for (String rida : read) {
            String[] väärtused = rida.split("\t");
            Tipp tipp = tipud.get(väärtused[0]);
            for (int i = 2; i < väärtused.length; i++)
                tipp.kaared.add(new Kaar(tipp, tipud.get(väärtused[i]), 1));
        }
        return new ArrayList<>(tipud.values());
    }

    /**
     * Kuvab külgnevusstruktuurina esitatud graafi.
     */
    public static void kuvaGraaf(List<Tipp> tipud) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ROOT));
        GraphCanvas<Tipp> lõuend = new GraphCanvas<>();
        for (Tipp tipp : tipud) {
            lõuend.drawVertex(tipp, tipp.info);
            for (Kaar kaar : tipp.kaared)
                lõuend.drawDirectedEdge(kaar.alg, kaar.lõpp, df.format(kaar.kaal));
        }
        Dendrologist.drawGraph(lõuend);
    }
}

