import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Kandydat extends Osoba implements Serializable {
    private static List<Kandydat> extent = new ArrayList<>();

    Rekrutacja rekrutacja;
    public Kandydat(String imie, String nazwisko, String danePaszportowe, List<String> wyksztalcenie, LocalDate dataUrodzenia) {
        super(imie, nazwisko, danePaszportowe, wyksztalcenie, dataUrodzenia);
        addKandydat(this);
    }


    private static void addKandydat(Kandydat kandydat) {
        extent.add(kandydat);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Kandydat>) stream.readObject();
    }

    public static List<Kandydat> getExtent() {
        return extent;
    }

    public void addRekrutacjaQualif(Rekrutacja rekrutacja) {
        if (rekrutacja == null) {
            throw new NullPointerException();
        }
        if(this.rekrutacja != rekrutacja) {
            this.rekrutacja = rekrutacja;

            rekrutacja.setKandydat(this);
        }
    }

    public void removeRekrutacja(Rekrutacja rekrutacja) {
        if (rekrutacja == null) {
            throw new NullPointerException();
        }

            this.rekrutacja = null;
            rekrutacja.removeKandydat(this);

    }
}
