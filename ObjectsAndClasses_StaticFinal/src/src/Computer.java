public class Computer {

    private final String ram;
    private final String storageOfInformation;
    private final String monitor;
    private final String keyboard;
    private final String processor;
    private final String vendor;
    private final String name;

    public Computer(String vendor, String name, String ram, String storageOfInformation, String monitor,
                    String keyboard, String processor) {
        this.ram = ram;
        this.storageOfInformation = storageOfInformation;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.processor = processor;
        this.vendor = vendor;
        this.name = name;
    }

    public Computer setVendor (String vendor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setName(String ram) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }


    public Computer setProccessor(String processor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setRam(String ram) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setStorageOfInformation(String storageOfInformation) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setMonitor(String monitor) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public Computer setKeyboard(String keyboard) {
        return new Computer(processor, ram, storageOfInformation,
                monitor, keyboard, vendor, name);
    }

    public String getProccessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getStorageOfInformation() {
        return storageOfInformation;
    }

    public String getMonitor() {
        return monitor;
    }

    public String getKeyboard() {
        return keyboard;
    }

}