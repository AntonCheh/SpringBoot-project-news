import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "skill2403BOX@";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
                        dbName + "?user="
                        + dbUser + "&password="
                        + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
                connection.createStatement().execute("DROP TABLE IF EXISTS visit_time");
                connection.createStatement().execute("CREATE TABLE visit_time(" +
                        "station INT NOT NULL, " +
                        "time BIGINT NOT NULL)");
                System.out.println("Connection established successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void addVisitTime(int station, long time) throws SQLException {
        String sql = "INSERT INTO visit_time(station, time) VALUES (?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, station);
            pstmt.setLong(2, time);
            pstmt.addBatch();
            pstmt.executeBatch();
        }
    }

    public void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES (?, ?, 1) " +
                "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, birthDay);
            pstmt.executeUpdate();
        }
    }

    public void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        try (ResultSet rs = getConnection().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("\t" + rs.getString("name") + " (" +
                        rs.getString("birthDate") + ") - " + rs.getInt("count"));
            }
        }
    }
}