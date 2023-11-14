package P8;

import java.util.HashSet;

public class Test1 {

    public static void main(String[] args) {

        HashSet<Integer> arvud = new HashSet<>();
        arvud.add(10);
        arvud.add(5);

        System.out.println(arvud.contains(15));
        System.out.println(arvud.contains(5));
        System.out.println(arvud.size());


    }

}
