package model;

import repository.itf.IFeastRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author LGD
 */
public class Order {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private String id;
    private String customerCode;
    private String feastCode;
    private int numberOfTables;
    private LocalDate date;
    private double totalCost;

    // constructor to take data from db
    public Order(String id, String customerCode, String feastCode, int numberOfTables, LocalDate date, double totalCost) {
        this.id = id;
        this.customerCode = customerCode;
        this.feastCode = feastCode;
        this.numberOfTables = numberOfTables;
        this.date = date;
        this.totalCost = totalCost;
    }

    // constructor to create object
    public Order(String customerCode, String feastCode, int numberOfTables, LocalDate date, IFeastRepository fr) {
        this.customerCode = customerCode;
        this.feastCode = feastCode;
        this.date = date;
        this.numberOfTables = numberOfTables;
        setTotalCost(totalCost, fr);
        this.id = generateId();
    }

    public static DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }
    
    public String getId() {
        return id;
    }

    private String generateId() {
        return String.join("-", customerCode, feastCode, date.format(FORMATTER));
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getFeastCode() {
        return feastCode;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost, IFeastRepository fr) {
        this.totalCost = numberOfTables * getPriceByFeastCode(feastCode, fr);
    }
    
    @Override
    public String toString() {
        return String.format("%-22s | %-11s | %-10s | %-10s | %-6d | %-12.2f", id, date, customerCode, feastCode, numberOfTables, totalCost);
    }

    private double getPriceByFeastCode(String code, IFeastRepository fr) {
        for (Feast f : fr.getFeastList()) {
            if (f.getCode().equalsIgnoreCase(code)) {
                return f.getPrice();
            }
        }
        return 0.0;
    }
}
