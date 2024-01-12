package Models;

public class Rental {
    private int rentalId;
    private int houseId;
    private String tenantId;
    private float monthlyPayment;

    public Rental(int rentalId, int houseId, String tenantId, String startDate, float monthlyPayment) {
        this.rentalId = rentalId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.monthlyPayment = monthlyPayment;
    }

    public Rental(int houseId, String tenantId) {
        this.houseId = houseId;
        this.tenantId = tenantId;
    }

    // Getters and setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public float getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(float monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}