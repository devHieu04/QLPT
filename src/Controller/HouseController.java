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
            String query = "INSERT INTO House (area, electricity_cost, water_cost, room_cost, furniture) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, house.getArea());
            preparedStatement.setFloat(2, house.getElectricityCost());
            preparedStatement.setFloat(3, house.getWaterCost());
            preparedStatement.setFloat(4, house.getRoomCost());
            preparedStatement.setString(5, house.getFurniture());

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

