package com.RA2_Grupo2.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.RA2_Grupo2.methods.WindowsPreset;

@SuppressWarnings("serial")
public class RegisterView extends JFrame {

	private JLabel lPass, lPass2, lName, lSurname, lNIF, lEmail;
	private JTextField tName, tSurname, tNIF, tEmail;
	private JPasswordField tPass, tPass2;
	private JButton bBack, bRegister;

	public RegisterView() {

		super("Sign up");

		setSize(400, 500);
		WindowsPreset.windowPreset(this);

		lEmail = new JLabel("Email: ");
		lEmail.setBounds(80, 50, 120, 25);
		tEmail = new JTextField(10);
		tEmail.setBounds(200, 50, 150, 25);
		tEmail.setToolTipText("Enter Email");

		lPass = new JLabel("Password: ");
		lPass.setBounds(80, 100, 120, 25);
		tPass = new JPasswordField(10);
		tPass.setBounds(200, 100, 150, 25);
		tPass.setToolTipText("Enter Password");

		lPass2 = new JLabel("Repeat password: ");
		lPass2.setBounds(80, 150, 120, 25);
		tPass2 = new JPasswordField(10);
		tPass2.setBounds(200, 150, 150, 25);
		tPass2.setToolTipText("Repeat Password");

		lName = new JLabel("Name: ");
		lName.setBounds(80, 200, 120, 25);
		tName = new JTextField(10);
		tName.setBounds(200, 200, 150, 25);
		tName.setToolTipText("Enter Name");

		lSurname = new JLabel("Surname: ");
		lSurname.setBounds(80, 250, 120, 25);
		tSurname = new JTextField(10);
		tSurname.setBounds(200, 250, 150, 25);
		tSurname.setToolTipText("Enter Surname");

		lNIF = new JLabel("NIF: ");
		lNIF.setBounds(80, 300, 120, 25);
		tNIF = new JTextField(10);
		tNIF.setBounds(200, 300, 150, 25);
		tNIF.setToolTipText("Enter NIF");

		bHandler handler = new bHandler();

		bBack = new JButton();
		bBack.setBounds(90, 360, 65, 65);
		WindowsPreset.buttonPreset(bBack, "Sign In", "src/main/resources/icons/volver.png");
		bBack.addActionListener(handler);

		bRegister = new JButton();
		bRegister.setBounds(245, 360, 65, 65);
		WindowsPreset.buttonPreset(bRegister, "Sign up", "src/main/resources/icons/confirmar.png");
		bRegister.addActionListener(handler);

		getContentPane().add(lPass);
		getContentPane().add(tPass);
		getContentPane().add(lPass2);
		getContentPane().add(tPass2);
		getContentPane().add(lName);
		getContentPane().add(tName);
		getContentPane().add(lSurname);
		getContentPane().add(tSurname);
		getContentPane().add(lNIF);
		getContentPane().add(tNIF);
		getContentPane().add(lEmail);
		getContentPane().add(tEmail);
		getContentPane().add(bBack);
		getContentPane().add(bRegister);

		setVisible(true);

	}

	public class bHandler implements ActionListener {
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bBack)) {
				dispose();
				LoginView lv = new LoginView();

			} else {
				dispose();
				LoginView lv = new LoginView();
			}
		}

	}

}
