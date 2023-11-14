package P6;

import java.util.ArrayDeque;
import java.util.Deque;

public class Ex2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int arv = fiboArv(i);
            System.out.println(arv);
        }


    }

    public static int fiboArv(int n) {
        int tulemus = 0;
        Deque<Integer> liidetavad = new ArrayDeque<>();
        liidetavad.push(n);
        while (!liidetavad.isEmpty()){
            int arv = liidetavad.pop();
            if (arv <= 1)
                tulemus += arv;
            else {
                liidetavad.push(arv - 1);
                liidetavad.push(arv - 2);
            }
        }
        return tulemus;
    }

}
