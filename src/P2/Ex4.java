package P2;

public class Ex4 {

    private static int abi1(int[] a, int n){
        if(n==0)
            return 1;
        int summa=abi1(lisa(a,0),n-1);
        summa+=abi1(lisa(a,1),n-1);
        summa+=abi1(lisa(a,2),n-1);
        return summa;
    }

    private static int abi2(int[] a, int i, int n){
        if (n == 0)
            return 1;

        a[i] = 0;
        int summa = abi2(a, i + 1,n-1);
        a[i] = 1;
        summa += abi2(a, i + 1,n-1);
        a[i] = 2;
        summa += abi2(a, i + 1,n-1);

        return summa;
    }

    public static int[] lisa(int[] a, int x){
        int[] uus=new int[a.length+1];
        int i;
        for (i=0;i<a.length;i++)
            uus[i]=a[i];
        uus[i]=x;
        return uus;
    }

    public static void main(String[] args) {

        for (int i = 10; i <= 20; i++) {
            System.out.println("Ratio for " + i + " is " + Math.round(abi2Ajakulu(i)/Math.pow(3, i)));
        }
    }

    public static long abi1Ajakulu(int n) {
        long start = System.currentTimeMillis();
        abi1(new int[0], n);
        long stop = System.currentTimeMillis();
        return stop - start;
    }

    public static long abi2Ajakulu(int n) {
        long start = System.currentTimeMillis();
        abi2(new int[n], 0, n);
        long stop = System.currentTimeMillis();
        return stop - start;
    }


}
