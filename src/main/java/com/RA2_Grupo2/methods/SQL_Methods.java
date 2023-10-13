package com.RA2_Grupo2.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.RA2_Grupo2.objects.Employee;
import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Supplier;
import com.RA2_Grupo2.objects.Transaction;

public class SQL_Methods {

	static Connection connection;
	private static String host = "jdbc:mysql://localhost/ra2_grupo2";
	private static String user = "root";
	private static String pass = "";

	public static boolean startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(host, user, pass);

			return true;
		} catch (SQLException ex) {
		} catch (ClassNotFoundException e) {
		}

		System.out.println("Connection failed!");
		return false;
	}

	public static int getMaxIdFromTable(String tableName) throws SQLException {
		PreparedStatement st = connection.prepareStatement("SELECT MAX(id) from " + tableName);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}

	public static Product getProductFromId(int id) throws SQLException {
		PreparedStatement st = connection
				.prepareStatement("SELECT * from products WHERE id = '" + String.valueOf(id) + "'");
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			Product p = new Product();
			p.setId(id);
			p.setQuantity(rs.getInt("quantity"));
			p.setPrice(rs.getFloat("price"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setCategory(rs.getString("category"));
			p.setImage(rs.getString("image"));
			p.setDeleted(rs.getInt("deleted"));
			return p;
		} else
			return null;
	}

	public static Supplier getSupplierFromId(int id) throws SQLException {
		PreparedStatement st = connection
				.prepareStatement("SELECT * from suppliers WHERE id = '" + String.valueOf(id) + "'");
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			Supplier s = new Supplier();
			s.setId(id);
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			s.setPhone(rs.getString("phone"));
			s.setDeleted(rs.getInt("deleted"));
			return s;
		} else
			return null;

	}

	private static ResultSet selectFromTable(String tableName) throws SQLException {
		PreparedStatement st = connection.prepareStatement("SELECT * from " + tableName);
		return st.executeQuery();
	}

	public static ArrayList<Employee> getEmployees() throws SQLException {
		ResultSet rs = selectFromTable("employees");
		ArrayList<Employee> employees = new ArrayList<>();
		while (rs.next()) {
			Employee e = new Employee();
			e.setId(rs.getInt("id"));
			e.setNIF(rs.getString("nif"));
			e.setName(rs.getString("name"));
			e.setSurname(rs.getString("surname"));
			e.setEmail(rs.getString("email"));
			e.setPassword(rs.getString("password"));

			employees.add(e);
		}
		return employees;
	}

	public static ArrayList<Product> getProducts() throws SQLException {
		ResultSet rs = selectFromTable("products");
		ArrayList<Product> products = new ArrayList<>();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setQuantity(rs.getInt("quantity"));
			p.setPrice(rs.getFloat("price"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setCategory(rs.getString("category"));
			p.setImage(rs.getString("image"));
			p.setDeleted(rs.getInt("deleted"));

			products.add(p);
		}
		return products;
	}

	public static ArrayList<Supplier> getSuppliers() throws SQLException {
		ResultSet rs = selectFromTable("suppliers");
		ArrayList<Supplier> suppliers = new ArrayList<>();
		while (rs.next()) {
			Supplier s = new Supplier();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAddress(rs.getString("address"));
			s.setPhone(rs.getString("phone"));
			s.setDeleted(rs.getInt("deleted"));

			suppliers.add(s);
		}
		return suppliers;
	}

	public static ArrayList<Transaction> getTransactions() throws SQLException {
		ResultSet rs = selectFromTable("transactions");
		ArrayList<Transaction> transactions = new ArrayList<>();
		while (rs.next()) {
			Transaction t = new Transaction();
			t.setId(rs.getInt("id"));
			t.setProductId(rs.getInt("product_id"));
			t.setSupplierId(rs.getInt("supplier_id"));
			t.setQuantity(rs.getInt("quantity"));
			t.setDate(rs.getDate("date"));

			transactions.add(t);
		}
		return transactions;
	}

	public static void insertEmployee(Employee e) throws SQLException {
		PreparedStatement st = connection.prepareStatement("INSERT INTO employees VALUES (?,?,?,?,?,?)");

		st.setInt(1, e.getId());
		st.setString(2, e.getNIF());
		st.setString(3, e.getName());
		st.setString(4, e.getSurname());
		st.setString(5, e.getEmail());
		st.setString(6, e.getPassword());

		st.executeUpdate();
		st.close();
	}

	public static void insertProduct(Product p) throws SQLException {
		PreparedStatement st = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?,?,?,?)");

		st.setInt(1, p.getId());
		st.setInt(2, p.getQuantity());
		st.setFloat(3, p.getPrice());
		st.setString(4, p.getName());
		st.setString(5, p.getDescription());
		st.setString(6, p.getCategory());
		st.setString(7, p.getImage());
		st.setInt(8, p.getDeleted());

		st.executeUpdate();
		st.close();
	}

	public static void insertSupplier(Supplier s) throws SQLException {
		PreparedStatement st = connection.prepareStatement("INSERT INTO suppliers VALUES (?,?,?,?,?)");

		st.setInt(1, s.getId());
		st.setString(2, s.getName());
		st.setString(3, s.getAddress());
		st.setString(4, s.getPhone());
		st.setInt(5, s.getDeleted());

		st.executeUpdate();
		st.close();
	}

	public static void insertTransaction(Transaction t, String option) throws SQLException {
		if (option.equals("Sum")) {
			PreparedStatement st = connection.prepareStatement("INSERT INTO transactions VALUES (?,?,?,?,?)");

			st.setInt(1, t.getId());
			st.setInt(2, t.getProductId());
			st.setInt(3, t.getSupplierId());
			st.setInt(4, t.getQuantity());
			st.setDate(5, t.getDate());

			st.executeUpdate();
			st.close();

		} else if (option.equals("Subtract")) {
			PreparedStatement st = connection.prepareStatement("INSERT INTO transactions VALUES (?,?,?,-?,?)");

			st.setInt(1, t.getId());
			st.setInt(2, t.getProductId());
			st.setInt(3, t.getSupplierId());
			st.setInt(4, t.getQuantity());
			st.setDate(5, t.getDate());

			st.executeUpdate();
			st.close();

		}
	}

	public static void updateProduct(Product p) throws SQLException {
		PreparedStatement st = connection.prepareStatement("UPDATE products SET "
				+ "quantity=?,price=?,name=?,description=?,category=?,image=?,deleted=?" + " WHERE id=?");

		st.setInt(1, p.getQuantity());
		st.setFloat(2, p.getPrice());
		st.setString(3, p.getName());
		st.setString(4, p.getDescription());
		st.setString(5, p.getCategory());
		st.setString(6, p.getImage());
		st.setInt(7, p.getDeleted());
		st.setInt(8, p.getId());

		st.executeUpdate();
		st.close();
	}

	public static void deleteProduct(Product p) throws SQLException {
		p.setDeleted(1);
		updateProduct(p);
	}

	public static void updateSupplier(Supplier s) throws SQLException {
		PreparedStatement st = connection
				.prepareStatement("UPDATE suppliers SET " + "name=?,address=?,phone=?,deleted=?" + " WHERE id=?");

		st.setString(1, s.getName());
		st.setString(2, s.getAddress());
		st.setString(3, s.getPhone());
		st.setInt(4, s.getDeleted());
		st.setInt(5, s.getId());

		st.executeUpdate();
		st.close();
	}

	public static void deleteSupplier(Supplier s) throws SQLException {
		s.setDeleted(1);
		updateSupplier(s);
	}
}