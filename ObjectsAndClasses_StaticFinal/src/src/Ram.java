public final class Ram {
    private final String typeOfRam;
    private final int volumeOfRam;
    private static int weightOfRam;

    public Ram(String typeOfRam, int volumeOfRam, int weightOfRam) {
        this.typeOfRam = typeOfRam;
        this.volumeOfRam = volumeOfRam;
        this.weightOfRam = weightOfRam;
    }

    public static int getWeight() {
        return weightOfRam;
    }

    public String toString() {
        return "Ram: " + this.typeOfRam + " ; " +  this.volumeOfRam + " ; " + this.weightOfRam;
    }
}
