package P14;

import java.io.File;
import java.io.IOException;

public class Y8 {
    public static void main(String[] args) throws IOException {
        NimedegaNaabrusmaatriks m = MaatriksAbi.loeNaabrusmaatriks(new File("linnade_kaugused.tsv"));
        eemaldaKaared(m, 50);
        System.out.println(m);
    }

    private static void eemaldaKaared(NimedegaNaabrusmaatriks m, int x) {
        int arv = m.nimed.length;

        for (int i = 0; i < arv; i++) {
            for (int j = 0; j < arv; j++) {
                if (m.M[i][j] > x)
                    m.M[i][j] = -1;
            }
        }


    }
}
