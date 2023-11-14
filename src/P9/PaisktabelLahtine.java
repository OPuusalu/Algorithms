package P9;

public class PaisktabelLahtine {
    private Isik[] tabel;
    public PaisktabelLahtine(int n){
        this.tabel = new Isik[n];
    }
    private int paiska(int k) { // Eeldame isendimuutujat "tabel".
        int m = tabel.length;
        double t = (Math.sqrt(5) - 1) / 2;
        return (int) Math.floor(m * (k * t - Math.floor(k * t)));
    }

    private String tabelSõneks() { // Eeldame isendimuutujat "tabel". Näitame lisaks paiskfunktsiooni väärtust.
        String sõne = "Paisktabel:";
        for (int i = 0; i < tabel.length; i++)
            if (tabel[i] == null) sõne += "\n\t" + i + ": " + tabel[i];
            else sõne += "\n\t" + i + ": " + tabel[i] + " (" + paiska(tabel[i].ID) + ")";
        return sõne;
    }

    public void lisa(Isik isik) {
        int indeks = paiska(isik.ID);
        while (tabel[indeks % tabel.length] != null) indeks++;
        tabel[indeks] = isik;
    }
    public Isik otsi(int id){
        int indeks = otsiIndeks(id);
        if (indeks == -1) return null;
        return tabel[indeks];
    }

    private int otsiIndeks(int id) {
        int indeks = paiska(id);
        for (int nihe = 0; nihe < tabel.length; nihe++) {
            if (tabel[(indeks+nihe) % tabel.length] == null) return -1;
            else if (tabel[(indeks+nihe) % tabel.length].ID == id) return (indeks+nihe) % tabel.length;
        }
        return -1;
    }

    public Isik eemalda(int id){
        int indeks = otsiIndeks(id);
        if (indeks == -1) return null;

        Isik isik = tabel[indeks];
        tabel[indeks] = null;

        for (int nihe = 0; nihe < tabel.length; nihe++) {
            if (tabel[(indeks+nihe) % tabel.length] == null) break;
            else {
                Isik jarg = tabel[(indeks+nihe) % tabel.length];
                tabel[(indeks+nihe) % tabel.length] = null;
                lisa(jarg);
            }
        }

        return isik;

    }

    @Override
    public String toString(){
        return tabelSõneks();
    }
}
