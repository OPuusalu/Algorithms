package P9;

public class Y4 {
    public static void main(String[] args) {
        int n = 10;
        PaisktabelLahtine tabel = new PaisktabelLahtine(n);

        tabel.lisa(new Isik(13));
        tabel.lisa(new Isik(5));

        System.out.println(tabel.eemalda(13));

        System.out.println(tabel);
    }
}
