package Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Task5.Spiewak.najglosniej;

public class Main {
    public static void main(String args[]) {
        Spiewak s1 = new Spiewak("Darrey") {
            @Override
            String spiewaj() {
                return "eeae";
            }
        };

        Spiewak s2 = new Spiewak("Darrey") {

            @Override
            String spiewaj() {
                return "bb";
            }
        };

        Spiewak s3 = new Spiewak("Houston") {
            @Override
            String spiewaj() {
                return "a4iBBiii";
            }
        };

        Spiewak s4 = new Spiewak("Carrey") {
            @Override
            String spiewaj() {
                return "oaooooooooooo";
            }
        };

        Spiewak s5 = new Spiewak("Madonna") {
            @Override
            String spiewaj() {
                return "aAa";
            }
        };


        Spiewak sp[] = {s1, s2, s3};

        for (Spiewak s : sp)
            System.out.println(s);

        System.out.println("\n" + najglosniej(sp));

        System.out.println("==========================================================================");

        List<Spiewak> spiewaks = new ArrayList<>();
        spiewaks.add(s3);
        spiewaks.add(s2);
        spiewaks.add(s1);
        spiewaks.add(s4);
        spiewaks.add(s5);

        Collections.sort(spiewaks);

        spiewaks.forEach(System.out::println);


    }
}
