package P9;

import AbiFailid.Abi;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Y2 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = Abi.juhulinkedlist(100_000,0,100_000);
        long start = System.currentTimeMillis();
        eemdalda(linkedList);
        long stop = System.currentTimeMillis();

        System.out.println("Time taken " + (stop-start) + " ms");

    }

    private static void eemdalda(LinkedList<Integer> linkedList) {
        Iterator<Integer> iterator = linkedList.iterator();
        HashSet<Integer> kohatud = new HashSet<>();
        while (iterator.hasNext()){
            int ele = iterator.next();
            if (kohatud.contains(ele)){ iterator.remove();}
            else kohatud.add(ele);
        }
    }
}
