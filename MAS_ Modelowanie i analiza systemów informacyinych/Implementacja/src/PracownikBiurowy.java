import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PracownikBiurowy extends Pracownik implements Serializable {
    private static List<PracownikBiurowy> extent = new ArrayList<>();


    String numerKarty;
    public PracownikBiurowy(String imie,
                            String nazwisko,
                            String danePaszportowe,
                            List<String> wyksztalcenie,
                            LocalDate dataUrodzenia,
                            LocalDate dataPrzyjecia,
                            String numerKarty
    ) {

        super(imie, nazwisko, danePaszportowe,wyksztalcenie,dataUrodzenia,dataPrzyjecia);
        this.numerKarty = numerKarty;

        addPracownikBiurowy(this);

    }

    private static void addPracownikBiurowy(PracownikBiurowy pracownik) {
        extent.add(pracownik);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (List<PracownikBiurowy>) stream.readObject();
    }

    public static List<PracownikBiurowy> getExtent() {
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

    @Override
    public String getImie() {
        return super.getImie();
    }

    @Override
    public String getNazwisko() {
        return super.getNazwisko();
    }

    @Override
    public String getDanePaszportowe() {
        return super.getDanePaszportowe();
    }

    @Override
    public LocalDate getDataUrodzenia() {
        return super.getDataUrodzenia();
    }

    @Override
    public List<String> getWyksztalcenie() {
        return super.getWyksztalcenie();
    }

    public String getNumerKarty() {
        return numerKarty;
    }

    @Override
    public String toString() {
        return   "PracownikBiurowy{" +
                " imie -" + getImie() +
                " nazwisko- " + getNazwisko()+
                "numerKarty='" + numerKarty + '\'' +
                "wyplaty - " + getWyplaty() +
                "premia - " + getPremium() +
                '}';
    }
}
