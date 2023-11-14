package P7;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Ex2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int arv = fibo(i);
            System.out.println(arv);
        }
    }

    private static int fibo(int n) {

        if (n <= 1) return n;

        Queue<Integer> queue = new ArrayDeque<>(List.of(0, 1));

        for (int i = 0; i < n; i++) {
            queue.add(queue.remove() + queue.element());
        }
        return queue.element();
    }

}
