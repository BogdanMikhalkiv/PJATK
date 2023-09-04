import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PremiaJednorazowa extends Premia implements Serializable {
    private static List<PremiaJednorazowa> extent = new ArrayList<>();


    public PremiaJednorazowa(LocalDate date, double kwota,Ksiegowy ksiegowy, Pracownik pracownik) {
        super(date, kwota,ksiegowy,pracownik);
        addPremiaJednorazowa(this);
    }

    private static void addPremiaJednorazowa(PremiaJednorazowa premiaJednorazowa) {
        extent.add(premiaJednorazowa);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<PremiaJednorazowa>) stream.readObject();
    }

    public static List<PremiaJednorazowa> getExtent() {
        return extent;
    }

    @Override
    public String toString() {
        return "premia jednorazowa" + " data" + getDate() + ",  kwota " + getKwota();
    }
}
