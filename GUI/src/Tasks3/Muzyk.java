package Tasks3;

public abstract class Muzyk {
    String imie;
    double czas;

    Muzyk(String imie, double czas) {
        this.imie = imie;
        this.czas = czas;
    }

    abstract String instrument ();
    abstract double stawka ();

    public String imie () {
        return imie;
    }

    static Muzyk maxHonorarium (Muzyk[] tabMuzykow) {

        double najwiekszaWartosc = 0;

        Muzyk muzykNajwiekszaStawka = null;

        for(Muzyk m : tabMuzykow) {
            if(m.czas*m.stawka() > najwiekszaWartosc){
                najwiekszaWartosc = m.czas*m.stawka();
                muzykNajwiekszaStawka = m;
            }
        }
        return muzykNajwiekszaStawka;
    }

    @Override
    public String toString() {
        return "imie='" + imie + '\'' +
                ", czas=" + czas +
                ", stawka= " + this.stawka();
    }
}
