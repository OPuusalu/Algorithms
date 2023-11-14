public class tasem {

    public static void main(String[] args) {

        int[] a = {60,10,20,40};

        //return 1 if (n==0) else Math.floor(2 + (Math.log(n)/Math.log(2)));

        int x = komp(0, a, 0);

        System.out.println(x);

    }

    private static int komp(int i, int[] a, int s) {

        if (i >= a.length) {
            if (s <= 50)
                return 1;
            return 0;
        } else {
            return komp(i + 2, a, s + a[i]) + komp(i + 1, a, s);
        }
    }
}
