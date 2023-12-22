package P14;

public class DemoMaatriks {

    public static void main(String[] args) {
        NimedegaNaabrusmaatriks m = MaatriksAbi.juhuMaatriks(5, 8, 0.3);
        MaatriksAbi.kuvaGraaf(m);

        NimedegaNaabrusmaatriks koopia = m.kopeeri();
        System.out.println(koopia);
    }
}
