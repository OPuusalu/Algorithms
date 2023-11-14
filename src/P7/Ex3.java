package P7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Ex3 {

    public static void main(String[] args) {

        leiaVektorid(4);

    }
    private static void leiaVektorid(int n) {

        Deque<Integer> deque = new ArrayDeque<>();
        while (deque.size() < n) deque.add(0);

        while (true){
            System.out.println(deque);
            while (deque.remove().equals(1)) {if (deque.isEmpty()) return;}
            deque.push(1);
            while (deque.size() < n) deque.push(0);
        }
    }
}
