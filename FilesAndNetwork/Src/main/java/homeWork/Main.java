package homeWork;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import com.solidfire.gson.JsonArray;
import com.solidfire.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static String path = FromZipArchive.out + "data";

    public static void main(String[] args) throws IOException {
        FindFolder.walkAndPrintFiles(Path.of(path));
        JsonParsing.parseJsonFiles(FindFolder.pathsOfJsons);
        CsvParsing.parseAndPrint(FindFolder.pathsOfCvs);
        WebParsingHtmlLines.print();
        WebParsingHtmlStations.printWebStations();

    }

    public static void saveLineAndStationListsToJson(List<Line> lines) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        JsonArray linesArray = new JsonArray();

        for (Line line : lines) {
            JsonObject lineObject = new JsonObject();
            lineObject.addProperty("lineNumber", line.getLineNumber());
            lineObject.addProperty("lineName", line.getLineName());
            JsonArray stationsArray = new JsonArray();

            for (Station station : line.getStations()) {
                JsonObject stationObject = new JsonObject();
                stationObject.addProperty("name", station.getStationName());
                stationObject.addProperty("line", line.getLineNumber());
               // stationObject.addProperty("date", station.getDate());
                stationObject.addProperty("depth", station.getDepth());
                stationObject.addProperty("hasConnection", station.hasConnection());
                stationsArray.add(stationObject);
            }

            lineObject.add("stations", stationsArray);
            linesArray.add(lineObject);
        }

        jsonObject.add("lines", linesArray);

        try (FileWriter writer = new FileWriter("stations.json")) {
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


