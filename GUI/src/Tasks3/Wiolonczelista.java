package Tasks3;

public class Wiolonczelista extends Muzyk {

    Wiolonczelista(String imie, double czas) {
        super(imie, czas);
    }

    @Override
    String instrument() {
        return "wiolonczela";
    }

    @Override
    double stawka() {
        return 250;
    }
}
