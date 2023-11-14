package P1;

public class Ex2 {

    public static void main(String[] args) {

        for (int n = 18; n < 28; n++){
            long start = System.currentTimeMillis();
            System.out.println(bitt_gen(n, ""));
            long stop = System.currentTimeMillis();

            System.out.println(n + " -> Time taken " + (stop-start) + " ms");
        }


    }

    private static int bitt_gen(int n, String vector){

        if (vector.length() == n){
            return 1;
        }
        return bitt_gen(n, vector+"0") + bitt_gen(n, vector+"1");

    }

}
