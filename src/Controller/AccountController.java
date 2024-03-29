package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Account;

public class AccountController {
    private final Connection connect = DatabaseConnection.getConnection();

    public boolean registerAccount(Account account) {
        try {
            String query = "INSERT INTO Account (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
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

    public Account login(String username, String password) {
        try {
            String query = "SELECT role, email FROM Account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                return new Account(email, role);
            } else
                return null; // Trả về 0 nếu không có kết quả hoặc đăng nhập không thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Trả về 0 nếu xảy ra lỗi trong quá trình đăng nhập
        }
    }

    public boolean checkEmailExists(String email) {
        try {
            String query = "SELECT * FROM Account WHERE email = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Trả về true nếu email tồn tại trong cơ sở dữ liệu, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra trong quá trình kiểm tra email
        }
    }
}