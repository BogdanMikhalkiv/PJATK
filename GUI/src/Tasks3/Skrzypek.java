package Tasks3;

public class Skrzypek extends Muzyk {

    Skrzypek(String imie, double czas) {
        super(imie, czas);
    }

    @Override
    String instrument() {
        return "skrzypce";
    }

    @Override
    double stawka() {
        return 100;
    }
}
