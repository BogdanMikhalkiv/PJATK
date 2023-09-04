package Task4;

public class ReversibleString implements Reversible {

    String napis;

    ReversibleString(String napis){
        this.napis = napis;
    }

    @Override
    public Reversible reverse() {
        String nowyNapis = "";

        for(int i=0;i <napis.length();i++){

            if(napis.charAt(i) >= 65 && napis.charAt(i) <= 122){
                if(napis.charAt(i) >= 97)
                    nowyNapis += (char)(napis.charAt(i)-32);
                else
                    nowyNapis += (char)(napis.charAt(i)+32);
            }
            else
                nowyNapis += napis.charAt(i);

        }

        napis = nowyNapis;
        return this;
    }

    @Override
    public String toString() {
        return "napis='" + napis;
    }
}
