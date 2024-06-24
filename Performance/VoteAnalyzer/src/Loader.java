import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static DBConnection dbConnection;

    public static void main(String[] args) {
        String fileName = "C:/Users/User/IdeaProjects/java_basics/Performance/VoteAnalyzer/res/data-1M.xml";
        dbConnection = new DBConnection();

        // Ensure the connection is established before proceeding
        if (DBConnection.getConnection() == null) {
            System.err.println("Failed to establish database connection!");
            return;
        }

        try {
            parseFile(fileName);
            dbConnection.printVoterCounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            private StringBuilder currentData;
            private Voter currentVoter;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                currentData = new StringBuilder();
                if (qName.equals("voter")) {
                    String name = attributes.getValue("name");
                    String birthDayStr = attributes.getValue("birthDay");
                    Date birthDay = null;
                    try {
                        birthDay = birthDayFormat.parse(birthDayStr);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    currentVoter = new Voter(name, birthDay);
                } else if (qName.equals("visit")) {
                    String stationStr = attributes.getValue("station");
                    Integer station = Integer.parseInt(stationStr);
                    String timeStr = attributes.getValue("time");
                    try {
                        Date time = visitDateFormat.parse(timeStr);
                        dbConnection.countVoter(currentVoter.getName(), birthDayFormat.format(currentVoter.getBirthDay()));
                        dbConnection.addVisitTime(station, time.getTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (currentData != null) {
                    currentData.append(new String(ch, start, length));
                }
            }
        };

        saxParser.parse(new File(fileName), handler);
    }
}