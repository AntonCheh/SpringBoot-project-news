package homeWork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
class WebParsingHtmlStations {

          static Document document;
    static  {
        try {
            document = Jsoup.connect("https://skillbox-java.github.io").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static Elements elements = document.select("div.js-metro-stations");
        public static void printWebStations () {

            StringBuilder builder = new StringBuilder();

            elements.forEach(element -> {
                String lineNumber = element.attr("data-line");
                builder.append("Линия метро: ").append(lineNumber).append("\n");

                Elements stations = element.select("p.single-station");
                stations.forEach(station -> {
                    String stationNumber = station.select("span.num").text();
                    String stationName = station.select("span.name").text();

                    builder.append("Номер линии:").append(stationNumber).append("\n");
                    builder.append("Имя станции:").append(stationName).append("\n\n");
                });
            });
            System.out.println(builder.toString());
        }

    }