import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Kodu3c {
    public static long ratsuTeekonnad(int n) {
        HashMap<Integer, List<Integer>> sammud = new HashMap<>();
        List<Integer> esimRida = new ArrayList<>();
        for (int i = 0; i < n; i++)
            esimRida.add(i);
        sammud.put(0, esimRida);

        for (int i = 0; i < n - 1; i++) {
            List<Integer> jargRida = new ArrayList<>(sammud.get(i));
            List<Integer> ylejargRida = new ArrayList<>();

            for (Integer integer : sammud.get(i)) {
                if (integer - 2 >= 0 && i + 1 < n)
                    jargRida.add(integer - 2);
                if (integer + 2 < n && i + 1 < n)
                    jargRida.add(integer + 2);
                if (integer - 1 >= 0 && i + 2 < n)
                    ylejargRida.add(integer - 1);
                if (integer + 1 < n && i + 2 < n)
                    ylejargRida.add(integer + 1);
            }
            sammud.put(i + 1, jargRida);
            if (i + 2 < n)
                sammud.put(i + 2, ylejargRida);
        }

        return sammud.get(n - 1).size();
    }

    public static void main(String[] args) {
        long x = ratsuTeekonnad(4);
        System.out.println(x);
    }


}