import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Wyplata implements Serializable {
    private static List<Wyplata> extent = new ArrayList<>();


    private Ksiegowy ksiegowy;
    private Pracownik pracownik;
    private LocalDate data;
    private double kwota;


    public  Wyplata(LocalDate data, double kwota, Ksiegowy ksiegowy, Pracownik pracownik) {
        if (ksiegowy == null) {
            throw new NullPointerException("ksiegowy nie moze byc null");
        }
        this.data = data;
        this.kwota = kwota;
        setKsiegowy(ksiegowy);
        setPracownik(pracownik);

        addWyplata(this);
    }

    private static void addWyplata(Wyplata wyplata) {
        extent.add(wyplata);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Wyplata>) stream.readObject();
    }


    public static List<Wyplata> getExtent() {
        return extent;
    }

    public void setKsiegowy (Ksiegowy newKsiegowy) {

        if (ksiegowy != newKsiegowy) {
            if (ksiegowy != null) {
                ksiegowy.removeWyplata(this);
            }
            this.ksiegowy = newKsiegowy;
            newKsiegowy.addWyplata(this);
        }
    }

    public void setPracownik(Pracownik newPracownik) {
        if (pracownik != newPracownik) {
            if (pracownik != null) {
                pracownik.removeWyplataPracownik(this);
            }
            pracownik = newPracownik;
            newPracownik.addWyplataPracownik(this);
        }
    }

    public void removeKsiegowy(Ksiegowy ksiegowy) {

        ksiegowy.removeWyplata(this);
        this.ksiegowy = null;

    }

    public void removePracownik(Pracownik pracownik) {

        pracownik.removeWyplataPracownik(this);
        this.pracownik = null;

    }


    @Override
    public String toString() {
        return "Wyplata{" +
                ", data=" + data +
                ", kwota=" + kwota +
                ", " + pracownik.getClass().getName() + "{" + "imie - " + pracownik.getImie() + ", nazwisko " + pracownik.getNazwisko()+

                '}';
    }
}
