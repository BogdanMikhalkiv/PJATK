package Theme1_Zaczynamy;

import java.time.LocalDate;

public class Taksowka extends Samochod {
    protected LocalDate dataWaznosciTaksometru;

    public Taksowka(String marka, String model, int rokProdukcji, LocalDate dataRejestracji, LocalDate dataOstatniegoPrzegladuOkresowego, LocalDate dataWaznosciOstatniegoUbezpieczenia, LocalDate dataWaznosciTaksometru) {
        super(marka, model, rokProdukcji, dataRejestracji, dataOstatniegoPrzegladuOkresowego, dataWaznosciOstatniegoUbezpieczenia);
        this.dataWaznosciTaksometru = dataWaznosciTaksometru;
    }

    public void setDataWaznosciTaksometru(LocalDate dataWaznosciTaksometru) {
        this.dataWaznosciTaksometru = dataWaznosciTaksometru;
    }

    @Override
    public boolean sprawzDateOstatniegoPrezgladuOkresowego(LocalDate localDate) {
        if (localDate.getMonth().getValue() - dataOstatniegoPrzegladuOkresowego.getMonth().getValue() > 6 || localDate.getYear() - dataOstatniegoPrzegladuOkresowego.getYear() >= 1) {
            return false;
        }
        if (localDate.getMonth().getValue() - dataOstatniegoPrzegladuOkresowego.getMonth().getValue() == 6 && localDate.getDayOfMonth() - dataOstatniegoPrzegladuOkresowego.getDayOfMonth() >= 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Taksowka{" +
                "dataWaznosciTaksometru=" + dataWaznosciTaksometru +
                ", dataOstatniegoPrzegladuOkresowego=" + dataOstatniegoPrzegladuOkresowego +
                ", dataWaznosciOstatniegoUbezpieczenia=" + dataWaznosciOstatniegoUbezpieczenia +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", dataRejestracji=" + dataRejestracji +
                '}';
    }
}
