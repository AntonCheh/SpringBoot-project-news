public class MainForCargo {
    public static void main(String[] args) {
        Cargo cargo = new Cargo( 45*4*11,
                20,
                "Russia",
                false,
                true,
                2424);
        Dimensions dimensions = new Dimensions(10,15,20);
        cargo.getDeliveryAddress();
        System.out.println("Адрес доставки: " + cargo.getDeliveryAddress());
        cargo.getDimension();
        System.out.println("Размер груза: " + cargo.getDimensions() + " куб.м.");
        cargo.getWeight();
        System.out.println("Вес груза: " + cargo.getWeight() + " кг.");
        cargo.isFragile();
        System.out.println("Состояние груза: " + cargo.isFragile());
        cargo.isProperty("Переворачивать нельзя!");
        System.out.println("Допустимо переворачивать: " + cargo.isProperty("Переворот"));
        cargo.getRegisterNumber();
        System.out.println("Регистрационный номер: " + cargo.getRegisterNumber());
    }
    }

