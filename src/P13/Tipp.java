package P13;

import java.util.ArrayList;
import java.util.List;

public class Tipp {
    public final String info; // tipu info
    public final List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
    int x = 0; // abiväli täisarvu hoidmiseks
    int y = 0; // teine abiväli täisarvu hoidmiseks
    public Tipp z = null; // abiväli tipu hoidmiseks

    public Tipp(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "'" + info + '\'' + (z == null ? "" : " <- (" + z.info + ")");
    }
}