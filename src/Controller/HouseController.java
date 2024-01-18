package Controller;

import Models.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseController {
    private Connection connection = DatabaseConnection.getConnection();

    public boolean addHouse(House house) {
        try {
            String query = "INSERT INTO House (area, room_cost, furniture, house_status) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, house.getArea());
            preparedStatement.setFloat(2, house.getRoomCost());
            preparedStatement.setString(3, house.getFurniture());
            preparedStatement.setInt(4, house.getHouse_status());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUtilityCost(int houseId, float electricityUsage, float waterUsage) {
        try {
            String query = "UPDATE House SET electricity_cost = ?, water_cost = ? WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Tính toán giá trị tiền điện và nước
            float electricCost = electricityUsage * 3500;
            float waterCost = waterUsage * 10000;

            preparedStatement.setFloat(1, electricCost);
            preparedStatement.setFloat(2, waterCost);
            preparedStatement.setInt(3, houseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public House getHouse(int house_id)  {
        String query = "SELECT * FROM House WHERE house_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, house_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                float area = resultSet.getFloat("area");
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");
                float roomCost = resultSet.getFloat("room_cost");
                String furniture = resultSet.getString("furniture");
                int house_status = resultSet.getInt("house_status");

                House house = new House(house_id, area, electricityCost, waterCost, roomCost, furniture, house_status);
                return house;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public float calculateTotalRentCost(int houseId) {
        try {
            String query = "SELECT electricity_cost, water_cost, room_cost FROM House WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");
                float roomCost = resultSet.getFloat("room_cost");

                // Tính tổng tiền nhà
                return electricityCost + waterCost + roomCost;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu có lỗi xảy ra
    }


    public boolean updateHouse(House house) {
        try {
            String query = "UPDATE House SET area = ?, room_cost = ?, furniture = ?, house_status = ? WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, house.getArea());
            preparedStatement.setFloat(2, house.getRoomCost());
            preparedStatement.setString(3, house.getFurniture());
            preparedStatement.setInt(4, house.getHouse_status());
            preparedStatement.setInt(5, house.getHouseId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<House> findHousesByArea(float minArea, float maxArea) {
        List<House> resultHouses = new ArrayList<>();
        try {
            String query = "SELECT * FROM House WHERE area BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, minArea);
            preparedStatement.setFloat(2, maxArea);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                float area = resultSet.getFloat("area");
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");
                float roomCost = resultSet.getFloat("room_cost");
                String furniture = resultSet.getString("furniture");
                int house_status = resultSet.getInt("house_status");

                House house = new House(houseId, area, electricityCost, waterCost, roomCost, furniture, house_status);
                resultHouses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultHouses;
    }
    public List<House> getSortedHousesByArea() {
        List<House> sortedHouses = new ArrayList<>();
        try {
            String query = "SELECT * FROM House ORDER BY area DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                float area = resultSet.getFloat("area");
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");
                float roomCost = resultSet.getFloat("room_cost");
                String furniture = resultSet.getString("furniture");
                int house_status = resultSet.getInt("house_status");

                House house = new House(houseId, area, electricityCost, waterCost, roomCost, furniture, house_status);
                sortedHouses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedHouses;
    }

    public boolean updateHouseStatus(int houseId, int newStatus) {
        try {
            String query = "UPDATE House SET house_status = ? WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newStatus);
            preparedStatement.setInt(2, houseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Integer> getUnoccupiedHouseIDs() {
        List<Integer> houseIDs = new ArrayList<>();
        try {
            String query = "SELECT house_id FROM House WHERE house_status = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                houseIDs.add(houseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseIDs;
    }
    public boolean deleteHouseById(int houseId) {
        try {
            String query = "DELETE FROM House WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getHouseStatusByHouseId(int houseId) {
        int houseStatus = -1; // Giá trị mặc định nếu không tìm thấy
        try {
            String query = "SELECT house_status FROM House WHERE house_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, houseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                houseStatus = resultSet.getInt("house_status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseStatus;
    }


    public List<House> getAllHouses() {
        List<House> houses = new ArrayList<>();
        try {
            String query = "SELECT * FROM House";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                float area = resultSet.getFloat("area");
                float electricityCost = resultSet.getFloat("electricity_cost");
                float waterCost = resultSet.getFloat("water_cost");
                float roomCost = resultSet.getFloat("room_cost");
                String furniture = resultSet.getString("furniture");
                int house_status = resultSet.getInt("house_status");

                House house = new House(houseId, area, electricityCost, waterCost, roomCost, furniture, house_status);
                houses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houses;
    }

}

