import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Osoba implements Serializable {
    private String imie, nazwisko,danePaszportowe;
    private List<String> wyksztalcenie ;

    private LocalDate dataUrodzenia;

    public Osoba(String imie, String nazwisko, String danePaszportowe, List<String> wyksztalcenie, LocalDate dataUrodzenia){
        this.danePaszportowe = danePaszportowe;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.wyksztalcenie = wyksztalcenie;
    }


    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getDanePaszportowe() {
        return danePaszportowe;
    }

    public List<String> getWyksztalcenie() {
        return wyksztalcenie;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }



    @Override
    public String toString() {
        return "Osoba{" +
                "imie='" + getImie() + '\'' +
                ", nazwisko='" + getNazwisko() + '\'' +
                ", danePaszportowe='" + getDanePaszportowe() + '\'' +
                ", wyksztalcenie=" + getWyksztalcenie() +
                ", dataUrodzenia=" + getDataUrodzenia() +
                '}';
    }
}
