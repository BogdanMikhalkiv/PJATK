import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Premia implements Serializable {


    private Ksiegowy ksiegowy;
    private Pracownik pracownik;
    private LocalDate date;
    private double kwota;


    public Premia(LocalDate date, double kwota, Ksiegowy ksiegowy, Pracownik pracownik) {
        this.date = date;
        this.kwota  = kwota;
        setKsiegowy(ksiegowy);
        setPracownik(pracownik);
    }

    public void setKsiegowy (Ksiegowy newKsiegowy) {

        if (ksiegowy != newKsiegowy) {
            if (ksiegowy != null) {
                ksiegowy.removePremium(this);
            }
            this.ksiegowy = newKsiegowy;
            newKsiegowy.addPremium(this);
        }
    }

    public void setPracownik(Pracownik newPracownik) {
        if (pracownik != newPracownik) {
            if (pracownik != null) {
                pracownik.removePremiumPracownik(this);
            }
            pracownik = newPracownik;
            newPracownik.addPremiumPracownik(this);
        }
    }

    public void removeKsiegowy(Ksiegowy ksiegowy) {

        ksiegowy.removePremium(this);
        this.ksiegowy = null;

    }

    public void removePracownik(Pracownik pracownik) {

        pracownik.removePremiumPracownik(this);
        this.pracownik = null;

    }


    public double getKwota() {
        return kwota;
    }

    public Ksiegowy getKsiegowy() {
        return ksiegowy;
    }

    public LocalDate getDate() {
        return date;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }
}
