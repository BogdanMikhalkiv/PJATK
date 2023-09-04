import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dyrektor extends Pracownik implements Serializable {
    private static List<Dyrektor> extent = new ArrayList<>();

    public Dyrektor(String imie,
                    String nazwisko,
                    String danePaszportowe,
                    List<String> wyksztalcenie,
                    LocalDate dataUrodzenia,
                    LocalDate dataPrzyjecia
    ) {

        super(imie, nazwisko, danePaszportowe,wyksztalcenie,dataUrodzenia,dataPrzyjecia);
        addDyrektor(this);
    }

    private static void addDyrektor(Dyrektor dyrektor) {
        extent.add(dyrektor);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Dyrektor>) stream.readObject();
    }

    public static List<Dyrektor> getExtent() {
        return extent;
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

    public List<Ksiegowy> getListOfKsiegowy(int iloscWyplat) {
        List<Ksiegowy> ksiegowies = new ArrayList<>();

        for (int i = 0; i < dzialKadr.getPracowniki().size(); i++) {
            if (dzialKadr.getPracowniki().get(i).getClass() == Ksiegowy.class &&
                dzialKadr.getPracowniki().get(i).getWyplatyZrobione().size() >= iloscWyplat) {
                    ksiegowies.add((Ksiegowy) dzialKadr.getPracowniki().get(i));
            }
        }
        return ksiegowies;
    }

    public void powolacNaStanowisko(Pracownik pracownik) {
        dzialKadr.addPracownik(pracownik);
    }
    public void zwalnicZeStanowisko(Pracownik pracownik) {
        dzialKadr.removePracownik(pracownik);
    }


    @Override
    public String toString() {
        return "Dyrektor{" +
                " imie -" + getImie() +
                " nazwisko- " + getNazwisko()+
                "wyplaty - " + getWyplaty() +
                "premia - " + getPremium() +
                '}';
    }
}

