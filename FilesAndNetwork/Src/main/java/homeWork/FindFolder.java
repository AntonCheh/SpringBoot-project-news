package homeWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FindFolder {
    public static String path = "/Users/User/Desktop/";
    public static String in = path + "stations.zip";
    public static String out = path + "resultdata";

//    public static void main(String[] args) throws Exception {
//
//        File outputDirectory = new File(out);
//        if (!outputDirectory.exists()) {
//            outputDirectory.mkdirs();  // Создаем директорию, если её нет
//        }
//        archiveZipOut();
//        searchFiles(out);
//    }

    public static void printFindFolder () throws Exception {
        File outputDirectory = new File(out);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();  // Создаем директорию, если её нет
        }
        archiveZipOut();
        searchFiles(out);
    }

    private static void archiveZipOut() throws Exception {
        FileInputStream inputStream = new FileInputStream(in);
        ZipInputStream zipInput = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry entry = zipInput.getNextEntry();
            if (entry == null) {
                break;
            }
            File file = new File(out + entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
               byte [] bytes = zipInput.readAllBytes();
               Files.write(Paths.get(file.getAbsolutePath()),bytes,StandardOpenOption.CREATE);
            }
        }
    }
    private static void searchFiles(String folderPath) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folderPath))) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    walkAndPrintFiles(path);
                } else {
                    printFileInfo(path);
                }
            }
        }
    }
    private static void walkAndPrintFiles(Path directory) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    walkAndPrintFiles(path);
                } else {
                    printFileInfo(path);
                }
            }
        }
    }
    private static void printFileInfo(Path file) {
        String fileName = file.getFileName().toString().toLowerCase();

        if (fileName.endsWith(".json") || fileName.endsWith(".csv")) {
            System.out.println("Найден файл: " + file);
        }
    }
}