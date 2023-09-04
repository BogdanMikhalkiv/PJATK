import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PremiumMieseczne extends Premia implements Serializable {

    private static List<PremiumMieseczne> extent = new ArrayList<>();

    double procentOdWynagrodzenia;
    static double maxProcent = 5;


    public PremiumMieseczne(LocalDate date, double kwota, Ksiegowy ksiegowy, Pracownik pracownik , double procentOdWynagrodzenia) throws Exception {
        super(date, kwota, ksiegowy, pracownik);
        if (procentOdWynagrodzenia > maxProcent) {
            throw new Exception("nie moze byc wiekszy niz " + maxProcent + "%");
        } else {

            this.procentOdWynagrodzenia = procentOdWynagrodzenia;
        }

        addPremiaMieseczne(this);

    }

    private static void addPremiaMieseczne(PremiumMieseczne premiumMieseczne) {
        extent.add(premiumMieseczne);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<PremiumMieseczne>) stream.readObject();
    }

    public static List<PremiumMieseczne> getExtent() {
        return extent;
    }

    @Override
    public String toString() {
        return "premia mesieczna " + " data" + getDate() + ",  kwota " + getKwota() + " procentOdWynagrodzenia - " + procentOdWynagrodzenia ;


    }
}
