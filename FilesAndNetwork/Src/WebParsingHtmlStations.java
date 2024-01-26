package Src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class WebParsingHtmlStations {
    public static void main(String[] args) throws IOException {
            Document document = Jsoup.connect("https://skillbox-java.github.io").get();
            Elements elements = document.select("div.js-metro-stations");

            StringBuilder builder = new StringBuilder();

            elements.forEach(element -> {
                String lineNumber = element.attr("data-line");
                builder.append("Линия метро: ").append(lineNumber).append("\n");

                Elements stations = element.select("p.single-station");
                stations.forEach(station -> {
                    String stationNumber = station.select("span.num").text();
                    String stationName = station.select("span.name").text();

                    builder.append("Номер станции: ").append(stationNumber).append("\n");
                    builder.append("Имя станции: ").append(stationName).append("\n\n");
                });
            });
            System.out.println(builder.toString());
        }
    }