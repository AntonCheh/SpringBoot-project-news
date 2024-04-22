import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer implements Runnable {
    private File file;
    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageResizer(File file, int newWidth, String dstFolder, long start) {
        this.file = file;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {
                return;
            }

            BufferedImage newImage = Scalr.resize(image, newWidth); // Использование библиотеки Imgscalr для изменения размера изображения

            File newFile = new File(dstFolder + "/" + file.getName());
            ImageIO.write(newImage, "jpg", newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finish " + file.getName() + " in " + (System.currentTimeMillis() - start) + " ms");
    }
}

