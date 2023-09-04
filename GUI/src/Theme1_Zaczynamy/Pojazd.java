package Theme1_Zaczynamy;

import java.time.LocalDate;

public class Pojazd {
    protected String marka;
    protected String model;
    protected int rokProdukcji;
    protected LocalDate dataRejestracji;

    public Pojazd(String marka, String model, int rokProdukcji, LocalDate dataRejestracji) {
        this.marka = marka;
        this.model = model;
        this.rokProdukcji = rokProdukcji;
        this.dataRejestracji = dataRejestracji;
    }

    @Override
    public String toString() {
        return "Pojazd{" +
                "marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                ", dataRejestracji=" + dataRejestracji +
                '}';
    }
}
