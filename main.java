import com.company.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws SQLException {
        DatabaseConfig.connectTODatabase();
        Connection conn = null;
        try {
            // grab data from CSV
            List<List<String>> data = ReadCSVFile.readCSV("./alex.csv");

            // Normalize data and send to database
            DatabasePop normy = new DatabasePop(conn, data);
            normy.InitializeDatabase();
            normy.inputdata();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}