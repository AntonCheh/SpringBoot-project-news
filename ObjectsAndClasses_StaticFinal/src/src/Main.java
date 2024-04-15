public class Main {
    public static void main(String[] args) {
Computer computer = new Computer(new Processor(1000, 2, "IBM", 100),
        new Ram("New", 2, 15),
        new StorageOfInformation("Hard disk", 2, 1500),
        new Monitor(19, "Samsung",800),
        new Keyboard("wireless", Illumination.available,  15000), "Samsung", "Max 2000");
        computer.toString();
        Computer.totalWeight();
        System.out.println("Общий вес компьютера: " + Computer.getTotalWeight() + " грамм");
        System.out.println(computer.toString());







    }
}