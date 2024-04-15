import java.util.BitSet;

public class Main {

    public static void main(String[] args) {

        Basket antonBasket = new Basket(10000);
        Basket iraBasket = new Basket(150000);
        Basket sergeiBasket = new Basket(100000);


        antonBasket.add("Молоко", 40, 20, 2.4);
        antonBasket.add("Яблоки", 25, 10, 2);
        antonBasket.add("Мука", 46, 1, 1);
        antonBasket.add("Яйца", 88, 10, 0.5);
        antonBasket.print("Корзина Антона");
        System.out.println();
        System.out.println("Cтоимость корзины Антона: " + antonBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес корзины Антона: " + antonBasket.getTotalWeight() + " кг.");


        System.out.println();
        iraBasket.add("Дыня", 90, 1, 1);
        iraBasket.add("Изюм", 80, 1, 1);
        iraBasket.add("Персик", 120, 2, 2);
        iraBasket.add("Бананы", 100, 2, 2);
        iraBasket.print("Корзина Иры");
        System.out.println("\n" + "Стоимость корзины Иры: " + iraBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес корзины Иры: " + iraBasket.getTotalWeight() + " кг." + "\n");

        System.out.println();
        sergeiBasket.add("Дыня", 90, 1, 1);
        sergeiBasket.add("Изюм", 80, 1, 1);
        sergeiBasket.add("Персик", 10, 1, 2);
        sergeiBasket.add("Бананы", 100, 1, 2);
        sergeiBasket.print ("Корзина Антона2");
        System.out.println("\n" + "Стоимость корзины Сергея: " + sergeiBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес корзины Сергея: " + iraBasket.getTotalWeight() + " кг." + "\n");


        System.out.println("Итоги по заданию:");
        System.out.println("Средняя стоимость одной корзины: " + Basket.getAveragePriceBasket());
        System.out.println("Средняя цена товара во всех корзинах: " + Basket.getAveragePrice());
        System.out.println();
        System.out.println("Количество корзин: " + Basket.getTotalCount());
        System.out.println("Общий вес всех корзин: " + Basket.getTotalWeightBaskets());





    }
}