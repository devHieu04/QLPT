//package Service;
//
//import Controller.DatabaseConnection;
//import Models.House;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class HouseService {
//    private final DatabaseConnection databaseConnector;
//
//    public HouseService(DatabaseConnection databaseConnector) {
//        this.databaseConnector = databaseConnector;
//    }
//
//    public void addHouse(House house) {
//        String query = "INSERT INTO house (area, electricity_bill, water_bill, has_fridge, has_table, has_chair, has_bed) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = databaseConnector.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setDouble(1, house.getArea());
//            preparedStatement.setDouble(2, house.getElectricityBill());
//            preparedStatement.setDouble(3, house.getWaterBill());
//            preparedStatement.setBoolean(4, house.isHasFridge());
//            preparedStatement.setBoolean(5, house.isHasTable());
//            preparedStatement.setBoolean(6, house.isHasChair());
//            preparedStatement.setBoolean(7, house.isHasBed());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateHouse(House house) {
//        String query = "UPDATE house SET area=?, electricity_bill=?, water_bill=?, has_fridge=?, has_table=?, has_chair=?, has_bed=? WHERE id=?";
//        try (Connection connection = databaseConnector.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setDouble(1, house.getArea());
//            preparedStatement.setDouble(2, house.getElectricityBill());
//            preparedStatement.setDouble(3, house.getWaterBill());
//            preparedStatement.setBoolean(4, house.isHasFridge());
//            preparedStatement.setBoolean(5, house.isHasTable());
//            preparedStatement.setBoolean(6, house.isHasChair());
//            preparedStatement.setBoolean(7, house.isHasBed());
//            preparedStatement.setInt(8, house.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteHouse(int houseId) {
//        String query = "DELETE FROM house WHERE id=?";
//        try (Connection connection = databaseConnector.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, houseId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
