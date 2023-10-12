package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Supplier;

@SuppressWarnings("serial")
public class DetailSupplier extends JFrame {

	// Attributes declaration.

	private JLabel jlId, jlName, jlAddress, jlPhone;
	private JTextField jtId, jtName, jtPhone;
	private JTextArea jtAddress;
	private JScrollPane jsAddress;
	private JButton jbBack;
	private Supplier s;

	// Constructor.

	public DetailSupplier(Supplier supplier) {

		// Windows Properties.

		super("Update");
		setSize(400, 400);
		WindowsPreset.windowPreset(this);
		getContentPane().setLayout(null);
		s = supplier;

		// Label's & TextField's configurations.

		jlId = new JLabel("Id:");
		jlId.setBounds(10, 30, 175, 35);
		jlId.setHorizontalAlignment(SwingConstants.CENTER);
		jlId.setBackground(Color.GRAY);
		getContentPane().add(jlId);
		jtId = new JTextField();
		jtId.setBounds(185, 30, 193, 35);
		jtId.setColumns(10);
		jtId.setText(String.valueOf(s.getId()));
		jtId.setToolTipText("Show id");
		jtId.setEditable(false);
		getContentPane().add(jtId);

		jlName = new JLabel("Name:");
		jlName.setBounds(10, 75, 175, 35);
		jlName.setHorizontalAlignment(SwingConstants.CENTER);
		jlName.setBackground(Color.GRAY);
		getContentPane().add(jlName);
		jtName = new JTextField();
		jtName.setBounds(185, 75, 193, 35);
		jtName.setColumns(10);
		jtName.setText(s.getName());
		jtName.setToolTipText("Show name");
		jtName.setEditable(false);
		getContentPane().add(jtName);

		jlPhone = new JLabel("Phone:");
		jlPhone.setBounds(10, 120, 175, 35);
		jlPhone.setHorizontalAlignment(SwingConstants.CENTER);
		jlPhone.setBackground(Color.GRAY);
		getContentPane().add(jlPhone);
		jtPhone = new JTextField();
		jtPhone.setBounds(185, 120, 193, 35);
		jtPhone.setColumns(10);
		jtPhone.setToolTipText("Show phone");
		jtPhone.setText(s.getPhone());
		jtPhone.setEditable(false);
		getContentPane().add(jtPhone);

		jlAddress = new JLabel("Address:");
		jlAddress.setBounds(10, 182, 175, 35);
		jlAddress.setHorizontalAlignment(SwingConstants.CENTER);
		jlAddress.setBackground(Color.GRAY);
		getContentPane().add(jlAddress);
		jtAddress = new JTextArea();
		jtAddress.setColumns(10);
		jtAddress.setToolTipText("Show address");
		jtAddress.setText(s.getAddress());
		jtAddress.setEditable(false);
		jsAddress = new JScrollPane(jtAddress);
		jsAddress.setBounds(185, 165, 193, 70);
		getContentPane().add(jsAddress);

		// Button's configurations.

		// Button to back.

		jbBack = new JButton();
		jbBack.setBounds(160, 265, 65, 65);
		WindowsPreset.buttonPreset(jbBack, "Cancel", "src/main/resources/icons/volver.png");
		jbBack.setBackground(new Color(166, 89, 89));
		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		getContentPane().add(jbBack);

		setVisible(true);
	}
}