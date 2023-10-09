package com.RA2_Grupo2.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.RA2_Grupo2.objects.Employee;

public class SQL_Methods {

	static Connection connection;
	private static String host="jdbc:mysql://localhost/ra2_grupo2";
	private static String user="root";
	private static String pass="";

	public static boolean startConnection() {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(host, user, pass);
			
			System.out.println("Connection success!");
			return true;
		}
		catch (SQLException ex){}
		catch (ClassNotFoundException e){}
		
		System.out.println("Connection failed!");
		return false;
	}

	public static void insertEmployee(Employee e) throws SQLException
	{
		PreparedStatement st=connection.prepareStatement(
					"INSERT INTO employees VALUES (?,?,?,?,?,?)");
		
		st.setInt(1,e.getId());
		st.setString(2,e.getNIF());
		st.setString(3,e.getName());
		st.setString(4,e.getSurname());
		st.setString(5,e.getEmail());
		st.setString(6,e.getPassword());
		
		st.executeUpdate();
		st.close();
	}
}