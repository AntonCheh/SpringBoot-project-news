public class Computer {


    private final Ram ram;
    private final StorageOfInformation storageOfInformation;
    private final Monitor monitor;
    private final Keyboard keyboard;
    private final Processor processor;
    private final String vendor;
    private final String name;


    public Computer(Processor processor, Ram ram, StorageOfInformation storageOfInformation,
                    Monitor monitor, Keyboard keyboard, String vendor, String name) {
        this.ram = ram;
        this.storageOfInformation = storageOfInformation;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.processor = processor;
        this.vendor = vendor;
        this.name = name;
    }


    public static int totalWeight;

    public static void totalWeight() {
        Computer.totalWeight = totalWeight + Keyboard.getWeight() + Monitor.getWeight() + Processor.getWeight()
                + Ram.getWeight() + StorageOfInformation.getWeight();
    }

    public static int getTotalWeight() {
        return totalWeight;
    }


    public Computer setVendor(String vendor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setName(String name) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setProccessor(Processor processor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setRam(Ram ram) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setStorageOfInformation(StorageOfInformation storageOfInformation) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setMonitor(Monitor monitor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Processor getProccessor() {
        return processor;
    }

    public Ram getRam() {
        return ram;
    }

    public StorageOfInformation getStorageOfInformation() {
        return storageOfInformation;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public String toString() {
        return "Информация о компьютере:" + "\n" +  keyboard.toString()
                + "\n" + monitor.toString()
                + "\n" + processor.toString()
                + "\n" + ram.toString()
                + "\n" + storageOfInformation.toString();

    }


}


