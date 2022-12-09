public class Cargo {

    private int dimension; // габариты
    private int weight; // вес
    private String deliveryAddress; // адрес доставки
    private static boolean property; // свойства
    private boolean fragile; // хрупкий
    private int registerNumber; // регистрационный номер


    public Cargo(int weight, String deliveryAddress, boolean property, boolean fragile, int registerNumber) {
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.property = property;
        this.fragile = fragile;
        this.registerNumber = registerNumber;
    }
    public int getWeight() {
        return weight;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public static boolean isProperty(String name) {
        return property;
    }
    public boolean isFragile() {
        return fragile;
    }
    public int getRegisterNumber() {
        return registerNumber;
    }
    public int getDimension() {
        return dimension;
    }





}
