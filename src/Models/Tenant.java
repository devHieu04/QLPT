package Models;

import java.util.Date;

public class Tenant {
    private String tenantId;
    private String name;
    private String dateOfBirth;
    private String email;
    private String startDate;
    private float electricityUsage;
    private float waterUsage;
    private int house_id;

    public Tenant(String tenantId, String name, String dateOfBirth, String email, String startDate, float electricityUsage, float waterUsage, int house_id) {
        this.tenantId = tenantId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.startDate = startDate;
        this.electricityUsage = electricityUsage;
        this.waterUsage = waterUsage;
        this.house_id = house_id;
    }


    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public float getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(float electricityUsage) {
        this.electricityUsage = electricityUsage;
    }

    public float getWaterUsage() {
        return waterUsage;
    }

    public void setWaterUsage(float waterUsage) {
        this.waterUsage = waterUsage;
    }
// Getters and setters
}

