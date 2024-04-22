import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    public class Main {
        private static final int newWidth = 300;
        private static final int numThreads = Runtime.getRuntime().availableProcessors(); // Получение количества ядер процессора

        public static void main(String[] args) {
            String srcFolder = "C:/Users/User/Desktop/src";
            String dstFolder = "C:/Users/User/Desktop/dst";
            File srcDir = new File(srcFolder);
            long start = System.currentTimeMillis();
            File[] files = srcDir.listFiles();

            ExecutorService executorService = Executors.newFixedThreadPool(numThreads); // Создание пула потоков

            try {
                for (File file : files) {
                    executorService.execute(new ImageResizer(file, newWidth, dstFolder, start)); // Запуск каждого изображения на изменение размера в отдельном потоке
                }
            } finally {
                executorService.shutdown(); // Завершение работы пула потоков после завершения всех задач
            }

            System.out.println("Duration: " + (System.currentTimeMillis() - start));
        }
    }
