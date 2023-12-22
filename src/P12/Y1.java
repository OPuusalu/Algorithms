package P12;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Y1 {

    public static void main(String[] args) {

        List<Integer> arvud = Arrays.asList(10,6,7,3,5,2);
        Kuhi kuhi = new Kuhi(arvud);
        System.out.println(kuhi.vasemIndeks(1));
        System.out.println(kuhi.paremIndeks(1));
        System.out.println(kuhi.ülemIndeks(1));

    }

}
