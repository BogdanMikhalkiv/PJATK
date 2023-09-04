import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rekrutacja implements Serializable {

    private static List<Rekrutacja> extent = new ArrayList<>();


    private List<String> listaStanowisk;
    private String warunkiPracy;
    private double wynagrodzenie;
    private String status;

    private String nrRekrut = "nr";
    static private int count = 0;


    Meneger meneger;
    Kandydat kandydat;

    public Rekrutacja(List<String> listaStanowisk, String warunkiPracy, double wynagrodzenie, String status , Meneger meneger, Kandydat kandydat) {
        this.listaStanowisk = listaStanowisk;
        this.warunkiPracy = warunkiPracy;
        this.wynagrodzenie = wynagrodzenie;
        this.status = status;

        count++;
        nrRekrut += count;
        setManager(meneger);
        setKandydat(kandydat);

        addRekrutacja(this);
    }

    private static void addRekrutacja(Rekrutacja rekrutacja) {
        extent.add(rekrutacja);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Rekrutacja>) stream.readObject();
    }

    public static List<Rekrutacja> getExtent() {
        return extent;
    }

    public void setKandydat(Kandydat kandydat) {
        if (this.kandydat != kandydat && kandydat != null){
            if (this.kandydat != null)
                this.kandydat.removeRekrutacja(this);
            this.kandydat = kandydat;
            meneger.addRekrutacjaQualif(this);
        }
    }

    public void removeKandydat(Kandydat kandydat) {
        if (kandydat == null) {
            throw new NullPointerException();
        } else {
            kandydat.removeRekrutacja(this);
            this.kandydat = null;
        }
    }


    public void setManager(Meneger meneger) {
        if (this.meneger != meneger && meneger != null){
            if (this.meneger != null)
                this.meneger.removeRekrutacja(this);
            this.meneger = meneger;
            meneger.addRekrutacjaQualif(this);
        }
    }

    public void removeManager(Meneger meneger) {
        if (meneger == null) {
            throw new NullPointerException();
        } else {
            meneger.removeRekrutacja(this);
            this.meneger = null;
        }
    }

    public String getNrRekrut() {
        return nrRekrut;
    }

    public String getStatus() {
        return status;
    }

    public void zmienicStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rekrutacja{" +
                "nrRekrut - " + getNrRekrut() +
                " manager= " + meneger.getImie() +
                " kandydat=" + kandydat.getImie() +

                " status=" + getStatus() +


                ", lista stanowisko='" + listaStanowisk + '\'' +
                '}';
    }

}
