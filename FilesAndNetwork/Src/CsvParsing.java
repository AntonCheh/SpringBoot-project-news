package Src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParsing {
    public static String filePath = "/Users/User/Desktop/resultdata/4/6/dates-1.csv";
    public static int count = 0;

    public static void main(String[] args) {
        print();
    }

    public static void print() {
        List<TypesOfDates> parsedFilesCVS = parseCSV(filePath);

        for (int i = 0; i < parsedFilesCVS.size(); i++) {
            TypesOfDates obj = parsedFilesCVS.get(i);
            System.out.print((i + 1) + " - ");
            System.out.println(obj);
        }
    }

    public static List<TypesOfDates> parseCSV(String filePath) {
        List<TypesOfDates> information = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean titleSkip = false; // флаг, чтобы пропустить первую строку, если она содержит заголовки

            while ((line = reader.readLine()) != null) {
                if (!titleSkip) {
                    titleSkip = true;
                    continue; // пропустить первую строку с заголовками
                }
                String[] fields = line.split(",");
                if (fields.length == 2) {
                    // Создаем объект и добавляем его в список
                    TypesOfDates typesOfDates = new TypesOfDates(fields[0], parseDate(fields[1]));
                    information.add(typesOfDates);
                    count++;
                } else {
                    System.err.println("Некорректное количество полей в строке: " + line);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return information;
    }

    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}

class TypesOfDates {
    private String name;
    private LocalDate date;

    public TypesOfDates(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return name + ' ' + date;
    }
}
