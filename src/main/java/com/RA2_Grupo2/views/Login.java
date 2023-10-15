package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Employee;

@SuppressWarnings("serial")
public class Login extends JFrame {

	// Attribute declaration.

	private JLabel lUser, lPass;
	public static JLabel lIncorrect;
	private JTextField tUser;
	private JPasswordField tPass;
	private JButton bLogIn, bSignUp;
	private static ArrayList<Employee> listE = new ArrayList<Employee>();
	public static String currentUser = "";

	// Constructor.

	public Login() {

		super("Login");

		// Frame configuration.

		setSize(400, 300);
		WindowsPreset.windowPreset(this);

		// Labels & TextFields configuration.

		// Email or NIF

		lUser = new JLabel("Email / NIF: ");
		lUser.setBounds(80, 50, 90, 25);
		tUser = new JTextField(10);
		tUser.setBounds(200, 50, 150, 25);
		tUser.setToolTipText("Enter email or NIF");

		// Password

		lPass = new JLabel("Password: ");
		lPass.setBounds(80, 100, 90, 25);
		tPass = new JPasswordField(10);
		tPass.setBounds(200, 100, 150, 25);
		tPass.setToolTipText("Enter Password");
		tPass.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			// Method to login when enter is pressed.

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					login();
				}
			}
		});

		// Label that will be show when user (email or NIF) and password don't match.

		lIncorrect = new JLabel("USER OR PASSWORD WRONG");
		lIncorrect.setBounds(80, 136, 270, 14);
		lIncorrect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lIncorrect.setForeground(new Color(255, 0, 0));
		lIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lIncorrect.setVisible(false);
		getContentPane().setLayout(null);
		getContentPane().add(lIncorrect);

		// Buttons configuration.

		// Handler.

		bHandler handler = new bHandler();

		// Button to Login.

		bLogIn = new JButton();
		bLogIn.setBounds(90, 160, 65, 65);
		bLogIn.addActionListener(handler);
		WindowsPreset.buttonPreset(bLogIn, "Log In", "src/main/resources/icons/signin.png");

		// Button to Register an employee (user).

		bSignUp = new JButton();
		bSignUp.setBounds(236, 160, 65, 65);
		WindowsPreset.buttonPreset(bSignUp, "Sign up", "src/main/resources/icons/signup.png");
		bSignUp.addActionListener(handler);

		getContentPane().add(lUser);
		getContentPane().add(tUser);
		getContentPane().add(lPass);
		getContentPane().add(tPass);
		getContentPane().add(bLogIn);
		getContentPane().add(bSignUp);

		setVisible(true);
	}

	// Getter and Setter for the employee list.

	public static ArrayList<Employee> getListE() {
		return listE;
	}

	public static void setListE(ArrayList<Employee> listE) {
		Login.listE = listE;
	}

	// Handler implementation.

	public class bHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// If press button login call method login.

			if (e.getSource().equals(bLogIn)) {
				login();
			}

			// If press button register open register view.

			else if (e.getSource().equals(bSignUp)) {
				@SuppressWarnings("unused")
				Register r = new Register();
				dispose();
			}
		}

	}

	// Method to check if the email or the NIF match with the password.

	@SuppressWarnings("deprecation")
	public void login() {
		try {
			boolean isMatch = false;
			listE = SQL_Methods.getEmployees();
			for (Employee em : listE) {
				if ((em.getEmail().equals(tUser.getText()) && em.getPassword().equals(tPass.getText()))
						|| (em.getNIF().toUpperCase().equals(tUser.getText().toUpperCase())
								&& em.getPassword().equals(tPass.getText()))) {
					currentUser = em.getEmail();
					@SuppressWarnings("unused")
					Inventory i = new Inventory();
					dispose();
				} else
					isMatch = true;

			}
			lIncorrect.setVisible(isMatch);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
