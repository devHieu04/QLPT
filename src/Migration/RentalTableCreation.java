package Migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalTableCreation {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/housing_rental";
        String username = "root";
        String password = "anhyeuem1902@";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            if (createRentalTable(conn)) {
                System.out.println("Rental table created successfully!");
            } else {
                System.out.println("Error creating Rental table");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean createRentalTable(Connection conn) {

        try (Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Rental (" +
                    "rental_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "house_id INT," +
                    "tenant_id VARCHAR(20)," +
                    "monthly_payment FLOAT," +
                    "FOREIGN KEY (house_id) REFERENCES House(house_id)," +
                    "FOREIGN KEY (tenant_id) REFERENCES Tenant(tenant_id))";

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}