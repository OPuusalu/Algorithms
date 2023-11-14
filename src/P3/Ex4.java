package P3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex4 {

    public static void main(String[] args) {

        int[] prices = new int[]{10, 20, 30};

        int[] sums = p(prices);

        System.out.println(Arrays.toString(sums));

    }

    private static int[] p(int[] prices) {

        List<Integer> lsums = prek(0, prices, 0);
        int[] tulemus = new int[lsums.size()];
        for (int i = 0; i < lsums.size(); i++) {
            tulemus[i] = lsums.get(i);
        }
        return tulemus;

    }

    private static List<Integer> prek(int i, int[] prices, int sum) {

        if (i == prices.length)
            return List.of(sum);
        List<Integer> cn = new ArrayList<>();
        List<Integer> c =  prek(i+1, prices, sum + prices[i]);
        List<Integer> n = prek(i+1, prices, sum);
        cn.addAll(c);
        cn.addAll(n);
        return cn;
    }

}
