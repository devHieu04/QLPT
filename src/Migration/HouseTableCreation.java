package Migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HouseTableCreation {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/housing_rental";
        String username = "root";
        String password = "anhyeuem1902@";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (createHouseTable(conn)) {
                System.out.println("House table created successfully!");
            } else {
                System.out.println("Error creating House table");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean createHouseTable(Connection conn) {

        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS House (" +
                    "house_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "area FLOAT," +
                    "electricity_cost FLOAT," +
                    "water_cost FLOAT," +
                    "room_cost FLOAT," +
                    "furniture VARCHAR(100)," +
                    "house_status INT)";

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}