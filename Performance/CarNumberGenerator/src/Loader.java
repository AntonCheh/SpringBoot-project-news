import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {

    private static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private static final int REGION_CODE = 199;
    private static final int THREAD_COUNT = 4; // Количество потоков для выполнения задачи

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadIndex = i;
            executor.submit(() -> {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("res/numbers_" + threadIndex + ".txt"))) {
                    generateNumbers(writer, threadIndex);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS); // Ожидание завершения всех задач

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void generateNumbers(BufferedWriter writer, int threadIndex) throws IOException {
        for (int number = 1; number < 1000; number++) {
            char firstLetter = LETTERS[threadIndex];
            for (char secondLetter : LETTERS) {
                for (char thirdLetter : LETTERS) {
                    String carNumber = firstLetter + padNumber(number, 3) + secondLetter + thirdLetter + padNumber(REGION_CODE, 2);
                    writer.write(carNumber);
                    writer.newLine();
                }
            }
        }
    }
    private static String padNumber(int number, int numberLength) {
        StringBuffer paddedNumber = new StringBuffer(Integer.toString(number));
        while (paddedNumber.length() < numberLength) {
            paddedNumber.insert(0, '0');
        }
        return paddedNumber.toString();
    }
}



/*
 Использование BufferedWriter вместо FileOutputStream для увеличения производительности.

 */