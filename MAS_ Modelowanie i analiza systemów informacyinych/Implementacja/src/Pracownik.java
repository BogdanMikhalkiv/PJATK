import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik extends Osoba implements Serializable {


    private LocalDate dataPrzyjecia;
    private LocalDate dataZwolnienia;
    List<Wyplata> wyplaty = new ArrayList<>();
    List<Wyplata> wyplatyZrobione = new ArrayList<>();

    List<Premia> premium = new ArrayList<>();
    DzialKadr dzialKadr;



    public Pracownik(
            String imie,
            String nazwisko,
            String danePaszportowe,
            List<String> wyksztalcenie,
            LocalDate dataUrodzenia,
            LocalDate dataPrzyjecia
            ) {

        super(imie, nazwisko, danePaszportowe, wyksztalcenie, dataUrodzenia);
        this.dataPrzyjecia = dataPrzyjecia;
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
    public List<String> getWyksztalcenie() {
        return super.getWyksztalcenie();
    }

    @Override
    public LocalDate getDataUrodzenia() {
        return super.getDataUrodzenia();
    }

    @Override
    public String getDanePaszportowe() {
        return super.getDanePaszportowe();
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

    public void addWyplataPracownik(Wyplata wyplata) {
        if (!wyplaty.contains(wyplata) ) {
            wyplaty.add(wyplata);
            wyplata.setPracownik(this);
        }
    }

    public void removeWyplataPracownik(Wyplata wyplata) {
        if (wyplata != null && wyplaty.contains(wyplata)) {
            wyplaty.remove(wyplata);
            wyplata.removePracownik(this);
        }
    }

    public void addPremiumPracownik(Premia premia) {
        if (!premium.contains(premia) ) {
            premium.add(premia);
            premia.setPracownik(this);
        }
    }

    public void removePremiumPracownik(Premia premia) {
        if (premia != null && premium.contains(premia)) {
            premium.remove(premia);
            premia.removePracownik(this);
        }
    }

    public List<Wyplata> getWyplaty() {
        return wyplaty;
    }

    public List<Wyplata> getWyplatyZrobione() {
        return wyplatyZrobione;
    }

    public List<Premia> getPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return super.toString() + "Pracownik{" +
                "dataPrzyjecia=" + dataPrzyjecia +
                ", dataZwolnienia=" + dataZwolnienia +
                ", wyplaty=" + wyplaty +
                ", premium=" + premium +
                ", dzialKadr=" + dzialKadr +
                '}';
    }
}
