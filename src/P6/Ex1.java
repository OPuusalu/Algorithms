package P6;

import java.util.ArrayDeque;
import java.util.Deque;

public class Ex1 {

    public static void main(String[] args) {


        String sona = "a man, a plan, a canal: panama";
        Boolean tulemus = true;
        Deque<Character> mag = new ArrayDeque<>();

        for (Character a : sona.toCharArray()) {
            if (Character.isAlphabetic(a)) {
                mag.push(Character.toLowerCase(a));
            }
        }

        for (Character a : sona.toCharArray()) {
            if (Character.isAlphabetic(a)) {
                if (mag.pop() != Character.toLowerCase(a)) {
                    tulemus = false;
                }
            }
        }

        System.out.println(tulemus);

    }

}
