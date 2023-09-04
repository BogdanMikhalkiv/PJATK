import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DzialKadr implements Serializable {
    private static List<DzialKadr> extent = new ArrayList<>();

    //zwykla
    private List<Pracownik> pracowniki = new ArrayList<>();
    private String nazwaFirmy;


    public DzialKadr (Pracownik ksiegowy, String nazwaFirmy) {
        if (ksiegowy == null) {
            throw new NullPointerException("ksiegowy nie moze byc null");
        }
        addPracownik(ksiegowy);

        this.nazwaFirmy = nazwaFirmy;
        addDzialKadr(this);
    }

    private static void addDzialKadr(DzialKadr dzialKadr) {
        extent.add(dzialKadr);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<DzialKadr>) stream.readObject();
    }

    public static List<DzialKadr> getExtent() {
        return extent;
    }

    public  void addPracownik(Pracownik newPracownik) throws NullPointerException {
        if (!pracowniki.contains(newPracownik) && newPracownik != null ) {
            pracowniki.add(newPracownik);
            newPracownik.setDzialKadrowy(this);
        }
    }

    public void removePracownik (Pracownik pracownik) {
        if (pracownik == null) {
            throw new NullPointerException();
        } else {
            pracowniki.remove(pracownik);
            pracownik.removeDzial(this);
        }
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public List<Pracownik> getPracowniki() {
        return pracowniki;
    }

    @Override
    public String toString() {
        return "DzialKadr{" +
                "nazwaFirmy='" + nazwaFirmy + '\'' +
                ", pracowniki=" + pracowniki +
                '}';
    }
}