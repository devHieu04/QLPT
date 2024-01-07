package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Account;

public class AccountController {
    private Connection connection = DatabaseConnection.getConnection();

    public boolean registerAccount(Account account) {
        try {
            String query = "INSERT INTO Account (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, "user"); // Đặt mặc định role là "user"

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int login(String username, String password) {
        try {
            String query = "SELECT role FROM Account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if (role.equalsIgnoreCase("admin")) {
                    return 2; // Trả về 2 nếu đăng nhập là admin
                } else {
                    return 1; // Trả về 1 nếu đăng nhập là user thông thường
                }
            } else {
                return 0; // Trả về 0 nếu không có kết quả hoặc đăng nhập không thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Trả về 0 nếu xảy ra lỗi trong quá trình đăng nhập
        }
    }
    public boolean checkEmailExists(String email) {
        try {
            String query = "SELECT * FROM Account WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Trả về true nếu email tồn tại trong cơ sở dữ liệu, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra trong quá trình kiểm tra email
        }
    }


}
