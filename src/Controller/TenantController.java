package Controller;

import Models.Tenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TenantController {
    private Connection connection = DatabaseConnection.getConnection();

    public boolean addTenant(Tenant tenant) {
        try {
            String query = "INSERT INTO Tenant (tenant_id, name, date_of_birth, email, start_date, electricity_usage, water_usage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenant.getTenantId());
            preparedStatement.setString(2, tenant.getName());
            preparedStatement.setString(3, tenant.getDateOfBirth());
            preparedStatement.setString(4, tenant.getEmail());
            preparedStatement.setString(5, tenant.getStartDate());
            preparedStatement.setFloat(6, tenant.getElectricityUsage());
            preparedStatement.setFloat(7, tenant.getWaterUsage());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        try {
            String query = "SELECT * FROM Tenant";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tenantId = resultSet.getString("tenant_id");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String email = resultSet.getString("email");
                String startDate = resultSet.getString("start_date");
                float electricityUsage = resultSet.getFloat("electricity_usage");
                float waterUsage = resultSet.getFloat("water_usage");

                Tenant tenant = new Tenant(tenantId, name, dateOfBirth, email, startDate, electricityUsage, waterUsage);
                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    // Các phương thức khác như updateTenant, deleteTenant có thể được thêm vào tương tự
}
