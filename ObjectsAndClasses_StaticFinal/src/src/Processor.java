public final class Processor {

        private final int frequency; // частота
        private final int cores; // ядра
        private final String manufacturer; // производитель
        private static int weight;

        public Processor(int frequency, int cores, String manufacturer, int weight) {
        this.frequency = frequency;
        this.cores = cores;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }
        public static int getWeight() {
                return weight;
        }

}
