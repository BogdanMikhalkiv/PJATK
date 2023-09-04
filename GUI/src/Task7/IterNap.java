package Task7;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class IterNap implements Iterable {
    String napis;

    public IterNap(String napis) {
        this.napis = napis;
    }


    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
