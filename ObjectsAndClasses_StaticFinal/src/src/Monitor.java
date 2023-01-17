public final class Monitor {

    private final int diagonal;
    private final String typeOfMatrix;
    private static int weight;

    public Monitor(int diagonal, String typeOfMatrix, int weight) {
        this.diagonal = diagonal;
        this.typeOfMatrix = typeOfMatrix;
        this.weight = weight;
    }

    public static int getWeight() {
        return weight;
    }

}
