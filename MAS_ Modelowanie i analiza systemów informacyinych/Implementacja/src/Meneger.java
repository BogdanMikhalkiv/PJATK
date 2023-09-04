import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Meneger extends Pracownik implements Serializable {

    private static List<Meneger> extent = new ArrayList<>();


    //kkwalif
    private Map<String, Rekrutacja> rekrutacjaMap = new TreeMap<>();

    public Meneger(String imie,
                   String nazwisko,
                   String danePaszportowe,
                   List<String> wyksztalcenie,
                   LocalDate dataUrodzenia,
                   LocalDate dataPrzyjecia
    ) {

        super(imie, nazwisko, danePaszportowe,wyksztalcenie,dataUrodzenia,dataPrzyjecia);
        addMeneger(this);
    }

    private static void addMeneger(Meneger meneger) {
        extent.add(meneger);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Meneger>) stream.readObject();
    }

    public Rekrutacja findRekrutQualif(String nrRekrrut) throws Exception {
        if(!rekrutacjaMap.containsKey("nr"+ nrRekrrut)) {
            throw new Exception("nie ma  " + nrRekrrut);
        }
        return rekrutacjaMap.get("nr" + nrRekrrut);
    }

    public static List<Meneger> getExtent() {
        return extent;
    }

    public void addRekrutacjaQualif(Rekrutacja rekrutacja) {
        if (rekrutacja == null) {
            throw new NullPointerException();
        }
        if(!rekrutacjaMap.containsKey(rekrutacja.getNrRekrut())) {
            rekrutacjaMap.put(rekrutacja.getNrRekrut(), rekrutacja);

            rekrutacja.setManager(this);
        }
    }

    public void removeRekrutacja(Rekrutacja rekrutacja) {
        if (rekrutacja == null) {
            throw new NullPointerException();
        }
        if (rekrutacjaMap.containsValue(rekrutacja)) {
            rekrutacjaMap.remove(rekrutacja.getNrRekrut());
            rekrutacja.removeManager(this);
        }
    }



    public void setDzialKadrowy(DzialKadr newDzialKadrowy) {
        if (newDzialKadrowy != null && dzialKadr != newDzialKadrowy) {
            if (dzialKadr != null) {
                dzialKadr.removePracownik(this);
            }
            dzialKadr = newDzialKadrowy;
            newDzialKadrowy.addPracownik(this);
        }
    }
    public void removeDzial(DzialKadr dzialKadrowy) {
        if (dzialKadrowy == null) {
            throw new NullPointerException("nie moze byc null");
        } else {
            this.dzialKadr = null;
            dzialKadrowy.removePracownik(this);
        }
    }

    public void powolacNaStanowisko(Pracownik pracownik) {
        dzialKadr.addPracownik(pracownik);
    }
    public void zwalnicZeStanowisko(Pracownik pracownik) {
        dzialKadr.removePracownik(pracownik);
    }



    @Override
    public String toString() {
        return "Meneger{" +
                " imie -" + getImie() +
                " nazwisko- " + getNazwisko()+
                "wyplaty - " + getWyplaty() +
                "premia - " + getPremium() +
                "rekrut " + rekrutacjaMap+
                '}';
    }
}
