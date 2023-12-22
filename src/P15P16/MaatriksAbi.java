package P15P16;

import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.GraphCanvas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MaatriksAbi {

    // Abimeetod andmete lugemiseks

    public static NimedegaNaabrusmaatriks loeNaabrusmaatriks(File fail) throws IOException {
        List<String> read = Files.readAllLines(fail.toPath());
        // Loeme esimeselt realt nimed
        String[] nimed = read.get(0).split("\t");
        // Loeme naabrusmaatriksi
        int n = read.size() - 1;
        int[][] M = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] väärtused = read.get(i + 1).split("\t");
            for (int j = 0; j < väärtused.length; j++)
                M[i][j] = Integer.parseInt(väärtused[j]);
        }
        return new NimedegaNaabrusmaatriks(M, nimed);
    }

    // Abimeetod teisenduseks

    public static List<Tipp> maatriksGraafiks(NimedegaNaabrusmaatriks maatriks) {
        List<Tipp> graaf = new ArrayList<>();
        for (String nimi : maatriks.nimed) graaf.add(new Tipp(nimi)); // Koostame iga nime kohta uue tipu.
        for (int mitmesTipp = 0; mitmesTipp < maatriks.nimed.length; mitmesTipp++) // Läbime kõik tipupaaride indeksid.
            for (int mitmesSihttipp = 0; mitmesSihttipp < maatriks.nimed.length; mitmesSihttipp++) {
                int kaugus = maatriks.M[mitmesTipp][mitmesSihttipp]; // Leiame iga paari kauguse.
                if (mitmesTipp != mitmesSihttipp && kaugus >= 0) { // Kui kaugus on positiivne eri tippude vahel.
                    Tipp lähteTipp = graaf.get(mitmesTipp); // Leiame lähtetipu ja ..
                    Tipp sihtTipp = graaf.get(mitmesSihttipp); // .. sihttipu ning ..
                    Kaar kaar = new Kaar(lähteTipp, sihtTipp, kaugus); // .. koostame nende vahele kaare.
                    lähteTipp.kaared.add(kaar); // Lisame kaare lähtetipu külge.
                }
            }
        return graaf;
    }

    // Abimeetodid graafide kuvamiseks dendroloj abil

    public static void kuvaGraaf(NimedegaNaabrusmaatriks maatriks) {
        kuvaGraaf(maatriks.M, maatriks.nimed);
    }

    public static void kuvaGraaf(int[][] M, String[] nimed) {
        // Märkus: See meetod eeldab ilma kontrollimata, et M on ruudukujuline.
        GraphCanvas<Integer> lõuend = new GraphCanvas<>();
        for (int i = 0; i < M.length; i++)
            lõuend.drawVertex(i, nimed[i]);
        for (int i = 0; i < M.length; i++)
            for (int j = i + 1; j < M.length; j++)
                if (M[i][j] == M[j][i]) {
                    // Naabrusmaatriks on sümmeetriline
                    if (M[i][j] > -1) lõuend.drawEdge(i, j, Integer.toString(M[i][j]));
                } else {
                    // Naabrusmaatriks ei ole sümmeetriline
                    if (M[i][j] > -1) lõuend.drawDirectedEdge(i, j, Integer.toString(M[i][j]));
                    if (M[j][i] > -1) lõuend.drawDirectedEdge(j, i, Integer.toString(M[j][i]));
                }
        Dendrologist.drawGraph(lõuend);
    }

    // Juhusliku graafi maatriksi genereerimine

    public static NimedegaNaabrusmaatriks juhuMaatriks(int tippe, int maxKaarepikkus, double ühendatuseTõenäosus) {
        String[] nimed = new String[tippe];
        for (int i = 0; i < nimed.length; i++)
            if (tippe <= 26) nimed[i] = "" + (char) (i + 65);
            else nimed[i] = "T" + (i);
        int[][] maatriks = new int[tippe][tippe];
        for (int rida = 0; rida < tippe; rida++)
            for (int veer = rida + 1; veer < tippe; veer++)
                if (Math.random() > ühendatuseTõenäosus) {
                    maatriks[rida][veer] = -1;
                    maatriks[veer][rida] = -1;
                } else {
                    int juhuarv = 1 + (int) (Math.random() * maxKaarepikkus);
                    maatriks[rida][veer] = juhuarv;
                    maatriks[veer][rida] = juhuarv;
                }
        return new NimedegaNaabrusmaatriks(maatriks, nimed);
    }
}
