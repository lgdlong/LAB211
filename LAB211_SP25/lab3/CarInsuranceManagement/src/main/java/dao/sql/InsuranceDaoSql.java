package dao.sql;

import entity.model.Insurance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsuranceDaoSql {
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

    public List<Insurance> getAll() {
        List<Insurance> insurances = new ArrayList<>();

        String sql = "SELECT * FROM insurances";

        try (Connection conn = getConnection()) {
            if (conn == null) {
                return insurances;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Insurance insurance = new Insurance(
                        rs.getString("id"),
                        rs.getString("licensePlate"),
                        rs.getDate("establishedDate").toLocalDate(),
                        rs.getInt("period"),
                        rs.getDouble("fee"),
                        rs.getString("ownerName")
                    );
                    insurances.add(insurance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insurances;
    }

    public void insert(Insurance insurance) {
        String sql = "INSERT INTO insurances VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            if (conn == null) {
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, insurance.getId());
                stmt.setString(2, insurance.getLicensePlate());
                stmt.setDate(3, Date.valueOf(insurance.getEstablishedDate()));
                stmt.setInt(4, insurance.getPeriod());
                stmt.setDouble(5, insurance.getFee());
                stmt.setString(6, insurance.getOwnerName());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
