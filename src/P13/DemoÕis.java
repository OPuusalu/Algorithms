package P13;

import ee.ut.dendroloj.Dendrologist;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DemoÕis {

    public static void main(String[] args) throws IOException {
        List<Tipp> tipud = Abi.loeKülgnevusstruktuur(new File("õis_andmed.tsv"));
        Dendrologist.setUIScale(0.8);
        Abi.kuvaGraafKaaludeta(tipud);
    }
}