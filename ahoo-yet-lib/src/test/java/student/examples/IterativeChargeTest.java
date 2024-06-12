package student.examples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class IterativeChargeTest {
    private Connection connection;

    @BeforeTest(alwaysRun = true)
    public void setup() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://192.168.136.129/test_data_db?user=postgres&password=qazwsx&ssl=false");
    }

    @Test
    public void testCharge() throws SQLException {
        System.out.println(connection.getClass());
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM charge_test_data\n" +
                "ORDER BY (id) ASC;");

        while (result.next()) {
            System.out.println(result.getString(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
        }
    }

}
