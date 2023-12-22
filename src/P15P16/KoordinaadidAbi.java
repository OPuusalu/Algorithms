package P15P16;

import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.GraphCanvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class KoordinaadidAbi {

    /**
     * Leiab kahe punkti vahelise kauguse kilomeetrites, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     */
    public static double kaugus(double lai1, double pik1, double lai2, double pik2) {
        double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
        double x = (1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2));
        double y = Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius + x * y));
    }

    /**
     * Loeb CSV failist tippude nimed ja koordinaadid.
     * Tagastab koordinaatide massiivi kujul [[laiuskraad_1, pikkuskraad_1], ..., [laiuskraad_n, pikkuskraad_n]]
     * ja nimede massiivi kujul [nimi_1, ..., nimi_n].
     * <p>
     * Mõeldud kasutamiseks failiga omniva.csv.
     */
    public static NimedegaKoordinaadid loeKoordinaadid(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        String[] nimed = new String[read.size()];
        double[][] K = new double[read.size()][];
        for (int i = 0; i < read.size(); i++) {
            String[] väärtused = read.get(i).split(",");
            nimed[i] = väärtused[0];
            K[i] = new double[]{Double.parseDouble(väärtused[1]), Double.parseDouble(väärtused[2])};
        }
        return new NimedegaKoordinaadid(K, nimed);
    }

    /**
     * Kuvab külgnevusstruktuurina esitatud graafi, kus tippude asukohad on määratud koordinaatide massiivis K olevate
     * koordinaatidega.
     * <p>
     * NB: See meetod eeldab, et koordinaatide massiivis K ja tipude järjendis on tipud samas järjestuses!
     */
    public static void kuvaGraaf(List<Tipp> tipud, double[][] K) {
        DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ROOT));
        GraphCanvas<Tipp> lõuend = new GraphCanvas<>();
        for (int i = 0; i < tipud.size(); i++) {
            // Pikkuskraadi cos(60°) = 0.5-ga korrutamine vähendab oluliselt moonutust
            // (vt https://et.wikipedia.org/wiki/Ruutlabaprojektsioon#Valemid)
            lõuend.drawFixedVertex(tipud.get(i), tipud.get(i).info.split(" ")[0], K[i][1] * 0.5, K[i][0]);
        }
        for (Tipp tipp : tipud) {
            for (Kaar kaar : tipp.kaared)
                lõuend.drawDirectedEdge(kaar.alg, kaar.lõpp, df.format(kaar.kaal));
        }
        Dendrologist.drawGraph(lõuend);
    }
}
