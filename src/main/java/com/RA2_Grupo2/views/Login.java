package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private JLabel lUser, lPass;
	public static JLabel lIncorrect;
	private JTextField tUser;
	private JPasswordField tPass;
	private JButton bLogIn, bSignUp;
	private static ArrayList<Employee> listE = new ArrayList<Employee>();
	public static String currentUser = "";

	public Login() {

		super("Login");
		setSize(400, 300);
		WindowsPreset.windowPreset(this);

		lUser = new JLabel("Email / NIF: ");
		lUser.setBounds(80, 50, 90, 25);
		tUser = new JTextField(10);
		tUser.setBounds(200, 50, 150, 25);
		tUser.setToolTipText("Enter email or NIF");

		lPass = new JLabel("Password: ");
		lPass.setBounds(80, 100, 90, 25);
		tPass = new JPasswordField(10);
		tPass.setBounds(200, 100, 150, 25);
		tPass.setToolTipText("Enter Password");

		lIncorrect = new JLabel("USER OR PASSWORD WRONG");
		lIncorrect.setBounds(80, 136, 270, 14);
		lIncorrect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lIncorrect.setForeground(new Color(255, 0, 0));
		lIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lIncorrect.setVisible(false);
		getContentPane().setLayout(null);
		getContentPane().add(lIncorrect);

		bHandler handler = new bHandler();

		bLogIn = new JButton();
		bLogIn.setBounds(90, 160, 65, 65);
		bLogIn.addActionListener(handler);
		WindowsPreset.buttonPreset(bLogIn, "Log In", "src/main/resources/icons/signin.png");

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

	public static ArrayList<Employee> getListE() {
		return listE;
	}

	public static void setListE(ArrayList<Employee> listE) {
		Login.listE = listE;
	}

	public class bHandler implements ActionListener {
		@SuppressWarnings({ "unused", "deprecation" })
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bLogIn)) {
				try {
					boolean isMatch = false;
					listE = SQL_Methods.getEmployees();
					for (Employee em : listE) {
						if ((em.getEmail().equals(tUser.getText()) && em.getPassword().equals(tPass.getText()))
								|| (em.getNIF().toUpperCase().equals(tUser.getText().toUpperCase())
										&& em.getPassword().equals(tPass.getText()))) {
							currentUser = em.getEmail();
							Main m = new Main();
							dispose();
						} else
							isMatch = true;

					}
					lIncorrect.setVisible(isMatch);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} else if (e.getSource().equals(bSignUp)) {
				Register r = new Register();
				dispose();
			}
		}

	}
}
