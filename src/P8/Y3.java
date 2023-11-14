package P8;

import AbiFailid.Abi;

import java.util.Arrays;
import java.util.HashMap;

public class Y3 {
    public static void main(String[] args) {
        int[] n = Abi.juhumassiiv(500_000, 0, 1_000_000);
//        System.out.println(Arrays.toString(n));
        int[] v = f(n);
        System.out.println(Arrays.toString(v));
    }

    private static int[] f(int[] n) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int len = n.length;
        for (int i : n) {
            int x = len-i;
            if (hm.containsKey(i)) return new int[]{x, i};
            hm.put(x, i);
        }
        return null;
    }
}
