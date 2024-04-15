public class Cargo {
    private final Dimensions dimensions; // габариты
    private final int weight; // вес
    private final String deliveryAddress; // адрес доставки
    private final boolean property; // свойства
    private final boolean fragile; // хрупкий
    private final int registerNumber; // регистрационный номер


    public Cargo(Dimensions dimensions, int weight, String deliveryAddress, boolean property, boolean fragile, int registerNumber) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.property = property;
        this.fragile = fragile;
        this.registerNumber = registerNumber;
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Cargo setWeight(int weight) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Cargo setProperty(boolean property) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Cargo setFragile(boolean fragile) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Cargo setRegisterNumber(int registerNumber) {
        return new Cargo(dimensions, weight, deliveryAddress, property, fragile, registerNumber);
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
    public int getWeight() {
        return weight;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public boolean isProperty() {
        return property;
    }
    public boolean isFragile() {
        return fragile;
    }
    public int getRegisterNumber() {
        return registerNumber;
    }
}
