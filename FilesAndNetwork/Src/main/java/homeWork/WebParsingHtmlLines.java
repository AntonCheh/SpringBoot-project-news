package homeWork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static homeWork.WebParsingHtmlStations.elements;

public class WebParsingHtmlLines {

    static Metro metro = new Metro();
    static Document document;

    static {
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printWebLines() {
        String htmlFile = parseFile(Path.of("data/code.html"));
        Document document = Jsoup.parse(htmlFile);
        Elements elements = document.select("div.js-toggle-depend");

        elements.forEach(element -> {
            String lineNumber = getLineNumber(element);
            String lineName = element.select("span.js-metro-line").text();
            Line line = new Line(lineNumber, lineName);

            // Получаем станции для текущей линии из второго класса
            String stationInfo = getStationInfo(lineNumber);
            addStationsToLine(line, stationInfo);

            metro.addLine(line);
        });

        metro.saveToJsonFile("data/metro.json1");
    }

    private static String getLineNumber(Element element) {
        String lineData = element.attr("data-depend");
        String[] parts = lineData.split("'ln-");
        if (parts.length > 1) {
            return parts[1].split("'")[0];
        } else {
            String dataLine = element.select("span.js-metro-line").attr("data-line");
            return dataLine.isEmpty() ? "N/A" : dataLine;
        }
    }

    private static String parseFile(Path path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
            lines.forEach(line -> builder.append(line).append("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static String getStationInfo(String lineNumber) {
        StringBuilder builder = new StringBuilder();
        elements.forEach(element -> {
            String line = element.attr("data-line");
            if (line.equals(lineNumber)) {
                Elements stations = element.select("p.single-station");
                stations.forEach(station -> {
                    String stationNumber = station.select("span.num").text();
                    String stationName = station.select("span.name").text();
                    builder.append("Номер линии: ").append(stationNumber).append("\n");
                    builder.append("Имя станции: ").append(stationName).append("\n\n");
                });
            }
        });
        return builder.toString();
    }

    private static void addStationsToLine(Line line, String stationInfo) {
        String[] lines = stationInfo.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String lineText = lines[i].trim();

            // Ищем строки с номером линии и именем станции
            if (lineText.startsWith("Номер линии:")) {
                String stationNumber = extractValue(lineText, "Номер линии:");
                String stationName = "";

                // Ищем строку с именем станции в следующей строке
                if (i + 1 < lines.length) {
                    stationName = extractValue(lines[i + 1].trim(), "Имя станции:");
                    // Заменяем все виды пробелов на обычные пробелы
                    stationName = stationName.replaceAll("[\\p{Z}\\s]+", " ").trim();
                }

                Station station = new Station(stationNumber, stationName);
                line.addStation(station);
            }
        }
    }

    private static String extractValue(String line, String prefix) {
        // Извлекаем значение, учитывая префикс
        return line.substring(prefix.length()).trim();
    }
}




