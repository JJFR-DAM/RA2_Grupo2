package com.RA2_Grupo2.start;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.views.Login;

public class Test {

	public static void main(String[] args) {

		// Checking if the program make connection with the database successfully.

		if (SQL_Methods.startConnection()) {

			// Open the user interface

			@SuppressWarnings("unused")
			Login l = new Login();
		}
	}

}
