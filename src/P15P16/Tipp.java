package P15P16;

import java.util.ArrayList;
import java.util.List;

class Tipp {
    final String info; // tipu info
    List<Kaar> kaared = new ArrayList<>(); // sellest tipust väljuvate kaarte loetelu
    double x = 0; // abiväli täisarvu hoidmiseks
    int y = 0; // teine abiväli täisarvu hoidmiseks
    Tipp z = null; // abiväli tipu hoidmiseks

    public Tipp(String info) {
        this.info = info;
    }
}
