package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Driver;
import model.User;

public class DBManagement {

	private Connection createConnection() throws SQLException {
		String url = "jdbc:MySQL://localhost:3306/";
		String dbname = "LogixGo";
		String user = "root";
		String password = "";
		Connection conn = DriverManager.getConnection(url + dbname, user, password);
		return conn;
	}

	public boolean doesEmailExist(String email) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT 1 FROM user_usr WHERE usr_email = ? LIMIT 1";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);

		ResultSet rs = pstmt.executeQuery();
		boolean exists = rs.next();

		conn.close();
		return exists;
	}

	public int insertUser(String firstName, String lastName, String email, String phoneNumber, String password,
			String role) throws SQLException {
		Connection conn = createConnection();
		String sql = "INSERT INTO user_usr (usr_first_name, usr_last_name, usr_email, usr_phone_number, usr_password, usr_role) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, email);
		pstmt.setString(4, phoneNumber);
		pstmt.setString(5, password);
		pstmt.setString(6, role);

		pstmt.executeUpdate();

		ResultSet rs = pstmt.getGeneratedKeys();
		int userId = -1;
		if (rs.next()) {
			userId = rs.getInt(1);
		}

		System.out.println("User inserted successfully with ID: " + userId);

		conn.close();
		return userId;
	}

	public void insertDriver(int driverId, String truckRegistration, int truckCapacity) throws SQLException {
		Connection conn = createConnection();
		String sql = "INSERT INTO driver_drv (drv_id, drv_truck_registration, drv_truck_capacity) VALUES (?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, driverId);
		pstmt.setString(2, truckRegistration);
		pstmt.setInt(3, truckCapacity);

		pstmt.executeUpdate();
		System.out.println("Driver inserted successfully.");

		conn.close();
	}

	public int validateEmailAndPassword(String email, String password) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT usr_id FROM user_usr WHERE usr_email = ? AND usr_password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();
		int userId = -1;
		if (rs.next()) {
			userId = rs.getInt("usr_id");
		}

		conn.close();
		return userId;
	}

	public User getUserById(int userId) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT usr_id, usr_first_name, usr_last_name, usr_email, usr_phone_number, usr_role FROM user_usr WHERE usr_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userId);

		ResultSet rs = pstmt.executeQuery();
		User user = null;
		if (rs.next()) {
			user = new User(rs.getInt("usr_id"), rs.getString("usr_first_name"), rs.getString("usr_last_name"),
					rs.getString("usr_email"), rs.getString("usr_phone_number"), rs.getString("usr_role"));
		}

		conn.close();
		return user;
	}

	public Driver getDriverById(int driverId) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT drv_id, drv_truck_registration, drv_truck_capacity FROM driver_drv WHERE drv_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, driverId);

		ResultSet rs = pstmt.executeQuery();
		Driver driver = null;
		if (rs.next()) {
			driver = new Driver(rs.getInt("drv_id"), rs.getString("drv_truck_registration"),
					rs.getInt("drv_truck_capacity"));
		}

		conn.close();
		return driver;
	}

	public void updateUserEmailAndPhone(int userId, String email, String phoneNumber) throws SQLException {
		Connection conn = createConnection();
		String sql = "UPDATE user_usr SET usr_email = ?, usr_phone_number = ? WHERE usr_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, phoneNumber);
		pstmt.setInt(3, userId);

		int rowsUpdated = pstmt.executeUpdate();
		System.out.println(rowsUpdated + " row(s) updated in user table.");

		conn.close();
	}

	public void updateDriverTruckDetails(int driverId, String truckRegistration, int truckCapacity)
			throws SQLException {
		Connection conn = createConnection();
		String sql = "UPDATE driver_drv SET drv_truck_registration = ?, drv_truck_capacity = ? WHERE drv_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, truckRegistration);
		pstmt.setInt(2, truckCapacity);
		pstmt.setInt(3, driverId);

		int rowsUpdated = pstmt.executeUpdate();
		System.out.println(rowsUpdated + " row(s) updated in driver table.");

		conn.close();
	}

	public void updateUserPassword(int userId, String password) throws SQLException {
		Connection conn = createConnection();
		String sql = "UPDATE user_usr SET usr_password = ? WHERE usr_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, userId);

		int rowsUpdated = pstmt.executeUpdate();
		System.out.println(rowsUpdated + " row(s) updated in user table for password.");

		conn.close();
	}

}
