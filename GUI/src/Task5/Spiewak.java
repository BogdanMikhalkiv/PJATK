package Task5;

import java.util.ArrayList;

public abstract class Spiewak implements Comparable<Spiewak> {
    String nazwisko;
    int nrStartowy;
    abstract String spiewaj();
    static int ileSpiewakow = 0;

    public Spiewak(String nazwisko){
        ileSpiewakow++;
        this.nazwisko = nazwisko;
        nrStartowy = ileSpiewakow;
    }

    static Spiewak najglosniej (Spiewak[] spiewaki) {

        int najwiekszaIloscLiter = 0;
        Spiewak szukanySpiewak = null;

        for (Spiewak spiewak : spiewaki) {

            boolean[] wystapieniaLiter = new boolean[(int)('z')];
            int iloscLiter = 0;
            String tekst = spiewak.spiewaj().toUpperCase();

            for(int i=0; i < tekst.length(); i++) {

                char aktualnaLitera = tekst.charAt(i);
                if (!wystapieniaLiter[aktualnaLitera] && ((aktualnaLitera >= 'A' && aktualnaLitera <= 'Z'))){
                    wystapieniaLiter[tekst.charAt(i)] = true;
                    iloscLiter++;
                }
            }

            if (iloscLiter > najwiekszaIloscLiter) {
                najwiekszaIloscLiter = iloscLiter;
                szukanySpiewak = spiewak;
            }
        }
        return szukanySpiewak;
    }

    @Override
    public int compareTo (Spiewak o) {

        if (this.numberOfTheLetters() == o.numberOfTheLetters()) {
            if (this.nazwisko.compareTo(o.nazwisko) == 0) {
                return Integer.compare(this.nrStartowy, (o.nrStartowy));
            }else {
                return this.nazwisko.compareTo((o.nazwisko));
            }
        }
        return o.numberOfTheLetters() - this.numberOfTheLetters();
    }

    private int numberOfTheLetters(){

        ArrayList<Character> characters = new ArrayList<>();

        for (int i = 0; i < spiewaj().length(); i++){
            char tmp = spiewaj().toLowerCase().charAt(i);
            if (!(tmp >= '0' && tmp <= '9'))
                characters.add(tmp);
        }
        for (int i = 0; i < characters.size(); i++){
            for(int z = i+1; z < characters.size(); z++){

                if (characters.get(i) == characters.get(z)){
                    characters.remove(z);
                    z--;
                }
            }
        }
        return characters.size();

    }

    @Override
    public String toString() {
        return  "(" + nrStartowy + ") " +
                nazwisko + ": " +
                this.spiewaj();
    }
}