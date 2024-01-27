package Src;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindFolder {
    public static void main(String[] args) {
         String files = "C:/Users/User/Desktop/stations-data/data";

        try {
            searchFiles(files);
        } catch (IOException e) {
            e.printStackTrace();
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