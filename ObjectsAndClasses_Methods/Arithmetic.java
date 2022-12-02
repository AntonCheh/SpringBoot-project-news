import java.util.Scanner;

public class Arithmetic {
    public static int number1;
    public static int number2;

    public Arithmetic(int number1, int number2) {
        this.number1= number1;
        this.number2 = number2;
    }


    public static void print() {
        System.out.println("Вычисления: " + "\n");
    }

    public static void sum() {
        System.out.println("Итог сложения: " + (number1 + number2) + "\n");
    }

    public static void increase() {
        System.out.println("Итог умножения: " + number1 * number2 + "\n");
    }

    public static boolean max() {
        if (number1 > number2) {
            System.out.println("Максимальное число: " + number1 + "\n");
            return true;
        } else if (number1 < number2) {
            System.out.println("Максимальное число: " + number2 + "\n");
            return true;
        } else {
            System.out.println("Числа равны");
            return false;
        }
    }

    public static boolean min() {
        if (number1 < number2) {
            System.out.println("Минимальное число: " + number1 + "\n");
            return true;
        } else if (number1 > number2) {
            System.out.println("Минимальное число: " + number2 + "\n");
            return true;
        }
        return false;

    }
}



