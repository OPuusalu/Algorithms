package P3;

import ee.ut.dendroloj.Dendrologist;
import ee.ut.dendroloj.Grow;

public class Ex1 {


    public static void main(String[] args) {

        Dendrologist.setUIScale(2);
        Dendrologist.wakeUp();

        bitvec(10, 2, "");

    }
    @Grow
    private static void bitvec(int n, int k, String s) {

        if (n == 0 && n == k){
            System.out.println(s);
        } else {
            if (k != 0)
                bitvec(n-1, k-1, s + "1");
            if (n-k > 0)
                bitvec(n-1, k, s + "0");
        }

    }


}
