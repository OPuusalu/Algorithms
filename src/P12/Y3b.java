package P12;

import java.util.Arrays;
import java.util.List;

public class Y3b {
    public static void main(String[] args) {
        List<Integer> arvud = Arrays.asList(0,6,7,3,5,2);
        Kuhi kuhi = new Kuhi(arvud);
        kuhi.kuva();
        kuhi.mullinaAlla(0);
        kuhi.kuva();
    }
}
