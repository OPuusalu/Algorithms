package P1;

public class Ex2b {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        dna(11, "");
        long stop = System.currentTimeMillis();

        System.out.println("Time taken " + (stop-start) + " ms");

    }

    private static void dna(int n, String genome){

        if (genome.length() == n){
            System.out.println(genome);
        }
        else
            for (Object o : new Object[]{"A", "T", "G", "C"}) {
                dna(n, genome+o);
            }

    }
}
