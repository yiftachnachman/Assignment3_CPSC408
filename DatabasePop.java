import com.company.DatabaseConfig;

import java.sql.*;
import java.util.List;

public class DatabasePop
{

    private Connection conn;
    private List<List<String>> data;



    public DatabasePop(Connection conn, List<List<String>> data) {
        this.conn = conn;
        this.data = data;
    }

    public void InitializeDatabase() throws SQLException
        {
            clearDatabase();
            List<String> tuples = data.get(0);

            PreparedStatement prep = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Customers(" +
                            "customer_ID INTEGER PRIMARY KEY NOT NULL," +            // customer_ID
                            tuples.get(0) + " VARCHAR(40)," +              // customerFirstName
                            tuples.get(1) + " VARCHAR(40)," +              // customerLastName
                            tuples.get(2) + " INTEGER)"                  // ZIP
            );
            prep.executeUpdate();

            prep = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS PostalCode(" +
                            tuples.get(2)+ "ZIP INTEGER PRIMARY KEY ," +             // ZIP
                            tuples.get(3) + " VARCHAR(40))"                         // city
            );
            prep.executeUpdate();

            prep = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS EmployeeToCustomerSales(" +
                            "customer_ID INTEGER PRIMARY KEY," +                           // customer_ID
                            "emplyee_ID Integer Primary Key )"                              // employee_ID
            );
            prep.executeUpdate();

            prep = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Employee(" +
                            "employee_ID INTEGER PRIMARY KEY AUTO_INCREMENT," +      // employee_ID
                            tuples.get(4) + " VARCHAR(40)," +                       // employeeFirstName
                            tuples.get(5) + " VARCHAR(40))"                         // employeeLastName
            );
            prep.executeUpdate();

            prep = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS SalesOffice(" +
                            "salesoffice_ID INTEGER PRIMARY KEY AUTO_INCREMENT," +            // SalesOffice_ID
                            tuples.get(6) + " VARCHAR(100)," +                              // address
                            tuples.get(7) + " INTEGER)"                                     // phone number
            );
            prep.executeUpdate();


            prep.close();
        }

        public void inputdata() throws SQLException {
            PreparedStatement tuples = null;
            for(int i = 1; i < data.size(); ++i)
            {
                List<String> row = data.get(i);

                // init all row variables
                String customerFirstName = row.get(0);
                String customerLastName = row.get(1);
                String ZIP = row.get(2);
                String city = row.get(3);
                String employeeFirstName = row.get(4);
                String employeeLastName = row.get(5);
                String address = row.get(6);
                String phonenumber = row.get(7);

                tuples = conn.prepareStatement("INSERT INTO Customer(customer_ID, customerFirstName, customerLastName, ZIP) VALUES(?,?,?,?)");
                tuples.setString(0, customerFirstName);
                tuples.setString(1, customerLastName);
                tuples.setInt(2, ZIP);
                tuples.executeUpdate();
                tuples.clearParameters();

                tuples = conn.prepareStatement("INSERT INTO PostalCode(ZIP, city) VALUES(?,?)");
                tuples.setInt(2, ZIP);
                tuples.setString(3, city);
                tuples.executeUpdate();
                tuples.clearParameters();

                tuples = conn.prepareStatement("INSERT INTO EmployeeToCustomerSales(customer_ID, employee_ID) VALUES(?,?)");
                tuples.executeUpdate();
                tuples.clearParameters();

                tuples = conn.prepareStatement("INSERT INTO Employee(employee_ID, employeeFirstName, EmployeeLastName, salesoffice_ID) VALUES(?,?,?,?)");
                tuples.setString(4, employeeFirstName);
                tuples.setString(5, employeeLastName);
                tuples.executeUpdate();
                tuples.clearParameters();

                tuples = conn.prepareStatement("INSERT INTO SalesOffice(salesoffice_ID, address, phonenumber) VALUES(?,?,?)");
                tuples.setString(6, address);
                tuples.setInt(7, phonenumber);
                tuples.executeUpdate();
                tuples.clearParameters();
            }
            assert tuples != null;
            tuples.close();
        }

        // Delete all tables to clear database
        private void clearDatabase() throws SQLException {

            PreparedStatement tuples = conn.prepareStatement("DROP TABLE IF EXISTS Customers");
            tuples.executeUpdate();

            tuples = conn.prepareStatement("DROP TABLE IF EXISTS PostalCode");
            tuples.executeUpdate();

            tuples = conn.prepareStatement("DROP TABLE IF EXISTS EmployeeToCustomerSales");
            tuples.executeUpdate();

            tuples = conn.prepareStatement("DROP TABLE IF EXISTS Employee");
            tuples.executeUpdate();

            tuples = conn.prepareStatement("DROP TABLE IF EXISTS SalesOffice");
            tuples.executeUpdate();


            tuples.close();
        }

        // Query and print all tables from database
        public void printdata() throws SQLException {
            System.out.println("Customers");
            PreparedStatement s = conn.prepareStatement("SELECT * FROM Customers");
            ResultSet rs = s.executeQuery();

            System.out.println("PostalCode");
            s = conn.prepareStatement("SELECT * FROM PostalCode");
            rs = s.executeQuery();


            System.out.println("EmployeeToCustomerSales");
            s = conn.prepareStatement("SELECT * FROM EmployeeToCustomerSales");
            rs = s.executeQuery();


            System.out.println("Employee");
            s = conn.prepareStatement("SELECT * FROM Employee");
            rs = s.executeQuery();


            System.out.println("SalesOffice");
            s = conn.prepareStatement("SELECT * FROM SalesOffice");
            rs = s.executeQuery();


            s.close();
            rs.close();
        }

    }

