import java.util.BitSet;

public class Main {

    public static void main(String[] args) {

        Basket antonBasket = new Basket(100000);
        Basket iraBasket = new Basket();
        System.out.println("Количество корзин: " + Basket.getTotalCount());
        antonBasket.add("Молоко", 40, 20, 5.4);
        antonBasket.add("Яблоки", 25, 10, 2);
        antonBasket.add("Мука", 46, 1, 1);
        antonBasket.add("Яйца", 88, 10, 0.5);
        antonBasket.print("Корзина Антона");
        System.out.println("Общая стоимость: " + antonBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + antonBasket.getTotalWeight() + " кг.");
        System.out.println("Средняя цена товара во всех корзинах: " +
                Basket.getAveragePriceAllBaskets());
        System.out.println("Средняя стоимость корзины: " + Basket.getAveragePriceBasket());
        antonBasket.clear();
        antonBasket.print("Корзина Антона");





        iraBasket.add("Дыня", 90, 1, 1);
        iraBasket.add("Изюм", 80, 1, 1);
        iraBasket.add("Персик", 120, 2, 2);
        iraBasket.add("Бананы", 100, 2, 2);
        iraBasket.print("Корзина Иры");
        System.out.println("Общая стоимость: " + iraBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + iraBasket.getTotalWeight() + " кг.");
        iraBasket.clear();
        iraBasket.print("Корзина Иры");




    }
}