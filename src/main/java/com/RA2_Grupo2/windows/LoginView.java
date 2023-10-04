package com.RA2_Grupo2.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Employee;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JLabel lUser, lPass;
	private JTextField tUser;
	private JPasswordField tPass;
	private JButton bSignIn, bSignUp;
	private static ArrayList<Employee> listE = new ArrayList<Employee>();

	public LoginView() {

		super("Login");
		setSize(400, 300);
		WindowsPreset.windowPreset(this);

		lUser = new JLabel("Username: ");
		lUser.setBounds(80, 50, 90, 25);
		tUser = new JTextField(10);
		tUser.setBounds(200, 50, 150, 25);
		tUser.setToolTipText("Enter Username");

		lPass = new JLabel("Password: ");
		lPass.setBounds(80, 100, 90, 25);
		tPass = new JPasswordField(10);
		tPass.setBounds(200, 100, 150, 25);
		tPass.setToolTipText("Enter Password");

		bHandler handler = new bHandler();

		bSignIn = new JButton();
		bSignIn.setBounds(90, 160, 65, 65);
		bSignIn.addActionListener(handler);
		WindowsPreset.buttonPreset(bSignIn, "Sign In", "src/main/resources/icons/signin.png");

		bSignUp = new JButton();
		bSignUp.setBounds(245, 160, 65, 65);
		WindowsPreset.buttonPreset(bSignUp, "Sign up", "src/main/resources/icons/signup.png");
		bSignUp.addActionListener(handler);

		getContentPane().add(lUser);
		getContentPane().add(tUser);
		getContentPane().add(lPass);
		getContentPane().add(tPass);
		getContentPane().add(bSignIn);
		getContentPane().add(bSignUp);

		setVisible(true);
	}

	public static ArrayList<Employee> getListE() {
		return listE;
	}

	public static void setListE(ArrayList<Employee> listE) {
		LoginView.listE = listE;
	}

	public class bHandler implements ActionListener {
//		@SuppressWarnings("deprecation")
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bSignIn)) {
				MainView mv = new MainView();
				dispose();
//				for (Employee em : listE) {
//					try {
//						if (tUser.getText().toUpperCase().equals(em.getEmail().toUpperCase())
//								&& tPass.getText().equals(em.getPassword())) {
//							@SuppressWarnings("unused")
//							MainView mv = new MainView();
//						}
//
//					} catch (NullPointerException NPE) {
//					}
//
//				}

			} else if (e.getSource().equals(bSignUp)) {
				RegisterView rv = new RegisterView();
				dispose();
			}
		}

	}
}
