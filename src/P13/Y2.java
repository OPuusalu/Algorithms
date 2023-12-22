package P13;

import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Y2 {

    public static void main(String[] args) throws IOException {
        List<Tipp> tipud = Abi.loeKülgnevusstruktuur(new File("src/õis_andmed.tsv"));
        Dendrologist.setUIScale(0.8);
//        Abi.kuvaGraafKaaludeta(tipud);

        Tipp node1 = Abi.leiaTipp("LTAT.03.001 (Programmeerimine)", tipud);
        Tipp node2 = Abi.leiaTipp("LTAT.03.006 (Automaadid, keeled ja translaatorid)", tipud);

        int distance = findFurthest(node1);

        System.out.println(distance);
    }

    private static int findFurthest(Tipp startingNode) {
        if (startingNode.kaared.isEmpty()){
            System.out.println(startingNode.info);
        }
        Set<Tipp> explored = new HashSet<>();
        Queue<Tipp> layer = new ArrayDeque<>();
        layer.add(startingNode);
        int distance = 1;

        while (!layer.isEmpty()){
            Queue<Tipp> newLayer = new ArrayDeque<>();
            for (Tipp node: layer) {
                for (Kaar edge : node.kaared) {
                    if (!explored.contains(edge.lõpp)){
                        newLayer.add(edge.lõpp);
                        explored.add(edge.lõpp);
                    }
                }
            }
            if (newLayer.isEmpty()) break;
            layer = newLayer;
            distance++;
        }
        System.out.println(layer);
        return distance;
    }
}
