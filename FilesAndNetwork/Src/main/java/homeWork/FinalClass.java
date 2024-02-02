package homeWork;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class FinalClass {
       public static void main(String[] args) {


    }

}



    /*
       public static void main(String[] args) throws Exception {

//        CsvParsing.print();
//           System.out.println("CVS...........");
//        WebParsingHtmlLines.printWebLines();
//           System.out.println("printWebLines...........");
//        WebParsingHtmlStations.printWebStations();
//           System.out.println("printWebStations............");
//        JsonParsing.printJsonParsing();
//           System.out.println("printJsonParsing..............");
//        FindFolder.printFindFolder();
//           System.out.println("printFindFolder...........");
//    }

           try {
               // Получаем данные из разных источников
               List<CsvParsing.TypesOfDates> parsedFilesCSV = CsvParsing.parseCSV(CsvParsing.filePath);
               List<WebParsingHtmlLines.Station> parsedFilesHtmlLines = WebParsingHtmlLines.parseWebLines();
               List<WebParsingHtmlStations.Station> parsedFilesHtmlStations = WebParsingHtmlStations.parseWebStations();
               List<JsonParsing.Station> parsedFilesJson = JsonParsing.parseJsonFile();

               // Объединяем списки
               List<Station> stations = mergeStations(parsedFilesHtmlLines, parsedFilesCSV, parsedFilesJson);

               // Конвертируем в JSON и записываем в файл
               saveStationsToJsonFile(stations, "data/stations.json");

               // Другие операции с данными...

           } catch (Exception e) {
               e.printStackTrace();
           }
       }

    private static List<Station> mergeStations(
            List<WebParsingHtmlLines.Station> htmlLines,
            List<CsvParsing.TypesOfDates> csv,
            List<JsonParsing.Station> json) {



        return new ArrayList<>();
    }

    private static void saveStationsToJsonFile(List<Station> stations, String filePath) {

    }


    static class Station {
        private String name;
        private String line;
        private String date;
        private int depth;
        private boolean hasConnection;

        // Конструктор, геттеры и сеттеры, если нужно...

        @Override
        public String toString() {
            // TODO:  формат вывода объекта Station

            return super.toString();
        }

        // Метод для конвертации в JSON
        public String toJson() {
            // TODO:  конвертацию объекта в формат JSON
            //
            return "";
        }
    }
}

     */


