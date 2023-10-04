package com.RA2_Grupo2.start;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.windows.LoginView;

public class Test {

	public static void main(String[] args) {
		
		if(SQL_Methods.startConnection())
		{
			// Open the user interface
			@SuppressWarnings("unused")
			LoginView lv = new LoginView();
		}
	}

}
