package homeWork;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class FinalClass {

    public static void main(String[] args)  {

        try {
            // Получаем список путей к файлам из папки
            List<String> filePaths = FindFolder.searchFiles(FindFolder.out);
            // Вызываем методы для парсинга CSV и JSON файлов
            CsvParsing.print(filePaths);
            JsonParsing.print(filePaths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


 class FileProcessor {
    public static void processFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            if (isJsonFile(filePath)) {
                System.out.println("Найден JSON файл: " + filePath);

            } else if (isCsvFile(filePath)) {
                System.out.println("Найден CSV файл: " + filePath);

            }
        }
    }

    private static boolean isJsonFile(String filePath) {
        return filePath.toLowerCase().endsWith(".json");
    }

    private static boolean isCsvFile(String filePath) {
        return filePath.toLowerCase().endsWith(".csv");
    }
}









