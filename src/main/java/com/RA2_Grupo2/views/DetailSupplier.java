package com.RA2_Grupo2.views;

import java.awt.Color;

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
	private Supplier s;

	// Constructor.

	public DetailSupplier(Supplier supplier) {

		// Frame Properties.

		super("Detail");
		setSize(400, 298);
		WindowsPreset.windowPreset(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

		setVisible(true);
	}
}