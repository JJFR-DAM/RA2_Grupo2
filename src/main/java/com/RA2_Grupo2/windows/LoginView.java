package com.RA2_Grupo2.windows;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.RA2_Grupo2.objects.Employee;

@SuppressWarnings("serial")
public class LoginView extends JFrame {

	private JLabel lUser, lPass;
	private JTextField tUser;
	private JPasswordField tPass;
	private JButton bSignIn, bSignUp;
	private Color c = Color.LIGHT_GRAY;
	private ArrayList<Employee> listE = new ArrayList<Employee>();

	public LoginView() {

		super("Login");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(c);
		setLocationRelativeTo(null);
		setResizable(false);
		Image icon1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/icons/icon.png");
		setIconImage(icon1);
		getContentPane().setLayout(null);

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

		bSignIn = new JButton();
		bSignIn.setBounds(90, 160, 65, 65);
		bSignIn.setToolTipText("Sign In");
		bSignIn.setBackground(c);
		bSignIn.setBorderPainted(false);
		Icon ic = new ImageIcon("src/main/resources/icons/signin.png");
		bSignIn.setIcon(ic);

		bSignUp = new JButton();
		bSignUp.setBounds(245, 160, 65, 65);
		bSignUp.setToolTipText("Sign Up");
		bSignUp.setBackground(c);
		bSignUp.setBorderPainted(false);
		ic = new ImageIcon("src/main/resources/icons/signup.png");
		bSignUp.setIcon(ic);

		getContentPane().add(lUser);
		getContentPane().add(tUser);
		getContentPane().add(lPass);
		getContentPane().add(tPass);
		getContentPane().add(bSignIn);
		getContentPane().add(bSignUp);

		setVisible(true);
	}

	public class bHandler implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bSignIn)) {
				for (Employee em : listE) {
					try {
						if (tUser.getText().toUpperCase().equals(em.getEmail().toUpperCase())
								&& tPass.getText().equals(em.getPassword())) {
							@SuppressWarnings("unused")
							MainView mv = new MainView();
						}

					} catch (NullPointerException NPE) {
					}

				}

			} else {
				@SuppressWarnings("unused")
				RegisterView rv = new RegisterView();
			}
		}

	}
}
