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
class WebParsingHtmlStations {

    static Document document;

    static {
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Elements elements = document.select("div.js-metro-stations");

    public static void main(String[] args) {
        printWebStations();
    }

    public static void printWebStations() {
        List<Line> lines = new ArrayList<>();

        elements.forEach(element -> {
            String lineNumber = element.attr("data-line");
            Line line = new Line(lineNumber);
            Elements stations = element.select("p.single-station");

            stations.forEach(station -> {
                // String stationNumber = station.select("span.num").text();
                String stationName = station.select("span.name").text();
                Station stationObject = new Station(stationName);
                line.addStation(stationObject);
            });

            lines.add(line);
        });

        saveToJsonFile(lines, "data/metro.json");
    }

    public static void saveToJsonFile(List<Line> lines, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        JsonObject stationsObject = new JsonObject();

        for (Line line : lines) {
            JsonArray stationsArray = new JsonArray();

            for (Station station : line.getStations()) {
                stationsArray.add(station.getStationName());
            }

            stationsObject.add(line.getLineNumber(), stationsArray);
        }

        jsonObject.add("stations", stationsObject);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(jsonObject, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}