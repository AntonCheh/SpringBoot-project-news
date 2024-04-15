package homeWork;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class WebParsingHtmlStations {

    static Document document;

    static {
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Elements elements = document.select("div.js-metro-stations");

//    public static void main(String[] args) {
//        printWebStations();
//    }

    public static void printWebStations() {
        List<Line> lines = new ArrayList<>();

        elements.forEach(element -> {
            String lineNumber = element.attr("data-line");
            Line line = new Line(lineNumber);
            Elements stations = element.select("p.single-station");

            stations.forEach(station -> {
                String stationName = station.select("span.name").text();
                Station stationObject = new Station(stationName);
                line.addStation(stationObject);
            });

            lines.add(line);
        });

        // Вместо сохранения в JSON выводим станции в консоль
        printStations(lines);
    }

    public static void printStations(List<Line> lines) {
        lines.forEach(line -> {
            System.out.println("Line: " + line.getLineNumber());
            line.getStations().forEach(station -> {
                System.out.println(station.getStationName());
            });
            System.out.println();
        });
    }
}