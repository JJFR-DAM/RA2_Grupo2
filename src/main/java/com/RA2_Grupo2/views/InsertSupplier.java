package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Supplier;

@SuppressWarnings("serial")
public class InsertSupplier extends JFrame {

	// Attributes declaration.

	private JLabel jlName, jlAddress, jlPhone;
	private JTextField jtName, jtPhone;
	private JTextArea jtAddress;
	private JScrollPane jsAddress;
	private JButton jbconfirm, jbcancel;

	// Constructor.

	public InsertSupplier() {

		// Frame Properties.

		super("Insert");
		setSize(400, 365);
		WindowsPreset.windowPreset(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

		jlPhone = new JLabel("Phone:");
		jlPhone.setBounds(10, 85, 175, 35);
		jlPhone.setHorizontalAlignment(SwingConstants.CENTER);
		jlPhone.setBackground(Color.GRAY);
		getContentPane().add(jlPhone);
		jtPhone = new JTextField();
		jtPhone.setBounds(185, 85, 193, 35);
		jtPhone.setColumns(10);
		jtPhone.setToolTipText("Insert phone");
		getContentPane().add(jtPhone);

		jlAddress = new JLabel("Address:");
		jlAddress.setBounds(10, 147, 175, 35);
		jlAddress.setHorizontalAlignment(SwingConstants.CENTER);
		jlAddress.setBackground(Color.GRAY);
		getContentPane().add(jlAddress);
		jtAddress = new JTextArea();
		jtAddress.setColumns(10);
		jtAddress.setToolTipText("Insert address");
		jsAddress = new JScrollPane(jtAddress);
		jsAddress.setBounds(185, 130, 193, 70);
		getContentPane().add(jsAddress);

		jtAddress.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

				/*
				 * If enter key is pressed check every field has been field, insert the supply
				 * and refresh the table.
				 */

				if (e.getKeyCode() == 10) {
					if (jtAddress.getText().isBlank() || jtName.getText().isBlank() || jtPhone.getText().isBlank()) {
						JOptionPane.showMessageDialog(getContentPane(),
								"You must fill every field to complete the data insertion. Try again.");
					} else {
						int id = 0;
						try {
							id = SQL_Methods.getMaxIdFromTable("suppliers");
							Supplier s = new Supplier(jtName.getText(), jtAddress.getText(), jtPhone.getText());
							s.setId(id + 1);
							SQL_Methods.insertSupplier(s);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						ProductAndSupplier.refreshTable();
						dispose();
					}
				}
			}
		});

		// Button's configurations.

		// Handler.

		bHandler handler = new bHandler();

		// Button to confirm the insertion.

		jbconfirm = new JButton();
		jbconfirm.setBounds(235, 230, 65, 65);
		WindowsPreset.buttonPreset(jbconfirm, "Insert the supplier", "src/main/resources/icons/confirmar.png");
		jbconfirm.setBackground(new Color(89, 166, 89));
		jbconfirm.addActionListener(handler);
		getContentPane().add(jbconfirm);

		// Button to cancel the insertion.

		jbcancel = new JButton();
		jbcancel.setBounds(100, 230, 65, 65);
		WindowsPreset.buttonPreset(jbcancel, "Cancel", "src/main/resources/icons/volver.png");
		jbcancel.setBackground(new Color(166, 89, 89));
		jbcancel.addActionListener(handler);
		getContentPane().add(jbcancel);

		setVisible(true);
	}

	// Handler implementation.

	private class bHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * If confirm button is pressed check every field has been field, insert the
			 * supply and refresh the table.
			 */

			if (e.getSource().equals(jbconfirm)) {
				if (jtAddress.getText().isBlank() || jtName.getText().isBlank() || jtPhone.getText().isBlank()) {
					JOptionPane.showMessageDialog(getContentPane(),
							"You must fill every field to complete the data insertion. Try again.");
				} else {
					int id = 0;
					try {
						id = SQL_Methods.getMaxIdFromTable("suppliers");
						Supplier s = new Supplier(jtName.getText(), jtAddress.getText(), jtPhone.getText());
						s.setId(id + 1);
						SQL_Methods.insertSupplier(s);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					ProductAndSupplier.refreshTable();
					dispose();
				}
			}

			// If cancel button is pressed close the insertion view.

			else if (e.getSource().equals(jbcancel)) {
				dispose();
			}
		}
	}
}
