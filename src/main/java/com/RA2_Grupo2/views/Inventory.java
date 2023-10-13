package com.RA2_Grupo2.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;

@SuppressWarnings("serial")
public class Inventory extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JButton bProductAndSupplier, bTransaction, bLogout;
	private JScrollBar scrollBar;

	private static List<Product> listP = new ArrayList<>();

	public Inventory() {
		super("Inventory");

		setSize(585, 575);
		WindowsPreset.windowPreset(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// Panels.

		ptable = new JPanel();
		ptable.setBounds(10, 10, 551, 443);
		pbutton = new JPanel();
		pbutton.setBounds(0, 454, 571, 84);
		getContentPane().setLayout(null);

		// Table.

		ptable.setLayout(null);
		ptable.setBackground(Color.LIGHT_GRAY);
		table = new JTable();
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setGridColor(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(551, 443);
		ptable.add(scrollPane);

		getContentPane().add(ptable);

		// Adding scroll bar to the table.

		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		getContentPane().add(ptable);

		refreshTable();

		// Buttons.

		bHandler handler = new bHandler();

		// Button for open Inventory.

		bProductAndSupplier = new JButton();
		bProductAndSupplier.setBounds(253, 10, 65, 65);
		WindowsPreset.buttonPreset(bProductAndSupplier, "Open product and supplier management.",
				"src/main/resources/icons/almacen.png");
		bProductAndSupplier.addActionListener(handler);

		// Button for open Transactions.

		bTransaction = new JButton();
		bTransaction.setBounds(358, 10, 65, 65);
		WindowsPreset.buttonPreset(bTransaction, "Open transactions history.",
				"src/main/resources/icons/transaccion.png");
		bTransaction.addActionListener(handler);

		// Button for close the program.

		bLogout = new JButton();
		bLogout.setBounds(138, 10, 65, 65);
		WindowsPreset.buttonPreset(bLogout, "Logout", "src/main/resources/icons/cerrar-sesion.png");
		bLogout.addActionListener(handler);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bProductAndSupplier);
		pbutton.add(bTransaction);
		pbutton.add(bLogout);

		getContentPane().add(pbutton);

		setVisible(true);

	}

	public static List<Product> getListP() {
		return listP;
	}

	public static void setListT(List<Product> listP) {
		Inventory.listP = listP;
	}

	public static void refreshTable() {
		DefaultTableModel dtm = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		String[] columns = { "NAME", "QUANTITY" };
		dtm.setColumnIdentifiers(columns);
		try {
			listP = SQL_Methods.getProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<Product> iter = listP.iterator();
		while (iter.hasNext()) {
			Product p = iter.next();
			if (p.getDeleted() == 0) {
				dtm.addRow(new Object[] { p.getName(), p.getQuantity() });
			}
		}
		Inventory.table.setModel(dtm);

	}

	private class bHandler implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bProductAndSupplier)) {
				dispose();
				ProductAndSupplier ps = new ProductAndSupplier();
			} else if (e.getSource().equals(bTransaction)) {
				dispose();
				TransactionHistory th = new TransactionHistory();
			} else if (e.getSource().equals(bLogout)) {
				dispose();
				Login l = new Login();
			}
		}

	}
}