public class Flower {
    double SepalLengthCm, SepalWidthCm, PetalLengthCm, PetalWidthCm, dlugosc;
    String Species, newSpecies;


    public Flower (double SepalLengthCm,double SepalWidthCm, double PetalLengthCm,double PetalWidthCm, String species) {
        this.SepalLengthCm = SepalLengthCm;
        this.SepalWidthCm = SepalWidthCm;
        this.PetalLengthCm = PetalLengthCm;
        this.PetalWidthCm = PetalWidthCm;
        Species = species;
    }

    public Flower (double SepalLengthCm,double SepalWidthCm, double PetalLengthCm,double PetalWidthCm) {
        this.SepalLengthCm = SepalLengthCm;
        this.SepalWidthCm = SepalWidthCm;
        this.PetalLengthCm = PetalLengthCm;
        this.PetalWidthCm = PetalWidthCm;
    }




    @Override
    public String toString() {
        return  + dlugosc + " " + Species + " " + newSpecies;
    }
}
