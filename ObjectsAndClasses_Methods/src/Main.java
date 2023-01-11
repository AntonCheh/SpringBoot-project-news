import java.util.BitSet;

public class Main {

    public static void main(String[] args) {

        Basket antonBasket = new Basket(10000);
        Basket iraBasket = new Basket(150000);

        antonBasket.add("Молоко", 40, 20, 5.4);
        antonBasket.add("Яблоки", 25, 10, 2);
        antonBasket.add("Мука", 46, 1, 1);
        antonBasket.add("Яйца", 88, 10, 0.5);
        antonBasket.print("Корзина Антона");
        System.out.println();
        System.out.println("Общая стоимость: " + antonBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + antonBasket.getTotalWeight() + " кг.");


        System.out.println();
        iraBasket.add("Дыня", 90, 1, 1);
        iraBasket.add("Изюм", 80, 1, 1);
        iraBasket.add("Персик", 120, 2, 2);
        iraBasket.add("Бананы", 100, 2, 2);
        iraBasket.print("Корзина Иры");
        System.out.println("\n" + "Общая стоимость: " + iraBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес: " + iraBasket.getTotalWeight() + " кг." + "\n");

        System.out.println("Итоги по заданию:");
        System.out.println("Средняя стоимость одной корзины: " + Basket.getAveragePriceBasket());
        System.out.println("Средняя цена товара во всех корзинах: " + Basket.getAveragePrice());
        System.out.println();
        System.out.println("Количество корзин: " + Basket.getTotalCount());





    }
}