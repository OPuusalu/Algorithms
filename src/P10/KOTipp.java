package P10;// Autor: A.P.

public class KOTipp {
	public String info = ""; // puu tipus hoitav kirje
	public int x = 0;        // mingi abiväli
	public KOTipp v = null;    // vasak alluv
	public KOTipp p = null;    // parem alluv

	public KOTipp(String info, KOTipp v, KOTipp p) {
		this.info = info;
		this.v = v;
		this.p = p;
	}

	public KOTipp(String info) {
		this.info = info;
	}

	public KOTipp() {
	}

	// tipu t kloon, v.a. väljad .v ja .p
	public KOTipp(KOTipp t) {
		this.info = t.info;
		this.x = t.x;
	}
}