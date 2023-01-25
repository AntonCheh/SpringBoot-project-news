public class Main {
    public static void main(String[] args) {
                Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());
        System.out.println();

        for (int i = 1; i < 65536; i++) {
            if ('А' <= (char) i && (char) i <= 'я' || (char) i == 'ё' || (char) i == 'Ё') {
                char alphabet = (char) i;
                System.out.println(i + " - " + alphabet);
            }
        }

    }

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

    }



