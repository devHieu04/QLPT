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
            String query = "INSERT INTO Tenant (tenant_id, name, date_of_birth, email, start_date, electricity_usage, water_usage,house_id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenant.getTenantId());
            preparedStatement.setString(2, tenant.getName());
            preparedStatement.setString(3, tenant.getDateOfBirth());
            preparedStatement.setString(4, tenant.getEmail());
            preparedStatement.setString(5, tenant.getStartDate());
            preparedStatement.setFloat(6, tenant.getElectricityUsage());
            preparedStatement.setFloat(7, tenant.getWaterUsage());
            preparedStatement.setInt(8, tenant.getHouse_id());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateElectricWaterUsageToZero(String tenantId) {
        try {
            String query = "UPDATE Tenant SET electricity_usage = 0, water_usage = 0 WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTenantByHouseId(int houseId) {
        try {
            String query = "DELETE FROM Tenant WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Tenant findTenantByIdOrEmail(String keyword) {
        Tenant tenant = null;
        try {
            String query = "SELECT * FROM Tenant WHERE tenant_id = ? OR email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String tenantId = resultSet.getString("tenant_id");
                String name = resultSet.getString("name");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String email = resultSet.getString("email");
                String startDate = resultSet.getString("start_date");
                float electricityUsage = resultSet.getFloat("electricity_usage");
                float waterUsage = resultSet.getFloat("water_usage");
                int house_id = resultSet.getInt("house_id");

                tenant = new Tenant(tenantId, name, dateOfBirth, email, startDate, electricityUsage, waterUsage, house_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenant;
    }

    public boolean updateTenant(Tenant tenant) {
        try {
            String query = "UPDATE Tenant SET name = ?, date_of_birth = ?, email = ?, start_date = ?, electricity_usage = ?, water_usage = ?, house_id = ? WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenant.getName());
            preparedStatement.setString(2, tenant.getDateOfBirth());
            preparedStatement.setString(3, tenant.getEmail());
            preparedStatement.setString(4, tenant.getStartDate());
            preparedStatement.setFloat(5, tenant.getElectricityUsage());
            preparedStatement.setFloat(6, tenant.getWaterUsage());
            preparedStatement.setInt(7, tenant.getHouse_id());
            preparedStatement.setString(8, tenant.getTenantId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailExists(String email) {
        try {
            String query = "SELECT * FROM Tenant WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Trả về true nếu có tồn tại email, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Xử lý ngoại lệ và trả về false nếu có lỗi xảy ra
        }
    }


    public boolean isTenantIdExists(String tenantId) {
        try {
            String query = "SELECT * FROM Tenant WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Trả về true nếu có tồn tại tenant_id, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Xử lý ngoại lệ và trả về false nếu có lỗi xảy ra
        }
    }

    public int getHouseIdByTenantId(String tenantId) {
        int houseId = -1; // Giá trị mặc định nếu không tìm thấy

        try {
            String query = "SELECT house_id FROM Tenant WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                houseId = resultSet.getInt("house_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houseId;
    }

    public boolean deleteTenant(String tenantId) {
        try {
            String query = "DELETE FROM Tenant WHERE tenant_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tenantId);

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
                int house_id = resultSet.getInt("house_id");

                Tenant tenant = new Tenant(tenantId, name, dateOfBirth, email, startDate, electricityUsage, waterUsage,house_id);
                tenants.add(tenant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenants;
    }

    // Các phương thức khác như updateTenant, deleteTenant có thể được thêm vào tương tự
}
