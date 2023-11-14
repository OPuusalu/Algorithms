package P10;// Autor: A.P.

public class Tipp {
	public String info = ""; // puu tipus hoitav kirje
	public int x = 0;        // mingi abiväli
	public Tipp v = null;    // vasak alluv
	public Tipp p = null;    // parem alluv

	public Tipp(String info, Tipp v, Tipp p) {
		this.info = info;
		this.v = v;
		this.p = p;
	}

	public Tipp(String info) {
		this.info = info;
	}

	public Tipp() {
	}

	// tipu t kloon, v.a. väljad .v ja .p
	public Tipp(Tipp t) {
		this.info = t.info;
		this.x = t.x;
	}
}