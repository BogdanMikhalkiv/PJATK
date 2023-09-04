import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PracownikBiurowy pracownikBiurowy = new PracownikBiurowy(
                "Alan",
                "Asdf",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(2001,1,1),
                LocalDate.of(2020,1,1),
                "12323423542"

        );
        Ksiegowy ksiegowy = new Ksiegowy(
                "Bob",
                "Krylov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(2001,1,1),
                LocalDate.of(2020,1,1)

        );

        Ksiegowy ksiegowy2 = new Ksiegowy(
                "Ilya",
                "Mikhov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(2001,1,1),
                LocalDate.of(2020,1,1)

        );

        Ksiegowy ksiegowy3 = new Ksiegowy(
                "Igor",
                "Romanov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(2001,1,1),
                LocalDate.of(2020,1,1)

        );
        DzialKadr kadr = new DzialKadr(pracownikBiurowy,"rdgfdsgfs");
        kadr.addPracownik(ksiegowy);
        kadr.addPracownik(ksiegowy2);
        kadr.addPracownik(ksiegowy3);

        System.out.println(kadr);

        Wyplata wyplata = new Wyplata(LocalDate.of(2022,1,1),2000,ksiegowy, pracownikBiurowy);
        Wyplata wyplata2 = new Wyplata(LocalDate.of(2022,2,1),2000,ksiegowy, pracownikBiurowy);


        PremiaJednorazowa premiaJednorazowa = new PremiaJednorazowa(LocalDate.of(2022,1,1), 100,ksiegowy,pracownikBiurowy);

        Meneger meneger = new Meneger(
                "Alex",
                "Babov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(1990,1,1),
                LocalDate.of(2020,1,1)
        );


        kadr.addPracownik(meneger);

        Kandydat kandydat = new Kandydat(
                "Yarik",
                "Krylov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(2001,1,1)
        );

        Rekrutacja rekrutacja = new Rekrutacja(
                List.of("driver"),
                "warunki",
                1000,
                "w rozpatrzeniu",
                meneger,
                kandydat

        );

        Wyplata wyplata3 = new Wyplata(LocalDate.of(2022,2,1),2000,ksiegowy, meneger);
        PremiaJednorazowa premiaJednorazowa2 = new PremiaJednorazowa(LocalDate.of(2022,1,1), 100,ksiegowy,meneger);


        System.out.println(kadr);



        System.out.println(rekrutacja);
        try {
            System.out.println(meneger.findRekrutQualif("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dyrektor dyrektor = new Dyrektor(
                "Ivan",
                "Smirnov",
                "sdfsdf",
                List.of("srednie"),
                LocalDate.of(1990,1,1),
                LocalDate.of(2020,1,1)
        );
        kadr.addPracownik(dyrektor);

        Wyplata wyplata4 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy, dyrektor);

        Wyplata wyplata5 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy2, dyrektor);
        Wyplata wyplata6 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy2, dyrektor);
        Wyplata wyplata7 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy2, dyrektor);

        Wyplata wyplata8 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy3, meneger);
        Wyplata wyplata9 = new Wyplata(LocalDate.of(2022,2,1),5000,ksiegowy3, pracownikBiurowy);


        System.out.println( "romer"+ksiegowy.getWyplatyZrobione());
        dyrektor.getListOfKsiegowy(3);

        System.out.println(meneger);

        try {
            System.out.println(meneger.findRekrutQualif("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("person.txt"));
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("person.txt"));

            //write
            Dyrektor.writeExtents(write);
            Ksiegowy.writeExtents(write);
            PracownikBiurowy.writeExtents(write);
            DzialKadr.writeExtents(write);
            Wyplata.writeExtents(write);
            PremiaJednorazowa.writeExtents(write);
            PremiumMieseczne.writeExtents(write);
            Meneger.writeExtents(write);
            Kandydat.writeExtents(write);
            Rekrutacja.writeExtents(write);

            //read
            Dyrektor.readExtents(read);
            Ksiegowy.readExtents(read);
            PracownikBiurowy.readExtents(read);
            DzialKadr.readExtents(read);
            Wyplata.readExtents(read);
            PremiaJednorazowa.readExtents(read);
            PremiumMieseczne.readExtents(read);
            Meneger.readExtents(read);
            Kandydat.readExtents(read);
            Rekrutacja.readExtents(read);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        System.out.println("pracownik extent");
        List<Dyrektor> ext = Dyrektor.getExtent();
        System.out.println(ext);


    }
}
