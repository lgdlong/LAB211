package Model;

/**
 *
 * @author LGD
 */
public class Order {
    private int id;
    private String customerCode;
    private String feastCode;
    private int numberOfTables;
    private double totalCost;

    public Order(int id, String customerCode, String feastCode, int numberOfTables, double totalCost) {
        this.id = id;
        this.customerCode = customerCode;
        this.feastCode = feastCode;
        this.numberOfTables = numberOfTables;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFeastCode() {
        return feastCode;
    }

    public void setFeastCode(String feastCode) {
        this.feastCode = feastCode;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
