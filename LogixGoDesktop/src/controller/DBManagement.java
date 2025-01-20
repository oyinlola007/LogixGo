package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DeliveryDetails;
import model.Driver;
import model.ProductDetails;
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

	public String[] getAllProductNames() throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT prd_name FROM product_prd";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		List<String> productNames = new ArrayList<>();

		while (rs.next()) {
			productNames.add(rs.getString("prd_name"));
		}

		conn.close();
		return productNames.toArray(new String[0]);
	}

	public int getProductWeight(String productName) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT prd_unit_weight FROM product_prd WHERE prd_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productName);

		ResultSet rs = pstmt.executeQuery();
		int weight = -1;
		if (rs.next()) {
			weight = rs.getInt("prd_unit_weight");
		}

		conn.close();
		return weight;
	}

	public int getProductIdByName(String productName) throws SQLException {
		Connection conn = createConnection();
		String sql = "SELECT prd_id FROM product_prd WHERE prd_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productName);

		ResultSet rs = pstmt.executeQuery();
		int productId = -1;
		if (rs.next()) {
			productId = rs.getInt("prd_id");
		}
		conn.close();
		return productId;
	}

	public void insertDeliveryAndItems(ArrayList<String> selectedProducts, int userId, String deliveryDate,
			String address1, String address2, String city, String region, String zipCode, String status)
			throws SQLException {
		Connection conn = createConnection();
		conn.setAutoCommit(false); // Begin transaction

		try {
			// Insert into delivery table
			String deliverySql = "INSERT INTO delivery_dlv (dlv_usr_id, dlv_date, dlv_address_line_1, dlv_address_line_2, dlv_city, dlv_region, dlv_zip_code, dlv_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement deliveryStmt = conn.prepareStatement(deliverySql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			deliveryStmt.setInt(1, userId);
			deliveryStmt.setString(2, deliveryDate);
			deliveryStmt.setString(3, address1);
			deliveryStmt.setString(4, address2);
			deliveryStmt.setString(5, city);
			deliveryStmt.setString(6, region);
			deliveryStmt.setString(7, zipCode);
			deliveryStmt.setString(8, status);
			deliveryStmt.executeUpdate();

			// Get the generated delivery ID
			ResultSet rs = deliveryStmt.getGeneratedKeys();
			int deliveryId = -1;
			if (rs.next()) {
				deliveryId = rs.getInt(1);
			}

			// Insert into delivery_item table
			String deliveryItemSql = "INSERT INTO delivery_item_dli (dli_delivery_id, dli_product_id, dli_total_weight) VALUES (?, ?, ?)";
			PreparedStatement deliveryItemStmt = conn.prepareStatement(deliveryItemSql);

			for (String entry : selectedProducts) {
				String[] parts = entry.split(", ");
				String productName = parts[0];
				int quantity = Integer.parseInt(parts[1]);

				int productId = getProductIdByName(productName); // Get product ID
				if (productId == -1) {
					throw new SQLException("Product not found: " + productName);
				}

				int productWeight = getProductWeight(productName); // Get product weight
				int totalWeight = productWeight * quantity; // Calculate total weight

				deliveryItemStmt.setInt(1, deliveryId);
				deliveryItemStmt.setInt(2, productId);
				deliveryItemStmt.setInt(3, totalWeight);
				deliveryItemStmt.addBatch(); // Batch the inserts for efficiency
			}

			deliveryItemStmt.executeBatch(); // Execute all batched inserts
			conn.commit(); // Commit transaction
			System.out.println("Delivery and items inserted successfully.");

		} catch (SQLException e) {
			conn.rollback(); // Rollback transaction on failure
			throw e;
		} finally {
			conn.setAutoCommit(true); // Restore default commit behavior
			conn.close();
		}
	}

	public ArrayList<String> getCustomerDeliveries(int userId) throws SQLException {
		ArrayList<String> deliveries = new ArrayList<>();
		Connection conn = createConnection();

		String sql = "SELECT d.dlv_id, d.dlv_date, d.dlv_status, " + "SUM(di.dli_total_weight) AS total_weight "
				+ "FROM delivery_dlv d " + "JOIN delivery_item_dli di ON d.dlv_id = di.dli_delivery_id "
				+ "JOIN product_prd p ON di.dli_product_id = p.prd_id " + "WHERE d.dlv_usr_id = ? "
				+ "GROUP BY d.dlv_id, d.dlv_date, d.dlv_status " + "ORDER BY d.dlv_date DESC";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userId);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			int deliveryId = rs.getInt("dlv_id");
			String deliveryDate = rs.getString("dlv_date");
			int totalWeight = rs.getInt("total_weight");
			String status = rs.getString("dlv_status");

			deliveries.add(deliveryId + ", " + deliveryDate + ", " + totalWeight + ", " + status);
		}

		conn.close();
		return deliveries;
	}

	public DeliveryDetails getDeliveryDetails(int deliveryId) throws SQLException {
		DeliveryDetails deliveryDetails = new DeliveryDetails();
		List<ProductDetails> products = new ArrayList<>();
		Connection conn = createConnection();

		try {
			// Query to get delivery details
			String deliverySql = "SELECT d.dlv_date, d.dlv_status, d.dlv_address_line_1, d.dlv_zip_code, d.dlv_city, "
					+ "SUM(di.dli_total_weight) AS total_weight, SUM(di.dli_total_weight / p.prd_unit_weight) AS total_quantity "
					+ "FROM delivery_dlv d " + "JOIN delivery_item_dli di ON d.dlv_id = di.dli_delivery_id "
					+ "JOIN product_prd p ON di.dli_product_id = p.prd_id " + "WHERE d.dlv_id = ? "
					+ "GROUP BY d.dlv_date, d.dlv_status, d.dlv_address_line_1, d.dlv_zip_code, d.dlv_city";

			PreparedStatement deliveryStmt = conn.prepareStatement(deliverySql);
			deliveryStmt.setInt(1, deliveryId);

			ResultSet rsDelivery = deliveryStmt.executeQuery();
			if (rsDelivery.next()) {
				deliveryDetails.setDate(rsDelivery.getString("dlv_date"));
				deliveryDetails.setStatus(rsDelivery.getString("dlv_status"));
				deliveryDetails.setAddressLine1(rsDelivery.getString("dlv_address_line_1"));
				deliveryDetails.setZipCode(rsDelivery.getString("dlv_zip_code"));
				deliveryDetails.setCity(rsDelivery.getString("dlv_city"));
				deliveryDetails.setTotalWeight(rsDelivery.getInt("total_weight"));
				deliveryDetails.setTotalQuantity(rsDelivery.getInt("total_quantity"));
			}

			// Query to get individual product details
			String productSql = "SELECT p.prd_name, di.dli_total_weight " + "FROM delivery_item_dli di "
					+ "JOIN product_prd p ON di.dli_product_id = p.prd_id " + "WHERE di.dli_delivery_id = ?";

			PreparedStatement productStmt = conn.prepareStatement(productSql);
			productStmt.setInt(1, deliveryId);

			ResultSet rsProduct = productStmt.executeQuery();
			while (rsProduct.next()) {
				String productName = rsProduct.getString("prd_name");
				Integer productWeight = rsProduct.getInt("dli_total_weight");
				products.add(new ProductDetails(productName, productWeight));
			}

			deliveryDetails.setProducts(products);

		} finally {
			conn.close();
		}

		return deliveryDetails;
	}
}
