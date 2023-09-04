package zad2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class Derevo {
    Derevo prawo, lewo, dad;
    char imie;

 public Derevo leftOrRight (char c) {
     if (c == 'R') {
         def(c);
         prawo.dad = this;
         return this.prawo;
     } else  {
         def(c);
         lewo.dad = this;
         return this.lewo;
     }
 }

 public void def(char c) {
    if (c == 'R' && prawo == null) {
        prawo = new Derevo();
    }
    if (c == 'L' && lewo == null) {
        lewo = new Derevo();
    }

 }

    public void getText(Derevo myDrevo) {
        if (myDrevo.lewo != null || myDrevo.prawo != null) {
            if (myDrevo.lewo != null) {
                getText(myDrevo.lewo);
            }
            if (myDrevo.prawo != null) {
                getText(myDrevo.prawo);
            }
        } else {
            verify(myDrevo);

        }
    }

    public void verify(Derevo derevo) {
        if (derevo != null) {
            s19362.curentWords += derevo.imie;
            verify(derevo.dad);
        } else {
            alphabet(s19362.finalWords, s19362.curentWords, 0);
        }
        s19362.curentWords = "";
    }

    public void alphabet (String s1, String s2, int i) {
     if (s19362.finalWords.length() == 0 || (i < s1.length() && i < s2.length())) {
        if (s19362.finalWords.length() == 0 || s1.charAt(i) < s2.charAt(i)) {
            s19362.finalWords = s19362.curentWords;
            s19362.curentWords = "";
        } else if (s1.charAt(i) == s2.charAt(i)){
            alphabet(s1,s2, ++i);
        }
     } else {
         if (s2.length() > s1.length()) {
             s19362.finalWords = s19362.curentWords;
             s19362.curentWords = "";
         }
     }
    }
}



public class s19362 {

    public static String finalWords = "";
    public static String curentWords = "";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(args[0]);
        Scanner scanner = null;
        String s = "";
        Derevo derevo = new Derevo();
        try {
            scanner = new Scanner(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String stroczka;
            String way;
            char imiaStroczki;
            stroczka = scanner.nextLine();

            if (stroczka.length() != 1) {
                imiaStroczki = stroczka.charAt(0);
                way = stroczka.substring(2);
                Derevo walking = derevo;
                for (int i = 0; i < way.length(); i++) {
                        walking = walking.leftOrRight(way.charAt(i));

                    if (i == way.length() - 1) {
                        walking.imie = imiaStroczki;
                    }
                }
            } else {
                derevo.imie = stroczka.charAt(0);
            }
        }
        derevo.getText(derevo);
        System.out.println(finalWords);

    }

}
