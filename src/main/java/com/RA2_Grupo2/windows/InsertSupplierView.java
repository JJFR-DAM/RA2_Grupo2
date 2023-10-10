package com.RA2_Grupo2.windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Supplier;

@SuppressWarnings("serial")
public class InsertSupplierView extends JFrame {

	// Attributes declaration.

	private JLabel jlName, jlAddress, jlPhone;
	private JTextField jtName, jtAddress, jtPhone;
	private JButton jbconfirm, jbcancel;

	// Constructor.

	public InsertSupplierView() {

		// Windows Properties.

		super("Insert");
		setSize(400, 330);
		WindowsPreset.windowPreset(this);
		getContentPane().setLayout(null);

		// Label's & TextField's configurations.

		jlName = new JLabel("Name:");
		jlName.setBounds(10, 40, 175, 35);
		jlName.setHorizontalAlignment(SwingConstants.CENTER);
		jlName.setBackground(Color.GRAY);
		getContentPane().add(jlName);
		jtName = new JTextField();
		jtName.setBounds(185, 40, 193, 35);
		jtName.setColumns(10);
		jtName.setToolTipText("Insert name");
		getContentPane().add(jtName);

		jlAddress = new JLabel("Address:");
		jlAddress.setBounds(10, 85, 175, 35);
		jlAddress.setHorizontalAlignment(SwingConstants.CENTER);
		jlAddress.setBackground(Color.GRAY);
		getContentPane().add(jlAddress);
		jtAddress = new JTextField();
		jtAddress.setBounds(185, 85, 193, 35);
		jtAddress.setColumns(10);
		jtAddress.setToolTipText("Insert address");
		getContentPane().add(jtAddress);

		jlPhone = new JLabel("Phone:");
		jlPhone.setBounds(10, 130, 175, 35);
		jlPhone.setHorizontalAlignment(SwingConstants.CENTER);
		jlPhone.setBackground(Color.GRAY);
		getContentPane().add(jlPhone);
		jtPhone = new JTextField();
		jtPhone.setBounds(185, 130, 193, 35);
		jtPhone.setColumns(10);
		jtPhone.setToolTipText("Insert phone");
		getContentPane().add(jtPhone);

		// Button's configurations.

		bHandler handler = new bHandler();

		// Button to confirm the insertion.

		jbconfirm = new JButton();
		jbconfirm.setBounds(235, 195, 65, 65);
		WindowsPreset.buttonPreset(jbconfirm, "Insert the supplier", "src/main/resources/icons/confirmar.png");
		jbconfirm.setBackground(new Color(89, 166, 89));
		jbconfirm.addActionListener(handler);
		getContentPane().add(jbconfirm);

		// Button to cancel the insertion.

		jbcancel = new JButton();
		jbcancel.setBounds(100, 195, 65, 65);
		WindowsPreset.buttonPreset(jbcancel, "Cancel", "src/main/resources/icons/volver.png");
		jbcancel.setBackground(new Color(166, 89, 89));
		jbcancel.addActionListener(handler);
		getContentPane().add(jbcancel);

		setVisible(true);
	}

	private class bHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(jbconfirm)) {
				if (jtAddress.getText().isBlank() || jtName.getText().isBlank() || jtPhone.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(),
							"You must fill every field to complete the data insertion. Try again.");
				} else {
					// Inserción de datos.
					int id = 0;
					try {
						id = SQL_Methods.getMaxIdFromTable("suppliers");
						Supplier s = new Supplier(jtName.getText(), jtAddress.getText(), jtPhone.getText());
						s.setId(id + 1);
						SQL_Methods.insertSupplier(s);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					MainView.refreshTable();
					dispose();
				}
			} else if (e.getSource().equals(jbcancel)) {
				dispose();
			}
		}
	}
}
