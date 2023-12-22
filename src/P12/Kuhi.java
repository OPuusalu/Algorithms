package P12;

import java.util.ArrayList;
import java.util.List;

public class Kuhi {

    private List<Integer> kuhi;

    public int vasemIndeks(int i){
        int indeks = (i * 2) + 1;
        if (indeks >= kuhi.size()) return -1;
        return indeks;
    }

    public int vasem(int i){
        return kuhi.get(vasemIndeks(i));
    }

    public int parem(int i){
        return kuhi.get(paremIndeks(i));
    }

    public int ülem(int i){
        return kuhi.get(ülemIndeks(i));
    }

    public void mullinaÜles(int i){
        if (i <= 0) return;
        int element = kuhi.get(i);
        int ülemineIndeks = ülemIndeks(i);
        int ülemineElement = ülem(i);

        if (ülemineElement < element){
            vaheta(i, element, ülemineIndeks, ülemineElement);
            mullinaÜles(ülemineIndeks);
        }

    }

    public void mullinaAlla(int i){
        if (i > ülemIndeks(kuhi.size()-1) || kuhi.size() <= 1) return;
        int element = kuhi.get(i);
        int alumineIndeks = vasemIndeks(i);
        if (paremIndeks(i) != -1 && vasem(i) < parem(i)) alumineIndeks = paremIndeks(i);
        if (element < kuhi.get(alumineIndeks)) {
            vaheta(i, element, alumineIndeks, kuhi.get(alumineIndeks));
            mullinaAlla(alumineIndeks);
        }
    }

    private void vaheta(int i, int element, int ülemineIndeks, int ülemineElement) {
        kuhi.set(i, ülemineElement);
        kuhi.set(ülemineIndeks, element);
    }

    public int paremIndeks(int i){
        int indeks = (i * 2) + 2;
        if (indeks >= kuhi.size()) return -1;
        return indeks;
    }

    public int ülemIndeks(int i){
        int indeks = (i - 1) / 2;
        if (i <= 0) return -1;
        return indeks;
    }

    public Kuhi(List<Integer> kuhi) {
        this.kuhi = new ArrayList<>(kuhi);
    }

    public Kuhi() {
        this.kuhi = new ArrayList<>();
    }

    public void kuva(){
        Tipp juur = teePuuKujule(0, kuhi);
        Tipp.kuvaKahendpuu(juur);
    }

    private Tipp teePuuKujule(int i, List<Integer> kuhi) {
        if (i <= -1 || i >= kuhi.size())return null;
        Tipp vasemHaru = teePuuKujule(vasemIndeks(i), kuhi);
        Tipp paremHaru = teePuuKujule(paremIndeks(i), kuhi);
        return new Tipp(kuhi.get(i), vasemHaru, paremHaru);
    }

    @Override
    public String toString() {
        return kuhi.toString();
    }
}
