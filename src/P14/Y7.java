package P14;

import P13.Abi;
import P13.Kaar;
import P13.Tipp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Y7 {
    public static void main(String[] args) throws IOException {
        NimedegaNaabrusmaatriks naabrusmaatriks = MaatriksAbi.loeNaabrusmaatriks(new File("täht.tsv"));
        System.out.println(naabrusmaatriks);
//        MaatriksAbi.kuvaGraaf(naabrusmaatriks);

        List<Tipp> graaf = maatriksGraafiks(naabrusmaatriks);
        Abi.kuvaGraaf(graaf);

    }

    private static List<Tipp> maatriksGraafiks(NimedegaNaabrusmaatriks naabrusmaatriks) {
        List<Tipp> graaf = new ArrayList<>();
        for (String nimi : naabrusmaatriks.nimed) {
            graaf.add(new Tipp(nimi));
        }
        for (int mitmesTipp = 0; mitmesTipp < naabrusmaatriks.nimed.length; mitmesTipp++) {
            for (int mitmesSihtTipp = 0; mitmesSihtTipp < naabrusmaatriks.nimed.length; mitmesSihtTipp++) {
                int kaugus = naabrusmaatriks.M[mitmesTipp][mitmesSihtTipp];
                if (kaugus > 0){
                    graaf.get(mitmesTipp).kaared.add(
                            new Kaar(graaf.get(mitmesTipp),
                                    graaf.get(mitmesSihtTipp),
                                    kaugus));
                }
            }
        }
        return graaf;
    }
}
