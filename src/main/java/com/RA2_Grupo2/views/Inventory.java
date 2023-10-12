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
import com.RA2_Grupo2.objects.Transaction;

@SuppressWarnings("serial")
public class Inventory extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JButton bAdd, bSubtract, bBack;
	private JScrollBar scrollBar;

	private static List<Transaction> listT = new ArrayList<>();

	public Inventory() {
		super("Inventory");

		setSize(720, 500);
		WindowsPreset.windowPreset(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// Panels.

		ptable = new JPanel();
		ptable.setBounds(10, 10, 565, 443);
		pbutton = new JPanel();
		pbutton.setBounds(575, 0, 130, 540);
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

		bHandler handler = new bHandler();

		// Button for create.

		bAdd = new JButton();
		bAdd.setBounds(32, 100, 65, 65);
		WindowsPreset.buttonPreset(bAdd, "Add a shopping transaction.", "src/main/resources/icons/mas.png");
		bAdd.addActionListener(handler);

		// Button for delete.

		bSubtract = new JButton();
		bSubtract.setBounds(32, 200, 65, 65);
		WindowsPreset.buttonPreset(bSubtract, "Add a selling transaction.", "src/main/resources/icons/menos.png");
		bSubtract.addActionListener(handler);

		// Button for close the program.

		bBack = new JButton();
		bBack.setBounds(32, 387, 65, 65);
		WindowsPreset.buttonPreset(bBack, "Back to the main view", "src/main/resources/icons/volver.png");
		bBack.addActionListener(handler);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bAdd);
		pbutton.add(bSubtract);
		pbutton.add(bBack);

		getContentPane().add(pbutton);

		setVisible(true);

	}

	public static List<Transaction> getListT() {
		return listT;
	}

	public static void setListT(List<Transaction> listT) {
		Inventory.listT = listT;
	}

	public static void refreshTable() {
		DefaultTableModel dtm = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		String[] columns = { "QUANTITY", "DATE" };
		dtm.setColumnIdentifiers(columns);
		try {
			listT = SQL_Methods.getTransactions();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<Transaction> iter = listT.iterator();
		while (iter.hasNext()) {
			Transaction t = iter.next();
			dtm.addRow(new Object[] { t.getQuantity(), t.getDate() });
		}
		Inventory.table.setModel(dtm);

	}

	private class bHandler implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bAdd)) {

			} else if (e.getSource().equals(bSubtract)) {

			} else if (e.getSource().equals(bBack)) {
				dispose();
				Main m = new Main();
			}
		}

	}
}