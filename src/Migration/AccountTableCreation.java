package Migration;

import java.sql.*;

public class AccountTableCreation {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/housing_rental";
        String username = "root";
        String password = "anhyeuem1902@";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            if(createAccountTable(conn)) {
                System.out.println("Account table created");
            } else {
                System.out.println("Error creating Account table");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean createAccountTable(Connection conn) {

        try (Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Account (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL UNIQUE," +
                    "role VARCHAR(50) NOT NULL)";

            stmt.executeUpdate(sql);

            // Thêm khóa ngoại từ bảng Tenant
            sql = "ALTER TABLE Tenant ADD FOREIGN KEY (email) REFERENCES Account(email)";
            stmt.executeUpdate(sql);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}