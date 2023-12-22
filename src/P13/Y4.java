package P13;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Y4 {

    public static void main(String[] args) throws IOException {
        List<Tipp> graph = Abi.loeKülgnevusstruktuur(new File("src/ük_näide.tsv"));
        Abi.kuvaGraafKaaludeta(graph);

        Tipp startingNode = Abi.leiaTipp("a", graph);
        depthFirst(startingNode, graph);
    }

    private static void depthFirst(Tipp startingNode, List<Tipp> graph) {
        Set<Tipp> exploredNodes = new HashSet<>();
        Deque<Tipp> magazine = new ArrayDeque<>();
        magazine.push(startingNode);

        while (!magazine.isEmpty()){
            Tipp workingNode = magazine.poll();
            if (!exploredNodes.contains(workingNode)) {
                List<Kaar> edges = workingNode.kaared;
                System.out.println(workingNode.info);
                for (Kaar edge : edges
                ) {
                    magazine.push(edge.lõpp);
                }
                exploredNodes.add(workingNode);
            }
        }

    }

}
