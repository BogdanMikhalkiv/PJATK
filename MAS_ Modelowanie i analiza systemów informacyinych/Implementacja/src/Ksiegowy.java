import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ksiegowy extends Pracownik implements Serializable {

    private static List<Ksiegowy> extent = new ArrayList<>();


    //zwykla
    private   DzialKadr dzialKadrowy;

    //kompozycja
   // private   List<Wyplata> wyplatyZrobione = new ArrayList<>();

    private   List<Premia> premiumZrobione = new ArrayList<>();





    public Ksiegowy(String imie,
                    String nazwisko,
                    String danePaszportowe,
                    List<String> wyksztalcenie,
                    LocalDate dataUrodzenia,
                    LocalDate dataPrzyjecia
                    ) {

        super(imie, nazwisko, danePaszportowe,wyksztalcenie,dataUrodzenia,dataPrzyjecia);
        addKsiegowy(this);
    }

    private static void addKsiegowy(Ksiegowy ksiegowy) {
        extent.add(ksiegowy);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<Ksiegowy>) stream.readObject();
    }

    public static List<Ksiegowy> getExtent() {
        return extent;
    }

    public void addWyplata(Wyplata wyplata) {
        if (!wyplatyZrobione.contains(wyplata) ) {
            wyplatyZrobione.add(wyplata);
            wyplata.setKsiegowy(this);
        }
    }

    public void removeWyplata(Wyplata wyplata) {
        if (wyplata != null && wyplatyZrobione.contains(wyplata)) {
            wyplatyZrobione.remove(wyplata);
            wyplata.removeKsiegowy(this);
        }
    }

    public void addPremium(Premia premia) {
        if (!premiumZrobione.contains(premia) ) {
            premiumZrobione.add(premia);
            premia.setKsiegowy(this);
        }
    }

    public void removePremium(Premia premia) {
        if (premia != null && premiumZrobione.contains(premia)) {
            premiumZrobione.remove(premia);
            premia.removeKsiegowy(this);
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



    @Override
    public String getImie() {
        return super.getImie();
    }

    @Override
    public String toString() {
        return   "ksiegowy{" +
                " imie -" + getImie() +
                " nazwisko- " + getNazwisko()+
                '}';
    }
}