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
    public boolean isTenantIdExists(String tenantId) {
        try {
            String query = "SELECT COUNT(*) FROM Rental WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateMonthlyPaymentByTenantId(String tenantId, float newMonthlyPayment) {
        try {
            // Cập nhật lại cột monthly_payment trong bảng Rental dựa trên tenant_id
            String query = "UPDATE Rental SET monthly_payment = ? WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, newMonthlyPayment);
            preparedStatement.setString(2, tenantId);

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
    public boolean updateMonthlyPaymentByHouseId(int houseId) {
        try {
            // Lấy thông tin về giá phòng, tiền điện và tiền nước từ bảng House
            String query = "SELECT room_cost, electricity_cost, water_cost FROM House WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                float roomCost = resultSet.getFloat("room_cost");
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");

                // Tổng tiền nhà bao gồm tiền phòng, tiền điện và tiền nước
                float totalRent = roomCost + electricityCost + waterCost;

                // Cập nhật lại cột monthly_payment trong bảng Rental
                String updateRentalQuery = "UPDATE Rental SET monthly_payment = ? WHERE house_id = ?";
                PreparedStatement updateRentalStatement = connection.prepareStatement(updateRentalQuery);
                updateRentalStatement.setFloat(1, totalRent);
                updateRentalStatement.setInt(2, houseId);

                int rowsAffected = updateRentalStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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


    public List<Rental> findRentalsByTenantId(String tenantId) {
        List<Rental> resultRentals = new ArrayList<>();
        try {
            String query = "SELECT * FROM Rental WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int rentalId = resultSet.getInt("rental_id");
                int houseId = resultSet.getInt("house_id");
                float monthlyPayment = resultSet.getFloat("monthly_payment");

                Rental rental = new Rental(rentalId, houseId, tenantId, monthlyPayment);
                resultRentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultRentals;
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
                float monthlyPayment = resultSet.getFloat("monthly_payment");

                Rental rental = new Rental(rentalId, houseId, tenantId, monthlyPayment);
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

}
