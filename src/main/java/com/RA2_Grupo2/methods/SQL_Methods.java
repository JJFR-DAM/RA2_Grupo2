package com.RA2_Grupo2.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}