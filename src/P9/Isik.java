package P9;

class Isik implements Comparable<Isik> {
    public final int ID;
    public final String nimi;
    public final int palk;
    public final int vanus;

    public Isik(int ID, String nimi, int palk, int vanus) {
        this.ID = ID;
        this.nimi = nimi;
        this.palk = palk;
        this.vanus = vanus;
    }

    public Isik(int id) {
        String nimi = new String[]{"Ae", "Gu", "Si", "Le", "Na", "Li", "Sä"}[(int) (7 * Math.random())];
        nimi += new String[]{"de", "ni", "su", "mi", "na", "je", "li"}[(int) (7 * Math.random())];
        this.ID = id;
        this.nimi = nimi;
        this.palk = (int) (Math.random() * 3000);
        this.vanus = (int) (Math.random() * 90);
    }

    @Override
    public String toString() {
        return "Isik nr. " + ID + " " + nimi;
    }

    @Override
    public int compareTo(Isik o) {
        return Integer.compare(ID, o.ID);
    }
}

