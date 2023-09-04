package Theme1_Zaczynamy;


import java.time.LocalDate;

public class Samochod extends Pojazd {

    protected LocalDate dataOstatniegoPrzegladuOkresowego;
    protected LocalDate dataWaznosciOstatniegoUbezpieczenia;

    public Samochod(String marka, String model, int rokProdukcji, LocalDate dataRejestracji, LocalDate dataOstatniegoPrzegladuOkresowego, LocalDate dataWaznosciOstatniegoUbezpieczenia) {
        super(marka, model, rokProdukcji, dataRejestracji);
        this.dataOstatniegoPrzegladuOkresowego = dataOstatniegoPrzegladuOkresowego;
        this.dataWaznosciOstatniegoUbezpieczenia = dataWaznosciOstatniegoUbezpieczenia;
    }

    public void setDataOstatniegoPrzegladuOkresowego(LocalDate dataOstatniegoPrzegladuOkresowego) {
        this.dataOstatniegoPrzegladuOkresowego = dataOstatniegoPrzegladuOkresowego;
    }

    public void setDataWaznosciOstatniegoUbezpieczenia(LocalDate dataWaznosciOstatniegoUbezpieczenia) {
        this.dataWaznosciOstatniegoUbezpieczenia = dataWaznosciOstatniegoUbezpieczenia;
    }

    public boolean sprawzDateOstatniegoPrezgladuOkresowego (LocalDate localDate) {
        if (localDate.getYear() - dataOstatniegoPrzegladuOkresowego.getYear() != 0) {
            if (localDate.getMonth().getValue() - dataOstatniegoPrzegladuOkresowego.getMonth().getValue() == 0 && localDate.getDayOfMonth() - dataOstatniegoPrzegladuOkresowego.getDayOfMonth() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "dataOstatniegoPrzegladuOkresowego=" + dataOstatniegoPrzegladuOkresowego +
                ", dataWaznosciOstatniegoUbezpieczenia=" + dataWaznosciOstatniegoUbezpieczenia +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", dataRejestracji=" + dataRejestracji +
                '}';
    }
}
