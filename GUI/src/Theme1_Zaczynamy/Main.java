package Theme1_Zaczynamy;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Taksowka taksowka1 = new Taksowka("Ford", "SV", 2017, LocalDate.now(), LocalDate.now(), LocalDate.now(),LocalDate.now());
        System.out.println(taksowka1.sprawzDateOstatniegoPrezgladuOkresowego(LocalDate.now()));
        System.out.println(taksowka1);

        Taksowka taksowka2 = new Taksowka("Ford", "SV", 2017, LocalDate.now(), LocalDate.of(2014, Month.JANUARY, 1), LocalDate.now(),LocalDate.now());
        System.out.println(taksowka2.sprawzDateOstatniegoPrezgladuOkresowego(LocalDate.now()));
        System.out.println(taksowka2.dataOstatniegoPrzegladuOkresowego.getMonth().getValue());
        taksowka2.setDataOstatniegoPrzegladuOkresowego(LocalDate.now());
        System.out.println(taksowka2.sprawzDateOstatniegoPrezgladuOkresowego(LocalDate.now()));

        Samochod samochod1 = new Samochod("Lada", "Sedan", 2008, LocalDate.of(2017,Month.NOVEMBER,15), LocalDate.now(), LocalDate.of(2018,Month.JANUARY,2));
        System.out.println(samochod1);
    }
}
