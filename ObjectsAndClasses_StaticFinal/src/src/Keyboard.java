public final class Keyboard {
    private final String type;
    private final boolean illuninaton;
    private static int weight;

    public static int getWeight() {
        return weight;
    }

    public Keyboard(String type, boolean illuninaton, int weight) {
        this.type = type;
        this.illuninaton = illuninaton;
        this.weight = weight;
    }

    public String toString() {
        return "Keyboard:";
    }
}
