package dao.sql;

import entity.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoSql {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CarInsuranceManagement;trustServerCertificate=true;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Cannot connect to the database: " + e.getMessage());
            return null;
        }
    }

    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection conn = getConnection()) {
            if (conn == null) { // checking null in connection
                return cars;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Car car = new Car(
                            rs.getString("licensePlate"),
                            rs.getString("ownerName"),
                            rs.getString("ownerPhone"),
                            rs.getString("brand"),
                            rs.getLong("price"),
                            rs.getDate("registrationDate").toLocalDate(),
                            rs.getString("registrationPlace"),
                            rs.getInt("seatCount")
                    );
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void insert(Car car) {
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            if (conn == null) { // checking null in connection
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, car.getLicensePlate());
                stmt.setString(2, car.getOwnerName());
                stmt.setString(3, car.getOwnerPhone());
                stmt.setString(4, car.getBrand());
                stmt.setLong(5, car.getPrice());
                stmt.setDate(6, Date.valueOf(car.getRegistrationDate()));
                stmt.setString(7, car.getRegistrationPlace());
                stmt.setInt(8, car.getSeatCount());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Car oldCar, Car newCar) {
        String sql = "UPDATE cars SET ownerName = ?, ownerPhone = ?, brand = ?, price = ?, registrationDate = ?, registrationPlace = ?, seatCount = ? WHERE licensePlate = ?";

        try (Connection conn = getConnection()) {
            if (conn == null) { // checking null in connection
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newCar.getOwnerName());
                stmt.setString(2, newCar.getOwnerPhone());
                stmt.setString(3, newCar.getBrand());
                stmt.setLong(4, newCar.getPrice());
                stmt.setDate(5, Date.valueOf(newCar.getRegistrationDate()));
                stmt.setString(6, newCar.getRegistrationPlace());
                stmt.setInt(7, newCar.getSeatCount());
                stmt.setString(8, oldCar.getLicensePlate());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}