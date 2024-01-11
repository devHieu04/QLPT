package Controller;

import Models.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalController {
    private Connection connection = DatabaseConnection.getConnection();

    public boolean rentHouse(Rental rental) {
        try {
            String query = "INSERT INTO Rental (house_id, tenant_id) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rental.getHouseId());
            preparedStatement.setString(2, rental.getTenantId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteRentalByTenantId(String tenantId) {
        try {
            String query = "DELETE FROM Rental WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateHouseIdByTenantId(String tenantId, int newHouseId) {
        try {
            String query = "UPDATE Tenant SET house_id = ? WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newHouseId);
            preparedStatement.setString(2, tenantId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRentalByHouseId(int houseId) {
        try {
            String query = "DELETE FROM Rental WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        try {
            String query = "SELECT * FROM Rental";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int rentalId = resultSet.getInt("rental_id");
                int houseId = resultSet.getInt("house_id");
                String tenantId = resultSet.getString("tenant_id");
                String startDate = resultSet.getString("start_date");
                float monthlyPayment = resultSet.getFloat("monthly_payment");

                Rental rental = new Rental(rentalId, houseId, tenantId, startDate, monthlyPayment);
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    // Các phương thức khác như updateRental, deleteRental có thể được thêm vào tương tự
}
