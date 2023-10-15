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
import com.RA2_Grupo2.objects.Supplier;
import com.RA2_Grupo2.objects.Transaction;

@SuppressWarnings("serial")
public class TransactionHistory extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JButton bSum, bSubtract, bBack;
	private JScrollBar scrollBar;

	private static List<Transaction> listT = new ArrayList<>();

	// Constructor.

	public TransactionHistory() {
		super("Transactions");

		// Frame properties.

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
		scrollPane.setSize(565, 465);
		ptable.add(scrollPane);

		getContentPane().add(ptable);

		// Adding scroll bar to the table.

		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		getContentPane().add(ptable);

		refreshTable();

		// Buttons.

		// Handler.

		bHandler handler = new bHandler();

		// Button for create.

		bSum = new JButton();
		bSum.setBounds(253, 10, 65, 65);
		WindowsPreset.buttonPreset(bSum, "Add a shopping transaction.", "src/main/resources/icons/mas.png");
		bSum.addActionListener(handler);

		// Button for delete.

		bSubtract = new JButton();
		bSubtract.setBounds(358, 10, 65, 65);
		WindowsPreset.buttonPreset(bSubtract, "Add a selling transaction.", "src/main/resources/icons/menos.png");
		bSubtract.addActionListener(handler);

		// Button for close the program.

		bBack = new JButton();
		bBack.setBounds(138, 10, 65, 65);
		WindowsPreset.buttonPreset(bBack, "Back to the main view", "src/main/resources/icons/volver.png");
		bBack.addActionListener(handler);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bSum);
		pbutton.add(bSubtract);
		pbutton.add(bBack);

		getContentPane().add(pbutton);

		setVisible(true);

	}

	// Getter & Setter for the transaction list.

	public static List<Transaction> getListT() {
		return listT;
	}

	public static void setListT(List<Transaction> listT) {
		TransactionHistory.listT = listT;
	}

	// Method to load data into the table.

	public static void refreshTable() {
		DefaultTableModel dtm = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		String[] columns = { "PRODUCT", "SUPPLIER", "QUANTITY", "DATE" };
		dtm.setColumnIdentifiers(columns);
		try {
			listT = SQL_Methods.getTransactions();
			Iterator<Transaction> iter = listT.iterator();
			while (iter.hasNext()) {
				Transaction t = iter.next();
				Product p = SQL_Methods.getProductFromId(t.getProductId());
				Supplier s = SQL_Methods.getSupplierFromId(t.getSupplierId());
				dtm.addRow(new Object[] { p.getName(), s.getName(), t.getQuantity(), t.getDate() });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TransactionHistory.table.setModel(dtm);

	}

	// Handler implementation.

	private class bHandler implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			String option;

			// If sum button is pressed open the transaction view with the sum option.

			if (e.getSource().equals(bSum)) {
				option = "Sum";
				InsertTransaction st = new InsertTransaction(option);

			}

			/*
			 * If subtract button is pressed open the transaction view with the subtract
			 * option.
			 */

			else if (e.getSource().equals(bSubtract)) {
				option = "Subtract";
				InsertTransaction st = new InsertTransaction(option);

			}

			// If cancel button is pressed go back to inventory view.

			else if (e.getSource().equals(bBack)) {
				dispose();
				Inventory i = new Inventory();
			}
		}

	}
}