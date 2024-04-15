public class MainForCargo {
    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(20,40,80);
        Cargo cargo = new Cargo(dimensions,
                20,
                "Russia",
                false,
                true,
                2424);

        cargo.getDeliveryAddress();
        System.out.println("Адрес доставки: " + cargo.getDeliveryAddress());
        dimensions.getVolume();
        System.out.println("Размер груза: " + dimensions.getVolume() + " куб.м.");
        cargo.getWeight();
        System.out.println("Вес груза: " + cargo.getWeight() + " кг.");
        cargo.isFragile();
        System.out.println("Состояние груза: " + cargo.isFragile());
        cargo.isProperty();
        System.out.println("Допустимо переворачивать: " + cargo.isProperty());
        cargo.getRegisterNumber();
        System.out.println("Регистрационный номер: " + cargo.getRegisterNumber());
    }
    }

