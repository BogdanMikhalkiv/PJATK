package Task4;

public class Main {
    public static void main(String args[]){
        Reversible[] revers = new Reversible[] {
                new ReversibleString("Kot2018"),
                new ReversibleInt(2),
                new ReversibleInt(3),
                new ReversibleString("Pies"),
                new ReversibleString("Ala ma kota i psa"),
                new ReversibleInt(10)
        };

        System.out.println("Normalne:");
        for (Reversible r : revers) {
            System.out.println(r);
        }

        for (Reversible r : revers) {
            r.reverse();
        }

        System.out.println("Odwrócone:");
        for (Reversible r : revers) {
            System.out.println(r);
        }

        System.out.println("Przywrócone i zmienione:");
        for (Reversible r : revers) {
            /*<-  co tu trzeba napisać */
            r.reverse();
            if(r instanceof ReversibleInt)
                ((ReversibleInt) r).liczba = ((ReversibleInt) r).liczba + 10;
            if(r instanceof ReversibleString)
                ((ReversibleString) r).napis = "Tekst" + ((ReversibleString) r).napis;
            System.out.println(r);
        }
    }
}

