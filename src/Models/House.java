package Models;

public class House {
    private int houseId;
    private float area;
    private float electricityCost;
    private float waterCost;
    private float roomCost;
    private String furniture;
    private int house_status;


    public House(int houseId, float area, float electricityCost, float waterCost, float roomCost, String furniture, int house_status) {
        this.houseId = houseId;
        this.area = area;
        this.electricityCost = electricityCost;
        this.waterCost = waterCost;
        this.roomCost = roomCost;
        this.furniture = furniture;
        this.house_status = house_status;
    }

    public House(float area, float roomCost, String furniture) {
        this.area = area;
        this.roomCost = roomCost;
        this.furniture = furniture;
    }

    public int getHouse_status() {
        return house_status;
    }

    public void setHouse_status(int house_status) {
        this.house_status = house_status;
    }

    // Getters and setters
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(float electricityCost) {
        this.electricityCost = electricityCost;
    }

    public float getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(float waterCost) {
        this.waterCost = waterCost;
    }

    public float getRoomCost() {
        return roomCost;
    }

    public void setRoomCost(float roomCost) {
        this.roomCost = roomCost;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }
}
