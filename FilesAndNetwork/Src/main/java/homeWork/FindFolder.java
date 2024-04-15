package homeWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class FindFolder {
    public static List<Path> pathsOfJsons = new ArrayList<>();
    public static List<Path> pathsOfCvs = new ArrayList<>();

    public static void main(String[] args) {
        try {
            walkAndPrintFiles(Path.of(FromZipArchive.out));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathsOfJsons);
        System.out.println(pathsOfCvs);
    }

    public static void walkAndPrintFiles(Path directory) throws IOException {
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

        if (fileName.endsWith(".json")) {
            pathsOfJsons.add(file);
        } else if (fileName.endsWith(".csv")) {
            pathsOfCvs.add(file);
        }
    }
}
