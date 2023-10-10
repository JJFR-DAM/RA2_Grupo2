package com.RA2_Grupo2.windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.RA2_Grupo2.methods.SQL_Methods;
import com.RA2_Grupo2.methods.WindowsPreset;
import com.RA2_Grupo2.objects.Product;
import com.RA2_Grupo2.objects.Supplier;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	// Attributes.

	private JPanel ptable, pbutton;
	public static JTable table;
	private JLabel currentUser, currentTable;
	private JButton bInsert, bDelete, bUpdate, bDetails, bInventory, bLogout;
	private JScrollBar scrollBar;
	private JComboBox<String> jcb1, jcb2, jcb3;

	private static int option = 0;
	private static String[] options = { "Product", "Supplier" };
	private static String[] filter1 = { "Name", "Category", "Price", "Quantity" };
	private static String[] filter2 = { "Name", "Phone" };
	private static List<Product> listP = new ArrayList<>();
	private static List<Supplier> listS = new ArrayList<>();

	// Constructor.

	public MainView() {

		// Windows Properties.

		super(options[option] + "s");
		setSize(720, 600);
		WindowsPreset.windowPreset(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// Panels.

		ptable = new JPanel();
		ptable.setBounds(10, 90, 565, 465);
		pbutton = new JPanel();
		pbutton.setBounds(575, 0, 130, 563);
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

		bInventory = new JButton();
		bInventory.setBounds(32, 341, 65, 65);
		WindowsPreset.buttonPreset(bInventory, "Open inventory view.", "src/main/resources/icons/transaccion.png");
		bInventory.addActionListener(handler);

		// Button for create.

		bInsert = new JButton();
		bInsert.setBounds(32, 41, 65, 65);
		String description = "Insert a " + options[option] + " in the table";
		WindowsPreset.buttonPreset(bInsert, description, "src/main/resources/icons/insertar.png");
		bInsert.addActionListener(handler);

		// Button for delete.

		bDelete = new JButton();
		bDelete.setBounds(32, 116, 65, 65);
		description = "Delete a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bDelete, description, "src/main/resources/icons/borrar.png");
		bDelete.addActionListener(handler);

		// Button for update.

		bUpdate = new JButton();
		bUpdate.setBounds(32, 191, 65, 65);
		description = "Update a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bUpdate, description, "src/main/resources/icons/actualizar.png");
		bUpdate.addActionListener(handler);

		// Button for read.

		bDetails = new JButton();
		bDetails.setBounds(32, 266, 65, 65);
		description = "Details a " + options[option] + " of the table";
		WindowsPreset.buttonPreset(bDetails, description, "src/main/resources/icons/lupa.png");
		bDetails.addActionListener(handler);

		// Button for close the program.

		bLogout = new JButton();
		bLogout.setBounds(32, 457, 65, 65);
		WindowsPreset.buttonPreset(bLogout, "Logout", "src/main/resources/icons/cerrar-sesion.png");
		bLogout.addActionListener(handler);

		pbutton.setLayout(null);
		pbutton.setBackground(Color.LIGHT_GRAY);
		pbutton.add(bInventory);
		pbutton.add(bInsert);
		pbutton.add(bDelete);
		pbutton.add(bUpdate);
		pbutton.add(bDetails);
		pbutton.add(bLogout);

		getContentPane().add(pbutton);

		jcb1 = new JComboBox<String>(options);
		jcb1.setBounds(348, 10, 130, 25);
		jcb1.addActionListener(handler);
		getContentPane().add(jcb1);

		jcb2 = new JComboBox<String>(filter1);
		jcb2.setBounds(348, 55, 200, 25);
		jcb2.addActionListener(handler);
		getContentPane().add(jcb2);

		jcb3 = new JComboBox<String>(filter2);
		jcb3.setBounds(348, 55, 200, 25);
		jcb3.addActionListener(handler);
		jcb3.setVisible(false);
		getContentPane().add(jcb3);

		currentUser = new JLabel("User: " + LoginView.currentUser);
		currentUser.setBounds(10, 55, 200, 25);
		getContentPane().add(currentUser);

		currentTable = new JLabel("THE TABLE IS SHOWING: ");
		currentTable.setBounds(208, 10, 130, 25);
		getContentPane().add(currentTable);

		setVisible(true);
	}

	// List's Getter & Setter.

	public static List<Product> getListP() {
		return listP;
	}

	public static void setListP(List<Product> listP) {
		MainView.listP = listP;
	}

	public static List<Supplier> getListS() {
		return listS;
	}

	public static void setListS(List<Supplier> listS) {
		MainView.listS = listS;
	}

	public static void refreshTable() {
		DefaultTableModel dtm = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		if (option == 0) {
			String[] columns = { "ID", "CAT", "NAME", "PRICE" };
			dtm.setColumnIdentifiers(columns);
			try {
				listP = SQL_Methods.getProducts();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Iterator<Product> iter = listP.iterator();
			while (iter.hasNext()) {
				Product p = iter.next();
				dtm.addRow(new Object[] { p.getId(), p.getCategory(), p.getName(), p.getPrice() });
			}
			MainView.table.setModel(dtm);
		} else {
			String[] columns = { "ID", "NAME", "PHONE" };
			dtm.setColumnIdentifiers(columns);
			try {
				listS = SQL_Methods.getSuppliers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Iterator<Supplier> iter = listS.iterator();
			while (iter.hasNext()) {
				Supplier s = iter.next();
				dtm.addRow(new Object[] { s.getId(), s.getName(), s.getPhone() });
			}
			MainView.table.setModel(dtm);

		}
	}

	private class bHandler implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(bInsert)) {
				if (option == 0) {
					InsertProductView ipv = new InsertProductView();
				} else {
					InsertSupplierView isv = new InsertSupplierView();
				}

			} else if (e.getSource().equals(bDelete)) {
				if (option == 0) {

				} else {

				}

			} else if (e.getSource().equals(bUpdate)) {
				if (option == 0) {
					UpdateProductView upv = new UpdateProductView();
				} else {
					UpdateSupplierView usv = new UpdateSupplierView();
				}

			} else if (e.getSource().equals(bDetails)) {
				if (option == 0) {
					// Crear vistas de detalles
				} else {

				}

			} else if (e.getSource().equals(bLogout)) {
				dispose();
				LoginView lv = new LoginView();
			} else if (e.getSource().equals(jcb1)) {
				if (jcb1.getSelectedIndex() == 0) {
					option = 0;
					refreshTable();
					jcb2.setVisible(true);
					jcb3.setVisible(false);
				} else {
					option = 1;
					refreshTable();
					jcb3.setVisible(true);
					jcb2.setVisible(false);
				}

			} else if (e.getSource().equals(jcb2)) {

			} else if (e.getSource().equals(jcb3)) {

			} else if (e.getSource().equals(bInventory)) {
				dispose();
				InventoryView iv = new InventoryView();
			}
		}

	}
}