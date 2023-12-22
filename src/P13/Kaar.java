package P13;

public class Kaar {
    final Tipp alg; // kaare lähtetipp
    public final Tipp lõpp; // kaare suubumistipp
    final int kaal; // kaare kaal (kui peaks vaja olema)

    public Kaar(Tipp alg, Tipp lõpp, int kaal) {
        this.alg = alg;
        this.lõpp = lõpp;
        this.kaal = kaal;
    }
}