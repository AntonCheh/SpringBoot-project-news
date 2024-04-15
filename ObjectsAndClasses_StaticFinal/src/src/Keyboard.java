public final class Keyboard {
    private final String type;
    private final Illumination illumination;

    private static int weight;

    public static int getWeight() {
        return weight;
    }

    public Keyboard(String type, Illumination illumination1, int weight) {
        this.type = type;
        this.illumination = illumination1;
        this.weight = weight;
    }

    

    public String toString() {
        return "Keyboard: " + this.type + " ; " + this.illumination  + " ; " + this.weight;
    }
}
