package P3;

import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.Grow;

public class Ex2 {

    public static void main(String[] args) {

        Dendrologist.wakeUp();
        int n = 4;
        String l = "";
        int x = f(n, l);
        System.out.println(x);

    }
    @Grow
    private static int f(int n, String ints) {

        if (n == 0) {
            System.out.println(ints);
            return 1;
        }
        else if (n > 0){
            int counter = 0;
            counter += f(n-1, ints+"+1");
            if (n > 1)
                counter += f(n-2, ints+"+2");
            return counter;
        } return 0;

    }

}
