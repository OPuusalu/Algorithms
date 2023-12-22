package P12;

import java.util.Arrays;
import java.util.List;

public class Y3a {

    public static void main(String[] args) {
        List<Integer> arvud = Arrays.asList(10,6,7,3,5,99);
        Kuhi kuhi = new Kuhi(arvud);
        kuhi.kuva();
        kuhi.mullinaÜles(arvud.size()-1);
        kuhi.kuva();
    }
}
