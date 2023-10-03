package com.RA2_Grupo2.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Methods {

	static Statement statement;
	static Connection cc;
	private String host = "jdbc:mysql://localhost/ra2_grupo2";
	private String user = "root";
	private String pass = "";

	public SQL_Methods() {
		try {
			cc = DriverManager.getConnection(host, user, pass);
			statement = cc.createStatement();
			System.out.println("Connection success!");
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
		}
	}

}