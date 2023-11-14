package P8;

import AbiFailid.Abi;

import java.util.Arrays;
public class Y1 {
    public static void main(String[] args) {
        int[] m1 = Abi.juhumassiiv(4,0,3);
        int[] m2 = Abi.juhumassiiv(4,0,3);
        int common = f(m1, m2);
        System.out.println(Arrays.toString(m1));
        System.out.println(Arrays.toString(m2));
        System.out.println(common);
    }
    private static int f(int[] m1, int[] m2) {
        int l = 0;
        for (int i = 0; i < m1.length; i++)
            if (g(i, m1, m2)) l++;
        return l;
    }
    private static boolean g(int i, int[] m1, int[] m2) {
        int e = m1[i];
        for (int j = 0; j < i; j++)
            if (m1[j] == e) return false;
        for (int k : m2) if (k == e) return true;
        return false;
    }
}
