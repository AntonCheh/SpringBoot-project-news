public final class StorageOfInformation {
    private final String typeOfStorage;
    private final int volumeOfMemory;
    private static int weight;

    public StorageOfInformation(String typeOfStorage, int volumeOfMemory, int weight) {
        this.typeOfStorage = typeOfStorage;
        this.volumeOfMemory = volumeOfMemory;
        this.weight = weight;
    }
    public static int getWeight() {
        return weight;
    }

    public String toString() {
        return "Storage of information: " + this.typeOfStorage + " ; " +  this.volumeOfMemory + " ; "
                + this.weight;
    }
}
