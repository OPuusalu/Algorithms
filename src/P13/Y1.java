package P13;

import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Y1 {

    public static void main(String[] args) throws IOException {
        List<Tipp> tipud = Abi.loeKülgnevusstruktuur(new File("src/õis_andmed.tsv"));
        Dendrologist.setUIScale(0.8);
//        Abi.kuvaGraafKaaludeta(tipud);

        Tipp node1 = Abi.leiaTipp("LTAT.03.001 (Programmeerimine)", tipud);
        Tipp node2 = Abi.leiaTipp("LTAT.03.006 (Automaadid, keeled ja translaatorid)", tipud);

        int distance = findDistance(node1, node2);

        System.out.println(distance);
    }

    private static int findDistance(Tipp node1, Tipp node2) {
        if (node1 == node2) return 0;
        int layers = 1;
        Queue<Tipp> layer = new ArrayDeque<>();
        layer.add(node1);
        while (!layer.isEmpty()){
            Queue<Tipp> newLayer = new ArrayDeque<>();
            for (Tipp node: layer) {
                for (Kaar edge : node.kaared) {
                    if (edge.lõpp == node2)
                        return layers;
                    newLayer.add(edge.lõpp);
                }
            }
            layers++;
            layer = newLayer;
        }
        return -1;
    }
}
