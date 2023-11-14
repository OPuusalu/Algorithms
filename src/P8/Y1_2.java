package P8;

import AbiFailid.Abi;

import java.util.HashSet;

public class Y1_2 {
    public static void main(String[] args) {
        int[] m1 = Abi.juhumassiiv(100_000,0,1_000_000);
        int[] m2 = Abi.juhumassiiv(100_000,0,1_000_000);
        int common = f(m1, m2);
//        System.out.println(Arrays.toString(m1));
//        System.out.println(Arrays.toString(m2));
        System.out.println(common);
    }

    private static int f(int[] m1, int[] m2) {
        HashSet<Integer> hs = new HashSet<>();
        HashSet<Integer> u = new HashSet<>();
        for (int i : m1) hs.add(i);
        for (int i : m2) {
            if (hs.contains(i)) u.add(i);
        }
        return u.size();
    }
}
