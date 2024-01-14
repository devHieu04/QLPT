package Migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TenantTableCreation {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/housing_rental";
        String username = "root";
        String password = "anhyeuem1902@";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            if (createTenantTable(conn)) {
                System.out.println("Tenant table created successfully!");
            } else {
                System.out.println("Error creating Tenant table");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean createTenantTable(Connection conn) {

        try (Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Tenant (" +
                    "tenant_id VARCHAR(20) PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "date_of_birth VARCHAR(20)," +
                    "email VARCHAR(100)," +
                    "start_date VARCHAR(20)," +
                    "electricity_usage FLOAT," +
                    "water_usage FLOAT," +
                    "house_id INT," +
                    "FOREIGN KEY (house_id) REFERENCES House(house_id))";

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}