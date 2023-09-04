package Task4;

public class ReversibleInt implements Reversible{

    int liczba;

    ReversibleInt(int liczba){
        this.liczba = liczba;
    }

    @Override
    public Reversible reverse() {
        if(liczba!=0){
            liczba = -liczba;
        }
        return this;
    }

    @Override
    public String toString() {
        return "liczba=" + liczba;
    }
}
